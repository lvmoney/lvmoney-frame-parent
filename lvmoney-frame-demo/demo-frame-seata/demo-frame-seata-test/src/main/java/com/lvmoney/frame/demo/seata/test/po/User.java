package com.lvmoney.frame.demo.seata.test.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
@TableName("user")
public class User extends Model<User> {
    @TableId(value = "tid", type = IdType.ASSIGN_ID)
    private String tid;
    private int stage;
    private String userId;
}
