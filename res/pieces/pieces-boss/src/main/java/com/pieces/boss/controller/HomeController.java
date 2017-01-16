package com.pieces.boss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pieces.boss.commons.LogConstant;
import com.pieces.boss.shiro.BossToken;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.User;
import com.pieces.service.MemberService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.utils.CommonUtils;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BOSS系统首页和登录
 * Created by wangbin on 2016/6/28.
 */
@Controller
public class HomeController extends BaseController{


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {
        return "home";
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
    @BizLog(type = LogConstant.user, desc = "用户登入")
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
            logger.info("subject.login Exception {} ",e.getMessage());
            return  new Result(false).info("用户名密码错误!");
        }

        // 存入用户信息到session
        Member mem = memberService.findByUsername(token.getUsername());
		Session s = subject.getSession();
		s.setAttribute(RedisEnum.MEMBER_SESSION_BOSS.getValue(), mem);
		Result result = new Result(true);
        String url="/";
        if ( WebUtils.getSavedRequest(request) != null) {
            url =  WebUtils.getSavedRequest(request).getRequestUrl();
            if(url.equals("/favicon.ico")){
                url="/";
            }
        }
		return result.data(url);
    }


}
