package com.pieces.biz.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: koabs
 * 8/17/16.
 * 订单
 */
@Controller
@RequestMapping("center/order/")
public class OrderController {

    /**
     * 用户订单页面
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "order_list";
    }


    /**
     * 用户订单详情
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id")Integer id) {

        return "order_detail";
    }

    /**
     * 订单提交成功
     * @return
     */
    @RequestMapping(value = "success/{id}", method = RequestMethod.GET)
    public String success(@PathVariable("id")Integer id) {
        return "order_success";
    }


}
