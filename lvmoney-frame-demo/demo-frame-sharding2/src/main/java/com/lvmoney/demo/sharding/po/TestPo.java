package com.lvmoney.demo.sharding.po;/**
 * 描述:
 * 包名:com.lvmoney.demo.sharding.po
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 19:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPo implements Serializable {
    private Long id;
    private Long tid;
}
