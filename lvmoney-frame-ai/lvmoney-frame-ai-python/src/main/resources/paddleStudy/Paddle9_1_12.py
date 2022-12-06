import paddle


# 定义网络结构，该任务中使用线性回归模型，网络由一个fc层构成
def linear_regression_net(input, hidden):
    # 区别1：飞桨的大部分API可以做到动静通用，但也有部分带有明显特性的API只能用于动态图或静态图，在模型中需要按需使用，
    # 另外由于动态图是基于面向对象的编程方式，而静态图还经常使用基于过程的编程方式，因此有些API虽然动静通用，
    # 但在静态图中使用静态图专用API可能会更加方便，比如与paddle.nn.Linear对应的静态图API是paddle.static.nn.fc，
    # 飞桨2.0及以上版本中，静态图专用API都统一置于paddle.static下
    out = paddle.static.nn.fc(input, hidden)
    return out


# 区别2：飞桨2.0及以上版本默认动态图模式，因此需要显式开启静态图运行模式
paddle.enable_static()
# 区别3：在静态图中需要明确定义输入变量，即“占位符”，在静态图组网阶段并没有读入数据，所以需要使用占位符指明输入数据的类型、shape等信息
# 波士顿房价预测任务中，共有13个特征，数据以batch形式组织，batch大小在定义时可以不确定，用None表示，因此shape=[None, 13]
main_program = paddle.static.Program()
startup_program = paddle.static.Program()
with paddle.static.program_guard(main_program, startup_program):
    x = paddle.static.data(name='x', shape=[None, 13], dtype='float32')
    # y代表实际结果，只有一个值，因此shape=[None, 1]
    y = paddle.static.data(name='y', shape=[None, 1], dtype='float32')

    # 区别4：训练和预测的数据读取处理基本与动态图一致，但需要注意由于静态图在定义DataLoader时并没有实际读入数据，
    # 所以需要通过feed_list参数指定要读取的变量列表
    batch_size = 20
    train_reader = paddle.io.DataLoader(paddle.text.datasets.UCIHousing(mode='train'), feed_list=[x, y],
                                        batch_size=batch_size, shuffle=True)
    test_reader = paddle.io.DataLoader(paddle.text.datasets.UCIHousing(mode='test'), feed_list=[x], batch_size=batch_size)

    # 调用网络，执行前向计算
    prediction = linear_regression_net(x, 1)

    # 计算损失值
    loss = paddle.nn.functional.square_error_cost(input=prediction, label=y)
    avg_loss = paddle.mean(loss)

    # 定义优化器，并调用minimize接口计算和更新梯度
    sgd_optimizer = paddle.optimizer.SGD(learning_rate=0.001)
    sgd_optimizer.minimize(avg_loss)

    # 区别5：静态图中需要使用执行器执行之前已经定义好的网络，此处创建执行器
    exe = paddle.static.Executor()

    # 区别6：静态图中需要显式对网络进行初始化操作
    exe.run(paddle.static.default_startup_program())

    max_epoch_num = 100  # 执行max_epoch_num次训练
    for epoch in range(max_epoch_num):
        for batch_id, (x_tensor, y_tensor) in enumerate(train_reader()):
            # 区别7：静态图中需要调用执行器的run方法执行计算过程，需要获取的计算结果（如avg_loss）需要通过fetch_list指定
            avg_loss_value, = exe.run(feed={'x': x_tensor, 'y': y_tensor}, fetch_list=[avg_loss])

            if batch_id % 10 == 0 and batch_id is not 0:
                print("epoch: {}, batch_id: {}, loss is: {}".format(epoch, batch_id, avg_loss_value))

    # 区别8：静态图中需要使用save_inference_model来保存模型，以供预测使用
    paddle.static.save_inference_model('./static_linear', [x], [prediction], exe)

infer_exe = paddle.static.Executor()
inference_scope = paddle.static.Scope()
# 使用训练好的模型做预测
with paddle.static.scope_guard(inference_scope):
    # 区别9：静态图中需要使用load_inference_model来加载之前保存的模型
    [inference_program, feed_target_names, fetch_targets
     ] = paddle.static.load_inference_model('./static_linear', infer_exe)

    # 读取一组测试数据
    (infer_x, infer_y) = next(test_reader())

    # 区别10：静态图中预测时也需要调用执行器的run方法执行计算过程，并指定之前加载的inference_program
    results = infer_exe.run(
        inference_program,
        feed={feed_target_names[0]: infer_x},
        fetch_list=fetch_targets)

    print("id: prediction ground_truth")
    for idx, val in enumerate(results[0]):
        print("%d: %.2f %.2f" % (idx, val, infer_y.__array__()[idx]))