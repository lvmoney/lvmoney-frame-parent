/**
 * 描述:
 * 包名:com.lvmoney.jwt.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:32:50
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:32:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 8327049017887409671L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 该字段需要自定义实体处理
     */
    private String other;
    /**
     * 系统id
     */
    private String sysId;

    /**
     * 租户id
     */
    private String tenantId;

    /**
     * 组织id
     */
    private String orgId;

}
