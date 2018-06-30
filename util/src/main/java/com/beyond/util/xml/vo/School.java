package com.beyond.util.xml.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 16:36
 */
@XmlAccessorType(XmlAccessType.NONE)
public class School {
    @XmlAttribute(name = "City")
    private String city;
    @XmlValue()
    private String name;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
