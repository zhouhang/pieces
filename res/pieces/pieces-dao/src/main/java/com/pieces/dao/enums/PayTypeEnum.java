package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/12/21.
 */
public enum PayTypeEnum {

    ALIPAY(1,"支付宝支付"),
    WXPAY(2,"微信支付");



    private PayTypeEnum(Integer value, String text) {
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
