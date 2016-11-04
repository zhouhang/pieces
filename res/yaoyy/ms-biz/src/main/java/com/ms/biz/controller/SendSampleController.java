package com.ms.biz.controller;

import com.ms.dao.enums.*;
import com.ms.dao.model.*;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.dao.vo.SendSampleVo;
import com.ms.dao.vo.UserVo;
import com.ms.service.*;
import com.ms.service.enums.RedisEnum;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    SendSampleService sendSampleService;

    @Autowired
    UserService userService;

    @Autowired
    SampleTrackingService sampleTrackingService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserDetailService userDetailService;




    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String apply(String name,ModelMap model) {
        //获取登陆用户userId
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        int userId=user.getId();
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
        if(id==null|| sendSampleVo==null){
            return "redirect:error/404";
        }

        SampleTrackingVo sampleTrackingVo=new SampleTrackingVo();
        sampleTrackingVo.setSendId(sendSampleVo.getId());
        List<SampleTrackingVo> trackingList=sampleTrackingService.findAllByParams(sampleTrackingVo);


        model.put("sendSampleVo",sendSampleVo);
        model.put("trackingList",trackingList);
        return "sample_detail";

    }
    @RequestMapping(value = "msg/{id}", method = RequestMethod.GET)
    public String getMsg(@PathVariable("id") Integer id,ModelMap model){
        if(id==null||sendSampleService.findById(id)==null){
            return "redirect:error/404";
        }
        model.put("sendId",id);
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
        User user = (User) httpSession.getAttribute(RedisEnum.USER_SESSION_BIZ.getValue());
        int userId=user.getId();
        UserDetail userDetail=userDetailService.findByUserId(userId);
        sampleTracking.setOperator(userId);
        if (userDetail!=null){
            sampleTracking.setName(userDetail.getNickname());
        }
        else{
            sampleTracking.setName("");
        }
        sampleTracking.setType(TrackingTypeEnum.TYPE_USER.getValue());


        sampleTrackingService.save(sampleTracking,null);
        return Result.success().data("提交成功");
    }






}
