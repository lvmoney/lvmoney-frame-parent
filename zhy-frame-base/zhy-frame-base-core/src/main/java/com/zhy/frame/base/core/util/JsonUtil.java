package com.zhy.frame.base.core.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class JsonUtil {
    /**
     * 把java对象转化成json string
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String t2JsonString(T t) {
        return JSON.toJSONString(t);
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    @SuppressWarnings("rawtypes")
    public static <T> T json2Obj(String content, Class<T> clazzItem, Class... classes) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(clazzItem, classes);
        try {
            return OBJECT_MAPPER.readValue(content, javaType);
        } catch (Exception e) {
            throw new RuntimeException("Json反序列化出错", e);
        }
    }

    /**
     * 复杂json转换实体demo 泛型
     * public <T> ResponseEntity<List<OrderDetail>> parseMoreGenericParams() {
     * ResponseEntity responseEntity = new ResponseEntity();
     * responseEntity.setCode("121212");
     * responseEntity.setMsg("abc");
     * List<OrderDetail> orderDetails = new ArrayList<>();
     * OrderDetail orderDetail0 = new OrderDetail();
     * orderDetail0.setAddress("1");
     * orderDetail0.setOrderId("1");
     * orderDetail0.setProductId("1");
     * <p>
     * OrderDetail orderDetail1 = new OrderDetail();
     * orderDetail1.setAddress("2");
     * orderDetail1.setOrderId("2");
     * orderDetail1.setProductId("2");
     * <p>
     * orderDetails.add(orderDetail0);
     * orderDetails.add(orderDetail1);
     * responseEntity.setData(orderDetails);
     * String tt = JsonUtil.t2JsonString(responseEntity);
     * String json = "{\"code\":\"1\",\"msg\":\"Success\",\"data\":{\"orderid1\":{\"address\":\"street 1\",\"pay\":\"111.0\",\"productId\":\"1342546\"}}}";
     * return JSONObject.parseObject(tt, new TypeReference<ResponseEntity<List<OrderDetail>>>() {
     * });
     * }
     * //普通的直接转换即可
     * ResponseEntity list2 = JSON.parseObject(tt, new TypeReference<ResponseEntity>() {
     * });
     */
    public static void main(String[] args) {
        Map map = new LinkedHashMap();
        map.put("a", "test");
        map.put("b", "tes2");
        map.put("c", "test3");
        map.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("v=" + v);
        });
        Map map2 = new LinkedHashMap();
        map2.put("c", "test3");
        map2.putAll(map);

        map2.forEach((k, v) -> {
            System.out.println("k=" + k);
            System.out.println("v=" + v);
        });

        Set<String> test = new HashSet<String>() {{
            add("a");
            add("b");
        }};
        System.out.println(JsonUtil.t2JsonString(test));
    }
}

