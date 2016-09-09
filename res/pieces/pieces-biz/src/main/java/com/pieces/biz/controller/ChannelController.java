package com.pieces.biz.controller;

import com.pieces.dao.model.User;
import com.pieces.dao.vo.ShippingAddressVo;
import com.pieces.service.enums.RedisEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by feng on 2016/9/9.
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController{

    @RequestMapping("/prescription")
    public String prescription(){
        return "prescription_list";
    }
}
