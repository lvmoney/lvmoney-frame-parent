package com.lvmoney.frame.ai.pyod.service;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.service
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.pyod.dto.GetPyodFieldDto;
import com.lvmoney.frame.ai.pyod.dto.GetPyodParamDto;
import com.lvmoney.frame.ai.pyod.ro.PyodFieldRo;
import com.lvmoney.frame.ai.pyod.ro.PyodInputRo;
import com.lvmoney.frame.ai.pyod.ro.PyodParamRo;
import com.lvmoney.frame.ai.pyod.ro.item.PyodFieldItem;
import com.lvmoney.frame.ai.pyod.ro.item.PyodParamItem;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 10:33
 */
public interface PyodService {
    /**
     * 保存 不同预测 孤立森林的配置到redis
     *
     * @param pyodParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean savePyodParam(PyodParamRo pyodParamRo);

    /**
     * 获得指定预测 孤立算法的参数
     *
     * @param getPyodParamDto:
     * @throws
     * @return: PyodParamItem
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:46
     */
    PyodParamItem getPyodParam(GetPyodParamDto getPyodParamDto);


    /**
     * 保存 不同预测 孤立森林的字段到redis
     *
     * @param pyodFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean savePyodField(PyodFieldRo pyodFieldRo);

    /**
     * 获得指定预测 孤立算法的字段
     *
     * @param getPyodFieldDto:
     * @throws
     * @return: PyodFieldItem
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    PyodFieldItem getPyodField(GetPyodFieldDto getPyodFieldDto);


    /**
     * 保存 不同Pyod数据入库
     *
     * @param pyodInputRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 15:17
     */
    boolean savePyodInput(PyodInputRo pyodInputRo);


}
