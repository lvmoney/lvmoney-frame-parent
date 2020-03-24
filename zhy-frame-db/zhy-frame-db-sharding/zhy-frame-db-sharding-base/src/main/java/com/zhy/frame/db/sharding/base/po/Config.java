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
 * @date 2019-09-20 10:21
 */
@Setter
@Getter
@ToString
public class Config {

    private Integer id;

    private String remark;

    private Date createTime;

    private Date lastModifyTime;
}
