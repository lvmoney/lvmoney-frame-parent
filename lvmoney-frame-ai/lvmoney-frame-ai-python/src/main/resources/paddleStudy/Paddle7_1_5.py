import math

import paddle
from paddle.nn import Linear, Embedding, Conv2D
import numpy as np
import paddle.nn.functional as F
import paddle.nn as nn

# 自定义一个电影ID数据
mov_id_data = np.array((1, 2)).reshape(-1).astype('int64')
# 对电影ID信息做映射，并紧接着一个FC层
MOV_DICT_SIZE = 3952 + 1
mov_emb = Embedding(num_embeddings=MOV_DICT_SIZE, embedding_dim=32)
mov_fc = Linear(32, 32)

print("输入的电影ID是:", mov_id_data)
mov_id_data = paddle.to_tensor(mov_id_data)
mov_id_feat = mov_fc(mov_emb(mov_id_data))
mov_id_feat = F.relu(mov_id_feat)
print("计算的电影ID的特征是", mov_id_feat.numpy(), "\n其形状是：", mov_id_feat.shape)
print("\n电影ID为 {} 计算得到的特征是：{}".format(mov_id_data.numpy()[0], mov_id_feat.numpy()[0]))
print("电影ID为 {} 计算得到的特征是：{}".format(mov_id_data.numpy()[1], mov_id_feat.numpy()[1]))

# 自定义一个电影类别数据
mov_cat_data = np.array(((1, 2, 3, 0, 0, 0), (2, 3, 4, 0, 0, 0))).reshape(2, -1).astype('int64')
# 对电影ID信息做映射，并紧接着一个Linear层
MOV_DICT_SIZE = 6 + 1
mov_emb = Embedding(num_embeddings=MOV_DICT_SIZE, embedding_dim=32)
mov_fc = Linear(in_features=32, out_features=32)

print("输入的电影类别是:", mov_cat_data[:, :])
mov_cat_data = paddle.to_tensor(mov_cat_data)
# 1. 通过Embedding映射电影类别数据；
mov_cat_feat = mov_emb(mov_cat_data)
# 2. 对Embedding后的向量沿着类别数量维度进行求和，得到一个类别映射向量；
mov_cat_feat = paddle.sum(mov_cat_feat, axis=1, keepdim=False)

# 3. 通过一个全连接层计算类别特征向量。
mov_cat_feat = mov_fc(mov_cat_feat)
mov_cat_feat = F.relu(mov_cat_feat)
print("计算的电影类别的特征是", mov_cat_feat.numpy(), "\n其形状是：", mov_cat_feat.shape)
print("\n电影类别为 {} 计算得到的特征是：{}".format(mov_cat_data.numpy()[0, :], mov_cat_feat.numpy()[0]))
print("\n电影类别为 {} 计算得到的特征是：{}".format(mov_cat_data.numpy()[1, :], mov_cat_feat.numpy()[1]))

# 自定义两个电影名称数据
mov_title_data = np.array(((1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                           (2, 3, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))).reshape(2, 1, 15).astype('int64')
# 对电影名称做映射，紧接着FC和pool层
MOV_TITLE_DICT_SIZE = 1000 + 1
mov_title_emb = Embedding(num_embeddings=MOV_TITLE_DICT_SIZE, embedding_dim=32)
mov_title_conv = Conv2D(in_channels=1, out_channels=1, kernel_size=(3, 1), stride=(2, 1), padding=0)
# 使用 3 * 3卷积层代替全连接层
mov_title_conv2 = Conv2D(in_channels=1, out_channels=1, kernel_size=(3, 1), stride=1, padding=0)

mov_title_data = paddle.to_tensor(mov_title_data)
print("电影名称数据的输入形状: ", mov_title_data.shape)
# 1. 通过Embedding映射电影名称数据；
mov_title_feat = mov_title_emb(mov_title_data)
print("输入通过Embedding层的输出形状: ", mov_title_feat.shape)
# 2. 对Embedding后的向量使用卷积层进一步提取特征；
mov_title_feat = F.relu(mov_title_conv(mov_title_feat))
print("第一次卷积之后的特征输出形状: ", mov_title_feat.shape)
mov_title_feat = F.relu(mov_title_conv2(mov_title_feat))
print("第二次卷积之后的特征输出形状: ", mov_title_feat.shape)

batch_size = mov_title_data.shape[0]
# 3. 最后对特征进行降采样，keepdim=False会让输出的维度减少，而不是用[2,1,1,32]的形式占位；
mov_title_feat = paddle.sum(mov_title_feat, axis=2, keepdim=False)
print("reduce_sum降采样后的特征输出形状: ", mov_title_feat.shape)

mov_title_feat = F.relu(mov_title_feat)
mov_title_feat = paddle.reshape(mov_title_feat, [batch_size, -1])
print("电影名称特征的最终特征输出形状：", mov_title_feat.shape)

print("\n计算的电影名称的特征是", mov_title_feat.numpy(), "\n其形状是：", mov_title_feat.shape)
print("\n电影名称为 {} 计算得到的特征是：{}".format(mov_title_data.numpy()[0, :, 0], mov_title_feat.numpy()[0]))
print("\n电影名称为 {} 计算得到的特征是：{}".format(mov_title_data.numpy()[1, :, 0], mov_title_feat.numpy()[1]))

mov_combined = Linear(in_features=96, out_features=200)
# 收集所有的电影特征
_features = [mov_id_feat, mov_cat_feat, mov_title_feat]
_features = [k.numpy() for k in _features]
_features = [paddle.to_tensor(k) for k in _features]

# 对特征沿着最后一个维度级联
mov_feat = paddle.concat(_features, axis=1)
mov_feat = mov_combined(mov_feat)
mov_feat = F.tanh(mov_feat)
print("融合后的电影特征维度是：", mov_feat.shape)
