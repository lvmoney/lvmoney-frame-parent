package com.lvmoney.demo.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * <p>
 * 会用相关字典表
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
@Data
public class Toca extends Model<Toca> {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String phone;
    private String code;
    private Integer num;
    private Long tenantId;

}
