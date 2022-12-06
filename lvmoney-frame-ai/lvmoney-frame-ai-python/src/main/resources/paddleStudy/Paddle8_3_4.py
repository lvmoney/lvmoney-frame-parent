import paddle
from paddle.autograd import PyLayer

# Inherit from PyLayer
class cus_tanh(PyLayer):
    @staticmethod
    def forward(ctx, x, func1, func2=paddle.square):
        # ctx is a context object that store some objects for backward.
        ctx.func = func2
        y = func1(x)
        # Pass tensors to backward.
        ctx.save_for_backward(y)
        return y

    @staticmethod
    # forward has only one output, so there is only one gradient in the input of backward.
    def backward(ctx, dy):
        # Get the tensors passed by forward.
        y, = ctx.saved_tensor()
        grad = dy * (1 - ctx.func(y))
        # forward has only one input, so only one gradient tensor is returned.
        return grad


data = paddle.randn([2, 3], dtype="float64")
data.stop_gradient = False
z = cus_tanh.apply(data, func1=paddle.tanh)
z.mean().backward()

print(data.grad.numpy())