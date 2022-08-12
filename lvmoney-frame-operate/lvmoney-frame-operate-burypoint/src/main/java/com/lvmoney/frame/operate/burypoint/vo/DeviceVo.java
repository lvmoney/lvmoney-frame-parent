package com.lvmoney.frame.operate.burypoint.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.operate.burypoint.vo
 * 版本信息: 版本1.0
 * 日期:2020/7/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/14 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceVo implements Serializable {
    private static final long serialVersionUID = -7989691616980240394L;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 屏幕高度
     */
    private String screenHeight;
    /**
     * 屏幕宽
     */
    private String screenWeight;
    /**
     * 浏览器名字
     */
    private String browser;
    /**
     * 浏览器版本
     */
    private String browserVersion;
    /**
     * 用户代理
     */
    private String userAgent;
    /**
     * 当前埋点采用的sdk的类型，入ios，android
     */
    private String lib;
    /**
     * 当前sdk版本
     */
    private String libVersion;

    /**
     * 用户公网ip
     */
    private String ip;


    /**
     * 国家
     */
    private String country;


    /**
     * 省
     */
    private String province;


    /**
     * 城市
     */
    private String city;

}
