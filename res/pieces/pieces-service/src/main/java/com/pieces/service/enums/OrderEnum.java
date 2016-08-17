package com.pieces.service.enums;

/**
 * Author: koabs
 * 8/17/16.
 * 订单状态
 */
public enum OrderEnum {
    // 待确认、待付款、付款审核中、等待发货、已发货、已完成、已取消、已删除
    UNPAID(1, "未付款"),
    PENDING_PAYMENT(2, "待付款"),
    VERIFY(3, "付款审核中"),
    WAIT_DELIVERY(4, "等待发货"),
    SHIPPED(5, "已发货"),
    COMPLETE(6, "已完成"),
    CANCEL(7, "已取消"),
    DELETED(8, "已删除");

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

}
