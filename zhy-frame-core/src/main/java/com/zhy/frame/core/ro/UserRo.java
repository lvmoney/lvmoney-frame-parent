/**
 * 描述:
 * 包名:com.zhy.jwt.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:49:22
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.core.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月7日 下午5:49:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4541709436582146541L;
    private String userId;
    private String username;
    private String password;
    private long expire;
    private String token;
    /**
     * new 考虑多系统，新增系统编号
     */
    private String sysId;
    /**
     * new 拓展字段存储为序列化后的json
     */
    private String other;
}
