package com.zhy.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/6
 * Copyright 四川******科技有限公司
 */


import com.deepoove.poi.data.RowRenderData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordTablesVo implements Serializable {
    private static final long serialVersionUID = -8798237224216550240L;
    /**
     * table key，对应模板文件中{{#tables}}
     */
    private String key;
    /**
     * table 的head
     */
    private RowRenderData tableHeads;
    /**
     * table 的body
     */
    private List<RowRenderData> tablebodys;

    /**
     * 描述
     */
    private String dataDesc;


    /**
     * table的宽度
     */

    private float width;
}
