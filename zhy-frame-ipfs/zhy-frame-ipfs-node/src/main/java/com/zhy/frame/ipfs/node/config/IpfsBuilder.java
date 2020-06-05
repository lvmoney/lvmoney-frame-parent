package com.zhy.frame.ipfs.node.config;/**
 * 描述:
 * 包名:com.zhy.frame.ipfs.node.handler
 * 版本信息: 版本1.0
 * 日期:2020/5/7
 * Copyright XXXXXX科技有限公司
 */


import io.ipfs.api.IPFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/7 9:11
 */
@Configuration
public class IpfsBuilder {
    @Value("${frame.ipfs.adress:/ip4/127.0.0.1/tcp/5001}")
    private String address;

    /**
     * 手动创建ipfs
     *
     * @param multiAddr:
     * @throws
     * @return: io.ipfs.api.IPFS
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 9:17
     */
    public IPFS build(String multiAddr) {
        return new IPFS(multiAddr);
    }

    /**
     * 通过配置创建ipfs
     *
     * @throws
     * @return: io.ipfs.api.IPFS
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 9:17
     */
    public IPFS build() {
        return new IPFS(address);
    }
}
