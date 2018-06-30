package com.beyond.util.xml.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 14:19
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Student")
public class Student {
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Age")
    private Integer age;
    /**
     * com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException: 1 counts of IllegalAnnotationExceptions
     * <p>
     * XmlElementWrapper 只能位于集合属性上, 而 "com.beyond.util.xml.vo.Student.address" 不是集合属性。
     */
    //@XmlElementWrapper(name = "AddressArray")
    @XmlElement(name = "Address")
    private Address address;

    @XmlElement(name = "School")
    private School school;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
