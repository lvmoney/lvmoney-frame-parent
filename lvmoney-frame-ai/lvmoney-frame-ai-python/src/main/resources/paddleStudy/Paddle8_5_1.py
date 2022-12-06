# jupyter中使用paddlex需要设置matplotlib
import matplotlib
from paddlehub.utils import paddlex

matplotlib.use('Agg')
# 设置使用0号GPU卡（如无GPU，执行此代码后仍然会使用CPU训练模型）
import os
os.environ['CUDA_VISIBLE_DEVICES'] = '0'
import paddlex as pdx

from paddlex.cls import transforms
train_transforms = transforms.Compose([
    transforms.RandomCrop(crop_size=224),
    transforms.RandomHorizontalFlip(),
    transforms.Normalize()
])
eval_transforms = transforms.Compose([
    transforms.ResizeByShort(short_size=256),
    transforms.CenterCrop(crop_size=224),
    transforms.Normalize()
])


train_dataset = pdx.datasets.ImageNet(
    data_dir='D:/data/paddle/makeup',
    file_list='D:/data/paddle/makeup/train_list.txt',
    label_list='D:/data/paddle/makeup/labels.txt',
    transforms=train_transforms,
    shuffle=True)
eval_dataset = pdx.datasets.ImageNet(
    data_dir='D:/data/paddle/makeup',
    file_list='D:/data/paddle/makeup/val_list.txt',
    label_list='D:/data/paddle/makeup/labels.txt',
    transforms=eval_transforms)

num_classes = len(train_dataset.labels)
model = pdx.cls.MobileNetV3_large_ssld(num_classes=num_classes)
model.train(num_epochs=10,
            train_dataset=train_dataset,
            train_batch_size=32,
            eval_dataset=eval_dataset,
            lr_decay_epochs=[4, 6, 8],
            save_interval_epochs=1,
            learning_rate=0.025,
            save_dir='output/mobilenetv3_large_ssld')




result = model.predict('D:/data/paddle/makeup/mascara/54.jpeg', topk=1)
print("Predict Result:", result)