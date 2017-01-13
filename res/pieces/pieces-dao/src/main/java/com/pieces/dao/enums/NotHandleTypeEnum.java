package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/11/30.
 */
public  enum NotHandleTypeEnum {

    ACCOUNT_BILL_NUM(1,"未处理账单数目"),
    ENQUIRYBILL_NUM(2,"未处理询价数目"),
    CERTIFY_RECORD_NUM(3,"未处理认证数目"),
    ANON_ENQUIRY_NUM(4,"未处理新客询价数目"),
    PAY_RECORD_NUM(5,"未处理支付记录数目"),
    RECRUIT_AGENT_NUM(6,"未处理合作伙伴申请数目");


    private Integer value;

    private String text;


    NotHandleTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

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
        for (NotHandleTypeEnum notHandleTypeEnum : NotHandleTypeEnum.values()) {
            if (notHandleTypeEnum.getValue().equals(id)) {
                return notHandleTypeEnum.getText();
            }
        }
        return null;
    }
}
