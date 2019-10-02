package com.beyond.util.json.vo;


import com.beyond.util.json.gson.DateSerializer;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: yanbing
 * @Date: 2018/6/30 14:19
 */
@Data
public class StudentSerializerOrDeserializerGson {

    private String name;

    private Integer age;

    @JsonAdapter(DateSerializer.class)
    private LocalDateTime birthdate;
}
