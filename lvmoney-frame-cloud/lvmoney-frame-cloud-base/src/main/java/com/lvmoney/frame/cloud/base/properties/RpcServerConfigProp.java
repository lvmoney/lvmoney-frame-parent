/**
 * 描述:
 * 包名:com.chaoqi.springboot_shiro_redis.properties
 * 版本信息: 版本1.0
 * 日期:2019年1月6日  下午4:15:17
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.cloud.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2019年1月6日 下午4:15:17
 */
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "rpc")
@Data
public class RpcServerConfigProp {
    private Map server;
}
