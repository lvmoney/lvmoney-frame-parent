package com.lvmoney.frame.base.core.vo;/**
 * 描述:
 * 包名:com.lvmoney.bigdata.canal.redis.vo
 * 版本信息: 版本1.0
 * 日期:2019/7/20
 * Copyright 四川******科技有限公司
 */


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：分页基本类
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/7/20 10:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorePage<T> implements Serializable {
    private static final long serialVersionUID = 3250193167291923077L;
    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;
    @ApiModelProperty("第几页")
    private Integer pageNo = 1;
    @ApiModelProperty("总行数")
    private Long total;
    @ApiModelProperty("页面数据")
    private List<T> data;
    @ApiModelProperty("是否获得全部")
    private boolean all;
}
