package com.zhy.frame.db.sharding.base.dao;/**
 * 描述:
 * 包名:com.lvmoney.mysql.subdb.dao
 * 版本信息: 版本1.0
 * 日期:2019/9/10
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.frame.db.sharding.base.po.MemberUser;
import com.zhy.frame.db.sharding.base.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/10 15:33
 */
public interface UserDao extends BaseMapper<User> {
    @Update("update user as a set a.num=99 where a.id=#{id}")
    int updateNum(@Param("id") String id);

    @Select("select  b.name as memberName,b.code as memberCode,a.id as userId,a.name as userName,a.code as userCode, a.num as userNum from user as a left join member as b on b.uid=a.id where a.id=#{id}")
    List<MemberUser> selectMemberByUserId(@Param("id") String uid);
}
