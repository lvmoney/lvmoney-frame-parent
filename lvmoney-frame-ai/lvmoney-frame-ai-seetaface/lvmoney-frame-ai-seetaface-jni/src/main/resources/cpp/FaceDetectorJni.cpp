#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative.h>
#include <seeta/FaceDetector.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative_init
(JNIEnv* env, jclass, jobject setting) 
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::FaceDetector* facedector = new seeta::FaceDetector(modelSetting);
	return (jlong)facedector;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative
 * Method:    detect
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaFaceInfoArray;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative_detect
(JNIEnv* env, jclass, jlong nativeId, jobject image)
{
	seeta::FaceDetector* facedector = (seeta::FaceDetector*)nativeId;
	SeetaImageData imageData = toSeetaImageData(env, image);

	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaFaceInfoArray infos = facedector->detect(imageData);
	jobject result = toSeetaFaceInfoArray(env, infos);

	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);

	return result;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative_set
(JNIEnv* env, jclass, jlong nativeId, jint property, jdouble val)
{
	seeta::FaceDetector* facedector = (seeta::FaceDetector*)nativeId;
	facedector->set(seeta::FaceDetector::Property(property), val);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative_get
(JNIEnv* env, jclass, jlong nativeId, jint property)
{
	seeta::FaceDetector* facedector = (seeta::FaceDetector*)nativeId;
	return facedector->get(seeta::FaceDetector::Property(property));
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceDetectorNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceDetector* facedector = (seeta::FaceDetector*)nativeId;
	delete facedector;
}

