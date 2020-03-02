package com.zhy.frame.authentication.oauth2.center.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */

@TableName("third_party_account")
@Data
public class ThirdPartyAccount extends Model<ThirdPartyAccount> {

    private static final long serialVersionUID = -6669281176772367442L;
    /**
     *用于记录用户在哪个子系统进行的注册
     */
    private String clientId;
    private String thirdParty;
    private String thirdPartyAccountId;
    /**
     * 多种登陆方式合并账号使用
     */
    private String accountOpenCode;
}
