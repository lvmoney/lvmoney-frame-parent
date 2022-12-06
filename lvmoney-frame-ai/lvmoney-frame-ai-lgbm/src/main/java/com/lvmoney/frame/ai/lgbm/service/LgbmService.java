package com.lvmoney.frame.ai.lgbm.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lgbm.service
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.lgbm.ro.LgbmInputRo;
import com.lvmoney.frame.ai.lgbm.ro.LgbmMultivariableFieldRo;
import com.lvmoney.frame.ai.lgbm.ro.LgbmParamRo;
import com.lvmoney.frame.ai.lgbm.ro.LgbmUnivariateFieldRo;
import com.lvmoney.frame.ai.lgbm.ro.item.LgbmModelBestParams;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/16 13:47
 */
public interface LgbmService {

    /**
     * 保存 不同预测 数据
     *
     * @param lgbmInputRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 15:17
     */
    boolean saveLgbmInput(LgbmInputRo lgbmInputRo);


    /**
     * 保存 不同预测 lgbm的配置到redis
     *
     * @param lgbmParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean saveMultivariableLgbmParam(LgbmParamRo lgbmParamRo);


    /**
     * 保存 不同预测 lgbm的配置到redis
     *
     * @param lgbmParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean saveLgbmUnivariateParam(LgbmParamRo lgbmParamRo);


    /**
     * 保存 不同预测 lgbm的字段到redis
     *
     * @param lgbmMultivariableFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean saveLgbmMultivariableField(LgbmMultivariableFieldRo lgbmMultivariableFieldRo);


    /**
     * 保存 不同预测 lgbm的字段到redis
     *
     * @param lgbmUnivariateFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean saveLgbmUnivariateField(LgbmUnivariateFieldRo lgbmUnivariateFieldRo);

    /**
     * 获取执行预测的model最优参数 单变量
     *
     * @param predictId:
     * @throws
     * @return: LgbmModelBestParams
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/21 11:27
     */

    LgbmModelBestParams getUnivariateByPredictId(String predictId);


    /**
     * 获取执行预测的model最优参数 多变量
     *
     * @param predictId:
     * @throws
     * @return: LgbmModelBestParams
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/21 11:27
     */

    LgbmModelBestParams getMultivariableByPredictId(String predictId);

}
