package com.pieces.service.listener;

import com.pieces.dao.event.OrderStatusEvent;
import com.pieces.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Author: koabs
 * 8/31/16.
 */
@Component
public class OrderStatusListener implements ApplicationListener<OrderStatusEvent> {

    @Autowired
    OrderFormService orderFormService;

    @Override
    public void onApplicationEvent(OrderStatusEvent event) {
        orderFormService.changeOrderStatus(event.getOrderId(),event.getStatus());
    }
}
