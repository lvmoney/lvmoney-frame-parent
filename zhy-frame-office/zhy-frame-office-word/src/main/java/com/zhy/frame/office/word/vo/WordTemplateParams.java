package com.zhy.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.office.word.enums.WordTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordTemplateParams implements Serializable {
    private static final long serialVersionUID = -1386012864435093369L;
    private String paramName;
    private WordTemplate wordTemplate;
    private String templateParam;
}
