package com.beyond.util.annotation;

import org.junit.Ignore;
import org.junit.Test;

public class AutoWiredAnnotationTest {
    @Ignore
    @Test
    public void testMethod() throws Exception {
        AutoWiredAnnotation.testMethod();
    }

    @Ignore
    @Test
    public void testAutoWired() {
        AutoWiredAnnotation.testAutoWired();
    }
}
