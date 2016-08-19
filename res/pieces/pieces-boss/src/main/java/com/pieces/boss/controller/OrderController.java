package com.pieces.boss.controller;

import com.pieces.dao.OrderFormDao;
import com.pieces.dao.OrderRemarkDao;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.OrderRemark;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.service.OrderFormService;
import org.apache.commons.io.ByteOrderMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Author: koabs
 * 8/15/16.
 */
@Controller
@RequestMapping("order/")
public class OrderController extends BaseController{

    @Autowired
    OrderRemarkDao orderRemarkDao;

    @Autowired
    OrderFormService orderFormService;

    /**
     * 订单列表页面
     * @return
     */
    @RequestMapping("index")
    public String index(OrderFormVo vo,Integer pageSize, Integer pageNum){
        orderFormService.findByParams(vo,pageNum,pageSize);
        return  "order";
    }

    /**
     * 订单详情
     * @param id
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id){
        orderFormService.findVoById(id);
        return  "order_detail";
    }

}
