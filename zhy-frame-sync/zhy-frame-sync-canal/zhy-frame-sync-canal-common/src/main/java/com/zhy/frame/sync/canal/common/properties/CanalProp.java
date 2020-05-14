package com.zhy.frame.sync.canal.common.properties;
/**
 * 描述:
 * 包名:com.zhy.common.config
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


/**
 * @describe：从yml文件中获得配置数据前缀是：canal.server
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "canal.server")
public class CanalProp {

    /**
     * 线程池最大连接数
     */
    private int maxPoolSize;
    /**
     * 线程池初始化连接数
     */
    private int initPoolSize;

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getInitPoolSize() {
        return initPoolSize;
    }

    public void setInitPoolSize(int initPoolSize) {
        this.initPoolSize = initPoolSize;
    }

    /**
     * 配置信息
     */
    private Map<String, Instance> instances = new LinkedHashMap<>();

    /**
     * 返回实例
     *
     * @param
     * @return
     */
    public Map<String, Instance> getInstances() {
        return instances;
    }

    /**
     * 设置实例
     *
     * @param instances
     * @return
     */
    public void setInstances(Map<String, Instance> instances) {
        this.instances = instances;
    }

    /**
     * canal 配置类
     */
    public static class Instance {

        /**
         * 是否是集群模式
         */
        private boolean clusterEnabled;


        /**
         * zookeeper 地址
         */
        private Set<String> zookeeperAddress = new LinkedHashSet<>();

        /**
         * canal 服务器地址，默认是本地的环回地址
         */
        private String host = "127.1.1.1";

        /**
         * canal 服务设置的端口，默认 11111
         */
        private int port = 11111;

        /**
         * 集群 设置的用户名
         */
        private String userName = "";

        /**
         * 集群 设置的密码
         */
        private String password = "";

        /**
         * 批量从 canal 服务器获取数据的最多数目
         */
        private int batchSize = 1000;


        /**
         * 是否有过滤规则
         */
        private String filter;

        /**
         * 当错误发生时，重试次数
         */
        private int retryCount = 5;

        /**
         * 信息捕获心跳时间
         */
        private long acquireInterval = 1000;

        public Instance() {
        }

        public boolean isClusterEnabled() {
            return clusterEnabled;
        }

        public void setClusterEnabled(boolean clusterEnabled) {
            this.clusterEnabled = clusterEnabled;
        }

        public Set<String> getZookeeperAddress() {
            return zookeeperAddress;
        }

        public void setZookeeperAddress(Set<String> zookeeperAddress) {
            this.zookeeperAddress = zookeeperAddress;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(int batchSize) {
            this.batchSize = batchSize;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public int getRetryCount() {
            return retryCount;
        }

        public void setRetryCount(int retryCount) {
            this.retryCount = retryCount;
        }

        public long getAcquireInterval() {
            return acquireInterval;
        }

        public void setAcquireInterval(long acquireInterval) {
            this.acquireInterval = acquireInterval;
        }
    }
}
