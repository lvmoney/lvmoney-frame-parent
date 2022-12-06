import  os

import cv2
import paddlehub as hub
import matplotlib.pyplot as plt


# 待预测图片
test_img_path = ["D:/data/paddle/123.jpg"]


import matplotlib.pyplot as plt
import matplotlib.image as mpimg

img = mpimg.imread(test_img_path[0])

# 展示待预测图片
plt.figure(figsize=(10,10))
plt.imshow(img)
plt.axis('off')
plt.show()


import paddlehub as hub
import matplotlib.image as mpimg
import matplotlib.pyplot as plt

module = hub.Module(name="deeplabv3p_xception65_humanseg")
res = module.segmentation(paths = ["D:/data/paddle/123.jpg"], visualization=True, output_dir='humanseg_output')


res_img_path = 'D:/data/paddle/humanseg_output/123.png'
img = mpimg.imread(res_img_path)
plt.figure(figsize=(10, 10))
plt.imshow(img)
plt.axis('off')
plt.show()


import paddlehub as hub
import matplotlib.image as mpimg
import matplotlib.pyplot as plt

module = hub.Module(name="ace2p")
res = module.segmentation(paths = ["D:/data/paddle/123.jpg"], visualization=True, output_dir='ace2p_output')

res_img_path = './ace2p_output/123.png'
img = mpimg.imread(res_img_path)
plt.figure(figsize=(10, 10))
plt.imshow(img)
plt.axis('off')
plt.show()

import paddlehub as hub
import matplotlib.image as mpimg
import matplotlib.pyplot as plt

module = hub.Module(name="ultra_light_fast_generic_face_detector_1mb_640")
res = module.face_detection(paths = ["D:/data/paddle/123.jpg"], visualization=True, output_dir='face_detection_output')

res_img_path = './face_detection_output/123.jpg'
img = mpimg.imread(res_img_path)
plt.figure(figsize=(10, 10))
plt.imshow(img)
plt.axis('off')
plt.show()


import paddlehub as hub
import matplotlib.image as mpimg
import matplotlib.pyplot as plt

module = hub.Module(name="human_pose_estimation_resnet50_mpii")
res = module.keypoint_detection(paths = ["D:/data/paddle/123.jpg"], visualization=True, output_dir='keypoint_output')

res_img_path = './keypoint_output/123.jpg'
img = mpimg.imread(res_img_path)
plt.figure(figsize=(10, 10))
plt.imshow(img)
plt.axis('off')
plt.show()