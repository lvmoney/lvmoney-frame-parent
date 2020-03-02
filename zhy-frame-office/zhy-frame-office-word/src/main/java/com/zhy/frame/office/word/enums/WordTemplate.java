package com.zhy.frame.office.word.enums;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public enum WordTemplate {
    /**
     * table表占位符
     */
    TABLE("#"),
    /**
     * 字符串占位符
     */
    STRING("\u0000"),
    /**
     * 图片占位符
     */
    PICTURE("@"),
    /**
     * 数字占位符
     */
    NUMBERIC("*");

    private String value;

    private WordTemplate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WordTemplate getByValue(String value) {
        WordTemplate[] wordTemplates = values();
        for (WordTemplate wordTemplate : wordTemplates) {
            if (wordTemplate.value.equals(value)) {
                return wordTemplate;
            }
        }
        return null;
    }


}
