package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.UserService;
import com.pieces.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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



    @RequestMapping(value = "member/address")
    @ResponseBody
    public List<ShippingAddressVo> memberAddress(Integer memberId){
        List<ShippingAddressVo> shippingAddressVos = shippingAddressService.findByUser(memberId);
        return shippingAddressVos;
    }



    @RequestMapping(value = "create")
    public void  createOrder(){


    }





}
