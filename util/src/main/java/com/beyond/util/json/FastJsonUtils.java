package com.beyond.util.json;

import com.alibaba.fastjson.JSON;
import com.beyond.util.json.vo.Student;
import com.beyond.util.json.vo.StudentArray;

import java.util.List;

/**
 * alibaba fastjson： 优势：json字符串可以不用完全解析，比如json字符串中有Sex字段，解析的时候VO类可以不用有Sex字段。jackjson则VO类必须要有Sex字段
 * fastjson  bug比较多，推荐Gson和Jackson
 * @Auther: yanbing
 * @Date: 2018/6/30 14:18
 */
public class FastJsonUtils {
    public static Student getObjectByJson(String json) {
        return JSON.parseObject(json, Student.class);
    }

    public static List<Student> getArrayObjectByJson(String jsonArray) {
        return JSON.parseArray(jsonArray, Student.class);
    }

    /**
     * @param json 带解析的json字符串
     *
     * @return : JSONObject对象，不能强制装换为类，需要通过getXXX方法取值
     */
    public static Object getJsonObjectByJson(String json) {
        return JSON.parse(json);
    }

    public static Object getJsonArrayObjectByJson(String jsonArray) {
        return JSON.parseArray(jsonArray, StudentArray.class);
    }
}
