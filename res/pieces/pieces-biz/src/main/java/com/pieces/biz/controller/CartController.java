package com.pieces.biz.controller;

import com.pieces.service.CartsCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by xiao on 2017/2/21.
 */

/**
 * 购物车操作以及页面
 */
@Controller
@RequestMapping("/cart")
public class CartController {

       @Autowired
       HttpSession httpSession;

       @Autowired
       CartsCommodityService cartsCommodityService;











}
