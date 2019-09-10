package com.beyond.util.excel;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author huangyanbing
 * @date 2019-09-10 10:01
 */
public class ExcelParseTest {
    //    @Ignore
    @Test
    public void Lambda() throws Exception {
//        ExcelParse excelParse=new ExcelParse();
//        excelParse.parse("src/main/resources/excel/info.xlsx");
        ExcelParse.parse("src/main/resources/excel/info.xlsx");
    }
}
