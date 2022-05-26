#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative.h>
#include <seeta/FaceRecognizer.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::FaceRecognizer* recognizer = new seeta::FaceRecognizer(modelSetting);
	return (jlong)recognizer;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    cropFace
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_cropFace
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobjectArray points)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaPointF* pointFs = toPoints(env, points);
	SeetaImageData face = recognizer->CropFace(imageData, pointFs);
	delete pointFs;
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);
	return toSeetaImageData(env, face);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    extractCroppedFace
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;)[F
 */
JNIEXPORT jfloatArray JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_extractCroppedFace
(JNIEnv* env, jclass, jlong nativeId, jobject image)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	int size = recognizer->GetExtractFeatureSize();
	float* features = new float[size];
	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	bool isSuccess = recognizer->ExtractCroppedFace(imageData, features);
	jfloatArray featuresJava = NULL;
	if (isSuccess) 
	{
		int size = recognizer->GetExtractFeatureSize();
		featuresJava = env->NewFloatArray(size);
		env->SetFloatArrayRegion(featuresJava, 0, size, features);
	}
	delete[] features;
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);
	return featuresJava;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    extract
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)[F
 */
JNIEXPORT jfloatArray JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_extract
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobjectArray points)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	int size = recognizer->GetExtractFeatureSize();
	float* features = new float[size];
	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaPointF* pointFs = toPoints(env, points);
	bool isSuccess = recognizer->Extract(imageData, pointFs, features);
	jfloatArray featuresJava = NULL;
	if (isSuccess)
	{
		featuresJava = env->NewFloatArray(size);
		env->SetFloatArrayRegion(featuresJava, 0, size, features);
	}
	delete[] features, pointFs;
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);
	return featuresJava;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    calculateSimilarity
 * Signature: (J[F[F)F
 */
JNIEXPORT jfloat JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_calculateSimilarity
(JNIEnv* env, jclass, jlong nativeId, jfloatArray featuresOne, jfloatArray featuresTwo)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	jfloat* features1 = env->GetFloatArrayElements(featuresOne, 0);
	jfloat* features2 = env->GetFloatArrayElements(featuresTwo, 0);
	jfloat result = recognizer->CalculateSimilarity(features1, features2);

	// @update 2020��11��16�� from gitee li_yanhui ����ԭ�Ȼᵼ���ڴ�й©������

	env->ReleaseFloatArrayElements(featuresOne, features1, JNI_COMMIT);
	env->ReleaseFloatArrayElements(featuresTwo, features2, JNI_COMMIT);

	// env->DeleteLocalRef(featuresOne);
	// env->DeleteLocalRef(featuresTwo);
	
	return result;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_set
(JNIEnv* env, jclass, jlong nativeId, jint property, jdouble val)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	recognizer->set(seeta::FaceRecognizer::Property(property), val);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_get
(JNIEnv* env, jclass, jlong nativeId, jint property)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	return recognizer->get(seeta::FaceRecognizer::Property(property));
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceRecognizerNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceRecognizer* recognizer = (seeta::FaceRecognizer*) nativeId;
	delete recognizer;
}