package com.ms.boss.controller;

import com.ms.dao.model.Admin;
import com.ms.tools.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 10/13/16.
 */
@Controller
@RequestMapping("admin/")
public class AdminController {

    /**
     *  管理员列表
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Integer pageNum, Integer pageSize, ModelMap model){
        return null;
    }


    /**
     * 详情页面
     * @param id
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model){
        return null;
    }

    /**
     *  登入页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return null;
    }

    /**
     *  登入
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String username, String password){
        return null;
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "loginOut", method = RequestMethod.POST)
    @ResponseBody
    public Result loginOut() {
        return null;
    }

    /**
     * 保存
     * @param admin
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(Admin admin) {
        return null;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "detete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id) {
        return null;
    }

}
