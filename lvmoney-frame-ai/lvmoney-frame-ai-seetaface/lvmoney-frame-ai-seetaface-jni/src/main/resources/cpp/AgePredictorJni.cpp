#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative.h>
#include <seeta/AgePredictor.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::AgePredictor* age = new seeta::AgePredictor(modelSetting);
	return (jlong)age;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    cropFace
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_cropFace
(JNIEnv* env, jclass, jlong nativeId, jobject imageData, jobjectArray seetaPointFs)
{
	seeta::AgePredictor* age = (seeta::AgePredictor*) nativeId;
	SeetaImageData image = toSeetaImageData(env, imageData);

	jbyteArray dataArray = getSeetaImageDataByteArray(env, imageData);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	image.data = (unsigned char*)array;
	
	SeetaImageData face;
	SeetaPointF* points = toPoints(env, seetaPointFs);
	bool isCrop = age->CropFace(image, points, face);

	delete points;
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(imageData);

	return isCrop ? toSeetaImageData(env, face) : NULL;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    predictAge
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;)I
 */
JNIEXPORT jint JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_predictAge
(JNIEnv* env, jclass, jlong nativeId, jobject face)
{
	seeta::AgePredictor* age = (seeta::AgePredictor*) nativeId;
	SeetaImageData faceData = toSeetaImageData(env, face);

	jbyteArray dataArray = getSeetaImageDataByteArray(env, face);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	faceData.data = (unsigned char*)array;

	int ageNum;
	bool isSuccess = age->PredictAge(faceData, ageNum);
	if (!isSuccess) {
		ageNum = -1;
	}

	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(face);

	return ageNum;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    predictAgeWithCrop
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)I
 */
JNIEXPORT jint JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_predictAgeWithCrop
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobjectArray points)
{
	seeta::AgePredictor* age = (seeta::AgePredictor*) nativeId;
	SeetaImageData imageData = toSeetaImageData(env, image);

	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaPointF* pointFs = toPoints(env, points);
	int ageNum;
	bool isSuccess = age->PredictAgeWithCrop(imageData, pointFs, ageNum);
	if (!isSuccess) {
		ageNum = -1;
	}

	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);

	return ageNum;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_set
(JNIEnv* env, jclass, jlong nativeId, jint property, jdouble value)
{
	seeta::AgePredictor* age = (seeta::AgePredictor*) nativeId;
	age->set(seeta::AgePredictor::Property(property), value);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_get
(JNIEnv* env, jclass, jlong nativeId, jint property)
{
	seeta::AgePredictor* age = (seeta::AgePredictor*) nativeId;
	return age->get(seeta::AgePredictor::Property(property));
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_AgePredictorNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::AgePredictor* age = (seeta::AgePredictor*) nativeId;
	delete age;
}