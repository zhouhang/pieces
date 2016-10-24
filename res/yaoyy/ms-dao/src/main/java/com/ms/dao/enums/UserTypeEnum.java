package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/24.
 */
public enum UserTypeEnum {

    TYPE_REJISTER(1,"注册用户"),
    TYPE_APPLY_SAMPLE(0,"未注册申样用户");




    private UserTypeEnum (Integer value, String text) {
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
