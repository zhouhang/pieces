package com.pieces.service.enums;

/**
 * Created by wangbin on 2016/8/18.
 */
public enum PathEnum {

    COMMODITY("commodity/"),WOOL("wool/");

    private String value;


    PathEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
