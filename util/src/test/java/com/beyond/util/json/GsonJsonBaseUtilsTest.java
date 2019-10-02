package com.beyond.util.json;

import com.beyond.util.json.vo.Address;
import com.beyond.util.json.vo.Student;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author huangyanbing
 * @date 2019-09-20 19:16
 */
public class GsonJsonBaseUtilsTest {

    @Test
    @Ignore
    public void getJsonObjectToJsonOne() {
        Student student = new Student();
        student.setName("yanbing");
        Address address = new Address();
        address.setCity("shanghai");
        address.setCountry("China");
        student.setAddress(address);
        GsonJsonBaseUtils.getJsonByObject(student);
    }

    @Test
    @Ignore
    public void getJsonByJsonObject() {
        GsonJsonBaseUtils.getJsonByJsonObject();
    }

    @Test
    @Ignore
    public void getObjectByJson() {
        String student = "{\"name\":\"yanbing\",\"address\":{\"city\":\"shanghai\",\"country\":\"China\"}}";

        GsonJsonBaseUtils.getObjectByJson(student);
    }

    @Test
    @Ignore
    public void getMapObjectByJson() {
        String student = "{\"name\":\"yanbing\",\"address\":{\"city\":\"shanghai\",\"country\":\"China\"}}";

        GsonJsonBaseUtils.getMapObjectByJson(student);
    }

    @Test
    @Ignore
    public void getArrayObjectByJson() {
        GsonJsonBaseUtils.getArrayObjectByJson();
    }

    @Test
    @Ignore
    public void getListObjectByJson() {
        String student = "[{\"name\":\"yanbing\",\"address\":{\"city\":\"shanghai\",\"country\":\"China\"}}]";

        GsonJsonBaseUtils.getListObjectByJson(student);
    }

}
