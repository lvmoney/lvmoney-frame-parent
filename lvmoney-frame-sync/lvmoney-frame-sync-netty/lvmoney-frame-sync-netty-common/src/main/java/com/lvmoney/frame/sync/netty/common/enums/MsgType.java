package com.lvmoney.frame.sync.netty.common.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.netty.common.enums
 * 版本信息: 版本1.0
 * 日期:2020/6/1
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/1 14:05
 */
public enum MsgType {
    /**
     * 文字信息
     */
    WORD("word"),
    /**
     * 文件
     */
    FILE("file"),

    /**
     * 视频
     */
    VIDEO("video"),

    /**
     * 语音
     */
    VOICE("voice"),
    ;


    private String value;

    private MsgType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
