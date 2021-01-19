package com.lvmoney.demo.transaction.provider.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.controller
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.transaction.mq.common.vo.TransactionVo;
import com.lvmoney.frame.transaction.mq.provider.dto.TransactionDto;
import com.lvmoney.frame.transaction.mq.provider.service.TransactionProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 13:35
 */
@RestController
public class TransactionController {
    @Autowired
    TransactionProviderService transactionProviderService;

    @PostMapping(value = "frame/transaction/test")
    public ApiResult<String> testJwt() {

        TransactionVo transactionVo = new TransactionVo();

        MessageVo messageVo = new MessageVo();
        messageVo.setData(transactionVo.getData());
        messageVo.setMsgType("USER_DATA_TYPE");
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setData(messageVo);
        transactionDto.setType("USER_DATA_TYPE");
        transactionProviderService.distributedTransaction(transactionDto);
        return ApiResult.success("file");
    }
}
