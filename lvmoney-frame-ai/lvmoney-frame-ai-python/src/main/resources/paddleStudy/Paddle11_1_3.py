import matplotlib.pyplot as plt
from PIL import Image

## 显示原图，读取名称为11.jpg的测试图像
img_path= "D:/data/paddle/code/PaddleOCR/doc/imgs/11.jpg"
img = Image.open(img_path)
plt.figure("test_img", figsize=(10,10))
plt.imshow(img)
plt.show()

## 显示轻量级模型识别结果
# img_path= "./inference_results/11.jpg"
img = Image.open(img_path)
plt.figure("results_img", figsize=(26,26))
plt.imshow(img)
plt.show()