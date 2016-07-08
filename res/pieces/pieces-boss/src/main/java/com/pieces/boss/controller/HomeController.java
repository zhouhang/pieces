package com.pieces.boss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pieces.boss.shiro.BossToken;
import com.pieces.dao.model.Member;
import com.pieces.service.MemberService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.tools.utils.WebUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * BOSS系统首页和登录
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController extends BaseController{

    private static final Logger logger = Logger.getLogger(HomeController.class);


    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {
        return "redirect:/user/index";
    }

    /**
     * 登录页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request,
                        HttpServletResponse response) {
        return "login";
    }

    /**
     *
     * @param request
     * @param response
     * @param username
     * @param password
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginSubmit(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam(required = true) String username,
                            @RequestParam(required = true) String password) {

        Subject subject = SecurityUtils.getSubject();
        BossToken token = new BossToken(username, password, false, CommonUtils.getRemoteHost(request), "");
        try {
            subject.login(token);
            WebUtil.print(response, new Result(true));
        } catch (Exception e) {
            e.printStackTrace();
            WebUtil.print(response, new Result(false).info("用户名密码错误!"));
        }
    }


}
