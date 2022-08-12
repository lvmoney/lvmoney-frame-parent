package com.lvmoney.frame.base.serializer.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.vo
 * 版本信息: 版本1.0
 * 日期:2020/8/20
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/20 11:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterceptorVo implements Serializable {
    private HandlerInterceptorAdapter handlerInterceptorAdapter;
    private int order;
    private String[] pathPatterns;
}
