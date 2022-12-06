import paddle

# 定义网络结构，该任务中使用线性回归模型，网络由一个FC层构成
class LinearRegression(paddle.nn.Layer):
    def __init__(self, input_dim, hidden):
        super(LinearRegression, self).__init__()
        self.linear = paddle.nn.Linear(input_dim, hidden)

    def forward(self, x):
        x = self.linear(x)
        return x


# 训练和预测的数据读取处理，这部分的用法动态图和静态图是一致的
batch_size = 20
train_reader = paddle.io.DataLoader(paddle.text.datasets.UCIHousing(mode='train'), batch_size=batch_size, shuffle=True)
test_reader = paddle.io.DataLoader(paddle.text.datasets.UCIHousing(mode='test'), batch_size=batch_size)

# 波士顿房价预测任务中，共有13个特征
input_feature = 13

# 定义网络
model = LinearRegression(input_dim=input_feature, hidden=1)
# 定义优化器
sgd = paddle.optimizer.SGD(learning_rate=0.001, parameters=model.parameters())

max_epoch_num = 100  # 执行max_epoch_num次训练
for epoch in range(max_epoch_num):
    # 读取训练数据进行训练
    for batch_id, data in enumerate(train_reader()):
        x_tensor, y_tensor = data
        # 调用网络，执行前向计算
        prediction = model(x_tensor)

        # 计算损失值
        loss = paddle.nn.functional.square_error_cost(prediction, y_tensor)
        avg_loss = paddle.mean(loss)

        if batch_id % 10 == 0 and batch_id is not 0:
            print("epoch: {}, batch_id: {}, loss is: {}".format(epoch, batch_id, avg_loss.numpy()))

        # 执行反向计算，并调用minimize接口计算和更新梯度
        avg_loss.backward()
        sgd.minimize(avg_loss)

        # 将本次计算的梯度值清零，以便进行下一次迭代和梯度更新
        model.clear_gradients()

# 训练结束，保存训练好的模型
paddle.save(model.state_dict(), 'model/linear.pdparams')

linear_infer = LinearRegression(input_dim=input_feature, hidden=1)
# 加载之前已经训练好的模型准备进行预测
model_dict = paddle.load("model/linear.pdparams")
linear_infer.set_dict(model_dict)
print("checkpoint loaded.")

# 开启评估测试模式（区别于训练模式）
linear_infer.eval()
(infer_x_tensor, infer_y_tensor) = next(test_reader())

infer_result = linear_infer(infer_x_tensor)
print(infer_result.numpy())

print("id: prediction ground_truth")
for idx, val in enumerate(infer_result.numpy()):
    print("%d: %.2f %.2f" % (idx, val, infer_y_tensor.numpy()[idx]))