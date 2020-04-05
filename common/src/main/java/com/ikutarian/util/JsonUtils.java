package com.ikutarian.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串
     *
     * @param obj 要输出json的对象
     */
    public static String objToJson(Object obj) {
        return objToJson(obj, false);
    }

    /**
     * 将对象转换成json字符串
     *
     * @param obj         要输出json的对象
     * @param prettyPrint 是否美化JSON输出
     */
    public static String objToJson(Object obj, boolean prettyPrint) {
        try {
            if (prettyPrint) {
                return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } else {
                return MAPPER.writeValueAsString(obj);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转化为对象
     *
     * @param jsonStr json数据
     * @param clazz   obj的类类型
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
        try {
            return MAPPER.readValue(jsonStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json数据转换成pojo对象list
     *
     * @param jsonStr json数据
     * @param clazz   obj的类类型
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
            return MAPPER.readValue(jsonStr, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
