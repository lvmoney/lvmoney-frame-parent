#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative.h>
#include <seeta/QualityOfClarity.h>

static jobject toQualityResult(JNIEnv* env, seeta::QualityResult result)
{
    //jclass qualityResultClazz = getClass(env, "com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult");
    jclass qualityResultClazz = getClass(env, "com/lvmoney/frame/ai/seetaface/jni/vo/QualityResult");
	jobject qualityResult = newObject(env, qualityResultClazz);
	setInt(env, qualityResult, qualityResultClazz, "level", result.level);
	setFloat(env, qualityResult, qualityResultClazz, "score", result.score);
	return qualityResult;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative
 * Method:    init
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative_init__
(JNIEnv*, jclass)
{
	seeta::QualityOfClarity* quality = new seeta::QualityOfClarity();
	return (jlong)quality;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative
 * Method:    init
 * Signature: (FF)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative_init__FF
(JNIEnv*, jclass, jfloat low, jfloat high)
{
	seeta::QualityOfClarity* quality = new seeta::QualityOfClarity(low, high);
	return (jlong)quality;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative
 * Method:    check
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/QualityResult;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative_check
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobject rect, jobjectArray points)
{
	seeta::QualityOfClarity* quality = (seeta::QualityOfClarity*)nativeId;
	int n = env->GetArrayLength(points);
	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaRect face = toRect(env, rect);
	SeetaPointF* pointFs = toPoints(env, points);
	seeta::QualityResult result = quality->check(imageData, face, pointFs, n);

	delete pointFs;
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);
	return toQualityResult(env, result);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfClarityNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::QualityOfClarity* quality = (seeta::QualityOfClarity*)nativeId;
	delete quality;
}