# -*- coding: utf-8 -*-
import face_recognition
import cv2


def demoFunc():
    '''
    在一张包含人脸的图片中圈出来人脸
    '''
    image = face_recognition.load_image_file("D:/data/images/lakers.jpg")
    face_locations = face_recognition.face_locations(image)
    for one in face_locations:
        y0, x1, y1, x0 = one
        cv2.rectangle(image, pt1=(x0, y0), pt2=(x1, y1), color=(0, 0, 255), thickness=3)
    cv2.imshow('aaa', image)
    if cv2.waitKey(0) & 0xFF == ord('q'):
        cv2.destroyAllWindows()


if __name__ == '__main__':
    demoFunc()
