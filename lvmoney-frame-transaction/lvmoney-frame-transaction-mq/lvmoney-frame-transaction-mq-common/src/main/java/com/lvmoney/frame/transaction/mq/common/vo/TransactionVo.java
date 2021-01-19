package com.lvmoney.frame.transaction.mq.common.vo; /**
 * 描述:
 * 包名:PACKAGE_NAME
 * 版本信息: 版本1.0
 * 日期:2020/11/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.transaction.mq.common.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/12 15:42
 */

@Data
public class TransactionVo<T> implements Serializable {
    /**
     * 随机值
     */
    private String uuid;
    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private T data;
    /**
     * 状态
     */
    private StatusEnum statusEnum;
    /**
     * 时间
     */
    private Long date;
}
