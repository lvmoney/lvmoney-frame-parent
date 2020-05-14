package com.zhy.frame.oss.common.exception;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.enums.ExceptionCodeLevel;
import com.zhy.frame.base.core.exception.ExceptionType;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface OssException {


    enum Proxy implements ExceptionType {
        /**
         * gridfs存储报错
         */
        GRIDFS_SAVE_ERROR(ExceptionCodeLevel.OSS.getValue() + 1, "gridFs save error"),
        /**
         * gridfs文件存储太大
         */
        GRIDFS_FILE_SIZE(ExceptionCodeLevel.OSS.getValue() + 2, "gridFs fileSize too long"),
        /**
         * gridfs获取文件报错
         */
        GRIDFS_QUERY_FILE_ERROR(ExceptionCodeLevel.OSS.getValue() + 3, "gridFs get file error"),

        /**
         * grifs 获取的文件不存在
         */
        GRIDFS_QUERY_FILE_NOT_EXIST(ExceptionCodeLevel.OSS.getValue() + 4, "gridFs get file not exsit"),

        /**
         * mongo排序类型是需要的
         */
        MONGO_SORT_TYPE_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 5, "mongo sortType is Required"),
        /**
         * mongo排序类型是指定的
         */
        MONGO_SORT_TYPE_IS_ERROR(ExceptionCodeLevel.CORE.getValue() + 6, "mongo sortType value is desc or asc"),
        ;
        private int code;
        private String description;

        private Proxy(int code, String description) {
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
