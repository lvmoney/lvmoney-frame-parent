package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.common.util
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.regex.Pattern;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/8/20 9:01
 */
public class IpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

    private static final int IP_LENGTH_MAX = 15;

    private static final int SERVERPORT_443 = 443;
    private static final int SERVERPORT_80 = 80;

    /**
     * @describe:判断ip是否属于某个网段
     * @param: [network, mask]
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * 2019/8/20 9:06
     */
    public static boolean isInRange(String network, String mask) {
        String[] networkips = network.split("\\.");
        int ipAddr = (Integer.parseInt(networkips[0]) << 24)
                | (Integer.parseInt(networkips[1]) << 16)
                | (Integer.parseInt(networkips[2]) << 8)
                | Integer.parseInt(networkips[3]);
        int type = Integer.parseInt(mask.replaceAll(".*/", ""));
        int mask1 = 0xFFFFFFFF << (32 - type);
        String maskIp = mask.replaceAll("/.*", "");
        String[] maskIps = maskIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)
                | (Integer.parseInt(maskIps[1]) << 16)
                | (Integer.parseInt(maskIps[2]) << 8)
                | Integer.parseInt(maskIps[3]);

        return (ipAddr & mask1) == (cidrIpAddr & mask1);
    }

    /**
     * @describe: 判断是不是ip
     * @param: [ip]
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * 2019/8/20 9:06
     */
    public static boolean isIp(String ip) {
        // ip的正则表达式
        String ipReg = "^(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.)(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.){2}([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))$";
        Pattern ipPattern = Pattern.compile(ipReg);
        boolean flag = ipPattern.matcher(ip).matches();
        return flag;
    }


    public static String getLocalhostIp() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
            String localip = ia.getHostAddress();
            return localip;
        } catch (Exception e) {
            LOGGER.error("获取本机ip地址报错:{}", e);
            return BaseConstant.LOCALHOST_IP;
        }
    }

    public static void main(String[] args) {
        System.out.println(isInRange("10.20.10.69", "10.20.10.69/0"));
        System.out.println(isInRange("10.20.10.69", "10.20.10.0/16"));
        System.out.println(isInRange("10.168.1.100", "10.168.0.224/21"));
        System.out.println(isInRange("192.168.0.1", "192.168.0.0/24"));
        System.out.println(isInRange("10.168.0.0", "10.168.0.0/32"));


    }

    /**
     * @describe: 获得request 的真实ip
     * @param: [request]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/8/20 10:08
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > IP_LENGTH_MAX) {
            String[] ips = ip.split(BaseConstant.CHARACTER_COMMA);
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }


    public static String getIpAddress(HttpServletRequest request) {
        String ip = "";
        ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || BaseConstant.IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getServerHost(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        Integer serverPort = request.getServerPort();
        boolean useedPort = serverPort == SERVERPORT_80 || serverPort == SERVERPORT_443;
        if (serverPort != null && useedPort) {
            return scheme + "://" + serverName;
        } else {
            return scheme + "://" + serverName + ":" + serverPort;
        }
    }
}
