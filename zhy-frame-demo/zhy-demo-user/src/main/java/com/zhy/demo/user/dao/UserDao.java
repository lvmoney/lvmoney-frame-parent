package com.zhy.demo.user.dao;/**
 * 描述:
 * 包名:com.zhy.tmc.user.dao
 * 版本信息: 版本1.0
 * 日期:2019/11/25
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.demo.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/25 8:58
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * 通过用户名和密码获得唯一的用户
     *
     * @param username:
     * @param password:
     * @throws
     * @return: User
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/25 9:09
     */
    @Select("<script>" +
            "select u.uid,u.username,u.password from user u " +
            "where u.username=#{username} and u.password=#{password}" +
            "</script>")
    User selectByLoginUser(@Param("username") String username, @Param("password") String password);
}
