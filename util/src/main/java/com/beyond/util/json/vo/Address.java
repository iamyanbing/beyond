package com.beyond.util.json.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 14:20
 */
@Data
public class Address {
    @JsonProperty("City")
    @JSONField(name = "City")
    private String city;
    @JsonProperty("Country")
    @JSONField(name = "Country")
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
