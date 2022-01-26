package com.lvmoney.frame.office.pdf.util.vo.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.office.pdf.util.vo.item
 * 版本信息: 版本1.0
 * 日期:2021/10/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/10/14 9:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pic2PdfVoItem implements Serializable {
    private static final long serialVersionUID = -5930102619716064097L;
    /**
     * 图片宽度
     */
    private float width;
    /**
     * 图片高度
     */
    private float height;
    /**
     * 距离右下角 x
     */
    private float x;
    /**
     * 距离右下角 y
     */
    private float y;
    /**
     * 图片
     */
    private byte[] image;
}
