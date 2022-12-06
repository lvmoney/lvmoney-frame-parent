import pickle
import numpy as np

mov_feat_dir = 'D:/data/paddle/data20452/save_feat/mov_feat.pkl'
usr_feat_dir = 'D:/data/paddle/data20452/save_feat/usr_feat.pkl'

usr_feats = pickle.load(open(usr_feat_dir, 'rb'))
mov_feats = pickle.load(open(mov_feat_dir, 'rb'))

usr_id = 2
usr_feat = usr_feats[str(usr_id)]

mov_id = 1
# 通过电影ID索引到电影特征
mov_feat = mov_feats[str(mov_id)]

# 电影特征的路径
movie_data_path = "D:/data/paddle/ml-1m/ml-1m/movies.dat"
mov_info = {}
# 打开电影数据文件，根据电影ID索引到电影信息
with open(movie_data_path, 'r', encoding="ISO-8859-1") as f:
    data = f.readlines()
    for item in data:
        item = item.strip().split("::")
        mov_info[str(item[0])] = item

usr_file = "D:/data/paddle/ml-1m/ml-1m/users.dat"
usr_info = {}
# 打开文件，读取所有行到data中
with open(usr_file, 'r') as f:
    data = f.readlines()
    for item in data:
        item = item.strip().split("::")
        usr_info[str(item[0])] = item

print("当前的用户是：")
print("usr_id:", usr_id, usr_info[str(usr_id)])
print("对应的特征是：", usr_feats[str(usr_id)])

print("/n当前电影是：")
print("mov_id:", mov_id, mov_info[str(mov_id)])
print("对应的特征是：")
print(mov_feat)

import paddle

# 根据用户ID获得该用户的特征
usr_ID = 2
# 读取保存的用户特征
usr_feats = pickle.load(open(usr_feat_dir, 'rb'))
# 根据用户ID索引到该用户的特征
usr_ID_feat = usr_feats[str(usr_ID)]

# 记录计算的相似度
cos_sims = []
# 记录下与用户特征计算相似的电影顺序

# 索引电影特征，计算和输入用户ID的特征的相似度
for idx, key in enumerate(mov_feats.keys()):
    mov_feat = mov_feats[key]
    usr_feat = paddle.to_tensor(usr_ID_feat)
    mov_feat = paddle.to_tensor(mov_feat)

    # 计算余弦相似度
    sim = paddle.nn.functional.common.cosine_similarity(usr_feat, mov_feat)
    # 打印特征和相似度的形状
    if idx == 0:
        print("电影特征形状：{}, 用户特征形状：{}, 相似度结果形状：{}，相似度结果：{}".format(mov_feat.shape, usr_feat.shape, sim.numpy().shape,
                                                                 sim.numpy()))
    # 从形状为（1，1）的相似度sim中获得相似度值sim.numpy()[0]，并添加到相似度列表cos_sims中
    cos_sims.append(sim.numpy()[0])

# 对相似度排序，获得最大相似度在cos_sims中的位置
index = np.argsort(cos_sims)
# 打印相似度最大的前topk个位置
topk = 5
print("相似度最大的前{}个索引是{}\n对应的相似度是：{}\n".format(topk, index[-topk:], [cos_sims[k] for k in index[-topk:]]))

for i in index[-topk:]:
    print("对应的电影分别是：movie:{}".format(mov_info[list(mov_feats.keys())[i]]))

top_k, pick_num = 10, 6

# 对相似度排序，获得最大相似度在cos_sims中的位置
index = np.argsort(cos_sims)[-top_k:]

print("当前的用户是：")
# usr_id, usr_info 是前面定义、读取的用户ID、用户信息
print("usr_id:", usr_id, usr_info[str(usr_id)])
print("推荐可能喜欢的电影是：")
res = []

# 加入随机选择因素，确保每次推荐的结果稍有差别
while len(res) < pick_num:
    val = np.random.choice(len(index), 1)[0]
    idx = index[val]
    mov_id = list(mov_feats.keys())[idx]
    if mov_id not in res:
        res.append(mov_id)

for id in res:
    print("mov_id:", id, mov_info[str(id)])


# 定义根据用户兴趣推荐电影
def recommend_mov_for_usr(usr_id, top_k, pick_num, usr_feat_dir, mov_feat_dir, mov_info_path):
    assert pick_num <= top_k
    # 读取电影和用户的特征
    usr_feats = pickle.load(open(usr_feat_dir, 'rb'))
    mov_feats = pickle.load(open(mov_feat_dir, 'rb'))
    usr_feat = usr_feats[str(usr_id)]

    cos_sims = []

    # with dygraph.guard():
    paddle.disable_static()
    # 索引电影特征，计算和输入用户ID的特征的相似度
    for idx, key in enumerate(mov_feats.keys()):
        mov_feat = mov_feats[key]
        usr_feat = paddle.to_tensor(usr_feat)
        mov_feat = paddle.to_tensor(mov_feat)
        # 计算余弦相似度
        sim = paddle.nn.functional.common.cosine_similarity(usr_feat, mov_feat)

        cos_sims.append(sim.numpy()[0])
    # 对相似度排序
    index = np.argsort(cos_sims)[-top_k:]

    mov_info = {}
    # 读取电影文件里的数据，根据电影ID索引到电影信息
    with open(mov_info_path, 'r', encoding="ISO-8859-1") as f:
        data = f.readlines()
        for item in data:
            item = item.strip().split("::")
            mov_info[str(item[0])] = item

    print("当前的用户是：")
    print("usr_id:", usr_id)
    print("推荐可能喜欢的电影是：")
    res = []

    # 加入随机选择因素，确保每次推荐的都不一样
    while len(res) < pick_num:
        val = np.random.choice(len(index), 1)[0]
        idx = index[val]
        mov_id = list(mov_feats.keys())[idx]
        if mov_id not in res:
            res.append(mov_id)

    for id in res:
        print("mov_id:", id, mov_info[str(id)])


top_k, pick_num = 10, 6
usr_id = 2
recommend_mov_for_usr(usr_id, top_k, pick_num, usr_feat_dir, mov_feat_dir, movie_data_path)


# 给定一个用户ID，找到评分最高的topk个电影

usr_a = 2
topk = 10

##########################################
## 获得ID为usr_a的用户评分过的电影及对应评分 ##
##########################################
rating_path = "./work/ml-1m/ratings.dat"
# 打开文件，ratings_data
with open(rating_path, 'r') as f:
    ratings_data = f.readlines()

usr_rating_info = {}
for item in ratings_data:
    item = item.strip().split("::")
    # 处理每行数据，分别得到用户ID，电影ID，和评分
    usr_id,movie_id,score = item[0],item[1],item[2]
    if usr_id == str(usr_a):
        usr_rating_info[movie_id] = float(score)

# 获得评分过的电影ID
movie_ids = list(usr_rating_info.keys())
print("ID为 {} 的用户，评分过的电影数量是: ".format(usr_a), len(movie_ids))

#####################################
## 选出ID为usr_a评分最高的前topk个电影 ##
#####################################
ratings_topk = sorted(usr_rating_info.items(), key=lambda item:item[1])[-topk:]

movie_info_path = "./work/ml-1m/movies.dat"
# 打开文件，编码方式选择ISO-8859-1，读取所有数据到data中
with open(movie_info_path, 'r', encoding="ISO-8859-1") as f:
    data = f.readlines()

movie_info = {}
for item in data:
    item = item.strip().split("::")
    # 获得电影的ID信息
    v_id = item[0]
    movie_info[v_id] = item

for k, score in ratings_topk:
    print("电影ID: {}，评分是: {}, 电影信息: {}".format(k, score, movie_info[k]))
