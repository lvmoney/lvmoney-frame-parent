package com.lvmoney.frame.transaction.mq.customer.service;/**
 * 描述:
 * 包名:mq.service
 * 版本信息: 版本1.0
 * 日期:2020/11/13
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.transaction.mq.common.ro.TransactionRo;
import com.lvmoney.frame.transaction.mq.common.vo.TransactionVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/13 15:35
 */
public interface TransactionCustomerService {
    /**
     * 事务信息同步到redis
     * redis key=前缀+应用名称+code+状态
     * code 等于请求实体排序拼接后的base64
     *
     * @param transactionRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/13 15:40
     */
    void transaction2Redis(TransactionRo transactionRo);

    /**
     * 事务
     *
     * @param messageVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/22 16:13
     */

    void receiveTransaction(MessageVo messageVo);

    /**
     * 发布事务，并做状态变更
     *
     * @param messageVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/23 15:01
     */

    void processTransaction(MessageVo messageVo);

    /**
     * 判断数据是否已经存在，防止重复信息.
     * 校验状态是否正确
     * 只有都通过才会继续执行
     *
     * @param messageVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/26 9:13
     */

    boolean isHandle(MessageVo messageVo);

}
