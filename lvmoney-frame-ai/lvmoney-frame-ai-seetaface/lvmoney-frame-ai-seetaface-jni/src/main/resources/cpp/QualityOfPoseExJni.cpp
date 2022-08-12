#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative.h>
#include <seeta/QualityOfPoseEx.h>

static jobject toQualityResult(JNIEnv* env, seeta::QualityResult result)
{
	jclass qualityResultClazz = getClass(env, "com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult");
	jobject qualityResult = newObject(env, qualityResultClazz);
	setInt(env, qualityResult, qualityResultClazz, "level", result.level);
	setFloat(env, qualityResult, qualityResultClazz, "score", result.score);
	return qualityResult;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::QualityOfPoseEx* quality = new seeta::QualityOfPoseEx(modelSetting);
	return (jlong)quality;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    check
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/QualityResult;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_check
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobject rect, jobjectArray points)
{
	seeta::QualityOfPoseEx* quality = (seeta::QualityOfPoseEx*)nativeId;
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
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_set
(JNIEnv*, jclass, jlong nativeId, jint property, jdouble val)
{
	seeta::QualityOfPoseEx* quality = (seeta::QualityOfPoseEx*)nativeId;
	quality->set(seeta::QualityOfPoseEx::PROPERTY(property), val);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_get
(JNIEnv*, jclass, jlong nativeId, jint property)
{
	seeta::QualityOfPoseEx* quality = (seeta::QualityOfPoseEx*)nativeId;
	return quality->get(seeta::QualityOfPoseEx::PROPERTY(property));
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfPoseExNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::QualityOfPoseEx* quality = (seeta::QualityOfPoseEx*)nativeId;
	delete quality;
}