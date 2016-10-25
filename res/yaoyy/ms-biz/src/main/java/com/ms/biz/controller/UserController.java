package com.ms.biz.controller;

import com.ms.biz.shiro.BizToken;
import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;
import com.ms.service.UserService;
import com.ms.service.enums.RedisEnum;
import com.ms.service.utils.CommonUtils;
import com.ms.tools.entity.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Author: koabs
 * 10/25/16.
 * 用户登入
 */
@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    UserService userService;

    /**
     * 账号密码登入页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * 短信验证码登入
     * @return
     */
    @RequestMapping(value = "loginSMS", method = RequestMethod.GET)
    public String loginSMSPage() {
        return "login_sms";
    }

    /**
     * 快速注册
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    /**
     * 账号密码登入
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String phone, String password) {

        // 登陆验证
        Subject subject = SecurityUtils.getSubject();
        BizToken token = new BizToken(phone, password, false, null, "");
        userService.login(subject, token);

        return "redirect:/index";
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String loginOut() {
        userService.logout();
        return "redirect:/user/login";
    }

    /**
     * 用户注册
     * @param phone
     * @param code
     * @param password
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(String phone, String code, String password, ModelMap model) {
        try {
            userService.register(phone, code, password);
            Subject subject = SecurityUtils.getSubject();
            BizToken token = new BizToken(phone, password, false, null, "");
            userService.login(subject, token);
        } catch (Exception e) {
            model.put("error",e.getMessage());
            return "register";
        }


        return "redirect:/index";
    }

    /**
     * 短信登入
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "loginSMS", method = RequestMethod.POST)
    public String loginSMS(String phone, String code, ModelMap model) {
        try {
            userService.loginSms(phone, code);
            Subject subject = SecurityUtils.getSubject();
            BizToken token = new BizToken(phone, null, false, null, "");
            userService.login(subject, token);
        } catch (Exception e) {
            model.put("error",e.getMessage());
            return "login_sms";
        }

        return "redirect:/index";
    }


    @RequestMapping(value = "sendRegistSms", method = RequestMethod.POST)
    @ResponseBody
    public Result sendRegistSms(String phone) {
        userService.sendRegistSms(phone);
        return Result.success();
    }

    @RequestMapping(value = "sendLoginSms", method = RequestMethod.POST)
    @ResponseBody
    public Result sendLoginSms(String phone) {
        userService.sendLoginSms(phone);
        return Result.success();
    }

}
