package com.zhy.frame.db.sharding.base.service.impl;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/1/7
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.util.ConsistentHashVirtualNodeUtil;
import com.zhy.frame.db.sharding.base.constant.DbConstant;
import com.zhy.frame.db.sharding.base.constant.TableConstant;
import com.zhy.frame.db.sharding.base.dao.UserDao;
import com.zhy.frame.db.sharding.base.po.MemberUser;
import com.zhy.frame.db.sharding.base.po.User;
import com.zhy.frame.db.sharding.base.service.UserService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.zhy.frame.base.core.constant.BaseConstant.DASH_LINE;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/7 15:56
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int saveUser(User user) {
        try (HintManager hintManager = HintManager.getInstance()) {
            Map<String, List<String>> tableDb = new HashMap() {{
                put("db-2081755213", new ArrayList() {{
                    add("user_1402699136");
                    add("user_1843098224");
                    add("user_205617636");
                    add("user_577140694");
                }});
                put("db-327186896", new ArrayList() {{
                    add("user_1402699136");
                    add("user_1843098224");
                    add("user_205617636");
                    add("user_577140694");
                }});
            }};
            int dbNum = ThreadLocalRandom.current().nextInt(2);
            int serverNameHash = ConsistentHashVirtualNodeUtil.getHash(DbConstant.DB_NAME_DB + dbNum);
            String dbName = DbConstant.DB_NAME_DB + DASH_LINE + serverNameHash;
            List<String> tableList = tableDb.get(dbName);
            String tableName = tableList.get(ThreadLocalRandom.current().nextInt(tableList.size()));
            hintManager.addDatabaseShardingValue(TableConstant.TABLE_NAME_USER, dbName);
            hintManager.addTableShardingValue(TableConstant.TABLE_NAME_USER, tableName);


            return userDao.insert(user);
        }

    }

    @Override
    public User getUserById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public int updateUserById(String id) {
        return userDao.updateNum(id);
    }

    @Override
    public int deleteUserById(String id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<MemberUser> seletMemberByUserId(String id) {
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.addDatabaseShardingValue("member", "");
            hintManager.addTableShardingValue("member", "");
            return userDao.selectMemberByUserId(id);
        }
    }


}
