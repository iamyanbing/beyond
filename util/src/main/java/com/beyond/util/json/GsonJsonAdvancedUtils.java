package com.beyond.util.json;

import com.beyond.util.json.gson.DateSerializer;
import com.beyond.util.json.vo.Address;
import com.beyond.util.json.vo.StudentGson;
import com.beyond.util.json.vo.StudentSerializerOrDeserializerGson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDateTime;


/**
 * 进阶部分
 *
 * @author huangyanbing
 * @date 2019-09-20 19:01
 */
@Slf4j
public class GsonJsonAdvancedUtils {
    /**
     * 泛型对象的反序列化
     * <p>
     * 利用 TypeToken 对象获取具体泛型类型 Result<User>, 然后在 fromJson 方法中传入就会根据对应类型的执行反序列化操作。
     *
     * @param student
     */
    public static void getGenericObjectByJson(String student) {
        Type type = new TypeToken<StudentGson<Address>>() {
        }.getType();
        StudentGson gsonS = new Gson().fromJson(student, type);
        log.info("Gson  is " + gsonS);
        Gson buildedGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        StudentGson buildedGsonS = buildedGson.fromJson(student, type);
        log.info("GsonBuilder is " + buildedGsonS);
    }

    /**
     * 如果我们要对Java 对象的某些字段进行特殊处理，比如隐藏某些字段的序列化，对字段的数据格式化处理等，
     * 我们可以通过实现 JsonSerializer 接口，对序列化逻辑进行自定义。例如，我们需要对 Date 类型属性进行特定格式的处理，
     **/
    public static void exeCustomSerialization() {
        StudentSerializerOrDeserializerGson studentSerializerOrDeserializerGson = new StudentSerializerOrDeserializerGson();
        studentSerializerOrDeserializerGson.setAge(25);
        studentSerializerOrDeserializerGson.setName("yanbing");
        studentSerializerOrDeserializerGson.setBirthdate(LocalDateTime.now());
        //在构建 Gson 对象前，利用 GsonBuilder 将 DateSerializer 实例进行注册
        //这样一来，一旦遇到要序列化 LocalDateTime 类型的字段时，都会通过自定义的 serialize 方法将日期以 yyyy年MM月dd日 HH:mm:ss E 格式进行输出
        Gson gsonBuilder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new DateSerializer()).create();
        String gsonBuilderS = gsonBuilder.toJson(studentSerializerOrDeserializerGson);
        log.info("GsonBuilder is " + gsonBuilderS);
    }

    /**
     * 与自定义序列化实现方式类似，想要自定义反序列化逻辑，就需要同样要实现一个叫 JsonDeserializer 的接口，进行自定义反序列化逻辑的实现
     **/
    public static void exeCustomDeserialization() {
    }

    /**
     * 注解@JsonAdapter
     * <p>
     * 注解@JsonAdapter 只作用于属性类型为类的上，主要作用就是代替GsonBuilder.registerTypeAdapter 方法的执行，
     * 直接通过 @JsonAdapter(DateSerializer.class) 方式指定 JsonDeserializer 对象或者 JsonSerializer 对象，可以起到相同的想过，
     * 并且优先级比GsonBuilder.registerTypeAdapter的优先级更高，由于只是将 registerTypeAdapter方法执行简化成了注解方法，
     **/
    public static void exeCustomSerializationAnnotation() {
        StudentSerializerOrDeserializerGson studentSerializerOrDeserializerGson = new StudentSerializerOrDeserializerGson();
        studentSerializerOrDeserializerGson.setAge(25);
        studentSerializerOrDeserializerGson.setName("yanbing");
        studentSerializerOrDeserializerGson.setBirthdate(LocalDateTime.now());
        //在构建 Gson 对象前，利用 GsonBuilder 将 DateSerializer 实例进行注册
        //这样一来，一旦遇到要序列化 LocalDateTime 类型的字段时，都会通过自定义的 serialize 方法将日期以 yyyy年MM月dd日 HH:mm:ss E 格式进行输出
        Gson gsonBuilder = new GsonBuilder().create();
        String gsonBuilderS = gsonBuilder.toJson(studentSerializerOrDeserializerGson);
        log.info("GsonBuilder is " + gsonBuilderS);
    }

    /**
     * 测试下面注解
     * <p>
     * 注解详细说名在StudentGson类中
     *
     * @Expose
     * @Since
     * @SerializedName
     */
    public static void exeAnnotation() {
        StudentGson<String> studentGson = new StudentGson();
        studentGson.setAddress("shanghai");
        studentGson.setAge(25);
        studentGson.setName("yanbing");
        Gson gson = new Gson();
        String gsonS = gson.toJson(studentGson);
        log.info("gson is " + gsonS);

        Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setVersion(1.0).setPrettyPrinting().serializeNulls().create();
        String buildedGson = gsonBuilder.toJson(studentGson);
        log.info("buildedGson is " + buildedGson);
    }
}
