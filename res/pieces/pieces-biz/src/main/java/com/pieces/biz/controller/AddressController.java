package com.pieces.biz.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.ShippingAddressService;
import com.pieces.service.constant.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by wangbin on 2016/8/15.
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{

    @Autowired
    private ShippingAddressService shippingAddressService;

    @RequestMapping("/index")
    @ResponseBody
    public Result index(){

        ShippingAddressVo shippingAddressVo = new ShippingAddressVo();
        shippingAddressVo.setId(1);
        PageInfo<ShippingAddressVo> page = shippingAddressService.findByParams(shippingAddressVo,1,10);
        return new Result(true).data(page);
    }


}
