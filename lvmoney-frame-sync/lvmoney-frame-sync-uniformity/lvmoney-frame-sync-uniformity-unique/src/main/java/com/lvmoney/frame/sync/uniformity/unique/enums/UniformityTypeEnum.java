package com.lvmoney.frame.sync.uniformity.unique.enums;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public enum UniformityTypeEnum {
    /**
     * 已发出
     */
    ISSUED("issued"),
    /**
     * 已接收
     */
    RECEIVED("received");

    private String type;

    public String getType() {
        return type;
    }

    UniformityTypeEnum() {
    }

    UniformityTypeEnum(String type) {
        this.type = type;
    }
}
