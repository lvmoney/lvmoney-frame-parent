package com.lvmoney.frame.core.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.vo
 * 版本信息: 版本1.0
 * 日期:2021/9/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.core.vo.item.PackageVoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/9/23 19:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageVo implements Serializable {
    private static final long serialVersionUID = 730396689076136944L;
    /**
     * 种类
     */
    private int count;
    /**
     * 总得分
     */
    private int value;
    /**
     * 具体
     */
    List<PackageVoItem> data;
}
