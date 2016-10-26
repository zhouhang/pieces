package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/19.
 */
public enum  CategoryEnum {

    STATUS_ON(1,"上架"),
    STATUS_OFF(0,"下架"),
    LEVEL_CATEGORY(1,"category"),
    LEVEL_BREED(2,"breed");

    private CategoryEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    private Integer value;
    private String text;
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

}
