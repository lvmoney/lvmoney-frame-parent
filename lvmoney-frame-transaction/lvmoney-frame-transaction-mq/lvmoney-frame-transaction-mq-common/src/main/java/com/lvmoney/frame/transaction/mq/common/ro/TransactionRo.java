package com.lvmoney.frame.transaction.mq.common.ro;/**
 * 描述:
 * 包名:mq.ro
 * 版本信息: 版本1.0
 * 日期:2020/11/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.transaction.mq.common.vo.TransactionVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/12 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRo implements Serializable {
    /**
     * 数据
     */
    private TransactionVo data;
    /**
     * 超时时间
     */
    private Long expired;
}
