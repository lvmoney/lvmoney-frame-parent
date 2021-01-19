package com.lvmoney.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltlvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright 四川******科技有限公司
 */


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
public class WordTemplateByteVo implements Serializable {
    private static final long serialVersionUID = 4437366265175491752L;
    private byte[] source;
    private List<WordNumbericVo> numberic;
    private List<WordPictureVo> picture;
    private List<WordStringVo> str;
    private List<WordTablesVo> table;
}
