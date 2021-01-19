package com.lvmoney.frame.transaction.mq.provider.service;/**
 * 描述:
 * 包名:mq.service
 * 版本信息: 版本1.0
 * 日期:2020/11/13
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.transaction.mq.common.ro.TransactionRo;
import com.lvmoney.frame.transaction.mq.provider.dto.TransactionDto;
import com.lvmoney.frame.transaction.mq.common.vo.TransactionVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/13 15:35
 */
public interface TransactionProviderService {
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
     * @param transactionRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/22 16:13
     */

    void newTransaction(TransactionRo transactionRo);

    /**
     * 发布事务，并做状态变更
     *
     * @param transactionRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/23 15:01
     */

    void publishTransaction(TransactionRo transactionRo);

    /**
     * 发送消息,这里用在保存事务信息到redis同时发送消息到mq
     * 发送消息的时间和存到redis的时间是一样的
     * 后续如果有消息没有返送成功的的消息会通过其他机制重新发送。那么此时消息发送时间和redis中记录的事务时间就会不同
     *
     * @param transactionVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/22 16:35
     */
    boolean sendMsg(TransactionVo transactionVo);

    /**
     * 分布式事务
     *
     * @param transactionDto:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/23 15:05
     */

    void distributedTransaction(TransactionDto transactionDto);
}
