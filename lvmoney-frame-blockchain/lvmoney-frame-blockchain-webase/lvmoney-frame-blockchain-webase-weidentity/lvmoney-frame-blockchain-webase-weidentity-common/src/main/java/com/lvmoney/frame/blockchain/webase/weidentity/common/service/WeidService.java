package com.lvmoney.frame.blockchain.webase.weidentity.common.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.common.service
 * 版本信息: 版本1.0
 * 日期:2021/7/2
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.common.ro.WeidRo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/2 10:46
 */
public interface WeidService {
    /**
     * 存储签名相关信息到redis
     *
     * @param weidRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/2 10:58
     */
    void saveSignRo2redis(WeidRo weidRo);

    /**
     * 通过随机值获得签名相关数据
     *
     * @param nonce:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.weidentity.common.ro.WeidRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/2 10:59
     */
    WeidRo getByNonce(String nonce);
}
