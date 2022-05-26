/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative */

#ifndef _Included_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
#define _Included_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_init
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    detect
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/BlurInfo;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_detect
  (JNIEnv *, jclass, jlong, jobject, jobjectArray);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_set
  (JNIEnv *, jclass, jlong, jint, jdouble);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_get
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_close
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
