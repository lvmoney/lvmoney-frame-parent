import math

import paddle
from paddle.nn import Linear, Embedding, Conv2D
import numpy as np
import paddle.nn.functional as F
import paddle.nn as nn

from paddleStudy.Paddle7_1_2 import MovieLen


class Model(nn.Layer):
    def __init__(self, use_poster, use_mov_title, use_mov_cat, use_age_job, fc_sizes):
        super(Model, self).__init__()

        # 将传入的name信息和bool型参数添加到模型类中
        self.use_mov_poster = use_poster
        self.use_mov_title = use_mov_title
        self.use_usr_age_job = use_age_job
        self.use_mov_cat = use_mov_cat
        self.fc_sizes = fc_sizes

        # 使用上节定义的数据处理类，获取数据集的信息，并构建训练和验证集的数据迭代器
        Dataset = MovieLen(self.use_mov_poster)
        self.Dataset = Dataset
        self.trainset = self.Dataset.train_dataset
        self.valset = self.Dataset.valid_dataset
        self.train_loader = self.Dataset.load_data(dataset=self.trainset, mode='train')
        self.valid_loader = self.Dataset.load_data(dataset=self.valset, mode='valid')

        """ define network layer for embedding usr info """
        USR_ID_NUM = Dataset.max_usr_id + 1
        # 对用户ID做映射，并紧接着一个FC层
        self.usr_emb = Embedding(num_embeddings=USR_ID_NUM, embedding_dim=32)
        self.usr_fc = Linear(32, 32)

        # 对用户性别信息做映射，并紧接着一个FC层
        USR_GENDER_DICT_SIZE = 2
        self.usr_gender_emb = Embedding(num_embeddings=USR_GENDER_DICT_SIZE, embedding_dim=16)
        self.usr_gender_fc = Linear(16, 16)

        # 对用户年龄信息做映射，并紧接着一个FC层
        USR_AGE_DICT_SIZE = Dataset.max_usr_age + 1
        self.usr_age_emb = Embedding(num_embeddings=USR_AGE_DICT_SIZE, embedding_dim=16)
        self.usr_age_fc = Linear(16, 16)

        # 对用户职业信息做映射，并紧接着一个FC层
        USR_JOB_DICT_SIZE = Dataset.max_usr_job + 1
        self.usr_job_emb = Embedding(num_embeddings=USR_JOB_DICT_SIZE, embedding_dim=16)
        self.usr_job_fc = Linear(16, 16)

        # 新建一个FC层，用于整合用户数据信息
        self.usr_combined = Linear(80, 200)

        # 新建一个Linear层，用于整合电影特征
        self.mov_concat_embed = Linear(in_features=96, out_features=200)

        user_sizes = [200] + self.fc_sizes
        acts = ["relu" for _ in range(len(self.fc_sizes))]
        self._user_layers = []
        for i in range(len(self.fc_sizes)):
            linear = Linear(
                in_features=user_sizes[i],
                out_features=user_sizes[i + 1],
                weight_attr=paddle.ParamAttr(
                    initializer=nn.initializer.Normal(
                        std=1.0 / math.sqrt(user_sizes[i]))))
            # 向模型中添加了一个 paddle.nn.Linear 子层
            self.add_sublayer('linear_user_%d' % i, linear)
            self._user_layers.append(linear)
            if acts[i] == 'relu':
                act = nn.ReLU()
                # 向模型中添加了一个 paddle.nn.ReLU() 子层
                self.add_sublayer('user_act_%d' % i, act)
                self._user_layers.append(act)

    # 定义计算用户特征的前向运算过程
    def get_usr_feat(self, usr_var):
        """ get usr features"""
        # 获取到用户数据
        usr_id, usr_gender, usr_age, usr_job = usr_var
        # 将用户的ID数据经过embedding和FC计算，得到的特征保存在feats_collect中
        feats_collect = []
        usr_id = self.usr_emb(usr_id)
        usr_id = self.usr_fc(usr_id)
        usr_id = F.relu(usr_id)
        feats_collect.append(usr_id)

        # 计算用户的性别特征，并保存在feats_collect中
        usr_gender = self.usr_gender_emb(usr_gender)
        usr_gender = self.usr_gender_fc(usr_gender)
        usr_gender = F.relu(usr_gender)

        feats_collect.append(usr_gender)
        # 选择是否使用用户的年龄-职业特征
        if self.use_usr_age_job:
            # 计算用户的年龄特征，并保存在feats_collect中
            usr_age = self.usr_age_emb(usr_age)
            usr_age = self.usr_age_fc(usr_age)
            usr_age = F.relu(usr_age)
            feats_collect.append(usr_age)
            # 计算用户的职业特征，并保存在feats_collect中
            usr_job = self.usr_job_emb(usr_job)
            usr_job = self.usr_job_fc(usr_job)
            usr_job = F.relu(usr_job)
            feats_collect.append(usr_job)

        # 将用户的特征级联，并通过FC层得到最终的用户特征
        print([f.shape for f in feats_collect])
        usr_feat = paddle.concat(feats_collect, axis=1)
        user_features = F.tanh(self.usr_combined(usr_feat))
        # 通过3层全链接层，获得用于计算相似度的用户特征和电影特征
        for n_layer in self._user_layers:
            user_features = n_layer(user_features)
        return user_features


# 下面使用定义好的数据读取器，实现从用户数据读取到用户特征计算的流程：
## 测试用户特征提取网络
fc_sizes = [128, 64, 32]
model = Model(use_poster=False, use_mov_title=True, use_mov_cat=True, use_age_job=True, fc_sizes=fc_sizes)
model.eval()

data_loader = model.train_loader

for idx, data in enumerate(data_loader()):
    # 获得数据，并转为动态图格式，
    usr, mov, score = data
    #         print(usr.shape)
    # 只使用每个Batch的第一条数据
    usr_v = [[var[0]] for var in usr]

    print("输入的用户ID数据：{}\n性别数据：{} \n年龄数据：{} \n职业数据{}".format(*usr_v))

    usr_v = [paddle.to_tensor(np.array(var)) for var in usr_v]
    usr_feat = model.get_usr_feat(usr_v)
    print("计算得到的用户特征维度是：", usr_feat.shape)
    break
