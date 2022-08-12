package com.lvmoney.frame.blockchain.webase.wallet.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.wallet.api.vo.item.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/23 20:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandleWithSignVo implements Serializable {
    private static final long serialVersionUID = -8284215788983989408L;
    /**
     *
     */
    private String transactionHash;
    /**
     *
     */
    private String transactionIndex;
    /**
     *
     */
    private String root;
    /**
     *
     */
    private String blockNumber;
    /**
     *
     */
    private String blockHash;
    /**
     *
     */
    private String from;
    /**
     *
     */
    private String to;
    /**
     *
     */
    private String gasUsed;
    /**
     *
     */
    private String contractAddress;
    /**
     *
     */
    private List<Log> logs;
    /**
     *
     */
    private String logsBloom;
    /**
     *
     */
    private String status;
    /**
     *
     */
    private String statusMsg;
    /**
     *
     */
    private String input;

    /**
     *
     */
    private String output;
    /**
     *
     */
    private String txProof;
    /**
     *
     */
    private String receiptProof;

    /**
     *
     */
    private String message;
    /**
     *
     */
    private Boolean statusOK;
}
