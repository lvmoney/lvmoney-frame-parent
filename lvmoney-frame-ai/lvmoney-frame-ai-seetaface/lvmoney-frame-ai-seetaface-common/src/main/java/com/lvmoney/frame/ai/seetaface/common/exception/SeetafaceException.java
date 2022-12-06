package com.lvmoney.frame.ai.seetaface.common.exception;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.enums.ExceptionCodeLevel;
import com.lvmoney.frame.base.core.exception.ExceptionType;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface SeetafaceException {


    enum Proxy implements ExceptionType {
        /**
         * 上传的两张图片对比报错
         */
        COMPARE_ERROR(ExceptionCodeLevel.SEATAFACE.getValue() + 1, "上传的两张图片对比报错"),

        /**
         * 和minio文件对比报错
         */
        COMPARE_RES_ERROR(ExceptionCodeLevel.SEATAFACE.getValue() + 2, "和minio文件对比报错"),

        /**
         * 和图片库文件对比报错
         */
        COMPARE_ID_CARD_ERROR(ExceptionCodeLevel.SEATAFACE.getValue() + 3, "和图片库文件对比报错"),

        /**
         * 识别出的图片有多个
         */
        FACE_NOT_ONLY(ExceptionCodeLevel.SEATAFACE.getValue() + 4, "识别出的图片有多个"),

        /**
         * 该类已被释放无法使用
         */
        RESOURCE_IS_CLOSED(ExceptionCodeLevel.SEATAFACE.getValue() + 5, "该类已被释放无法使用"),

        /**
         * 参数不能为空
         */
        PARAM_IS_NULL(ExceptionCodeLevel.SEATAFACE.getValue() + 6, "参数不能为空"),

        /**
         * 配置不能为空
         */
        SETTINGIS_NULL(ExceptionCodeLevel.SEATAFACE.getValue() + 7, "配置不能为空"),

        /**
         * 配置不能为空
         */
        OS_NOT_SUPPORT(ExceptionCodeLevel.SEATAFACE.getValue() + 8, "操作系统不支持"),

        /**
         * 配置不能为空
         */
        ARCH_NOT_SUPPORT(ExceptionCodeLevel.SEATAFACE.getValue() + 9, "不支持64位操作系统"),

        ;
        private int code;
        private String description;

        Proxy(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
    }

}
