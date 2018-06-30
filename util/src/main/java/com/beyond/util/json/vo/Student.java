package com.beyond.util.json.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 14:19
 */
public class Student {
    @JsonProperty("Name")
    @JSONField(name = "Name")
    private String name;
    @JsonProperty("Age")
    @JSONField(name = "Age")
    private Integer age;
    @JsonProperty("Address")
    @JSONField(name = "Address")
    private Address address;

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
