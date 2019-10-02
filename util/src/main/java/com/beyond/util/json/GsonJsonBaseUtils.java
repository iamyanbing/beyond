package com.beyond.util.json;

import com.beyond.util.json.vo.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 基础部分
 * Gson缺点执行效率慢
 * <p>
 * 我们通常将对象与JSON字符串间的转换称之为序列化和反序列化(Serialization/Deserialization)。将对象转化成 JSON字符串的过程称为序列化，将JSON 字符串转化成对象的过程称为反序列化。
 * <p>
 * Gson 对象的创建主要有两种方式：
 * <p>
 * 使用 new 关键字直接创建：Gson gson = new Gson()
 * 由 GsonBuilder 对象构建：Gson gson = new GsonBuilder().create()
 * </p>
 * 通常情况下，上面两种方式创建的 Gson 对象在进行序列化与反序列操作时行为都是一样的，但是第二种方式构建 Gson 对象时，
 * 允许进行额外的行为定制，比如格式化 JSON 字符串的输出内容，是否序列化 null 值等等。
 * <p>
 * Gson 提供简易的API fromJson/toJson 来实现 Java 与 JSON 之间的转换
 * </p>
 *
 * @author huangyanbing
 * @date 2019-09-20 19:01
 */
@Slf4j
public class GsonJsonBaseUtils {

    /**
     * 简单对象的序列化(toJson方法)
     *
     * @param student
     * @return
     */
    public static String getJsonByObject(Student student) {
        Gson gson = new Gson();
        String json = gson.toJson(student);
        log.info("json is " + json);
        //setPrettyPrinting():格式化打印字符串
        //serializeNulls():打印null
        Gson buildedGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        String buildedJson = buildedGson.toJson(student);
        log.info("buildedJson is " + buildedJson);
        return json;

    }

    /**
     * JosnObject 生成 JSON
     *
     * @return
     */
    public static String getJsonByJsonObject() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        //JsonObject 使用 addProperty(property,value) 方法只能用来添加 String，Number，Boolean，Character这四类数据，
        jsonObject.addProperty("name", "yanbing");
        //如果需要在 JsonObject 对象上添加其他对象时，就需要直接使用 add(String property, JsonElement value) 方法添加
        // 一个 JsonElement 对象。这里的 JsonElement 是一个抽象类，JsonObject 和 JsonPrimitive 都继承了JsonElement，
        // 所以我们最终通过新的 JsonObject 对象来作为原 JsonObject 上的属性对象
        JsonObject nestJsonObject = new JsonObject();
        nestJsonObject.addProperty("city", "shanghai");
        jsonObject.add("address", nestJsonObject);
        String toJson = gson.toJson(jsonObject);
        log.info(toJson);
        return toJson;
    }

    /**
     * 简单对象的反序列化
     *
     * @param student
     */
    public static void getObjectByJson(String student) {
        Student gsonStudent = new Gson().fromJson(student, Student.class);
        log.info("Gson  is " + gsonStudent);
        Gson buildedGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        Student buildedGsonStudent = buildedGson.fromJson(student, Student.class);
        log.info("GsonBuilder is " + buildedGsonStudent);
    }

    /**
     * 反序列化 Map
     * <p>
     * 需要注意的是转换后的 Map 对象真实类型并不是我们经常用的 HashMap，而是 Gson 自定义集合LinkedTreeMap ，它实现Map 接口了，
     * 存储键值对，在新增和删除上实现上进行了优化，并且将存储键值对的顺序作为遍历顺序，也就是先存入的先被遍历到。除此之外，
     * JSON 字符串里的数值型数据都会转转换为 Double 类型，而 true/false 数据被会被转换成 Boolean 类型，
     * 具体判断依据可以参考 com.google.gson.internal.bind.ObjectTypeAdapter#read 方法实现。
     *
     * @param student
     */
    public static void getMapObjectByJson(String student) {
        Gson gson = new Gson();
        Map gsonMap = gson.fromJson(student, Map.class);
        log.info("Gson  is " + gsonMap);
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        Map buildedGsonMap = gsonBuilder.fromJson(student, Map.class);
        log.info("GsonBuilder is " + buildedGsonMap);
    }

    /**
     * JSON 转换 Array
     * <p>
     * 当我们正对 JSON 数据进行数组转换时，类似普通对象转换的方式即可。
     * oJson 方法直接使用转为 JSON 数据；使用fromJson 指定数组类型转换为对应类型的数组。
     */
    public static void getArrayObjectByJson() {
        Gson gson = new Gson();
        String[] gsonArray = gson.fromJson("[\"abc\",\"def\",\"ghi\"]", String[].class);
        log.info("Gson  is " + gsonArray);
        int[] gsonArrayInt = gson.fromJson("[1,2,3,4,5]", int[].class);
        log.info("Gson is " + gsonArrayInt);
    }

    /**
     * JSON 转换 List
     * <p>
     * 这个方法中的 Type 对象通过 TypeToken 对象的 getType 方法获取到，是 TypeToken 对象所关联的泛型类型。
     * 而这里 TypeToken 是 Gson 为了支持泛型而引入的类，来解决 Java 无法提供泛型类型表示的问题，
     * 由于 TypeToken 的构造方法是protected修饰的，无法直接构造，
     * 使用就需要写成new TypeToken<List<String>>() {}.getType() 形式。
     * 泛型在进阶部分详细讲解
     *
     * @param student
     */
    public static void getListObjectByJson(String student) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Student>>() {
        }.getType();
        List<Student> gsonList = gson.fromJson(student, type);
        log.info("gson is " + gsonList);

        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        List<Student> buildedGsonList = gsonBuilder.fromJson(student, type);
        log.info("gson is " + buildedGsonList);
    }

}
