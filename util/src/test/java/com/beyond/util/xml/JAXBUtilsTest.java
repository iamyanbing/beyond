package com.beyond.util.xml;

import com.beyond.util.xml.vo.Address;
import com.beyond.util.xml.vo.School;
import com.beyond.util.xml.vo.Student;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 16:27
 */
public class JAXBUtilsTest {

    @Ignore
    @Test
    public void testError() throws Exception {
        Student student = new Student();
        student.setAge(18);
        student.setName("yanbing");
        Address address = new Address();
        address.setCity("shanghai");
        address.setCountry("china");
        student.setAddress(address);
        School school = new School();
        school.setCity("shanghai");
        school.setName("fudan");
        student.setSchool(school);
        String xml = JAXBUtils.getStringByObject(student);
        System.out.println(xml);
    }
}
