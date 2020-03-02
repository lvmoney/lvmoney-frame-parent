package com.zhy.frame.authentication.oauth2.center.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.frame.authentication.oauth2.center.db.entity.ThirdPartyAccount;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface ThirdPartyAccountDao extends BaseMapper<ThirdPartyAccount> {
    /**
     * 查询三方账号信息
     *
     * @param thirdParty:          三方账号
     * @param thirdPartyAccountId: 三方账号
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.db.entity.ThirdPartyAccount
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:50
     */
    ThirdPartyAccount findByThirdPartyAndThirdPartyAccountId(String thirdParty, String thirdPartyAccountId);
}
