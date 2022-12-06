package com.lvmoney.frame.ai.adtk.service;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.service
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.adtk.dto.GetAdtkFieldDto;
import com.lvmoney.frame.ai.adtk.dto.GetAdtkParamDto;
import com.lvmoney.frame.ai.adtk.ro.AdtkFieldRo;
import com.lvmoney.frame.ai.adtk.ro.AdtkInputRo;
import com.lvmoney.frame.ai.adtk.ro.AdtkParamRo;
import com.lvmoney.frame.ai.adtk.ro.item.AdtkFieldItem;
import com.lvmoney.frame.ai.adtk.ro.item.AdtkParamItem;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 10:33
 */
public interface AdtkService {
    /**
     * 保存 不同预测 孤立森林的配置到redis
     *
     * @param pyodParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean saveAdtkParam(AdtkParamRo pyodParamRo);

    /**
     * 获得指定预测 孤立算法的参数
     *
     * @param getAdtkParamDto:
     * @throws
     * @return: AdtkParamItem
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:46
     */
    AdtkParamItem getAdtkParam(GetAdtkParamDto getAdtkParamDto);


    /**
     * 保存 不同预测 孤立森林的字段到redis
     *
     * @param pyodFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean saveAdtkField(AdtkFieldRo pyodFieldRo);

    /**
     * 获得指定预测 孤立算法的字段
     *
     * @param getAdtkFieldDto:
     * @throws
     * @return: AdtkFieldItem
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    AdtkFieldItem getAdtkField(GetAdtkFieldDto getAdtkFieldDto);


    /**
     * 保存 不同Adtk数据入库
     *
     * @param pyodInputRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 15:17
     */
    boolean saveAdtkInput(AdtkInputRo pyodInputRo);


}
