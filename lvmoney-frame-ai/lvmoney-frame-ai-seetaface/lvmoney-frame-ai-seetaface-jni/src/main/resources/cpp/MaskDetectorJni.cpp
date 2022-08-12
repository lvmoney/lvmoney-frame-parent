#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative.h>
#include <seeta/MaskDetector.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative_init
(JNIEnv* env, jclass, jobject setting)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::MaskDetector* maskDetector = new seeta::MaskDetector(modelSetting);
	return (jlong)maskDetector;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative
 * Method:    detect
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;F)Z
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative_detect
(JNIEnv* env, jclass, jlong nativeId, jobject imageData, jobject rect)
{
	SeetaImageData image = toSeetaImageData(env, imageData);
    //此处你忘记把照片数据赋值了，老哥
    jbyteArray dataArray = getSeetaImageDataByteArray(env, imageData);
	jbyte* array = env->GetByteArrayElements(dataArray, 0);
	image.data = (unsigned char*)array;

	SeetaRect face = toRect(env, rect);
	seeta::MaskDetector* maskDetector = (seeta::MaskDetector*) nativeId;
	float* score = new float();
	bool isMask = maskDetector->detect(image, face, score);
	jclass maskStatusClazz = getClass(env, "com/lvmoney/frame/ai/seetaface/jni/vo/MaskStatus");
	jobject maskStatus = newObject(env, maskStatusClazz);
	jfieldID statusfield = env->GetFieldID(maskStatusClazz, "status", "Z");
	jfieldID scorefield = env->GetFieldID(maskStatusClazz, "score", "F");
	env->SetBooleanField(maskStatus, statusfield, isMask);
	env->SetFloatField(maskStatus, scorefield, *score);
	delete score;
	return maskStatus;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_MaskDetectorNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::MaskDetector* maskDetector = (seeta::MaskDetector*) nativeId;
	delete maskDetector;
}