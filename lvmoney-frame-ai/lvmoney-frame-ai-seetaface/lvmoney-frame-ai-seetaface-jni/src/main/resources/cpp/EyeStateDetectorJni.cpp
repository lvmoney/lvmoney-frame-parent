#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative.h>
#include <seeta/EyeStateDetector.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::EyeStateDetector* detector = new seeta::EyeStateDetector(modelSetting);
	return (jlong)detector;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative
 * Method:    detect
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/EyeState;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative_detect
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobjectArray points)
{
	seeta::EyeStateDetector* detector = (seeta::EyeStateDetector*) nativeId;
	SeetaImageData imageData = toSeetaImageData(env, image);

	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaPointF* pointFs = toPoints(env, points);
	seeta::EyeStateDetector::EYE_STATE leftstate;
	seeta::EyeStateDetector::EYE_STATE rightstate;
	detector->Detect(imageData, pointFs, leftstate, rightstate);

	delete pointFs;

	jclass eyeStateClazz = getClass(env, "com/lvmoney/frame/ai/seetaface/jni/vo/EyeState");
	jobject eyeState = newObject(env, eyeStateClazz);
	setInt(env, eyeState, eyeStateClazz, "left", leftstate);
	setInt(env, eyeState, eyeStateClazz, "right", rightstate);

	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);

	return eyeState;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative_set
(JNIEnv* env, jclass, jlong nativeId, jint property, jdouble value)
{
	seeta::EyeStateDetector* detector = (seeta::EyeStateDetector*) nativeId;
	detector->set(seeta::EyeStateDetector::Property(property), value);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative_get
(JNIEnv* env, jclass, jlong nativeId, jint property)
{
	seeta::EyeStateDetector* detector = (seeta::EyeStateDetector*) nativeId;
	return detector->get(seeta::EyeStateDetector::Property(property));
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_EyeStateDetectorNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::EyeStateDetector* detector = (seeta::EyeStateDetector*) nativeId;
	delete detector;
}