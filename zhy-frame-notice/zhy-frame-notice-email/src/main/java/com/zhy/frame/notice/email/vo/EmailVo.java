package com.zhy.frame.notice.email.vo;/**
 * 描述:
 * 包名:com.zhy.email.vo
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/22 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVo implements Serializable {
    private static final long serialVersionUID = 8547202814041767882L;
    /**
     * 发送者
     */
    private String from;
    /**
     * 发送给谁
     */
    private String to;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String context;
}
