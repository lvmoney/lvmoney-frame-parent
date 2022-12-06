# -*- coding: utf-8 -*-

import numpy as np
from PIL import Image
from paddle.dataset.image import cv2
from paddleocr import PaddleOCR

import Const


def getText(img: np.ndarray):
    '''
    图文识别，使用百度的paddleocr，识别效果非常好
    :param img:
    :return:
    '''
    ocr = PaddleOCR(use_angle_cls=Const.PADDLE_OCR_CLS, lang=Const.PADDLE_OCR_LANG)
    result = ocr.ocr(img, cls=Const.PADDLE_OCR_CLS)
    txts = [line[1][0] for line in result]
    return txts


if __name__ == '__main__':
    img_path = 'D:/data/images/20210715212959618.png'
    img = Image.open(img_path)
    image_np = cv2.imread(img_path)
    print(type(image_np))
    print(getText(image_np))
