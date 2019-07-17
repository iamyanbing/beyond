package com.beyond.util.json;

import com.beyond.util.json.vo.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * writeValue(File arg0, Object arg1)           把arg1转成json序列，并保存到arg0文件中
 * writeValue(OutputStream arg0, Object arg1)   把arg1转成json序列，并保存到arg0输出流中
 * writeValueAsBytes(Object arg0)               把arg0转成json序列，并把结果输出成字节数组
 * writeValueAsString(Object arg0)              把arg0转成json序列，并把结果输出成字符串
 * </pre>
 * <pre>
 * 实体上使用 @JsonInclude(JsonInclude.Include.NON_NULL)
 * 1、如果放在属性上，如果该属性为NULL则不参与序列化
 * 2、如果放在类上，那对这个类的全部属性起作用
 * JsonInclude.Include.ALWAYS               默认
 * JsonInclude.Include.NON_DEFAULT          属性为默认值不序列化
 * JsonInclude.Include.NON_EMPTY            属性为 空（””）或者为 NULL 都不序列化
 * JsonInclude.Include.NON_NULL             属性为NULL不序列化
 * </pre>
 * <pre>
 * JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性
 * JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
 * JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称
 * </pre>
 * <p>
 * <pre>
 * objectMapper = new ObjectMapper()
 * objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)            如果为空则不输出
 * objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)                   对于空的对象转json的时候不抛出错误
 * objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)             禁用序列化日期为timestamps
 * objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)          禁用遇到未知属性抛出异常
 * objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)   视空字符传为null
 * objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true)                  低层级配置
 * objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)      允许属性名称没有引号
 * objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)    允许特殊字符出现
 * objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)             允许单引号
 * objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false)            取消对非ASCII字符的转码
 *
 * enable、disable、configure里面的值还可以这样用；表达的意思是一样，只是写法不同
 * objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
 * </pre>
 * <p>https://www.programcreek.com/java-api-examples/index.php?api=com.fasterxml.jackson.databind.SerializationFeature</p>
 * @Auther: yanbing
 * @Date: 2018/6/30 15:30
 */
public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        //通过ObjectMapper 对象进行设置;objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);注意：ObjectMapper 只对VO起作用；对Map List不起作用
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static Student getObjectByJson(String jsonArray) throws Exception {
        return objectMapper.readValue(jsonArray, Student.class);
    }

    /**
     * List<Student> ticketItems = objectMapper.readValue(jsonArray, List.class);
     * 直接按照上面转：
     * 转换的对象不是Student的实例，是LinkedHashMap对象；属性和值也是LinkedHashMap。
     * 这样进行其他操作往往会报错
     * @param jsonArray
     * @return
     * @throws Exception
     */
    public static List<Student> getArrayObjectByJson(String jsonArray) throws Exception {
        JavaType convertToTicketItem = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Student.class);
        List<Student> ticketItems = (List<Student>) objectMapper.readValue(jsonArray, convertToTicketItem);
        return ticketItems;
    }
}
