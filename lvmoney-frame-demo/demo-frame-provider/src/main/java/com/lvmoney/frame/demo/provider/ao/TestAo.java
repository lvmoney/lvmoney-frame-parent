package com.lvmoney.frame.demo.provider.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.demo.provider.ao
 * 版本信息: 版本1.0
 * 日期:2020/11/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/18 14:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAo implements Serializable {
    private static final long serialVersionUID = -6114768309725338674L;
    private Integer no;
}
