/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative */

#ifndef _Included_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
#define _Included_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_init
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    check
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/QualityResult;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_check
  (JNIEnv *, jclass, jlong, jobject, jobject, jobjectArray);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_set
  (JNIEnv *, jclass, jlong, jint, jdouble);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_get
  (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_close
  (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif