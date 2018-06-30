package com.beyond.util.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beyond.util.json.vo.Student;
import com.beyond.util.json.vo.StudentArray;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * json字符串中多Sex字段，VO类中没有Sex字段
 *
 * @Auther: yanbing
 * @Date: 2018/6/30 14:30
 */
public class FastJsonUtilsTest {
    String json;
    String jsonArray;

    @Before
    public void init() {
        json = "{\n" +
                "\t\"Name\": \"yb\",\n" +
                "\t\"Age\": 18,\n" +
                "\t\"Sex\": \"female\",\n" +
                "\t\"Address\": {\n" +
                "\t\t\"City\": \"shanghai\",\n" +
                "\t\t\"Country\": \"China\"\n" +
                "\t}\n" +
                "}";
        jsonArray = "[\n" +
                "{\n" +
                "\t\"Name\": \"yb\",\n" +
                "\t\"Age\": 18,\n" +
                "\t\"Sex\": \"female\",\n" +
                "\t\"Address\": {\n" +
                "\t\t\"City\": \"shanghai\",\n" +
                "\t\t\"Country\": \"China\"\n" +
                "\t}\n" +
                "},{\n" +
                "\t\"Name\": \"yanbing\",\n" +
                "\t\"Age\": 18,\n" +
                "\t\"Sex\": \"male\",\n" +
                "\t\"Address\": {\n" +
                "\t\t\"City\": \"shanghai\",\n" +
                "\t\t\"Country\": \"China\"\n" +
                "\t}\n" +
                "}\n" +
                "]";
    }

    @Ignore
    @Test
    public void getObjectToJson() throws Exception {
        Student student = FastJsonUtils.getObjectByJson(json);
        System.out.println(student.getName());
    }


    @Ignore
    @Test
    public void getArrayObjectToJson() throws Exception {
        List<Student> students = FastJsonUtils.getArrayObjectByJson(jsonArray);
        System.out.println(students.size());
        for (Student student :
                students) {
            System.out.println(student.getName());
        }
    }

    @Ignore
    @Test(expected = java.lang.ClassCastException.class)
    public void getJsonObjectToJson() throws Exception {
        Object student = (Student) FastJsonUtils.getJsonObjectByJson(json);
        System.out.println(student.toString());
    }

    @Ignore
    @Test()
    public void getJsonObjectToJsonOne() throws Exception {
        JSONObject student = (JSONObject) FastJsonUtils.getJsonObjectByJson(json);
        System.out.println(student.getString("Name"));
        System.out.println(student.getJSONObject("Address").getString("City"));
    }

    @Ignore
    @Test()
    public void getJsonArrayObjectToJson() throws Exception {
        JSONArray student =(JSONArray) FastJsonUtils.getJsonArrayObjectByJson(jsonArray);
        System.out.println(student.get(0));
    }
}
