package com.pieces.dao.enums;

/**
 * Author: koabs
 * 8/30/16.
 */
public enum PayEnum {

    UNPAID(0, "待处理"),
    SUCCESS(1, "支付成功"),
    FAIL(2, "支付失败");


    private PayEnum(Integer value, String text) {
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

    /**
     * 通过ID来查询属性名称
     *
     * @param id
     * @return
     */
    public static String findByValue(Integer id) {
        for (PayEnum payEnum : PayEnum.values()) {
            if (payEnum.getValue().equals(id)) {
                return payEnum.getText();
            }
        }
        return null;
    }
}
