package com.lvmoney.frame.newsql.common.exception;


import com.lvmoney.frame.base.core.enums.ExceptionCodeLevel;
import com.lvmoney.frame.base.core.exception.ExceptionType;

/**
 * @describe：路由错误码定义接口
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月29日 上午11:34:43
 */
public interface NewsqlException {
    /**
     * frame错误码对应
     */
    enum Proxy implements ExceptionType {

        ;
        private int code;
        private String description;

        Proxy(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
