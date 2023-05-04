package com.zrkizzy.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.zrkizzy.common.config.mapper.JacksonObjectMapper;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Json工具类
 *
 * @author zhangrongkang
 * @since 2023/4/8
 */
public class JsonUtil {
    
    /**
     * ObjectMapper
     */
    private static final JacksonObjectMapper OBJECT_MAPPER = new JacksonObjectMapper();

    /**
     * 将Java对象转换成Json字符串
     *
     * @param object Java对象
     * @return Java对象对应JSON数据
     */
    public static String objectToJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            //打印异常信息
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换为对象
     *
     * @param json Json数据
     * @param clazz 对象类型
     * @param <T> 泛型
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json.getBytes(StandardCharsets.UTF_8), clazz);
        } catch (JsonParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将JSON数据转为实体类List集合
     *
     * @param json JSON数据
     * @param beanType 对象类型
     * @return 对象集合
     * @param <T> 泛型
     */
    public static <T> List<T> jsonToList(String json, Class<T> beanType) {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}