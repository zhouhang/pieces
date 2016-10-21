package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/21.
 */
public enum TrackingTypeEnum {


    TYPE_ADMIN(0,"后台用户"),
    TYPE_USER(1,"前台用户");




    private TrackingTypeEnum (Integer value, String text) {
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
