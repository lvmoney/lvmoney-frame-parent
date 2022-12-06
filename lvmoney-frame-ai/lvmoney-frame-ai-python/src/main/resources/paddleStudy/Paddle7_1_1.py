import numpy as np

usr_file = "D:/data/paddle/ml-1m/ml-1m/users.dat"
# 打开文件，读取所有行到data中
with open(usr_file, 'r') as f:
    data = f.readlines()
# 打印data的数据长度、第一条数据、数据类型
print("data 数据长度是：", len(data))
print("第一条数据是：", data[0])
print("数据类型：", type(data[0]))


def gender2num(gender):
    return 1 if gender == 'F' else 0


print("性别M用数字 {} 表示".format(gender2num('M')))
print("性别F用数字 {} 表示".format(gender2num('F')))

usr_info = {}
max_usr_id = 0
# 按行索引数据
for item in data:
    # 去除每一行中和数据无关的部分
    item = item.strip().split("::")
    usr_id = item[0]
    # 将字符数据转成数字并保存在字典中
    usr_info[usr_id] = {'usr_id': int(usr_id),
                        'gender': gender2num(item[1]),
                        'age': int(item[2]),
                        'job': int(item[3])}
    max_usr_id = max(max_usr_id, int(usr_id))

print("用户ID为3的用户数据是：", usr_info['3'])

movie_info_path = "D:/data/paddle/ml-1m/ml-1m/movies.dat"
# 打开文件，编码方式选择ISO-8859-1，读取所有数据到data中
with open(movie_info_path, 'r', encoding="ISO-8859-1") as f:
    data = f.readlines()

# 读取第一条数据，并打印
item = data[0]
print(item)
item = item.strip().split("::")
print("movie ID:", item[0])
print("movie title:", item[1][:-7])
print("movie year:", item[1][-5:-1])
print("movie genre:", item[2].split('|'))

movie_info_path = "D:/data/paddle/ml-1m/ml-1m/movies.dat"
# 打开文件，编码方式选择ISO-8859-1，读取所有数据到data中
with open(movie_info_path, 'r', encoding="ISO-8859-1") as f:
    data = f.readlines()

movie_info = {}
for item in data:
    item = item.strip().split("::")
    # 获得电影的ID信息
    v_id = item[0]
    movie_info[v_id] = {'mov_id': int(v_id)}
max_id = max([movie_info[k]['mov_id'] for k in movie_info.keys()])
print("电影的最大ID是：", max_id)

# 用于记录电影title每个单词对应哪个序号
movie_titles = {}
# 记录电影名字包含的单词最大数量
max_title_length = 0
# 对不同的单词从1 开始计数
t_count = 1
# 按行读取数据并处理
for item in data:
    item = item.strip().split("::")
    # 1. 获得电影的ID信息
    v_id = item[0]
    v_title = item[1][:-7]  # 去掉title中年份数据
    v_year = item[1][-5:-1]
    titles = v_title.split()
    # 获得title最大长度
    max_title_length = max((max_title_length, len(titles)))

    # 2. 统计电影名字的单词，并给每个单词一个序号，放在movie_titles中
    for t in titles:
        if t not in movie_titles:
            movie_titles[t] = t_count
            t_count += 1

    v_tit = [movie_titles[k] for k in titles]
    # 保存电影ID数据和title数据到字典中
    movie_info[v_id] = {'mov_id': int(v_id),
                        'title': v_tit,
                        'years': int(v_year)}

print("最大电影title长度是：", max_title_length)
ID = 1
# 读取第一条数据，并打印
item = data[0]
item = item.strip().split("::")
print("电影 ID:", item[0])
print("电影 title:", item[1][:-7])
print("ID为1 的电影数据是：", movie_info['1'])

# 用于记录电影类别每个单词对应哪个序号
movie_titles, movie_cat = {}, {}
max_title_length = 0
max_cat_length = 0

t_count, c_count = 1, 1
# 按行读取数据并处理
for item in data:
    item = item.strip().split("::")
    # 1. 获得电影的ID信息
    v_id = item[0]
    cats = item[2].split('|')

    # 获得电影类别数量的最大长度
    max_cat_length = max((max_cat_length, len(cats)))

    v_cat = item[2].split('|')
    # 3. 统计电影类别单词，并给每个单词一个序号，放在movie_cat中
    for cat in cats:
        if cat not in movie_cat:
            movie_cat[cat] = c_count
            c_count += 1
    v_cat = [movie_cat[k] for k in v_cat]

    # 保存电影ID数据和title数据到字典中
    movie_info[v_id] = {'mov_id': int(v_id),
                        'category': v_cat}

print("电影类别数量最多是：", max_cat_length)
ID = 1
# 读取第一条数据，并打印
item = data[0]
item = item.strip().split("::")
print("电影 ID:", item[0])
print("电影种类 category:", item[2].split('|'))
print("ID为1 的电影数据是：", movie_info['1'])

# 建立三个字典，分别存放电影ID、名字和类别
movie_info, movie_titles, movie_cat = {}, {}, {}
# 对电影名字、类别中不同的单词从 1 开始标号
t_count, c_count = 1, 1

count_tit = {}
# 按行读取数据并处理
for item in data:
    item = item.strip().split("::")
    # 1. 获得电影的ID信息
    v_id = item[0]
    v_title = item[1][:-7]  # 去掉title中年份数据
    cats = item[2].split('|')
    v_year = item[1][-5:-1]

    titles = v_title.split()
    # 2. 统计电影名字的单词，并给每个单词一个序号，放在movie_titles中
    for t in titles:
        if t not in movie_titles:
            movie_titles[t] = t_count
            t_count += 1
    # 3. 统计电影类别单词，并给每个单词一个序号，放在movie_cat中
    for cat in cats:
        if cat not in movie_cat:
            movie_cat[cat] = c_count
            c_count += 1
    # 补0使电影名称对应的列表长度为15
    v_tit = [movie_titles[k] for k in titles]
    while len(v_tit) < 15:
        v_tit.append(0)
    # 补0使电影种类对应的列表长度为6
    v_cat = [movie_cat[k] for k in cats]
    while len(v_cat) < 6:
        v_cat.append(0)
    # 4. 保存电影数据到movie_info中
    movie_info[v_id] = {'mov_id': int(v_id),
                        'title': v_tit,
                        'category': v_cat,
                        'years': int(v_year)}

print("电影数据数量：", len(movie_info))
ID = 2
print("原始的电影ID为 {} 的数据是：".format(ID), data[ID - 1])
print("电影ID为 {} 的转换后数据是：".format(ID), movie_info[str(ID)])


def get_movie_info(path):
    # 打开文件，编码方式选择ISO-8859-1，读取所有数据到data中
    with open(path, 'r', encoding="ISO-8859-1") as f:
        data = f.readlines()
    # 建立三个字典，分别用户存放电影所有信息，电影的名字信息、类别信息
    movie_info, movie_titles, movie_cat = {}, {}, {}
    # 对电影名字、类别中不同的单词计数
    t_count, c_count = 1, 1
    # 初始化电影名字和种类的列表
    titles = []
    cats = []
    count_tit = {}
    # 按行读取数据并处理
    for item in data:
        item = item.strip().split("::")
        v_id = item[0]
        v_title = item[1][:-7]
        cats = item[2].split('|')
        v_year = item[1][-5:-1]

        titles = v_title.split()
        # 统计电影名字的单词，并给每个单词一个序号，放在movie_titles中
        for t in titles:
            if t not in movie_titles:
                movie_titles[t] = t_count
                t_count += 1
        # 统计电影类别单词，并给每个单词一个序号，放在movie_cat中
        for cat in cats:
            if cat not in movie_cat:
                movie_cat[cat] = c_count
                c_count += 1
        # 补0使电影名称对应的列表长度为15
        v_tit = [movie_titles[k] for k in titles]
        while len(v_tit) < 15:
            v_tit.append(0)
        # 补0使电影种类对应的列表长度为6
        v_cat = [movie_cat[k] for k in cats]
        while len(v_cat) < 6:
            v_cat.append(0)
        # 保存电影数据到movie_info中
        movie_info[v_id] = {'mov_id': int(v_id),
                            'title': v_tit,
                            'category': v_cat,
                            'years': int(v_year)}
    return movie_info, movie_cat, movie_titles


movie_info_path = 'D:/data/paddle/ml-1m/ml-1m/movies.dat'
movie_info, movie_cat, movie_titles = get_movie_info(movie_info_path)
print("电影数量：", len(movie_info))
ID = 1
print("原始的电影ID为 {} 的数据是：".format(ID), data[ID - 1])
print("电影ID为 {} 的转换后数据是：".format(ID), movie_info[str(ID)])

print("电影种类对应序号：'Animation':{} 'Children's':{} 'Comedy':{}".format(movie_cat['Animation'],
                                                                   movie_cat["Children's"],
                                                                   movie_cat['Comedy']))
print("电影名称对应序号：'The':{} 'Story':{} ".format(movie_titles['The'], movie_titles['Story']))

use_poster = False
if use_poster:
    rating_path = "D:/data/paddle/ml-1m/ml-1m/new_rating.txt"
else:
    rating_path = "D:/data/paddle/ml-1m/ml-1m/ratings.dat"
# 打开文件，读取所有行到data中
with open(rating_path, 'r') as f:
    data = f.readlines()
# 打印data的数据长度，以及第一条数据中的用户ID、电影ID和评分信息
item = data[0]

print(item)

item = item.strip().split("::")
usr_id, movie_id, score = item[0], item[1], item[2]
print("评分数据条数：", len(data))
print("用户ID：", usr_id)
print("电影ID：", movie_id)
print("用户对电影的评分：", score)


def get_rating_info(path):
    # 打开文件，读取所有行到data中
    with open(path, 'r') as f:
        data = f.readlines()
    # 创建一个字典
    rating_info = {}
    for item in data:
        item = item.strip().split("::")
        # 处理每行数据，分别得到用户ID，电影ID，和评分
        usr_id, movie_id, score = item[0], item[1], item[2]
        if usr_id not in rating_info.keys():
            rating_info[usr_id] = {movie_id: float(score)}
        else:
            rating_info[usr_id][movie_id] = float(score)
    return rating_info


# 获得评分数据
# rating_path = "./work/ml-1m/ratings.dat"
rating_info = get_rating_info(rating_path)
print("ID为1的用户一共评价了{}个电影".format(len(rating_info['1'])))

from PIL import Image
import matplotlib.pyplot as plt

# 使用海报图像和不使用海报图像的文件路径不同，处理方式相同
use_poster = True
if use_poster:
    rating_path = "D:/data/paddle/ml-1m/ml-1m/new_rating.txt"
else:
    rating_path = "D:/data/paddle/ml-1m/ml-1m/ratings.dat"

with open(rating_path, 'r') as f:
    data = f.readlines()

# 从新的rating文件中收集所有的电影ID
mov_id_collect = []
for item in data:
    item = item.strip().split("::")
    usr_id, movie_id, score = item[0], item[1], item[2]
    mov_id_collect.append(movie_id)

# 根据电影ID读取图像
poster_path = "D:/data/paddle/ml-1m/ml-1m/posters/"

# 显示mov_id_collect中第几个电影ID的图像
idx = 1

poster = Image.open(poster_path + 'mov_id{}.jpg'.format(str(mov_id_collect[idx])))

plt.figure("Image")  # 图像窗口名称
plt.imshow(poster)
plt.axis('on')  # 关掉坐标轴为 off
plt.title("poster with ID {}".format(mov_id_collect[idx]))  # 图像题目
plt.show()


def get_dataset(usr_info, rating_info, movie_info):
    trainset = []
    # 按照评分数据的key值索引数据
    for usr_id in rating_info.keys():
        usr_ratings = rating_info[usr_id]
        for movie_id in usr_ratings:
            trainset.append({'usr_info': usr_info[usr_id],
                             'mov_info': movie_info[movie_id],
                             'scores': usr_ratings[movie_id]})
    return trainset


dataset = get_dataset(usr_info, rating_info, movie_info)
print("数据集总数据数：", len(dataset))

import random

use_poster = False


def load_data(dataset=None, mode='train'):
    # 定义数据迭代Batch大小
    BATCHSIZE = 256

    data_length = len(dataset)
    index_list = list(range(data_length))

    # 定义数据迭代加载器
    def data_generator():
        # 训练模式下，打乱训练数据
        if mode == 'train':
            random.shuffle(index_list)
        # 声明每个特征的列表
        usr_id_list, usr_gender_list, usr_age_list, usr_job_list = [], [], [], []
        mov_id_list, mov_tit_list, mov_cat_list, mov_poster_list = [], [], [], []
        score_list = []
        # 索引遍历输入数据集
        for idx, i in enumerate(index_list):
            # 获得特征数据保存到对应特征列表中
            usr_id_list.append(dataset[i]['usr_info']['usr_id'])
            usr_gender_list.append(dataset[i]['usr_info']['gender'])
            usr_age_list.append(dataset[i]['usr_info']['age'])
            usr_job_list.append(dataset[i]['usr_info']['job'])

            mov_id_list.append(dataset[i]['mov_info']['mov_id'])
            mov_tit_list.append(dataset[i]['mov_info']['title'])
            mov_cat_list.append(dataset[i]['mov_info']['category'])
            mov_id = dataset[i]['mov_info']['mov_id']

            if use_poster:
                # 不使用图像特征时，不读取图像数据，加快数据读取速度
                poster = Image.open(poster_path + 'mov_id{}.jpg'.format(str(mov_id)))
                poster = poster.resize([64, 64])
                if len(poster.size) <= 2:
                    poster = poster.convert("RGB")

                mov_poster_list.append(np.array(poster))

            score_list.append(int(dataset[i]['scores']))
            # 如果读取的数据量达到当前的batch大小，就返回当前批次
            if len(usr_id_list) == BATCHSIZE:
                # 转换列表数据为数组形式，reshape到固定形状
                usr_id_arr = np.array(usr_id_list)
                usr_gender_arr = np.array(usr_gender_list)
                usr_age_arr = np.array(usr_age_list)
                usr_job_arr = np.array(usr_job_list)

                mov_id_arr = np.array(mov_id_list)

                mov_cat_arr = np.reshape(np.array(mov_cat_list), [BATCHSIZE, 6]).astype(np.int64)
                mov_tit_arr = np.reshape(np.array(mov_tit_list), [BATCHSIZE, 1, 15]).astype(np.int64)

                if use_poster:
                    mov_poster_arr = np.reshape(np.array(mov_poster_list) / 127.5 - 1, [BATCHSIZE, 3, 64, 64]).astype(
                        np.float32)
                else:
                    mov_poster_arr = np.array([0.])

                scores_arr = np.reshape(np.array(score_list), [-1, 1]).astype(np.float32)

                # 返回当前批次数据
                yield [usr_id_arr, usr_gender_arr, usr_age_arr, usr_job_arr], \
                      [mov_id_arr, mov_cat_arr, mov_tit_arr, mov_poster_arr], scores_arr

                # 清空数据
                usr_id_list, usr_gender_list, usr_age_list, usr_job_list = [], [], [], []
                mov_id_list, mov_tit_list, mov_cat_list, score_list = [], [], [], []
                mov_poster_list = []

    return data_generator


dataset = get_dataset(usr_info, rating_info, movie_info)
print("数据集总数量：", len(dataset))

trainset = dataset[:int(0.8 * len(dataset))]
train_loader = load_data(trainset, mode="train")
print("训练集数量：", len(trainset))

validset = dataset[int(0.8 * len(dataset)):]
valid_loader = load_data(validset, mode='valid')
print("验证集数量:", len(validset))

for idx, data in enumerate(train_loader()):
    usr_data, mov_data, score = data

    usr_id_arr, usr_gender_arr, usr_age_arr, usr_job_arr = usr_data
    mov_id_arr, mov_cat_arr, mov_tit_arr, mov_poster_arr = mov_data
    print("用户ID数据尺寸", usr_id_arr.shape)
    print("电影ID数据尺寸", mov_id_arr.shape, ", 电影类别genres数据的尺寸", mov_cat_arr.shape, ", 电影名字title的尺寸", mov_tit_arr.shape)
    break
