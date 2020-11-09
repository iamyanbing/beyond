package com.beyond.util.csv.dto;

import lombok.Data;

/**
 * @author pengcheng
 * @date 2020/4/20 22:00
 * @description
 */
@Data
public class OccupationClassifyDto {

    private String pCode;

    private String code;

    private String occupationClassifyName;

    private String lifeInsurance;

    private Short category;
}
