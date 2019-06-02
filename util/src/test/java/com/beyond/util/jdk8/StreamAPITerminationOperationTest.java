package com.beyond.util.jdk8;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 */
public class StreamAPITerminationOperationTest {

    @Ignore
    @Test
    public void Lambda() throws Exception {
        StreamAPITerminationOperation.exeBase();
        StreamAPITerminationOperation.exeReduce();
        StreamAPITerminationOperation.exeCollect();
    }

}
