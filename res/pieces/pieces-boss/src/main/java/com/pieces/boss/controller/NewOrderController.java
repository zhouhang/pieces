package com.pieces.boss.controller;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangbin on 2016/8/17.
 */
@Controller
@RequestMapping("order")
public class NewOrderController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "member")
    public String newOrderIndex(Integer pageNum,
                                Integer pageSize,
                                UserVo userVo){
        PageInfo<User> userPage =  userService.findByCondition(userVo,pageNum,pageSize);

        return "order_member";
    }




}
