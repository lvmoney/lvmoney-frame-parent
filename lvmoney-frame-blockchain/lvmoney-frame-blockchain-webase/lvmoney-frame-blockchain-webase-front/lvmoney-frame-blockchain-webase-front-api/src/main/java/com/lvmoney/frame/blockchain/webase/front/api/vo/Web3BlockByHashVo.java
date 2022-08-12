package com.lvmoney.frame.blockchain.webase.front.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.front.api.vo.item.Transactions;
import com.lvmoney.frame.blockchain.webase.front.api.vo.item.Signature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/6/23 18:55  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Web3BlockByHashVo implements Serializable {
    /**
     *
     */
    private Long number;

    /**
     *
     */
    private String hash;
    /**
     *
     */
    private String logsBloom;
    /**
     *
     */
    private String transactionsRoot;
    /**
     *
     */
    private String receiptsRoot;
    /**
     *
     */
    private String dbHash;
    /**
     *
     */
    private String stateRoot;
    /**
     *
     */
    private String sealer;
    /**
     *
     */
    private List<String> sealerList;
    /**
     *
     */
    private List<String> extraData;
    /**
     *
     */
    private String gasLimit;
    /**
     *
     */
    private String gasUsed;
    /**
     *
     */
    private String timestamp;
    /**
     *
     */
    private List<Signature> signatureList ;
    /**
     *
     */
    private List<Transactions> transactions;

}
