package com.lvmoney.frame.ai.isolationforest.service;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.service
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.isolationforest.dto.GetIsolationForestFieldDto;
import com.lvmoney.frame.ai.isolationforest.dto.GetIsolationForestParamDto;
import com.lvmoney.frame.ai.isolationforest.ro.IsolationForestFieldRo;
import com.lvmoney.frame.ai.isolationforest.ro.IsolationForestInputRo;
import com.lvmoney.frame.ai.isolationforest.ro.IsolationForestParamRo;
import com.lvmoney.frame.ai.isolationforest.ro.item.IsolationForestField;
import com.lvmoney.frame.ai.isolationforest.ro.item.IsolationForestParam;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 10:33
 */
public interface IsolationForestService {
    /**
     * 保存 不同预测 孤立森林的配置到redis
     *
     * @param isolationForestParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean saveIsolationForestParam(IsolationForestParamRo isolationForestParamRo);

    /**
     * 获得指定预测 孤立算法的参数
     *
     * @param getIsolationForestParamDto:
     * @throws
     * @return: com.lvmoney.frame.ai.isolationforest.ro.item.IsolationForestParam
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:46
     */
    IsolationForestParam GetIsolationForestParam(GetIsolationForestParamDto getIsolationForestParamDto);


    /**
     * 保存 不同预测 孤立森林的字段到redis
     *
     * @param isolationForestFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean saveIsolationForestField(IsolationForestFieldRo isolationForestFieldRo);

    /**
     * 获得指定预测 孤立算法的字段
     *
     * @param getIsolationForestFieldDto:
     * @throws
     * @return: com.lvmoney.frame.ai.isolationforest.ro.item.IsolationForestField
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    IsolationForestField GetIsolationForestField(GetIsolationForestFieldDto getIsolationForestFieldDto);


    /**
     * 保存 不同预测 孤立森林数据入库
     *
     * @param isolationForestInputRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 15:17
     */
    boolean saveIsolationForestInput(IsolationForestInputRo isolationForestInputRo);


}
