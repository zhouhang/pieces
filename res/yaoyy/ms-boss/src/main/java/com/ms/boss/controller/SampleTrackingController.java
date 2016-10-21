package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.enums.TrackingTypeEnum;
import com.ms.dao.model.Admin;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.service.AdminService;
import com.ms.service.SampleTrackingService;
import com.ms.service.TrackingDetailService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiao on 2016/10/18.
 */

@Controller
@RequestMapping("tracking/")
public class SampleTrackingController {

    @Autowired
    SampleTrackingService sampleTrackingService;

    @Autowired
    TrackingDetailService trackingDetailService;

    @Autowired
    AdminService adminService;

    /**
     * 创建寄样单跟踪记录
     * @param sampleTracking
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Result createTracking(SampleTracking sampleTracking){
        //如果是用户预约或是寄送样品另外保存详细信息
        //要t通过session取后台用户id
        int userId=1;
        sampleTracking.setOperator(userId);
        sampleTracking.setType(TrackingTypeEnum.TYPE_ADMIN.getValue());
        /*
        Admin admin=adminService.findById(userId);
        sampleTracking.setName(admin.getUsername());
        */
        sampleTracking.setName("测试肖");

        /*
        if(sampleTracking.getType()==3 ||sampleTracking.getType()==4){
            TrackingDetail trackingDetail=new TrackingDetail();

            trackingDetailService.create(trackingDetail);
        }*/



        sampleTrackingService.create(sampleTracking);
        return Result.success().data("创建成功");
    }








}