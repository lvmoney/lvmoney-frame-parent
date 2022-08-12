package com.lvmoney.frame.blockchain.webase.trans.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.trans.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 19:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallAo implements Serializable {
    private static final long serialVersionUID = -5021217071562991326L;
    /**
     * 群组编号
     */
    private Long groupId;
    /**
     * 部署业务流水号
     */
    private String uuidDeploy;
    /**
     * 合约地址
     */
    private String contractAddress;
    /**
     * 合约Abi
     */
    private List contractAbi;
    /**
     * 调用方法名
     */
    private String funcName;
    /**
     * 方法参数
     */
    private List<String> funcParam;
}
