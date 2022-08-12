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
     * lstm相关统一前缀
     */
    public static final String SMART_DATA_LSTM_PREFIX = SMART_DATA_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "Lstm:";

    /**
     * python lstm 数据
     */
    public static final String SMART_DATA_LSTM_INPUT = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "input";


    /**
     * lstm 分类 多变量
     */
    public static final String SMART_LSTM_CLASSIFY_MULT = "multivariable";

    /**
     * lstm 分类 单变量
     */
    public static final String SMART_LSTM_CLASSIFY_UNIV = "univariate";

    /**
     * python lstm 配置 多变量
     */
    public static final String SMART_DATA_LSTM_PARAM_MULT = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_MULT + BaseConstant.CONNECTOR_UNDERLINE + "param";


    /**
     * python lstm 配置 单变量
     */
    public static final String SMART_DATA_LSTM_PARAM_UNIV = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_UNIV + BaseConstant.CONNECTOR_UNDERLINE + "param";


    /**
     * python lstm model 最优参数 多变量
     */
    public static final String SMART_DATA_LSTM_BEST_MODEL_PARAM_MULT = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_MULT + BaseConstant.CONNECTOR_UNDERLINE + "modelParam";


    /**
     * python lstm model 最优参数 单变量
     */
    public static final String SMART_DATA_LSTM_BEST_MODEL_PARAM_UNIV = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_UNIV + BaseConstant.CONNECTOR_UNDERLINE + "modelParam";


    /**
     * python lstm 字典
     */
    public static final String SMART_DATA_LSTM_FIELD_MULT = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_MULT + BaseConstant.CONNECTOR_UNDERLINE + "field";

    /**
     * python lstm 字典
     */
    public static final String SMART_DATA_LSTM_FIELD_UNIV = SMART_DATA_LSTM_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_UNIV + BaseConstant.CONNECTOR_UNDERLINE + "field";


}
