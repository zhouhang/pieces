package com.pieces.dao.enums;

/**
 * Created by wangbin on 2016/9/6.
 */
public enum  BillEnum {

    REJECT(-1, "拒绝"),
    APPLY(0, "申请中"),
    UN_FINISH(1, "未完结"),
    ALREADY_FINISH(2, "已完结");

    //状态(-1:拒绝,0:s申请中,1:未完结,2:已完结)
    private Integer value;

    private String text;


     BillEnum(Integer value, String text) {
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
        for (BillEnum billEnum : BillEnum.values()) {
            if (billEnum.getValue().equals(id)) {
                return billEnum.getText();
            }
        }
        return null;
    }
}
