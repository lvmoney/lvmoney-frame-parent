package com.zhy.frame.authentication.oauth2.center.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.frame.authentication.oauth2.center.db.entity.UserAccount;
import org.apache.ibatis.annotations.Select;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface UserAccountDao extends BaseMapper<UserAccount> {
    /**
     * 获取用户信息
     *
     * @param username: 用户名
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.db.entity.UserAccount
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:52
     */
    @Select("select * from user_account u where u.username=#{username}")
    UserAccount findByUsername(String username);

}
