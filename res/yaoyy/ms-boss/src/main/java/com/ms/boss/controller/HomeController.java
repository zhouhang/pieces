package com.ms.boss.controller;

import com.ms.boss.shiro.BossToken;
import com.ms.dao.model.Member;
import com.ms.service.MemberService;
import com.ms.tools.entity.Result;
import com.ms.service.enums.RedisEnum;
import com.ms.service.utils.CommonUtils;
import com.ms.tools.utils.WebUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return "login";
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
    @ResponseBody
    public Result loginSubmit(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam(required = true) String username,
                            @RequestParam(required = true) String password) {

        Subject subject = SecurityUtils.getSubject();
        BossToken token = new BossToken(username, password, false, CommonUtils.getRemoteHost(request), "");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().msg("用户名密码错误!");
        }

        // 存入用户信息到session
        Member mem = memberService.findByUsername(token.getUsername());
		Session s = subject.getSession();
		s.setAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue(), mem);
		return Result.success();
    }

    /**
     * 退出系统
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        // 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    /**
     * 控制台首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
