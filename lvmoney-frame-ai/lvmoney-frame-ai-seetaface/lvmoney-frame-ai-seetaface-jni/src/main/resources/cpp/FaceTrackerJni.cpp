#include "pch.h"
#include <lvmoney/com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative.h>
#include <seeta/FaceTracker.h>

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    init
 * Signature: (Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaModelSetting;II)J
 */
JNIEXPORT jlong JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_init
(JNIEnv* env, jclass, jobject setting, jint width, jint height)
{
	seeta::ModelSetting modelSetting = toSetting(env, setting);
	seeta::FaceTracker* tracker = new seeta::FaceTracker(modelSetting, width, height);
	return (jlong)tracker;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    setSingleCalculationThreads
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_setSingleCalculationThreads
(JNIEnv* env, jclass, jlong nativeId, jint num)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	tracker->SetSingleCalculationThreads(num);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    track
 * Signature: (JLcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaImageData;I)Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaTrackingFaceInfoArray;
 */
JNIEXPORT jobject JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_track
(JNIEnv* env, jclass, jlong nativeId, jobject image, jint frameNo)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	SeetaImageData imageData = toSeetaImageData(env, image);
	jbyteArray dArray = getSeetaImageDataByteArray(env, image);
	jbyte* array = env->GetByteArrayElements(dArray, 0);
	imageData.data = (unsigned char*)array;

	SeetaTrackingFaceInfoArray infos = tracker->Track(imageData, frameNo);
	jclass datasClazz = getClass(env, "com.lvmoney.frame.ai.seetaface.jni.vo.SeetaTrackingFaceInfoArray");
	jobject datas = newObject(env, datasClazz);
	jclass dataClazz = getClass(env, "com.lvmoney.frame.ai.seetaface.jni.vo.SeetaTrackingFaceInfo");
	jobjectArray dataArray = env->NewObjectArray(infos.size, dataClazz, NULL);
	jclass rectClazz = getClass(env, "com/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect");
	jfieldID posField = env->GetFieldID(dataClazz, "pos", "Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaRect;");

	setInt(env, datas, dataClazz, "size", infos.size);
	for (int i = 0; i < infos.size; i++) {
		SeetaTrackingFaceInfo info = infos.data[i];
		jobject data = newObject(env, dataClazz);
		setInt(env, data, dataClazz, "frameNo", info.frame_no);
		setFloat(env, data, dataClazz, "score", info.score);
		setInt(env, data, dataClazz, "pid", info.PID);
		setInt(env, data, dataClazz, "step", info.step);

		SeetaRect rect = info.pos;
		jobject pos = newObject(env, rectClazz);
		setInt(env, pos, rectClazz, "x", rect.x);
		setInt(env, pos, rectClazz, "y", rect.y);
		setInt(env, pos, rectClazz, "width", rect.width);
		setInt(env, pos, rectClazz, "height", rect.height);
		env->SetObjectField(data, posField, pos);
		env->SetObjectArrayElement(dataArray, i, data);
	}
	jfieldID dataField = env->GetFieldID(datasClazz, "data", "[Lcom/lvmoney/frame/ai/seetaface/jni/vo/SeetaTrackingFaceInfo;");
	env->SetObjectField(datas, dataField, dataArray);

	env->ReleaseByteArrayElements(dArray, array, 0);
	env->DeleteLocalRef(image);

	return datas;
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    setMinFaceSize
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_setMinFaceSize
(JNIEnv*, jclass, jlong nativeId, jint size)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	tracker->SetMinFaceSize(size);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    getMinFaceSize
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_getMinFaceSize
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	return tracker->GetMinFaceSize();
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    setThreshold
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_setThreshold
(JNIEnv*, jclass, jlong nativeId, jfloat thresh)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	tracker->SetThreshold(thresh);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    getThreshold
 * Signature: (J)F
 */
JNIEXPORT jfloat JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_getThreshold
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	return tracker->GetThreshold();
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    setVideoStable
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_setVideoStable
(JNIEnv*, jclass, jlong nativeId, jboolean stable)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	tracker->SetVideoStable(stable);
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    getVideoStable
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_getVideoStable
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	return tracker->GetVideoStable();
}

/*
 * Class:     com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative
 * Method:    close
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_lvmoney_frame_ai_seetaface_jni_natives_FaceTrackerNative_close
(JNIEnv*, jclass, jlong nativeId)
{
	seeta::FaceTracker* tracker = (seeta::FaceTracker*) nativeId;
	delete tracker;
}