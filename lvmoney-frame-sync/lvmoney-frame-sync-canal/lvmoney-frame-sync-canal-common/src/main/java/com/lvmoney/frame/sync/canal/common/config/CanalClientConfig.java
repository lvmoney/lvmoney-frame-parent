package com.lvmoney.frame.sync.canal.common.config;

import com.lvmoney.frame.sync.canal.common.client.SimpleCanalClient;
import com.lvmoney.frame.sync.canal.common.properties.CanalProp;
import com.lvmoney.frame.sync.canal.common.spring.HandListenerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class CanalClientConfig {
    /**
     * 记录日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CanalClientConfig.class);

    /**
     * canal 配置
     */
    @Autowired
    private CanalProp canalProp;
    @Autowired
    HandListenerContext handListenerContext;

    /**
     * 返回 canal 的客户端
     *
     * @throws
     * @return: com.lvmoney.frame.sync.canal.common.client.SimpleCanalClient
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/15 17:31
     */
    @Bean
    private SimpleCanalClient canalClient() {
        LOGGER.info("初始化canal服务");
        //连接 canal 客户端
        SimpleCanalClient canalClient = new SimpleCanalClient(canalProp, handListenerContext);
        LOGGER.info("开始连接cannal服务");
        //开启 canal 客户端
        canalClient.start();
        LOGGER.info("连接canal服务成功");
        //返回结果
        return canalClient;
    }
}
