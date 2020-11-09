package com.beyond.util.excel;

import com.beyond.util.http.HttpClientUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author huangyanbing
 * @date 2019-09-09 19:16
 */

public class ExcelParse {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    public static void parse(String fileName) throws Exception {
        //第一种读取文件方法
//        InputStream inputStream = new FileInputStream("src/main/resources/excel/info.xlsx");
        //第二种读取文件方法
        InputStream inputStream = ExcelParse.class.getClassLoader().getResourceAsStream("excel/info.xlsx");
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("excel/info.xlsx");
        //第三种方法
        File file = new File(fileName);
        InputStream in = new FileInputStream(file);
        //得到整个excel对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        //获取整个excel有多少个sheet
        int sheets = xssfWorkbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            //获取最后一行的num。总行数 = num + 1
            int maxRow = sheet.getLastRowNum();
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            LOGGER.info("maxRow :" + maxRow);
            LOGGER.info("physicalNumberOfRows :" + physicalNumberOfRows);
            for (int row = 0; row <= maxRow; row++) {
                //获取最后单元格num，即总单元格数。注意：此处从1开始计数,但是获取还是要从0获取
                int maxCol = sheet.getRow(row).getLastCellNum();
                int physicalNumberOfCells = sheet.getRow(row).getPhysicalNumberOfCells();
                LOGGER.info("maxCol :" + maxCol);
                LOGGER.info("physicalNumberOfCells :" + physicalNumberOfCells);
                LOGGER.info("--------第" + row + "行的数据如下--------");
                for (int col = 0; col < maxCol; col++) {
                    LOGGER.info(sheet.getRow(row).getCell(col) + "  ");
                }
            }
        }
    }
}
