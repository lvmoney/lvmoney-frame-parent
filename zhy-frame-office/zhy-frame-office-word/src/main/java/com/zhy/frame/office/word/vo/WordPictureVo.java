package com.zhy.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import com.deepoove.poi.data.PictureRenderData;
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
public class WordPictureVo implements Serializable {
    private static final long serialVersionUID = 7460228318767573403L;
    /**
     * 图片key，在模板文件中用{{@picture}},@告诉模板是图片
     */
    private String key;
    /**
     * 图片填充值
     */
    private PictureRenderData value;
}
