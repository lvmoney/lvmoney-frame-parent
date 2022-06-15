package com.lvmoney.frame.ai.lstm.constant;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.common.constant
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 10:25
 */
public class LstmConstant {
    /**
     * 数据智能统一前缀
     */
    public static final String SMART_DATA_PREFIX = "smartData";


    /**
     * 孤立森林相关统一前缀
     */
    public static final String SMART_DATA_ISOLATION_FOREST_PREFIX = SMART_DATA_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "IsolationForest:";

    /**
     * python 孤立森林 数据
     */
    public static final String SMART_DATA_ISOLATION_FOREST_INPUT = SMART_DATA_ISOLATION_FOREST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "input";


    /**
     * python 孤立森林 配置
     */
    public static final String SMART_DATA_ISOLATION_FOREST_PARAM = SMART_DATA_ISOLATION_FOREST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "param";


    /**
     * python 孤立森林 字典
     */
    public static final String SMART_DATA_ISOLATION_FOREST_FIELD = SMART_DATA_ISOLATION_FOREST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "field";
}
