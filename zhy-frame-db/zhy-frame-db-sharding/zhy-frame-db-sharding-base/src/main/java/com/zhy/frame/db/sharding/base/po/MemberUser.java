package com.zhy.frame.db.sharding.base.po;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.entity
 * 版本信息: 版本1.0
 * 日期:2020/1/8
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/8 15:34
 */
@Data
public class MemberUser implements Serializable {
    private String memberName;
    private String memberCode;
    private String userId;
    private String userName;
    private String userCode;
    private Integer userNum;
}
