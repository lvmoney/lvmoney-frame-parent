package com.lvmoney.frame.authentication.oauth2.center.vo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public enum GenderEnum {
    /**
     * 男
     */
    MALE("男"),
    /**
     * 女
     */
    FEMALE("女"),
    /**
     * 未知
     */
    UNKNOWN("未知");

    private String meaning;

    public String getMeaning() {
        return meaning;
    }

    GenderEnum() {
    }

    GenderEnum(String meaning) {
        this.meaning = meaning;
    }
}
