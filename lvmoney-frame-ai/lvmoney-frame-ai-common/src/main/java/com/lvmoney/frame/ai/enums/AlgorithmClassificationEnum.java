package com.lvmoney.frame.ai.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.enums
 * 版本信息: 版本1.0
 * 日期:2022/8/9
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/8/9 17:20
 */
public enum AlgorithmClassificationEnum {
    /**
     * pyod
     */
    PYOD("pyod"),

    /**
     * adtk
     */
    ADTK("adtk"),
    /**
     * lgbm
     */
    LGBM("lgbm"),
    /**
     * adk&pyod
     */
    ADTK_PYOD("adtk&pyod"),
    ;
    private String value;

    AlgorithmClassificationEnum(String value) {
        this.value = value;
    }

    /**
     * 根据vlue获得枚举类
     *
     * @param key:
     * @throws
     * @return: com.lvmoney.platform.smart.common.enums.TimeSlotEnum
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/8/9 10:44
     */
    public static AlgorithmClassificationEnum getAlgorithmClassificationEnum(String key) {
        for (AlgorithmClassificationEnum e : AlgorithmClassificationEnum.values()) {
            if (e.getValue().equals(key)) {
                return e;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }


}
