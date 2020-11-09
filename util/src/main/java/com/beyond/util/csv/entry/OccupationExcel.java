package com.beyond.util.csv.entry;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.beyond.util.csv.dto.OccupationClassifyDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liucy
 * @description
 * @date 2020/5/13
 */
public class OccupationExcel {
    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOccupationClassifyName() {
        return occupationClassifyName;
    }

    public void setOccupationClassifyName(String occupationClassifyName) {
        this.occupationClassifyName = occupationClassifyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLifeInsurance() {
        return lifeInsurance;
    }

    public void setLifeInsurance(String lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    @Excel(name = "上级代码", fixedIndex = 0)
    private String pCode;

    @Excel(name = "代码", fixedIndex = 1)
    private String code;

    @Excel(name = "名称", fixedIndex = 2)
    private String occupationClassifyName;

    @Excel(name = "意外职业类别", fixedIndex = 3)
    private String category;

    @Excel(name = "寿险", fixedIndex = 4)
    private String lifeInsurance;


    public static OccupationClassifyDto covert(OccupationExcel occupationExcel) {
        OccupationClassifyDto occupationClassifyDto = new OccupationClassifyDto();
        if (StringUtils.isNotBlank(occupationExcel.getpCode())) {
            occupationClassifyDto.setPCode(occupationExcel.getpCode());
        } else {
            occupationClassifyDto.setPCode("0");
        }

        if (StringUtils.isNotBlank(occupationExcel.getCode())) {
            occupationClassifyDto.setCode(occupationExcel.getCode());
        }

        if (StringUtils.isNotBlank(occupationExcel.getOccupationClassifyName())) {
            occupationClassifyDto.setOccupationClassifyName(occupationExcel.getOccupationClassifyName());
        }

        if (StringUtils.isNotBlank(occupationExcel.getCategory())) {
            occupationClassifyDto.setCategory(Short.valueOf(occupationExcel.getCategory().trim()));
        }

        if (StringUtils.isNotBlank(occupationExcel.getLifeInsurance())) {
            occupationClassifyDto.setLifeInsurance(occupationExcel.getLifeInsurance());
        }
        return occupationClassifyDto;
    }

    public static List<OccupationClassifyDto> covert(List<OccupationExcel> occupationExcelList) {
        List<OccupationClassifyDto> occupationDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(occupationExcelList)) {
            for (OccupationExcel occupationExcel : occupationExcelList) {
                occupationDtoList.add(covert(occupationExcel));
            }
        }
        return occupationDtoList;
    }
}
