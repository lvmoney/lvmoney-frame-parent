/**
 * 描述:
 * 包名:com.lvmoney.mongo.handler
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午2:20:05
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.oss.gridfs.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2019年1月10日 下午2:20:05
 */
@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.database}")
    String mongoDb;

    @Bean
    public GridFSBucket getGridFsBucket(MongoClient mongoClient) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(mongoDb);
        GridFSBucket bucket = GridFSBuckets.create(mongoDatabase);
        return bucket;
    }
}
