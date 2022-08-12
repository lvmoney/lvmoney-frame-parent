package com.lvmoney.frame.office.word.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/16
 * Copyright 四川XXXXXX公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川XXXXXX公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class BaseChangeByteOutVo implements Serializable {
    private byte[] target;
    private String fileName;
}
