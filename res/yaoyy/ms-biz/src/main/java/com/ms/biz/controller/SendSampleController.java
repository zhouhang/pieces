package com.ms.biz.controller;

import com.ms.dao.enums.SampleEnum;
import com.ms.dao.enums.UserTypeEnum;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.SendSample;
import com.ms.dao.model.User;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.CommodityService;
import com.ms.service.SendSampleService;
import com.ms.service.UserDetailService;
import com.ms.service.UserService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by xiao on 2016/10/24.
 */

@Controller
@RequestMapping("sample/")
public class SendSampleController {

    @Autowired
    CommodityService commodityService;

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    SendSampleService sendSampleService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "apply", method = RequestMethod.GET)
    public String apply(Integer commdityId,ModelMap model) {

        Commodity commodity=commodityService.findById(commdityId);

        model.put("commodity", commodity);
        return "apply_sample";
    }


    @RequestMapping(value = "apply", method = RequestMethod.POST)
    @ResponseBody
    public Result applySample(SendSampleVo sendSampleVo) {

        UserVo userVo=new UserVo();
        userVo.setPhone(sendSampleVo.getPhone());
        UserVo userInfo=userService.findByPhone(userVo);
        //如果用户注册

        Date now=new Date();

        if (userInfo==null){
             User user=new User();
             user.setPhone(sendSampleVo.getPhone());
             user.setType(UserTypeEnum.TYPE_APPLY_SAMPLE.getValue());
             user.setSalt("");
             user.setPassword("");
             user.setOpenid("");
             user.setUpdateTime(now);
             user.setCreateTime(now);
             userService.create(user);

             userInfo=userService.findByPhone(userVo);
        }

        UserDetail userDetail=new UserDetail();
        userDetail.setPhone(sendSampleVo.getPhone());
        userDetail.setNickname(sendSampleVo.getNickname());
        userDetail.setArea(sendSampleVo.getArea());
        userDetail.setUserId(userInfo.getId());
        userDetail.setName("");
        userDetail.setRemark("");
        userDetail.setType(0);
        userDetail.setUpdateTime(now);
        userDetail.setCreateTime(now);
        userDetailService.create(userDetail);
        SendSample sendSample=new SendSample();
        sendSample.setUserId(userInfo.getId());
        sendSample.setCode("2016111112");
        sendSample.setStatus(SampleEnum.SAMPLE_NOTHANDLE.getValue());
        sendSample.setIntention(sendSampleVo.getIntention());
        sendSample.setUpdateTime(now);
        sendSample.setCreateTime(now);
        sendSampleService.create(sendSample);
        //获取登陆状态后设置
        userInfo.setIslogin(false);

        return Result.success().data(userInfo);
    }



}
