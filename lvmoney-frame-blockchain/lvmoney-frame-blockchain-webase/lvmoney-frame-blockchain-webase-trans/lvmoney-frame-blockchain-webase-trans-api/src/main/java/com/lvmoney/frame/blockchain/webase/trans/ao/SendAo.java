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
 * @version:v1.0 2021/6/24 18:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendAo implements Serializable {
    private static final long serialVersionUID = 8379221168103089213L;
    /**
     * 群组编号
     */
    private Integer groupId;
    /**
     * 交易业务流水号
     */
    private String uuidStateless;
    /**
     * 部署业务流水号
     */
    private String uuidDeploy;
    /**
     * 签名类型 0-本地配置私钥签名，1-本地随机私钥签名，2-调用WeBASE-Sign签名
     */
    private Integer signType;
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
    /**
     * 签名用户编号 signType为2时必填
     */
    private String signUserId;
}
