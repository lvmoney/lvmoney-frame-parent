package com.zhy.frame.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @describe：序列化工具
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ObjectSerializerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectSerializerUtil.class);

    /**
     * 反序列化
     *
     * @param data:
     * @throws
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:34
     */
    public static Object deserializable(byte[] data) {
        if (data != null && data.length > 0) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bis);
                return ois.readObject();
            } catch (Exception e) {
                LOGGER.info("[异常信息] {}", e);
            }
            return null;
        } else {
            LOGGER.info("[反序列化] 入参为空");
            return null;
        }
    }

    /**
     * 序列化对象
     *
     * @param obj:
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:34
     */
    public static byte[] serializable(Object obj) {
        if (obj != null) {
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);
                oos.flush();
                oos.close();
                return bos.toByteArray();
            } catch (IOException e) {
                LOGGER.error("序列化对象报错:{}", e);
            }
            return null;
        } else {
            return null;
        }
    }
}
