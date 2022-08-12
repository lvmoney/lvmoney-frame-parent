package com.lvmoney.frame.ai.seetaface.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 比对历史
 * </p>
 *
 * @author lvmoney
 * @since 2022-02-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComparisonHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String target;

    private String res;

    /**
     * 分类
     */
    private String classify;

    /**
     * 结果
     */
    private String result;

    /**
     * 得分
     */
    private BigDecimal score;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建人id
     */
    private Long clientId;

    /**
     * 更新人id
     */
    private Long updateId;

    /**
     * 删除标识，0：删除，1：未删除
     */
    private Boolean valid;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    private String createName;

    private String updateName;

    private String extra;

}
