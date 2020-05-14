package com.zhy.frame.workflow.activiti.dao;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright xxxx科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.frame.workflow.activiti.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询所有用户
     *
     * @throws
     * @return: java.util.List<com.zhy.activiti.entity.User>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:55
     */
    @Select("select * from user")
    List<User> selectAll();

    /**
     * 根据名字获得账户
     *
     * @param name: 账户名
     * @throws
     * @return: java.util.List<com.zhy.activiti.entity.User>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:56
     */
    @Select("select * from user where name = #{name}")
    List<User> selectByName(String name);
}
