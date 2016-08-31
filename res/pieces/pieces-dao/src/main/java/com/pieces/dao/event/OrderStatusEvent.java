package com.pieces.dao.event;

import org.springframework.context.ApplicationEvent;

/**
 * Author: koabs
 * 8/31/16.
 */
public class OrderStatusEvent extends ApplicationEvent {

    private Integer orderId;
    private Integer status;

    public OrderStatusEvent(Object source) {
        super(source);
    }


    public OrderStatusEvent(Integer orderId, Integer status) {
        super(orderId);
        this.orderId = orderId;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getStatus() {
        return status;
    }
}


