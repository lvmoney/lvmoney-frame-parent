import paddle
from paddle.nn import Linear, Embedding, Conv2D
import numpy as np
import paddle.nn.functional as F
import paddle.nn as nn

# 声明用户的最大ID，在此基础上加1（算上数字0）
USR_ID_NUM = 6040 + 1
# 声明Embedding 层，将ID映射为32长度的向量
usr_emb = Embedding(num_embeddings=USR_ID_NUM,
                    embedding_dim=32,
                    sparse=False)
# 声明输入数据，将其转成tensor
arr_1 = np.array([1], dtype="int64").reshape((-1))
print(arr_1)
arr_pd1 = paddle.to_tensor(arr_1)
print(arr_pd1)
# 计算结果
emb_res = usr_emb(arr_pd1)
# 打印结果
print("数字 1 的embedding结果是： ", emb_res.numpy(), "\n形状是：", emb_res.shape)

# 声明用户的最大ID，在此基础上加1（算上数字0）
USR_ID_NUM = 10
# 声明Embedding 层，将ID映射为16长度的向量
usr_emb = Embedding(num_embeddings=USR_ID_NUM,
                    embedding_dim=16,
                    sparse=False)
# 定义输入数据，输入数据为不超过10的整数，将其转成tensor
arr = np.random.randint(0, 10, (3)).reshape((-1)).astype('int64')
print("输入数据是：", arr)
arr_pd = paddle.to_tensor(arr)
emb_res = usr_emb(arr_pd)
print("默认权重初始化embedding层的映射结果是：", emb_res.numpy())

# 观察Embedding层的权重
emb_weights = usr_emb.state_dict()
print(emb_weights.keys())

print("\n查看embedding层的权重形状：", emb_weights['weight'].shape)

# 声明Embedding 层，将ID映射为16长度的向量，自定义权重初始化方式
# 定义KaimingNorma初始化方式
init = nn.initializer.KaimingNormal()
param_attr = paddle.ParamAttr(initializer=init)

usr_emb2 = Embedding(num_embeddings=USR_ID_NUM,
                     embedding_dim=16,
                     weight_attr=param_attr)
emb_res = usr_emb2(arr_pd)
print("\KaimingNormal初始化权重embedding层的映射结果是：", emb_res.numpy())

# 自定义一个用户ID数据
usr_id_data = np.random.randint(0, 6040, (2)).reshape((-1)).astype('int64')
print("输入的用户ID是:", usr_id_data)

USR_ID_NUM = 6040 + 1
# 定义用户ID的embedding层和fc层
usr_emb = Embedding(num_embeddings=USR_ID_NUM,
                    embedding_dim=32,
                    sparse=False)
usr_fc = Linear(in_features=32, out_features=32)

usr_id_var = paddle.to_tensor(usr_id_data)
usr_id_feat = usr_fc(usr_emb(usr_id_var))

usr_id_feat = F.relu(usr_id_feat)
print("用户ID的特征是：", usr_id_feat.numpy(), "\n其形状是：", usr_id_feat.shape)

# 自定义一个用户性别数据
usr_gender_data = np.array((0, 1)).reshape(-1).astype('int64')
print("输入的用户性别是:", usr_gender_data)

# 用户的性别用0， 1 表示
# 性别最大ID是1，所以Embedding层size的第一个参数设置为1 + 1 = 2
USR_ID_NUM = 2
# 对用户性别信息做映射，并紧接着一个FC层
USR_GENDER_DICT_SIZE = 2
usr_gender_emb = Embedding(num_embeddings=USR_GENDER_DICT_SIZE,
                           embedding_dim=16)

usr_gender_fc = Linear(in_features=16, out_features=16)

usr_gender_var = paddle.to_tensor(usr_gender_data)
usr_gender_feat = usr_gender_fc(usr_gender_emb(usr_gender_var))
usr_gender_feat = F.relu(usr_gender_feat)
print("用户性别特征的数据特征是：", usr_gender_feat.numpy(), "\n其形状是：", usr_gender_feat.shape)
print("\n性别 0 对应的特征是：", usr_gender_feat.numpy()[0, :])
print("性别 1 对应的特征是：", usr_gender_feat.numpy()[1, :])

# 自定义一个用户年龄数据
usr_age_data = np.array((1, 18)).reshape(-1).astype('int64')
print("输入的用户年龄是:", usr_age_data)

# 对用户年龄信息做映射，并紧接着一个Linear层
# 年龄的最大ID是56，所以Embedding层size的第一个参数设置为56 + 1 = 57
USR_AGE_DICT_SIZE = 56 + 1

usr_age_emb = Embedding(num_embeddings=USR_AGE_DICT_SIZE,
                        embedding_dim=16)
usr_age_fc = Linear(in_features=16, out_features=16)

usr_age = paddle.to_tensor(usr_age_data)
usr_age_feat = usr_age_emb(usr_age)
usr_age_feat = usr_age_fc(usr_age_feat)
usr_age_feat = F.relu(usr_age_feat)

print("用户年龄特征的数据特征是：", usr_age_feat.numpy(), "\n其形状是：", usr_age_feat.shape)
print("\n年龄 1 对应的特征是：", usr_age_feat.numpy()[0, :])
print("年龄 18 对应的特征是：", usr_age_feat.numpy()[1, :])

# 自定义一个用户职业数据
usr_job_data = np.array((0, 20)).reshape(-1).astype('int64')
print("输入的用户职业是:", usr_job_data)

# 对用户职业信息做映射，并紧接着一个Linear层
# 用户职业的最大ID是20，所以Embedding层size的第一个参数设置为20 + 1 = 21
USR_JOB_DICT_SIZE = 20 + 1
usr_job_emb = Embedding(num_embeddings=USR_JOB_DICT_SIZE, embedding_dim=16)
usr_job_fc = Linear(in_features=16, out_features=16)

usr_job = paddle.to_tensor(usr_job_data)
usr_job_feat = usr_job_emb(usr_job)
usr_job_feat = usr_job_fc(usr_job_feat)
usr_job_feat = F.relu(usr_job_feat)

print("用户年龄特征的数据特征是：", usr_job_feat.numpy(), "\n其形状是：", usr_job_feat.shape)
print("\n职业 0 对应的特征是：", usr_job_feat.numpy()[0, :])
print("职业 20 对应的特征是：", usr_job_feat.numpy()[1, :])

FC_ID = Linear(in_features=32, out_features=200)
FC_JOB = Linear(in_features=16, out_features=200)
FC_AGE = Linear(in_features=16, out_features=200)
FC_GENDER = Linear(in_features=16, out_features=200)

# 收集所有的用户特征
_features = [usr_id_feat, usr_job_feat, usr_age_feat, usr_gender_feat]
_features = [k.numpy() for k in _features]
_features = [paddle.to_tensor(k) for k in _features]

id_feat = F.tanh(FC_ID(_features[0]))
job_feat = F.tanh(FC_JOB(_features[1]))
age_feat = F.tanh(FC_AGE(_features[2]))
genger_feat = F.tanh(FC_GENDER(_features[-1]))

# 对特征求和
usr_feat = id_feat + job_feat + age_feat + genger_feat
print("用户融合后特征的维度是：", usr_feat.shape)

usr_combined = Linear(in_features=80, out_features=200)

# 收集所有的用户特征
_features = [usr_id_feat, usr_job_feat, usr_age_feat, usr_gender_feat]

print("打印每个特征的维度：", [f.shape for f in _features])

_features = [k.numpy() for k in _features]
_features = [paddle.to_tensor(k) for k in _features]

# 对特征沿着最后一个维度级联
usr_feat = paddle.concat(_features, axis=1)
usr_feat = F.tanh(usr_combined(usr_feat))
print("用户融合后特征的维度是：", usr_feat.shape)
