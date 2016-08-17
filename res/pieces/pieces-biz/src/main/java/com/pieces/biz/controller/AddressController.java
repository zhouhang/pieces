package com.pieces.biz.controller;

import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * Created by wangbin on 2016/8/15.
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{

    @Autowired
    private ShippingAddressService shippingAddressService;


    @RequestMapping("/user")
    @ResponseBody
    public List<ShippingAddressVo> findByUser(){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        List<ShippingAddressVo>  list =   shippingAddressService.findByUser(user.getId());
        return list;
    }

    /**
     * 保存收货地址
     * @param shippingAddress
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(ShippingAddress shippingAddress){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        shippingAddress.setUserId(user.getId());
        String message = "新增收获地址成功!";
        if(shippingAddress.getId()==null){
            shippingAddress.setCreateTime(new Date());
            shippingAddressService.create(shippingAddress);
        }else{
            shippingAddressService.update(shippingAddress);
            message="修改收获地址成功!";
        }
        return new Result(true).info(message).data(shippingAddress);
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Integer id){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        shippingAddressService.delete(user.getId(),id);
        return new Result(true).info("删除地址成功!");
    }


}
