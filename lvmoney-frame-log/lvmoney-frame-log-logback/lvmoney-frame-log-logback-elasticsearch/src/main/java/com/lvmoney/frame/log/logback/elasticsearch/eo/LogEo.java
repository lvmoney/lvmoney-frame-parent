package com.lvmoney.frame.log.logback.elasticsearch.eo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.elasticsearch.mo
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.log.logback.elasticsearch.constant.LogbackEsConstant;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 17:47
 */
@Data
@Document(indexName = LogbackEsConstant.LOG_INDEX_NAME, type = LogbackEsConstant.LOG_TYPE, shards = 1, replicas = 0)
public class LogEo implements Serializable {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 系统名称
     */
    private String sysName;
    /**
     * 日志类型
     */
    private String level;
    /**
     * 信息
     */
    private String msg;

    /**
     * 线程
     */
    private String thread;

    /**
     * 执行时间
     */
    private String exeDate;

    /**
     * 创建时间
     */
    private String createDate;
}
