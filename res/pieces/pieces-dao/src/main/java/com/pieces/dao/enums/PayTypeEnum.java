package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/12/21.
 */
public enum PayTypeEnum {

    ORIGINPAY(0,"线下打款"),
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
    /**
     * 通过ID来查询属性名称
     *
     * @param id
     * @return
     */
    public static String findByValue(Integer id) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (payTypeEnum.getValue().equals(id)) {
                return payTypeEnum.getText();
            }
        }
        return null;
    }

}
