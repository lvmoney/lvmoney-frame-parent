package com.lvmoney.frame.transaction.mq.customer.aspect;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.transaction.mq.customer.service.TransactionCustomerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Aspect
@Component
public class HandleTransactionAspect {
    @Autowired
    TransactionCustomerService transactionCustomerService;

    /***
     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
    @Pointcut("@annotation(com.lvmoney.frame.transaction.mq.customer.annotations.HandleTransaction)")
    public void customerAspect() {
    }

    /**
     * @describe: 在进入消息处理操作之前直接将数据写入redis
     * 如果redis已经存在这个key直接跳出不再执行后续方法
     * @param: [joinPoint]
     * @return: ResultData
     * @author： lvmoney /四川******科技有限公司
     * 2019/2/1
     */
    @Around("customerAspect()")
    public void receiveTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        MessageVo messageVo = getCustomerParam(joinPoint);
        if (transactionCustomerService.isHandle(messageVo)) {
            transactionCustomerService.receiveTransaction(messageVo);
            joinPoint.proceed();
        } else {
            return;
        }
    }

    /**
     * 消息处理完成后，修改redis数据状态
     * 如果处理过程中报错，将不会进入该方法
     * 更改状态的时候首先判断前序状态存在的redis数据是否存在，若不存在直接返回
     *
     * @param joinPoint:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/25 16:48
     */

    @AfterReturning(value = "customerAspect()")
    public void processTransaction(JoinPoint joinPoint) throws Throwable {
        transactionCustomerService.processTransaction(getCustomerParam(joinPoint));
    }

    /**
     * 获得 消息消费者收到的参数
     *
     * @param point:
     * @throws
     * @return: com.lvmoney.frame.mq.common.vo.MessageVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/24 11:22
     */
    private MessageVo getCustomerParam(JoinPoint point) throws Exception {
        Object[] args = point.getArgs();
        MessageVo messageVo = (MessageVo) args[0];
        return messageVo;
    }


}
