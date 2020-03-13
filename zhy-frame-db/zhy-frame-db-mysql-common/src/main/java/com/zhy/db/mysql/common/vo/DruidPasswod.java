package com.zhy.db.mysql.common.vo;/**
 * 描述:
 * 包名:com.zhy.mysql.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright 四川******科技有限公司
 */


import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/6 9:50
 */
public class DruidPasswod implements Serializable {
    private static final long serialVersionUID = -7074980926731973248L;
    /**
     * 加密后的密码
     */
    private String password;
    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 明文
     */

    private String plaintext;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }
}
