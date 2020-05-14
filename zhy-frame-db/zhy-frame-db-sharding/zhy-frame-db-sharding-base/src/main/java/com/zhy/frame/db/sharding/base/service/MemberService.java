package com.zhy.frame.db.sharding.base.service;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.service
 * 版本信息: 版本1.0
 * 日期:2020/1/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.db.sharding.base.po.Member;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/8 15:09
 */
public interface MemberService {
    int saveMember(Member member);
}
