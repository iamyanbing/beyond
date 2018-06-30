package com.beyond.util.json;

import com.beyond.util.json.vo.Student;
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
public class JscksonUtilsTest {
    String json;
    String jsonError;
    String jsonArray;

    @Before
    public void init() {
        json = "{\n" +
                "\t\"Name\": \"yb\",\n" +
                "\t\"Age\": 18,\n" +
                "\t\"Address\": {\n" +
                "\t\t\"City\": \"shanghai\",\n" +
                "\t\t\"Country\": \"China\"\n" +
                "\t}\n" +
                "}";
        jsonError = "{\n" +
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
                "\t\"Address\": {\n" +
                "\t\t\"City\": \"shanghai\",\n" +
                "\t\t\"Country\": \"China\"\n" +
                "\t}\n" +
                "},{\n" +
                "\t\"Name\": \"yanbing\",\n" +
                "\t\"Age\": 18,\n" +
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
        Student student = JacksonUtils.getObjectByJson(json);
        System.out.println(student.getName());
    }


    @Ignore
    @Test
    public void getArrayObjectToJson() throws Exception {
        List<Student> students = JacksonUtils.getArrayObjectByJson(jsonArray);
        System.out.println(students.size());
        for (Student student :
                students) {
            System.out.println(student.getName());
        }
    }

    @Ignore
    @Test
    public void testError() throws Exception {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //设置以上代码，则json字符串可以比VO类多字段而不会报错
        Student student = JacksonUtils.getObjectByJson(jsonError);
        System.out.println(student.getName());
    }
}
