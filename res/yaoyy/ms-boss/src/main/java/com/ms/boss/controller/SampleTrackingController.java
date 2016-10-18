package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.service.SampleTrackingService;
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

    /**
     * 获取寄样单跟踪记录
     * @param sendSampleVo
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String trackingList(SampleTrackingVo sendSampleVo, Integer pageNum,
                               Integer pageSize, ModelMap model)
    {
        List<SampleTrackingVo> sampleTrackingVoList=sampleTrackingService.findAllByParams(sendSampleVo);
        return "";
    }

    /**
     * 创建寄样单跟踪记录
     * @param sampleTracking
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Result createTracking(SampleTracking sampleTracking){

        sampleTrackingService.create(sampleTracking);
        return Result.success().data("创建成功");
    }








}
