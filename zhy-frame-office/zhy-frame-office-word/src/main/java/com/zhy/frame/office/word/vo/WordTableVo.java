package com.zhy.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import com.deepoove.poi.data.RenderData;
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
@AllArgsConstructor
@NoArgsConstructor
public class WordTableVo implements Serializable {
    private static final long serialVersionUID = 4624055792060925284L;
    /**
     * table key，对应模板文件中{{#tables}}
     */
    private String key;
    /**
     * table 的head
     */
    private List<RenderData> tableHeads;
    /**
     * table 的body
     */
    private List<Object> tablebodys;
    /**
     * 描述
     */
    private String dataDesc;
    /**
     * table的宽度
     */
    private int width;


}
