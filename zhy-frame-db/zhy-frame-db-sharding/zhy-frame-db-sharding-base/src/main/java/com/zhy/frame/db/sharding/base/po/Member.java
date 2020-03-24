package com.zhy.frame.db.sharding.base.po;/**
 * 描述:
 * 包名:com.lvmoney.mysql.subdb.po
 * 版本信息: 版本1.0
 * 日期:2020/1/8
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/8 15:07
 */
@Data
public class Member extends Model<Member> {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String code;
    private String uid;
}
