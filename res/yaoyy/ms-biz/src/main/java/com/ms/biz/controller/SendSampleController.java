package com.ms.biz.controller;

import com.ms.dao.enums.*;
import com.ms.dao.model.*;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.*;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    SampleTrackingService sampleTrackingService;



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
        UserDetail userDetail=userDetailService.findById(userInfo.getId());
        if (userDetail==null){
            userDetail=new UserDetail();
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
        }
        else{
            userDetail.setPhone(sendSampleVo.getPhone());
            userDetail.setNickname(sendSampleVo.getNickname());
            userDetail.setArea(sendSampleVo.getArea());
            userDetail.setUpdateTime(now);
            userDetailService.update(userDetail);
        }





        SendSample sendSample=new SendSample();
        sendSample.setUserId(userInfo.getId());
        sendSample.setStatus(SampleEnum.SAMPLE_NOTHANDLE.getValue());
        sendSample.setIntention(sendSampleVo.getIntention());
        sendSample.setUpdateTime(now);
        sendSample.setCreateTime(now);
        sendSample.setCode("");
        sendSampleService.create(sendSample);
        sendSample.setCode(SeqNoUtil.get("", sendSample.getId(), 6));
        sendSampleService.update(sendSample);
        //获取登陆状态后设置
        userInfo.setIslogin(false);

        return Result.success().data(userInfo);
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String apply(String name,ModelMap model) {
        //获取登陆用户userId
        int userId=1;
        List<SendSampleVo> sampleList = new ArrayList<>();
        if(name!=null) {
            List<Commodity> commodities = commodityService.findByName(name);
            if (commodities.size() != 0) {
                List<Integer> list = new ArrayList<>();
                for (Commodity c : commodities) {
                    list.add(c.getId());
                }
                sampleList = sendSampleService.findByCommodityId(userId,list);
            }
        }
        else{
            sampleList = sendSampleService.findByUserId(userId);
        }
        model.put("sampleList",sampleList);
        model.put("name",name);
        return "sample_list";

    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model) {

        SendSampleVo sendSampleVo=sendSampleService.findDetailById(id);
        List<Commodity> commodityList = commodityService.findByIds(sendSampleVo.getIntention());
        sendSampleVo.setCommodityList(commodityList);

        SampleTrackingVo sampleTrackingVo=new SampleTrackingVo();
        sampleTrackingVo.setSendId(sendSampleVo.getId());
        List<SampleTrackingVo> trackingList=sampleTrackingService.findAllByParams(sampleTrackingVo);
        //过滤点不用给用户显示的type


        model.put("sendSampleVo",sendSampleVo);
        model.put("trackingList",trackingList);
        return "sample_detail";

    }
    @RequestMapping(value = "msg", method = RequestMethod.GET)
    public String getMsg(){
        return "sample_msg";
    }

    /**
     * 用户留言或是确认收货
     * @param sampleTracking
     * @return
     */
    @RequestMapping(value = "feedBack", method = RequestMethod.POST)
    @ResponseBody
    public Result feedBack(SampleTracking sampleTracking){
        //session获取用户信息

        int userId=1;
        Date now=new Date();
        sampleTracking.setOperator(userId);
        sampleTracking.setName("测试肖");
        if(sampleTracking.getExtra()==null){
            sampleTracking.setExtra("");
        }
        sampleTracking.setCreateTime(now);

        sampleTrackingService.create(sampleTracking);
        return Result.success().data("提交成功");
    }






}
