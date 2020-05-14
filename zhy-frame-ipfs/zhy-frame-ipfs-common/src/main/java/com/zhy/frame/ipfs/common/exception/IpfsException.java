package com.zhy.frame.ipfs.common.exception;/**
 * 描述:
 * 包名:com.zhy.oauth2.center.feign
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
public interface IpfsException {


    enum Proxy implements ExceptionType {
        /**
         * 保存ipfs文件报错
         */
        IPFS_SAVE_ERROR(ExceptionCodeLevel.IPFS.getValue() + 1, "ipfs save error"),
        /**
         * 从ipfs获得文件报错
         */
        IPFS_GET_ERROR(ExceptionCodeLevel.IPFS.getValue() + 2, "get ipfs file error"),
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
