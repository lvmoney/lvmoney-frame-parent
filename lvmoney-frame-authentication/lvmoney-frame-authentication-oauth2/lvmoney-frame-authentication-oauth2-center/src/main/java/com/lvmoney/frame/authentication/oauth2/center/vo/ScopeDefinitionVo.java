package com.lvmoney.frame.authentication.oauth2.center.vo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ScopeDefinitionVo extends CommonVo {
    /**
     *
     */
    private static final long serialVersionUID = 2862177859444895431L;
    private String scope;
    /**
     * 定义 解释
     */
    private String definition;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
