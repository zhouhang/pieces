package com.pieces.boss.controller;

import com.pieces.dao.model.Member;
import com.pieces.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BOSS用户管理
 * Created by wangbin on 2016/7/8.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController extends BaseController{

    @Autowired
    private MemberService memberService;

    /**
     * BOSS用户列表页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                        HttpServletResponse response){
        return "member";
    }

    /**
     * BOSS用户编辑页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String info(HttpServletRequest request,
                       HttpServletResponse response){
        return "member-info";
    }


    /**
     * BOSS用户存储页面
     * @param request
     * @param response
     * @param member
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(HttpServletRequest request,
                     HttpServletResponse response,
                     Member member){


    }



}
