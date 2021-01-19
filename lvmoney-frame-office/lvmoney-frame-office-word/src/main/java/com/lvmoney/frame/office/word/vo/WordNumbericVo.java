package com.lvmoney.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltlvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import com.deepoove.poi.data.TextRenderData;
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
public class WordNumbericVo implements Serializable {
    private static final long serialVersionUID = 8588716049309823462L;
    /**
     * 列表key，对应模板文件中{{*active}}
     */
    private String key;
    /**
     * 列表填充内容
     */
    private List<TextRenderData> body;

}
