/**
 * 描述:
 * 包名:com.lvmoney.pay.vo
 * 版本信息: 版本1.0
 * 日期:2018年10月9日  下午6:06:32
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.core.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月9日 下午6:06:32
 */
@Data
public class EcdsaVo implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -2171545226000703897L;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 明文
     */
    private String plaintext;
    /**
     * 密文
     */
    private String ciphertext;
    @JSONField(name = "test3")
    private String test;


    @Override
    public String toString() {
        return "EcdsaVo [privateKey=" + privateKey + ", publicKey=" + publicKey + ", plaintext=" + plaintext
                + ", ciphertext=" + ciphertext + "]";
    }

}
