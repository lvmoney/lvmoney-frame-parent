package com.lvmoney.frame.demo.seata.provider.service;

/**
 * Created by Administrator on 2019/6/26.
 */


import com.lvmoney.frame.demo.seata.provider.po.User;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface UserService {
    /**
     * 保存用户
     *
     * @param user: 用户
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:57
     */
    int insertUser(User user);
}
