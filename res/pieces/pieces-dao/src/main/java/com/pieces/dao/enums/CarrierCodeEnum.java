package com.pieces.dao.enums;

/**
 * Author: koabs
 * 3/28/17.
 * 快递公司编号枚举
 */
public enum CarrierCodeEnum {


    SF("SF", "顺丰快递"),
    YTO("YTO", "圆通快递"),
    ZTO("ZTO", "中通快递"),
    STO("STO", "申通快递 "),
    YD("YD", "韵达快递 "),
    HTKY("HTKY", "百世汇通");


    CarrierCodeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
     * @param code
     * @return
     */
    public static String findByValue(String code) {
        for (CarrierCodeEnum carrierCodeEnum : CarrierCodeEnum.values()) {
            if (carrierCodeEnum.getValue().equals(code)) {
                return carrierCodeEnum.getText();
            }
        }
        return null;
    }
}
