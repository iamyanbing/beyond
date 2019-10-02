package com.beyond.util.json.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import lombok.Data;

/**
 * <pre>
 *     @Expose
 *     这个注解只能用在字段上，作用就是注明对应的字段是否将在序列化或者反序列化时暴露出来，
 *     有两个属性 serialize 和 deserialize ，默认都为 true。
 *     当给一个字段加上 注解@Expose(serialize = true, deserialize = false)，
 *     则表示了该字段尽在序列化时可见，在反序列化时会忽略赋值。
 *     需要额外注意的一点是，@Expose 注解只有在用 GsonBuilder 方式构建 Gson 时有效，并且构建前必须调用 excludeFieldsWithoutExposeAnnotation 方法，否则解析时对声明注解的字段没有任何效果，
 *
 *     在 Gson 中 transient 关键字修饰的字段默认不会被序列化和反序列化，这个行为是与 Java 原生的序列化和反序列化操作一致的。
 *
 *     只要有一个字段中引用了@Expose注解，其他想要序列化和反序列化的字段都要引入该注解
 * </pre>
 * <pre>
 *     @Since
 *     该注解用于标记对应字段或者类型的版本，让 Gson 可以指定版本号进行序列化和反序列化操作。当Web服务上的 JSON 数据对应的实体类存在多个版本的字段时，这个注解就十分有用。
 *     同样地，该注解只针对使用 GsonBuilder 方式构建的 Gson 对象，并且使用 setVersion 方法指明版本号时有效，设置后只会解析对象中对应版本的字段，
 *
 *     字段上没有出现@Since则该字段默认进行序列化和反序列化
 * </pre>
 * <pre>
 *     @SerializedName
 *     指定了成员字段被序列化和反序列化时所采用的名称，便于我们调整JSON数据与对应实体类字段名不一致的问题
 *
 *     字段上没有出现@SerializedName则该字段进行序列化和反序列化时默认用属性名
 *   </pre>
 *
 * @Auther: yanbing
 * @Date: 2018/6/30 14:19
 */
@Data
public class StudentGson<T> {
    //    @Since(1.0)
    @Expose
    @SerializedName("Name")
    private String name;
    @Since(1.0)
    @Expose(serialize = false, deserialize = true)
    @SerializedName("Age")
    private Integer age;
    @Since(1.1)
    @Expose
    @SerializedName("Address")
    private T address;
}
