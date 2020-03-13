package com.zhy.frame.authentication.oauth2.center.db.entity;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.db.entity
 * 版本信息: 版本1.0
 * 日期:2019/8/7
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/7 10:12
 */
@Data
public class UserRole extends Model<UserRole> {
    private String username;
    private String code;
}
