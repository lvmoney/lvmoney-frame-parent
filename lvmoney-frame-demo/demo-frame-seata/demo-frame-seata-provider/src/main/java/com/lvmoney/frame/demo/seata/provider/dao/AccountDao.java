package com.lvmoney.frame.demo.seata.provider.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvmoney.frame.demo.seata.provider.po.Account;
import org.apache.ibatis.annotations.Update;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface AccountDao extends BaseMapper<Account> {
    /**
     * 更新用户金额
     *
     * @param userId: 用户id
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:12
     */
    @Update("update account as a set a.money = a.money-20 where a.user_id=#{userId}")
    int updateAccount(String userId);
}
