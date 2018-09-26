package com.chang.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * 单例，全局ObjectMapper
 */
public class MyObjectMapper {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getInstance() {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);      // ObjectMapper转换忽略多余字段
        return OBJECT_MAPPER;
    }

    public static Map<String, String> getStrMap(Object obj) {
        Map<String, String> map = getInstance().convertValue(obj, Map.class);
        return map;
    }

    public static Map<String, Object> getObjMap(Object obj) {
        Map<String, Object> map = getInstance().convertValue(obj, Map.class);
        return map;
    }

    public static MultiValueMap<String, Object> getMultiValueMap(Object obj) {
        MultiValueMap<String, Object> map = getInstance().convertValue(obj, MultiValueMap.class);
        return map;
    }

    public static String getJson(Object obj) throws Exception {
        String json = getInstance().writeValueAsString(obj);
        return json;
    }
}
