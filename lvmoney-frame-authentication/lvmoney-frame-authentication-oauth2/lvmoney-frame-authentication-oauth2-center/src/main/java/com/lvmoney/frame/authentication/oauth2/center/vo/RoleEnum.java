package com.lvmoney.frame.authentication.oauth2.center.vo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public enum RoleEnum {
    /**
     * 角色：普通用户
     */
    ROLE_USER("普通用户"),
    /**
     * 角色：管理员
     */
    ROLE_ADMIN("管理员"),
    /**
     * 角色：超级
     */
    ROLE_SUPER("超级");

    private String meaning;

    public String getMeaning() {
        return meaning;
    }

    RoleEnum() {
    }

    RoleEnum(String meaning) {
        this.meaning = meaning;
    }
}
