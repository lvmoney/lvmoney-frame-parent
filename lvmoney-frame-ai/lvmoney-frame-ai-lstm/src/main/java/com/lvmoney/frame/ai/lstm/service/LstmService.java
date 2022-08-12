package com.lvmoney.frame.ai.lstm.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.service
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.lstm.ro.LstmMultivariableFieldRo;
import com.lvmoney.frame.ai.lstm.ro.LstmInputRo;
import com.lvmoney.frame.ai.lstm.ro.LstmParamRo;
import com.lvmoney.frame.ai.lstm.ro.LstmUnivariateFieldRo;
import com.lvmoney.frame.ai.lstm.ro.item.LstmModelBestParams;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/16 13:47
 */
public interface LstmService {

    /**
     * 保存 不同预测 数据
     *
     * @param lstmInputRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 15:17
     */
    boolean saveLstmInput(LstmInputRo lstmInputRo);


    /**
     * 保存 不同预测 lstm的配置到redis
     *
     * @param lstmParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean saveMultivariableLstmParam(LstmParamRo lstmParamRo);


    /**
     * 保存 不同预测 lstm的配置到redis
     *
     * @param lstmParamRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 11:09
     */
    boolean saveLstmUnivariateParam(LstmParamRo lstmParamRo);


    /**
     * 保存 不同预测 lstm的字段到redis
     *
     * @param lstmMultivariableFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean saveLstmMultivariableField(LstmMultivariableFieldRo lstmMultivariableFieldRo);


    /**
     * 保存 不同预测 lstm的字段到redis
     *
     * @param lstmUnivariateFieldRo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/12 13:46
     */
    boolean saveLstmUnivariateField(LstmUnivariateFieldRo lstmUnivariateFieldRo);

    /**
     * 获取执行预测的model最优参数 单变量
     *
     * @param predictId:
     * @throws
     * @return: com.lvmoney.frame.ai.lstm.ro.item.LstmModelBestParams
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/21 11:27
     */

    LstmModelBestParams getUnivariateByPredictId(String predictId);


    /**
     * 获取执行预测的model最优参数 多变量
     *
     * @param predictId:
     * @throws
     * @return: com.lvmoney.frame.ai.lstm.ro.item.LstmModelBestParams
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/21 11:27
     */

    LstmModelBestParams getMultivariableByPredictId(String predictId);

}
