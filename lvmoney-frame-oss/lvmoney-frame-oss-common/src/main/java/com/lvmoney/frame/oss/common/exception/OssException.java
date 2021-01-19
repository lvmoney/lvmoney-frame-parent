package com.lvmoney.frame.oss.common.exception;/**
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
        MONGO_SORT_TYPE_IS_REQUIRED(ExceptionCodeLevel.OSS.getValue() + 5, "mongo sortType is Required"),
        /**
         * mongo排序类型是指定的
         */
        MONGO_SORT_TYPE_IS_ERROR(ExceptionCodeLevel.OSS.getValue() + 6, "mongo sortType value is desc or asc"),
        /**
         * 文件类型不被允许
         */
        FILE_TYPE_NOT_REQUIRED(ExceptionCodeLevel.OSS.getValue() + 7, "文件类型不被允许"),

        /**
         * fileType 支持与否
         */
        FILE_TYPE_SUPPORT_ERROR(ExceptionCodeLevel.OSS.getValue() + 8, "frame.fileType.support value is 'true' or 'false'"),
        /**
         * 文件大小受限
         */
        FILE_SIZE_ERROR(ExceptionCodeLevel.OSS.getValue() + 9, "文件超过系统限制大小"),

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
