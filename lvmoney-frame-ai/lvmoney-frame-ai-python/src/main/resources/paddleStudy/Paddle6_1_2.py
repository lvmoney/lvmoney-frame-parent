# encoding=utf8
import re
import random
import tarfile
import requests
import numpy as np
import paddle
from paddle.nn import Embedding
import paddle.nn.functional as F
from paddle.nn import LSTM, Embedding, Dropout, Linear

# 定义一个用于情感分类的网络实例，SentimentClassifier
from paddleStudy.Paddle5_1_1 import build_dict
from paddleStudy.Paddle6_1_1 import load_imdb, build_batch


class SentimentClassifier(paddle.nn.Layer):

    def __init__(self, hidden_size, vocab_size, embedding_size, class_num=2, num_steps=128, num_layers=1,
                 init_scale=0.1, dropout_rate=None):
        # 参数含义如下：
        # 1.hidden_size，表示embedding-size，hidden和cell向量的维度
        # 2.vocab_size，模型可以考虑的词表大小
        # 3.embedding_size，表示词向量的维度
        # 4.class_num，情感类型个数，可以是2分类，也可以是多分类
        # 5.num_steps，表示这个情感分析模型最大可以考虑的句子长度
        # 6.num_layers，表示网络的层数
        # 7.dropout_rate，表示使用dropout过程中失活的神经元比例
        # 8.init_scale，表示网络内部的参数的初始化范围,长短时记忆网络内部用了很多Tanh，Sigmoid等激活函数，\
        # 这些函数对数值精度非常敏感，因此我们一般只使用比较小的初始化范围，以保证效果
        super(SentimentClassifier, self).__init__()
        self.hidden_size = hidden_size
        self.vocab_size = vocab_size
        self.embedding_size = embedding_size
        self.class_num = class_num
        self.num_steps = num_steps
        self.num_layers = num_layers
        self.dropout_rate = dropout_rate
        self.init_scale = init_scale

        # 声明一个LSTM模型，用来把每个句子抽象成向量
        self.simple_lstm_rnn = paddle.nn.LSTM(input_size=hidden_size, hidden_size=hidden_size, num_layers=num_layers)

        # 声明一个embedding层，用来把句子中的每个词转换为向量
        self.embedding = paddle.nn.Embedding(num_embeddings=vocab_size, embedding_dim=embedding_size, sparse=False,
                                             weight_attr=paddle.ParamAttr(
                                                 initializer=paddle.nn.initializer.Uniform(low=-init_scale,
                                                                                           high=init_scale)))

        # 声明使用上述语义向量映射到具体情感类别时所需要使用的线性层
        self.cls_fc = paddle.nn.Linear(in_features=self.hidden_size, out_features=self.class_num,
                                       weight_attr=None, bias_attr=None)

        # 一般在获取单词的embedding后，会使用dropout层，防止过拟合，提升模型泛化能力
        self.dropout_layer = paddle.nn.Dropout(p=self.dropout_rate, mode='upscale_in_train')

    # forwad函数即为模型前向计算的函数，它有两个输入，分别为：
    # input为输入的训练文本，其shape为[batch_size, max_seq_len]
    # label训练文本对应的情感标签，其shape维[batch_size, 1]
    def forward(self, inputs):
        # 获取输入数据的batch_size
        batch_size = inputs.shape[0]

        # 本实验默认使用1层的LSTM，首先我们需要定义LSTM的初始hidden和cell，这里我们使用0来初始化这个序列的记忆
        init_hidden_data = np.zeros(
            (self.num_layers, batch_size, self.hidden_size), dtype='float32')
        init_cell_data = np.zeros(
            (self.num_layers, batch_size, self.hidden_size), dtype='float32')

        # 将这些初始记忆转换为飞桨可计算的向量，并且设置stop_gradient=True，避免这些向量被更新，从而影响训练效果
        init_hidden = paddle.to_tensor(init_hidden_data)
        init_hidden.stop_gradient = True
        init_cell = paddle.to_tensor(init_cell_data)
        init_cell.stop_gradient = True

        # 对应以上第2步，将输入的句子的mini-batch转换为词向量表示，转换后输入数据shape为[batch_size, max_seq_len, embedding_size]
        x_emb = self.embedding(inputs)
        x_emb = paddle.reshape(x_emb, shape=[-1, self.num_steps, self.embedding_size])
        # 在获取的词向量后添加dropout层
        if self.dropout_rate is not None and self.dropout_rate > 0.0:
            x_emb = self.dropout_layer(x_emb)

        # 对应以上第3步，使用LSTM网络，把每个句子转换为语义向量
        # 返回的last_hidden即为最后一个时间步的输出，其shape为[self.num_layers, batch_size, hidden_size]
        rnn_out, (last_hidden, last_cell) = self.simple_lstm_rnn(x_emb, (init_hidden, init_cell))
        # 提取最后一层隐状态作为文本的语义向量，其shape为[batch_size, hidden_size]
        last_hidden = paddle.reshape(last_hidden[-1], shape=[-1, self.hidden_size])

        # 对应以上第4步，将每个句子的向量表示映射到具体的情感类别上, logits的维度为[batch_size, 2]
        logits = self.cls_fc(last_hidden)

        return logits


train_corpus = load_imdb(True)
word2id_freq, word2id_dict = build_dict(train_corpus)
paddle.seed(0)
random.seed(0)
np.random.seed(0)

# 定义训练参数
epoch_num = 5
batch_size = 128

learning_rate = 0.0001
dropout_rate = 0.2
num_layers = 1
hidden_size = 256
embedding_size = 256
max_seq_len = 128
vocab_size = len(word2id_freq)

# 实例化模型
sentiment_classifier = SentimentClassifier(hidden_size, vocab_size, embedding_size, num_steps=max_seq_len,
                                           num_layers=num_layers, dropout_rate=dropout_rate)

# 指定优化策略，更新模型参数
optimizer = paddle.optimizer.Adam(learning_rate=learning_rate, beta1=0.9, beta2=0.999,
                                  parameters=sentiment_classifier.parameters())

# 定义训练函数
# 记录训练过程中的损失变化情况，可用于后续画图查看训练情况
losses = []
steps = []


def train(model):
    # 开启模型训练模式
    model.train()

    # 建立训练数据生成器，每次迭代生成一个batch，每个batch包含训练文本和文本对应的情感标签
    train_loader = build_batch(word2id_dict, train_corpus, batch_size, epoch_num, max_seq_len)

    for step, (sentences, labels) in enumerate(train_loader):
        # 获取数据，并将张量转换为Tensor类型
        sentences = paddle.to_tensor(sentences)
        labels = paddle.to_tensor(labels)

        # 前向计算，将数据feed进模型，并得到预测的情感标签和损失
        logits = model(sentences)

        # 计算损失
        loss = F.cross_entropy(input=logits, label=labels, soft_label=False)
        loss = paddle.mean(loss)

        # 后向传播
        loss.backward()
        # 更新参数
        optimizer.step()
        # 清除梯度
        optimizer.clear_grad()

        if step % 100 == 0:
            # 记录当前步骤的loss变化情况
            losses.append(loss.numpy()[0])
            steps.append(step)
            # 打印当前loss数值
            print("step %d, loss %.3f" % (step, loss.numpy()[0]))


# 训练模型
train(sentiment_classifier)

# 保存模型，包含两部分：模型参数和优化器参数
model_name = "model/sentiment_classifier"
# 保存训练好的模型参数
paddle.save(sentiment_classifier.state_dict(), "{}.pdparams".format(model_name))
# 保存优化器参数，方便后续模型继续训练
paddle.save(optimizer.state_dict(), "{}.pdopt".format(model_name))


def evaluate(model):
    # 开启模型测试模式，在该模式下，网络不会进行梯度更新
    model.eval()

    # 定义以上几个统计指标
    tp, tn, fp, fn = 0, 0, 0, 0

    # 构造测试数据生成器
    test_loader = build_batch(word2id_dict, test_corpus, batch_size, 1, max_seq_len)

    for sentences, labels in test_loader:
        # 将张量转换为Tensor类型
        sentences = paddle.to_tensor(sentences)
        labels = paddle.to_tensor(labels)

        # 获取模型对当前batch的输出结果
        logits = model(sentences)

        # 使用softmax进行归一化
        probs = F.softmax(logits)

        # 把输出结果转换为numpy array数组，比较预测结果和对应label之间的关系，并更新tp，tn，fp和fn
        probs = probs.numpy()
        for i in range(len(probs)):
            # 当样本是的真实标签是正例
            if labels[i][0] == 1:
                # 模型预测是正例
                if probs[i][1] > probs[i][0]:
                    tp += 1
                # 模型预测是负例
                else:
                    fn += 1
            # 当样本的真实标签是负例
            else:
                # 模型预测是正例
                if probs[i][1] > probs[i][0]:
                    fp += 1
                # 模型预测是负例
                else:
                    tn += 1

    # 整体准确率
    accuracy = (tp + tn) / (tp + tn + fp + fn)

    # 输出最终评估的模型效果
    print("TP: {}\nFP: {}\nTN: {}\nFN: {}\n".format(tp, fp, tn, fn))
    print("Accuracy: %.4f" % accuracy)


# 加载训练好的模型进行预测，重新实例化一个模型，然后将训练好的模型参数加载到新模型里面
saved_state = paddle.load("model/sentiment_classifier.pdparams")
sentiment_classifier = SentimentClassifier(hidden_size, vocab_size, embedding_size, num_steps=max_seq_len,
                                           num_layers=num_layers, dropout_rate=dropout_rate)
sentiment_classifier.load_dict(saved_state)

# 评估模型
evaluate(sentiment_classifier)
