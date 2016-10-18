package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;
import com.ms.service.SendSampleService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 根据查询条件获取寄样列表，历史寄样也可以从这里获取
     * @param sendSampleVo
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listSample(SendSampleVo sendSampleVo, Integer pageNum,
                               Integer pageSize, ModelMap model
    ) {
        PageInfo<SendSampleVo> list = sendSampleService.findByParams(sendSampleVo,pageNum,pageSize);
        return "";
    }

    /**
     * 寄样单的变更，主要是更改状态
     * @param sendSample
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(SendSample sendSample)
    {
        sendSampleService.update(sendSample);
        return Result.success().msg("修改成功");
    }

    /**
     * 废弃
     * @param id
     * @return
     */
    @RequestMapping(value = "detete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id) {
        SendSample sendSample=new SendSample();
        sendSample.setId(id);
        //设置状态
        sendSample.setStatus(0);
        sendSampleService.update(sendSample);
        return null;
    }





}
