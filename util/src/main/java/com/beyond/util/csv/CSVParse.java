package com.beyond.util.csv;

import cn.afterturn.easypoi.csv.CsvImportUtil;
import cn.afterturn.easypoi.csv.entity.CsvImportParams;
import com.beyond.util.csv.dto.OccupationClassifyDto;
import com.beyond.util.csv.entry.OccupationExcel;
import com.beyond.util.http.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author huangyanbing
 * @date 2019-09-09 19:16
 */

public class CSVParse {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    public static void parse() throws Exception {
        File tmpfile = new File("src/main/resources/csv/info.csv");
        InputStream fileInputStream = new FileInputStream(tmpfile);
        CsvImportParams params = new CsvImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        params.setEncoding("GBK");
        OccupationExcel occupationExcel = new OccupationExcel();
        occupationExcel.setpCode("");
        List<OccupationExcel>  occupationExcelList = CsvImportUtil.importCsv(fileInputStream, OccupationExcel.class, params);

        List<OccupationClassifyDto> dtos = OccupationExcel.covert(occupationExcelList);
        LOGGER.info(dtos.toString());
    }

}
