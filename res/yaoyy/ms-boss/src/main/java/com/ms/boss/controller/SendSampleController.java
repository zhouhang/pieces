package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;
import com.ms.service.SendSampleService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiao on 2016/10/18.
 */
@Controller
@RequestMapping("sample/")
public class SendSampleController {

    @Autowired
    SendSampleService sendSampleService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listSample(SendSampleVo sendSampleVo, Integer pageNum,
                               Integer pageSize, ModelMap model
    ) {
        PageInfo<SendSampleVo> list = sendSampleService.findByParams(sendSampleVo,pageNum,pageSize);
        return "";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(SendSample sendSample)
    {
        sendSampleService.update(sendSample);
        return Result.success().msg("修改成功");
    }






}
