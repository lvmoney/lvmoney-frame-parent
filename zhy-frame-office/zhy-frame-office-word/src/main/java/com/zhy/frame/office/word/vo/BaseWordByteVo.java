package com.zhy.frame.office.word.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright xxxx科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class BaseWordByteVo implements Serializable {
    private byte[] file;
    private String fileName;
}
