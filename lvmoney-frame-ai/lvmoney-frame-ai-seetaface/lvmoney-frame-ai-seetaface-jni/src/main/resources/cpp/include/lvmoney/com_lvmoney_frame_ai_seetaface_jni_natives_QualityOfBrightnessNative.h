/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative */

#ifndef _Included_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative
#define _Included_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative
 * Method:    init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative_init__
  (JNIEnv *, jclass);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative
 * Method:    init
 * Signature: (FFFF)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative_init__FFFF
  (JNIEnv *, jclass, jfloat, jfloat, jfloat, jfloat);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative
 * Method:    check
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/QualityResult;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative_check
  (JNIEnv *, jclass, jlong, jobject, jobject, jobjectArray);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfBrightnessNative_close
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
