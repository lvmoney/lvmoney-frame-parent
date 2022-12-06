import torch

print(torch.__version__)  # 显示Pytorch版本
print(torch.cuda.is_available())  # 返回False为版本不匹配，报该错误；返回Ture，解决问题
