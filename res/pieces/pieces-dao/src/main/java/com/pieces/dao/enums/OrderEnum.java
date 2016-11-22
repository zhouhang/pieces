package com.pieces.dao.enums;

/**
 * Author: koabs
 * 8/17/16.
 * 订单状态
 */
public enum OrderEnum {
    // 待确认、待付款、付款审核中、等待发货、已发货、已完成、已取消、已删除
    UNPAID(1, "待付款"),
    VERIFY(2, "付款待确认"),
    WAIT_DELIVERY(3, "等待发货"),
    SHIPPED(4, "已发货"),
    COMPLETE(5, "已完成"),
    CANCEL(6, "已取消"),
    DELETED(7, "已删除"),
    SHIPPED_FAIL(8, "发货失败");

    private OrderEnum(Integer value, String text) {
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
     * @param id
     * @return
     */
    public static String findByValue(Integer id){
        for(OrderEnum orderEnum :OrderEnum.values() ){
            if(orderEnum.getValue().equals(id)){
                return orderEnum.getText();
            }
        }
        return null;
    }

}
