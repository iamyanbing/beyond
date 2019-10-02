package com.beyond.util.json;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author huangyanbing
 * @date 2019-09-20 19:16
 */
public class GsonJsonAdvancedUtilsTest {

    @Test
    @Ignore
    public void exeAnnotation() {
        GsonJsonAdvancedUtils.exeAnnotation();
    }

    @Test
    @Ignore
    public void getGenericObjectByJson() {
        GsonJsonAdvancedUtils.getGenericObjectByJson("{\"Name\":\"yanbing\",\"Age\":25,\"Address\":{\"city\":\"shanghai\",\"country\":\"China\"}}");
    }

    @Test
    @Ignore
    public void exeCustomSerialization() {
        GsonJsonAdvancedUtils.exeCustomSerialization();
    }

    @Test
    @Ignore
    public void exeCustomSerializationAnnotation() {
        GsonJsonAdvancedUtils.exeCustomSerializationAnnotation();
    }
}
