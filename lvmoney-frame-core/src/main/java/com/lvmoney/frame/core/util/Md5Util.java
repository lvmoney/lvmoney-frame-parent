package com.lvmoney.frame.core.util;

import com.lvmoney.frame.base.core.constant.BaseConstant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月4日 下午1:57:49
 */
public class Md5Util {
    /**
     * md5
     *
     * @param content:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:48
     */
    public static String digest(String content) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance(BaseConstant.MD5_SIGNATURE_TYPE);
            md.update(content.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String str = Integer.toHexString(b & 0xFF);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

}
