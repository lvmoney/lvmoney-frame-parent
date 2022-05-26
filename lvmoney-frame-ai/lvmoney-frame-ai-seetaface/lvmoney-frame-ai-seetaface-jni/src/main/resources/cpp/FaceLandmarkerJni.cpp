#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative.h>
#include <seeta/FaceLandmarker.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::FaceLandmarker* faceLandmarker = new seeta::FaceLandmarker(modelSetting);
	return (jlong)faceLandmarker;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative
 * Method:    number
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative_number
(JNIEnv* env, jclass, jlong nativeId)
{
	seeta::FaceLandmarker* landmarker = (seeta::FaceLandmarker*) nativeId;
	return landmarker->number();
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative
 * Method:    mark
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;)[Lcom/lvmoney/frame/ai/seetaface/jni/vo/PointWithMask;
 */
JNIEXPORT jobjectArray JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative_mark
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobject rect)
{
	// ����mark_v2
	seeta::FaceLandmarker* landmarker = (seeta::FaceLandmarker*) nativeId;

	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaRect face = toRect(env, rect);
	vector<seeta::FaceLandmarker::PointWithMask> result = landmarker->mark_v2(imageData, face);

	// ��װ���
	jclass maskClazz = getClass(env, "com/lvmoney/frame/ai/seetaface/jni/vo/PointWithMask");
	jobjectArray masks = env->NewObjectArray(landmarker->number(), maskClazz, 0);
	jmethodID maskInitMethod = getInitMethod(env, maskClazz);
	jclass pointFClazz = getClass(env, "com/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF");
	jfieldID maskField = env->GetFieldID(maskClazz, "mask", "Z");
	jfieldID seetaPointFField = env->GetFieldID(maskClazz, "point", "Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;");
	jfieldID xField = env->GetFieldID(pointFClazz, "x", "D");
	jfieldID yField = env->GetFieldID(pointFClazz, "y", "D");
	int i = 0;
	for (auto &mask : result) {
		jobject maskJni = newObject(env, maskClazz);
		env->SetBooleanField(maskJni, maskField, mask.mask);

		jobject pointF = newObject(env, pointFClazz);
		SeetaPointF pf = mask.point;
		env->SetDoubleField(pointF, xField, pf.x);
		env->SetDoubleField(pointF, yField, pf.y);
		env->SetObjectField(maskJni, seetaPointFField, pointF);

		env->SetObjectArrayElement(masks, i++, maskJni);
	}
	
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);

	return masks;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceLandmarkerNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceLandmarker* landmarker = (seeta::FaceLandmarker*) nativeId;
	delete landmarker;
}