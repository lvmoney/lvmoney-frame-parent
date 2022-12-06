# 该示例代码展示整数循环+1，循环10次，输出计数结果
import paddle

# 定义cond方法，作为while_loop的判断条件
def cond(i, ten):
    return i < ten


# 定义body方法，作为while_loop的执行体，只要cond返回值为True，while_loop就会一直调用该方法进行计算
# 由于在使用while_loop OP时，cond和body的参数都是由while_loop的loop_vars参数指定的，
# 所以cond和body必须有相同数量的参数列表，因此body中虽然只需要i这个参数，
# 但是仍然要保持参数列表个数为2，此处添加了一个dummy参数来进行"占位"
def body(i, dummy):
    # 计算过程是对输入参数i进行自增操作，即 i = i + 1
    i = i + 1
    return i, dummy

# 为了与上面cell的代码进行隔离
main_program = paddle.static.Program()
startup_program = paddle.static.Program()
with paddle.static.program_guard(main_program, startup_program):
    paddle.enable_static()
    i = paddle.full(shape=[1], fill_value=0, dtype='int64')  # 循环计数器
    ten = paddle.full(shape=[1], fill_value=10, dtype='int64')  # 循环次数
    # while_loop的返回值是一个tensor列表，其长度，结构，类型与loop_vars相同
    out, ten = paddle.static.nn.while_loop(cond, body, [i, ten])

    exe = paddle.static.Executor(place=paddle.CPUPlace())
    res = exe.run(paddle.static.default_main_program(), feed={}, fetch_list=out)
    print(res)  # [array([10], dtype=int64)]
paddle.disable_static()