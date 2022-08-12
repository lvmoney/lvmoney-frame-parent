package com.lvmoney.frame.db.mysql.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.db.mysql.base.service.UidService;
import org.springframework.stereotype.Service;
import com.xfvape.uid.UidGenerator;

import javax.annotation.Resource;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 16:52
 */
@Service
public class UidServiceImpl implements UidService {

    @Resource(name = "cachedUidGenerator")
    private UidGenerator uidGenerator;

    @Override
    public long getUid() {
        return uidGenerator.getUID();
    }

}
