package com.lvmoney.frame.office.word.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/16
 * Copyright 成都三合力通科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class BaseChangeFileVo implements Serializable {
    private byte[] resouce;
    private String fileName;
}
