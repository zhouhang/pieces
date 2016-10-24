package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/24.
 */
public enum TrackingDetailEnum {

    TYPE_SEND(0,"寄送信息"),
    TYPE_ORDER(1,"来访信息");




    private TrackingDetailEnum (Integer value, String text) {
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
