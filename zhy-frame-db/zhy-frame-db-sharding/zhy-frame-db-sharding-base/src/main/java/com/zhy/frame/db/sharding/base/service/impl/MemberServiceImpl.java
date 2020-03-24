package com.zhy.frame.db.sharding.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.mysql.subdb.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/1/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.util.ConsistentHashVirtualNodeUtil;
import com.zhy.frame.db.sharding.base.constant.DbConstant;
import com.zhy.frame.db.sharding.base.constant.TableConstant;
import com.zhy.frame.db.sharding.base.dao.MemberDao;
import com.zhy.frame.db.sharding.base.po.Member;
import com.zhy.frame.db.sharding.base.service.MemberService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/8 15:10
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public int saveMember(Member member) {

        try (HintManager hintManager = HintManager.getInstance()) {
            Map<String, List<String>> tableDb = new HashMap() {{
                put("db-2081755213", new ArrayList() {{
                    add("member_1123723861");
                    add("member_745725798");
                }});
                put("db-327186896", new ArrayList() {{
                    add("member_1123723861");
                    add("member_745725798");
                }});
            }};
            int dbNum = ThreadLocalRandom.current().nextInt(2);
            int serverNameHash = ConsistentHashVirtualNodeUtil.getHash(DbConstant.DB_NAME_DB + dbNum);
            String dbName = DbConstant.DB_NAME_DB + "-" + serverNameHash;
            List<String> tableList = tableDb.get(dbName);
            String tableName = tableList.get(ThreadLocalRandom.current().nextInt(tableList.size()));
            hintManager.addDatabaseShardingValue(TableConstant.TABLE_NAME_MEMBER, dbName);
            hintManager.addTableShardingValue(TableConstant.TABLE_NAME_MEMBER, tableName);
            return memberDao.insert(member);
        }
    }
}
