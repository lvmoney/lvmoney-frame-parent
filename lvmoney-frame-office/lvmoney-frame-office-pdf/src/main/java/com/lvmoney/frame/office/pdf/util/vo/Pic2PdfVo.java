package com.lvmoney.frame.office.pdf.util.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.office.pdf.util.vo
 * 版本信息: 版本1.0
 * 日期:2021/10/14
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.office.pdf.util.vo.item.Pic2PdfVoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/10/14 9:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pic2PdfVo implements Serializable {
    private static final long serialVersionUID = 4141637444867401537L;
    /**
     * 图片列表
     */
    private List<Pic2PdfVoItem> item;
    /**
     * pdf源
     */
    private byte[] pdf;
    /**
     * 文件目录
     */
    private String directory;
    /**
     * 文件名称
     */
    private String pdfName;
    /**
     * 签字的页码，9999：最后一页，1：第一页，
     */
    private Integer pageNo;
}
