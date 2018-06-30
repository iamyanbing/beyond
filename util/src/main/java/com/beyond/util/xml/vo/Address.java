package com.beyond.util.xml.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 14:20
 */
@XmlAccessorType(XmlAccessType.NONE)
public class Address {
    @XmlElement(name = "City")
    private String city;
    @XmlElement(name = "County")
    private String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
