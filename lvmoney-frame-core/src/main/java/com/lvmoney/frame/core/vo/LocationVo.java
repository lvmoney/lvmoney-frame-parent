/**
 * 描述:
 * 包名:com.lvmoney.common.util.ro
 * 版本信息: 版本1.0
 * 日期:2018年11月6日  下午2:23:16
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.core.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年11月6日 下午2:23:16
 */

public class LocationVo implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -7580422466945968660L;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 经度
     */
    private String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationVo [lat=" + lat + ", lon=" + lon + "]";
    }

}
