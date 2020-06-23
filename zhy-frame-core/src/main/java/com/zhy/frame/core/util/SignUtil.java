/**
 * 描述:
 * 包名:com.scltzhy.pay.utils
 * 版本信息: 版本1.0
 * 日期:2018年10月11日  上午10:02:58
 * Copyright XXXXXX科技有限公司
 */

package com.zhy.frame.core.util;


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.core.vo.SignVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

import static com.zhy.frame.base.core.constant.BaseConstant.CHARACTER_ENCODE_UTF8_LOWER;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年10月11日 上午10:02:58
 */

public class SignUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtil.class);
    /**
     * 默认忽略 sign
     */
    private static final String IGNORE_SIGN = "sign";

    /**
     * 默认忽略 serialVersionUID
     */
    private static final String IGNORE_SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 泛型实体椭圆曲线私钥签名
     *
     * @param t:         实体
     * @param publicKey: 公钥
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:20
     */
    public static <T> String t2Sign(T t, String publicKey) {
        String res = getPlaintext(t);
        String sign = EcdsaUtil.getEcdsaSign(res, BaseConstant.EEC_FACTORY_TYPE, publicKey, BaseConstant.EEC_SIGNATURE_TYPE);
        return sign;
    }

    /**
     * 椭圆曲线公钥校验签名
     *
     * @param res:       原文
     * @param sign:      密文
     * @param publicKey: 公钥
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:20
     */
    public static boolean verifySign(String res, String sign, String publicKey) {
        boolean bool = false;
        bool = EcdsaUtil.verifyEcdsa(res, sign, BaseConstant.EEC_FACTORY_TYPE, publicKey, BaseConstant.EEC_SIGNATURE_TYPE);
        return bool;
    }

    /**
     * 获得泛型实体需要签名的明文
     *
     * @param t: 实体vo对象
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:21
     */
    public static <T> String getPlaintext(T t) {
        SignVo<T> signVo = new SignVo<>();
        signVo.setKeyToUpper(BaseConstant.API_TO_UPPER);
        signVo.setUrlEncode(BaseConstant.API_URL_ENCODE);
        signVo.setData(t);
        String res = getContent(signVo).getContent();
        return res;
    }


    /**
     * 通过实体获得拼接后的字符串
     *
     * @param signVo:
     * @param ign:
     * @throws
     * @return: com.zhy.frame.core.vo.SignVo<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/12 18:04
     */
    public static <T> SignVo<T> getContent(SignVo<T> signVo, String... ign) {
        Map<String, String> paraMap = getKeyValueFormVo(signVo.getData(), ign);
        String content = formatUrlMap(paraMap, signVo.isUrlEncode(), signVo.isKeyToUpper());
        signVo.setContent(content);
        return signVo;
    }

    /**
     * 反射遍历获得泛型实体的key和value值。根据业务需要排除sign,serialVersionUID字段
     *
     * @param t:
     * @param ignore:
     * @throws
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/12 17:58
     */
    public static <T> Map<String, String> getKeyValueFormVo(T t, String... ignore) {
        Map<String, String> result = new HashMap<String, String>(BaseConstant.MAP_DEFAULT_SIZE);
        Object clazz;
        Object pClazz;
        try {
            clazz = t.getClass().newInstance();
            pClazz = t.getClass().getSuperclass().newInstance();
            // 父实体属性
            Field[] pFields = pClazz.getClass().getDeclaredFields();
            for (int i = 0; i < pFields.length; i++) {
                Field f = pFields[i];
                // 属性名
                String key = f.getName();
                // 属性值
                String value = null;
                // 忽略序列化版本ID号
                if (!isContain(key, ignore)) {
                    // 取消Java语言访问检查
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(t);
                        String temp = obj != null ? JsonUtil.t2JsonString(obj) : "";
                        if (temp.contains(BaseConstant.COLON)) {
                            value = temp;
                        } else {
                            value = obj.toString();
                        }
                    } catch (IllegalArgumentException e) {
                        LOGGER.error("通过实体构造map报错:{}", e);
                    } catch (IllegalAccessException e) {
                        LOGGER.error("通过实体构造map报错:{}", e);
                    }
                    result.put(key, value);
                }
            }
            // 实体属性
            Field[] fields = clazz.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                // 属性名
                String key = f.getName();
                // 属性值
                String value = null;
                // 忽略序列化版本ID号
                if (!isContain(key, ignore)) {
                    // 取消Java语言访问检查
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(t);
                        String temp = obj != null ? JsonUtil.t2JsonString(obj) : "";
                        if (temp.contains(BaseConstant.COLON)) {
                            value = temp;
                        } else {
                            value = obj.toString();
                        }
                    } catch (IllegalArgumentException e) {
                        LOGGER.error("通过实体构造map报错:{}", e);
                    } catch (IllegalAccessException e) {
                        LOGGER.error("通过实体构造map报错:{}", e);
                    }
                    result.put(key, value);
                }
            }
        } catch (InstantiationException | IllegalAccessException e1) {
            result = null;
            LOGGER.error("通过实体构造map报错:{}", e1);
        }
        return result;
    }

    /**
     * 对所有传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串
     *
     * @param paraMap    要排序的Map对象 *
     * @param urlEncode  是否需要URLENCODE
     * @param keyToUpper 是否需要将Key转换为全大写 * true:key转化成大写，false:不转化
     * @return
     * @author: lvmoney /xxxx科技有限公司
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToUpper) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (!StringUtil.isEmpty(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, CHARACTER_ENCODE_UTF8_LOWER);
                    }
                    buf.append(key + "=" + val);
                    buf.append("&");
                }
            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            LOGGER.error("通过map构造url报错:{}", e);
            return null;
        }
        if (keyToUpper) {
            return buff.toUpperCase();
        }
        return buff;
    }

    private static boolean isContain(String key, String... ignore) {
        if (key.equals(IGNORE_SERIAL_VERSION_UID)) {
            return true;
        }
        if (key.equals(IGNORE_SIGN)) {
            return true;
        }
        for (String str : ignore) {
            if (key.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得请求参数签名
     *
     * @param secretKey:
     * @param baseRequest:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/12 16:58
     */
    /**
     * 排序-》参数构造-》HmacSha1-》base64
     *
     * @param secretKey:
     * @param signVo:
     * @param ign:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/13 17:45
     */
    public static String signature(String secretKey, SignVo signVo, String... ign) {
        String content = SignUtil.getContent(signVo, ign).getContent();
        return Base64Util.encode(HashUtil.HmacSha1Hash(secretKey, content));
    }

}
