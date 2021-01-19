package com.lvmoney.demo.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 会用相关字典表
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
@TableName("dictionaries_info")
@Data
public class DictionariesInfo extends Model<DictionariesInfo> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 名字
     */
    private String name;

    /**
     * 名字
     */
    private String tenant_id;

    /**
     * 组code
     */

    private String groupCode;

    /**
     * code
     */
    private String code;

    /**
     * 描述
     */
    private String remark;

    private Integer sysStatus;

    /**
     * 0：表示是最高层
     */
    private Integer level;


    private String createUid;

    private String updateUid;


}
