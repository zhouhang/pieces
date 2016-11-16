package com.pieces.biz.controller;

import com.pieces.dao.model.User;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.UserCertificationService;
import com.pieces.service.UserQualificationService;
import com.pieces.service.UserService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by xiao on 2016/11/16.
 */
//用户认证
@Controller
@RequestMapping(value = "/center/certificate")
public class UserCertificateController {


    @Autowired
    HttpSession httpSession;

    @Autowired
    UserQualificationService userQualificationService;

    @Autowired
    UserCertificationService userCertificationService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/stepOne", method = RequestMethod.GET)
    public String stepOne(){
        return "certificate_1";
    }

    @RequestMapping(value = "/stepTwo", method = RequestMethod.GET)
    public String stepTwo(){
        return "certificate_2";
    }

    @RequestMapping(value = "/stepThree", method = RequestMethod.GET)
    public String stepThree(){
        return "certificate_3";
    }

    @RequestMapping(value = "/stepOne", method = RequestMethod.POST)
    @ResponseBody
    public Result stepOnePost(UserCertificationVo userCertificationVo){
        httpSession.setAttribute(RedisEnum.USER_SESSION_CERTIFICATION.getValue(),userCertificationVo);
        return new Result(true).info("提交成功");
    }

    @RequestMapping(value = "/stepTwo", method = RequestMethod.POST)
    @ResponseBody
    public Result stepTwoPost(@RequestBody List<UserQualificationVo> userQualificationVos){
        UserCertificationVo certificationVo=( UserCertificationVo)httpSession.getAttribute(RedisEnum.USER_SESSION_CERTIFICATION.getValue());
        User user = (User)httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        if(certificationVo==null){
            return new Result(false).info("session 过期");
        }
        Date now=new Date();
        certificationVo.setUserId(user.getId());
        certificationVo.setCreateTime(now);
        certificationVo.setUpdateTime(now);
        userCertificationService.create(certificationVo);
        for(UserQualificationVo userQualificationVo:userQualificationVos){
            userQualificationVo.setUserId(user.getId());
            userQualificationVo.setCreateTime(now);
            userQualificationVo.setUpdateTime(now);
            userQualificationService.create(userQualificationVo);
        }
        user.setCertifyStatus();
        userService.update(user);
        return new Result(true).info("提交成功");
    }






}
