package com.zhy.demo.user.entity;/**
 * 描述:
 * 包名:com.zhy.tmc.user.entity
 * 版本信息: 版本1.0
 * 日期:2019/11/25
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/25 8:55
 */
@Data
@TableName("user")
public class User {

    @TableId(value = "uid", type = IdType.ID_WORKER_STR)
    private String uid;
    private String username;
    private String password;
}
