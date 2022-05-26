package com.lvmoney.frame.ai.seetaface.jni.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

/**
 * @describe：质量评估结果集
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityResult {

    /**
     * 表示质量等级 差、良、优
     * <pre>
     * 差 LOW = 0
     * 良 MEDIUM = 1
     * 优 HIGH = 2
     * </pre>
     */
    public int level;

    /**
     * 质量评价 但是不保证取值范围，越大质量越好
     */
    public float score;

}
