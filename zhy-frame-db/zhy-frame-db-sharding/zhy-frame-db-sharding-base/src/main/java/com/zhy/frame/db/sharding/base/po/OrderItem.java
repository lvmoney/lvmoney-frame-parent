package com.zhy.frame.db.sharding.base.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-19 16:31
 */
@Getter
@Setter
@ToString
public class OrderItem {

    private Long itemId;

    private Integer orderId;

    private String remark;

    private Date createTime;

    private Date lastModifyTime;
}
