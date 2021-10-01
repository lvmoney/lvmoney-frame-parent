package com.lvmoney.frame.oss.common.service;/**
 * 描述:
 * 包名:com.lvmoney.platform.oss.common.service
 * 版本信息: 版本1.0
 * 日期:2021/7/8
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/8 10:56
 */
public interface OssBeanService {
    /**
     * 通过name 获得bean
     *
     * @param type:
     * @throws
     * @return: com.lvmoney.frame.oss.common.service.OssService
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/8 11:34
     */
    OssService getOssHandService(String type);
}
