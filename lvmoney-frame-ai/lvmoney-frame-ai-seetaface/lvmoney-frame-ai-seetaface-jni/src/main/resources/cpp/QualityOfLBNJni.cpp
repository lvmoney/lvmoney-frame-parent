#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative.h>
#include <seeta/QualityOfLBN.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::QualityOfLBN* quality = new seeta::QualityOfLBN(modelSetting);
	return (jlong)quality;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    detect
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaPointF;)Lcom/lvmoney/frame/ai/seetaface/jni/vo/BlurInfo;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_detect
(JNIEnv* env, jclass, jlong nativeId, jobject image, jobjectArray points)
{
	seeta::QualityOfLBN* quality = (seeta::QualityOfLBN*) nativeId;

	int* light = new int(), *blur = new int(), *noise = new int();
	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dataArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaPointF* pointFs = toPoints(env, points);
	quality->Detect(imageData, pointFs, light, blur, noise);
	jclass blurInfoClazz = getClass(env, "com.lvmoney.frame.ai.seetaface.jni.vo.BlurInfo");
	jobject blurInfo = newObject(env, blurInfoClazz);
	setInt(env, blurInfo, blurInfoClazz, "light", *light);
	setInt(env, blurInfo, blurInfoClazz, "blur", *blur);
	setInt(env, blurInfo, blurInfoClazz, "noise", *noise);
	delete pointFs, light, blur, noise;
	env->ReleaseByteArrayElements(dataArray, array, 0);
	env->DeleteLocalRef(image);
	return blurInfo;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    set
 * Signature: (JID)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_set
(JNIEnv* env, jclass, jlong nativeId, jint property, jdouble value)
{
	seeta::QualityOfLBN* quality = (seeta::QualityOfLBN*) nativeId;
	quality->set(seeta::QualityOfLBN::Property(property), value);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    get
 * Signature: (JI)D
 */
JNIEXPORT jdouble JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_get
(JNIEnv*, jclass, jlong nativeId, jint property)
{
	seeta::QualityOfLBN* quality = (seeta::QualityOfLBN*) nativeId;
	return quality->get(seeta::QualityOfLBN::Property(property));
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_QualityOfLBNNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::QualityOfLBN* quality = (seeta::QualityOfLBN*) nativeId;
	delete quality;
}