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
 * @date 2019-09-18 10:33
 */
@Getter
@Setter
@ToString
public class Order {

    private Integer orderId;

    private Integer userId;

    private Integer configId;

    private String remark;

    private Date createTime;

    private Date lastModifyTime;
}
