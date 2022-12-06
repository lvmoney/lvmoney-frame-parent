import paddle
import paddle.static as static

paddle.enable_static()

# 当输入为单个张量时
train_program = static.Program()
start_program = static.Program()

places = static.cpu_places()
with static.program_guard(train_program, start_program):
    data = static.data(name="data1", shape=[2, 3], dtype="float32")
    data2 = static.data(name="data2", shape=[3, 4], dtype="float32")
    res = paddle.matmul(data, data2)
    print(static.default_main_program())