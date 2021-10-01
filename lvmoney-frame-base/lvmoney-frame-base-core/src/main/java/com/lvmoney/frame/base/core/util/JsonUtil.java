package com.lvmoney.frame.base.core.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class JsonUtil {
    /**
     * 把java对象转化成json string
     *
     * @param t:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:07
     */
    public static <T> String t2JsonString(T t) {
        return JSON.toJSONString(t);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final ObjectWriter OBJECT_WRITER;
    private static final ObjectReader OBJECT_READER;

    static {
        // sort by letter
        OBJECT_MAPPER.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        // when map is serialization, sort by key
        OBJECT_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        // ignore mismatched fields
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // use field for serialize and deSerialize
        OBJECT_MAPPER.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        OBJECT_MAPPER.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        OBJECT_WRITER = OBJECT_MAPPER.writer().withDefaultPrettyPrinter();
        OBJECT_READER = OBJECT_MAPPER.reader();
    }


    /**
     * 对象序列化
     *
     * @param object:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:07
     */
    public static <T> String serialize(T object) {
        StringWriter write = new StringWriter();

        try {
            OBJECT_MAPPER.writeValue(write, object);
        } catch (JsonGenerationException var3) {
            LOGGER.error("JsonGenerationException when serialize object to json", var3);
        } catch (JsonMappingException var4) {
            LOGGER.error("JsonMappingException when serialize object to json", var4);
        } catch (IOException var5) {
            LOGGER.error("IOException when serialize object to json", var5);
        }

        return write.toString();
    }

    /**
     * 判断list是否包含某个list
     *
     * @param src:
     * @param dst:
     * @throws
     * @return: java.util.List<java.lang.Boolean>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:08
     */
    public static List<Boolean> strictCheckExistence(List<String> src, List<String> dst) {
        List<Boolean> result = new ArrayList();
        int index = 0;

        for (int i = 0; i < src.size(); ++i) {
            if (((String) src.get(i)).equalsIgnoreCase((String) dst.get(index))) {
                result.add(true);
                ++index;
            } else {
                result.add(false);
            }
        }

        return result;
    }


    /**
     * list 转 list<BitInteger>
     *
     * @param list:
     * @param size:
     * @throws
     * @return: java.util.List<java.math.BigInteger>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:11
     */
    public static List<BigInteger> listToListBigInteger(List<BigInteger> list, int size) {
        List<BigInteger> bigIntegerList = new ArrayList();
        Iterator var3 = list.iterator();

        while (var3.hasNext()) {
            BigInteger bs = (BigInteger) var3.next();
            bigIntegerList.add(bs);
        }

        List<BigInteger> addList = new ArrayList();
        if (bigIntegerList.size() < size) {
            for (int i = 0; i < size - bigIntegerList.size(); ++i) {
                addList.add(BigInteger.ZERO);
            }

            bigIntegerList.addAll(addList);
        }

        return bigIntegerList;
    }

    /**
     * 是不是json
     *
     * @param json:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:11
     */
    public static boolean isValidJsonStr(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        } else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.readTree(json);
                return true;
            } catch (IOException var2) {
                return false;
            }
        }
    }

    /**
     * 复制对象
     *
     * @param obj:
     * @throws
     * @return: T
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:11
     */
    public static <T extends Serializable> T clone(T obj) {
        Serializable clonedObj = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (Serializable) ois.readObject();
            ois.close();
        } catch (Exception var6) {
            LOGGER.error("clone object has error.", var6);
        }

        return (T) clonedObj;
    }


    /**
     * json 转 list
     *
     * @param json:
     * @param clazz:
     * @throws
     * @return: java.util.List<T>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:13
     */
    public static <T> List<T> deserializeToList(String json, Class<T> clazz) {
        List object = null;

        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, new Class[]{TypeFactory.rawClass(clazz)});
            object = (List) OBJECT_MAPPER.readValue(json, javaType);
        } catch (JsonParseException var4) {
            LOGGER.error("JsonParseException when serialize object to json", var4);
            return null;
        } catch (JsonMappingException var5) {
            LOGGER.error("JsonMappingException when serialize object to json", var5);
            return null;
        } catch (IOException var6) {
            LOGGER.error("IOException when serialize object to json", var6);
        }

        return object;
    }


    /**
     * map 转实体
     *
     * @param map:
     * @param clazz:
     * @throws
     * @return: T
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 9:15
     */
    public static <T> T mapToObj(Map<String, Object> map, Class<T> clazz) {
        T pojo = OBJECT_MAPPER.convertValue(map, clazz);
        return pojo;
    }


    public static String removeDoubleQuotes(String inputValue) {
        return inputValue.replace(BaseConstant.DOUBLE_QUOTATION_MARKS_ESCAPE, BaseConstant.EMPTY);
    }

    public static Map<String, Object> convertJsonToSortedMap(String looseJson) {
        try {
            Map<String, Object> map = (Map<String, Object>) jsonStrToObj(
                    new HashMap<String, Object>(), looseJson);
            return new TreeMap<>(map);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Json String to Object.
     *
     * @param obj     Object
     * @param jsonStr Json String
     * @return Object
     */
    public static Object jsonStrToObj(Object obj, String jsonStr) {

        try {
            return OBJECT_READER.readValue(
                    OBJECT_MAPPER.getFactory().createParser(jsonStr),
                    obj.getClass());
        } catch (IOException e) {
            LOGGER.error("jsonStrToObj error:{}", e);
            return null;
        }
    }

    /**
     * Object to Json String.
     *
     * @param obj Object
     * @return String
     */
    public static String objToJsonStr(Object obj) {

        try {
            return OBJECT_WRITER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("jsonStrToObj error:{}", e);
            return null;
        }
    }

    /**
     * Convert a Map to compact Json output, with keys ordered. Use Jackson JsonNode toString() to ensure key order and compact output.
     *
     * @param map input map
     * @return JsonString
     */
    public static String mapToCompactJson(Map<String, Object> map) throws Exception {
        ObjectWriter objectWriter = OBJECT_MAPPER.writer();
        return objectWriter.writeValueAsString(map);
    }

    /**
     * Convert a POJO to Map.
     *
     * @param object POJO
     * @return Map
     */
    public static Map<String, Object> objToMap(Object object) throws Exception {
        JsonNode jsonNode = OBJECT_MAPPER.readTree(objToJsonStr(object));
        return (HashMap<String, Object>) OBJECT_MAPPER.convertValue(jsonNode, HashMap.class);
    }


}

