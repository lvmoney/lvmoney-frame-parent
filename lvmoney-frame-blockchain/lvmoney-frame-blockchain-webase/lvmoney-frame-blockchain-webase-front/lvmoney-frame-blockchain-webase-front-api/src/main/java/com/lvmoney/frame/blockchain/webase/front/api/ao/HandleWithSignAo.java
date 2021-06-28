package com.lvmoney.frame.blockchain.webase.front.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSONArray;
import com.lvmoney.frame.blockchain.webase.front.api.ao.item.Abi;
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
public class HandleWithSignAo implements Serializable {
    private static final long serialVersionUID = 8893563125068154888L;
    /**
     * 群组ID
     */
    private Long groupId;
    /**
     * 用户编号
     */
    private String signUserId;
    /**
     * 合约编译后生成的abi文件内容
     */
    private JSONArray contractAbi;
    /**
     * 合约地址
     */
    private String contractAddress;
    /**
     * 方法名
     */
    private String funcName;
    /**
     * 方法参数
     */
    private List<String> funcParam;
    /**
     * 是否使用cns调用
     */
    private Boolean useCns;
    /**
     * cns名称
     */
    private String cnsName;
    /**
     * cns版本
     */
    private String version;
}
