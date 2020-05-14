package com.zhy.frame.profile.entity;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.entity
 * 版本信息: 版本1.0
 * 日期:2019/9/10
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/10 15:29
 */
@Data
@TableName("t_address")
public class Address extends Model<Address> {
    private String id;
    private String code;
    private String name;
    private String pid;
    private Integer type;
    private Integer lit;
}
