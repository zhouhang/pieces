package com.pieces.boss.controller;

import com.pieces.dao.OrderFormDao;
import com.pieces.dao.OrderRemarkDao;
import com.pieces.dao.model.OrderRemark;
import org.apache.commons.io.ByteOrderMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Author: koabs
 * 8/15/16.
 */
@Controller
@RequestMapping("order/")
public class OrderController {

    @Autowired
    OrderRemarkDao orderRemarkDao;

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        OrderRemark orderRemark = new OrderRemark();
        orderRemark.setContent("sd");
        orderRemark.setCreaterTime(new Date());
        orderRemark.setUserId(12);
        orderRemark.setOrderId(1);
        orderRemarkDao.create(orderRemark);
        return  "";
    }
}
