package com.zhy.frame.cache.lock.vo.req;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionReqVo implements Serializable {

    private static final long serialVersionUID = -6670575497459956922L;
    /**
     * 区间长度
     */
    private int seqSize;
    /**
     * 结果长度
     */
    private int size;

    private Long expire;

    private String name;


}
