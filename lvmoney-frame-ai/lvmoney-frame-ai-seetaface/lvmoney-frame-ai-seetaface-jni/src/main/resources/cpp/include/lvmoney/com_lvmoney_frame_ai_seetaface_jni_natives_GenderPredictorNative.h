/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative */

#ifndef _Included_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
#define _Included_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_init
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    cropFace
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_cropFace
  (JNIEnv *, jclass, jlong, jobject, jobjectArray);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    predictGender
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;)I
 */
JNIEXPORT jint JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_predictGender
  (JNIEnv *, jclass, jlong, jobject);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    predictGenderWithCrop
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)I
 */
JNIEXPORT jint JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_predictGenderWithCrop
  (JNIEnv *, jclass, jlong, jobject, jobjectArray);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_set
  (JNIEnv *, jclass, jlong, jint, jdouble);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_get
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_GenderPredictorNative_close
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
