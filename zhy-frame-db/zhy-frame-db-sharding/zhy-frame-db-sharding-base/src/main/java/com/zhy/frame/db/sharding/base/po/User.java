package com.zhy.frame.db.sharding.base.po;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.entity
 * 版本信息: 版本1.0
 * 日期:2019/9/10
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/10 15:29
 */
@Data
public class User extends Model<User> {
    private Long id;
    private String name;
    private String phone;
    private String code;
    private Integer num;
}