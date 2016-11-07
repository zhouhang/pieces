package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;
import com.ms.service.UserService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 10/13/16.
 * 用户管理列表
 */
@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(UserVo user, Integer pageNum, Integer pageSize, ModelMap model){
        PageInfo<UserVo> pageInfo = userService.findByParams(user, pageNum, pageSize);
        model.put("pageInfo", pageInfo);
        return "user_list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result detail(@PathVariable("id")Integer id){
        return Result.success("用户详情").data(userService.findById(id));
    }

    /**
     * 禁用用户
     * @param id
     * @return
     */
    @RequestMapping(value = "disable/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result disable(@PathVariable("id")Integer id) {
        userService.disable(id);
        return Result.success("禁用成功!");
    }

    /**
     * 启用账号
     * @param id
     * @return
     */
    @RequestMapping(value = "enable/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result enable(@PathVariable("id")Integer id) {
        userService.enable(id);
        return Result.success("启用成功!");
    }

}
