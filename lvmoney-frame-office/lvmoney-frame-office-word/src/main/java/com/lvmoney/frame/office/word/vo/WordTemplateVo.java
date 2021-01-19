package com.lvmoney.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltlvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.lvmoney.frame.base.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordTemplateVo implements Serializable {
    private static final long serialVersionUID = -2657163066871628154L;
    private String source;
    private String target;
    private List<WordNumbericVo> numberic;
    private List<WordPictureVo> picture;
    private List<WordStringVo> str;
    private List<WordTablesVo> table;
}
