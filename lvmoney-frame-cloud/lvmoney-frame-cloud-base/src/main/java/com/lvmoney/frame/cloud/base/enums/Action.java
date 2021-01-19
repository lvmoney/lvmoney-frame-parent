package com.lvmoney.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/10/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/18 14:52
 */
public enum Action {
    /**
     * 默认行为，不配置action的话就采用这种行为，它会根据regex来去匹配source_labels标签上的值，并将并将匹配到的值写入target_label中
     */
    replace("replace"),
    /**
     * 它会根据regex去匹配标签名称，并将匹配到的内容作为新标签的名称，其值作为新标签的值
     */
    labelmap("labelmap"),
    /**
     * 仅收集匹配到regex的源标签，而会丢弃没有匹配到的所有标签，用于选择
     */
    keep("keep"),
    /**
     * 丢弃匹配到regex的源标签，而会收集没有匹配到的所有标签，用于排除
     */
    drop("drop"),
    /**
     * 使用regex匹配标签，符合regex规则的标签将从target实例中移除，其实也就是不收集不保存
     */
    labeldrop("labeldrop"),
    /**
     * 使用regex匹配标签，仅收集符合regex规则的标签，不符合的不收集
     */
    labelkeep("labelkeep"),
    ;
    private String value;

    Action(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
