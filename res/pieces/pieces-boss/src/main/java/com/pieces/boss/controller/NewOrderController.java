package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.*;
import com.pieces.service.*;
import com.pieces.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangbin on 2016/8/17.
 */
@Controller
@RequestMapping("order")
public class NewOrderController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private EnquiryBillsService enquiryBillsService;
    @Autowired
    private OrderFormService orderFormService;
    @Autowired
    private OrderCommodityService orderCommodityService;

    /**
     * 新建订单客户列表
     * @param pageNum
     * @param pageSize
     * @param model
     * @param userVo
     * @return
     */
    @RequestMapping(value = "customer")
    public String customerOrderIndex(Integer pageNum,
                                     Integer pageSize,
                                     ModelMap model,
                                     UserVo userVo){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageInfo<User> customerPage =  userService.findByCondition(userVo,pageNum,pageSize);
        model.put("customerParams", Reflection.serialize(userVo));
        model.put("customerPage",customerPage);
        return "order_customers";
    }

    /**
     * 创建订单页面
     * @param customerId
     * @return
     */
    @RequestMapping(value = "create/{customerId}")
    public String createOrder(@PathVariable("customerId") Integer customerId,
                              ModelMap model){
        User user = userService.findById(customerId);
        //查询用户最近5条询价记录
        EnquiryRecordVo  enquiryRecordVo = new EnquiryRecordVo();
        enquiryRecordVo.setUserId(user.getId());
        enquiryRecordVo.setStatus(1);
        PageInfo<EnquiryBills> billsPageInfo =  enquiryBillsService.findByPage(1,5,enquiryRecordVo);

        //订单form
        PageInfo<OrderFormVo>  orderFormVoPageInfo = orderFormService.findOrderByUserId(customerId,1,1);
        List<OrderFormVo> orderList = orderFormVoPageInfo.getList();
        if(!orderList.isEmpty()){
            OrderFormVo orderFormVo = orderList.get(0);
            List<OrderCommodityVo> orderCommodityList =  orderCommodityService.findByOrderId(orderFormVo.getId());
            orderFormVo.setCommodityVos(orderCommodityList);
            model.put("orderFormVo", orderFormVo);
        }
        model.put("billsPage",billsPageInfo);
        model.put("user",user);
        return "order_create";
    }


    @RequestMapping(value = "member/address")
    @ResponseBody
    public List<ShippingAddressVo> memberAddress(Integer memberId){
        List<ShippingAddressVo> shippingAddressVos = shippingAddressService.findByUser(memberId);
        return shippingAddressVos;
    }







}
