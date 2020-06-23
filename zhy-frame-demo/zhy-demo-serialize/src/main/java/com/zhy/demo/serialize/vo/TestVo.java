package com.zhy.demo.serialize.vo;/**
 * 描述:
 * 包名:com.zhy.demo.serialize.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 10:21
 */
@Data
public class TestVo implements Serializable {
    private String uid;
    private String name;
    private TestItem item;
}
