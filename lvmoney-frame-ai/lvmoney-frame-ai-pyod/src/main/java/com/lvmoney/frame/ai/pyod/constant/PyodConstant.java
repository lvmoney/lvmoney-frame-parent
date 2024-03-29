package com.lvmoney.frame.ai.pyod.constant;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.common.constant
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;

import static com.lvmoney.frame.ai.constant.AiConstant.SMART_DATA_PREFIX;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 10:25
 */
public class PyodConstant {


    /**
     * 孤立森林相关统一前缀
     */
    public static final String SMART_DATA_PYOD_PREFIX = SMART_DATA_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "pyod:";

    /**
     * python 孤立森林 数据
     */
    public static final String SMART_DATA_PYOD_INPUT = SMART_DATA_PYOD_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "input";


    /**
     * python 孤立森林 配置
     */
    public static final String SMART_DATA_PYOD_PARAM = SMART_DATA_PYOD_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "param";


    /**
     * python 孤立森林 字典
     */
    public static final String SMART_DATA_PYOD_FIELD = SMART_DATA_PYOD_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + "field";

    /**
     * 返回值json默认删除key
     */
    public static final String DEFAULT_REMOVE_KEY = "index";

    /**
     * json data字段
     */
    public static final String KEY_DATA = "data";
    /**
     * json columns字段
     */
    public static final String KEY_COLUMN = "columns";
}
