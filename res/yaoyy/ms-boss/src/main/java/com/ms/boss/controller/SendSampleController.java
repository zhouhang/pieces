package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.enums.SampleEnum;
import com.ms.dao.model.*;
import com.ms.dao.vo.*;
import com.ms.service.*;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.Reflection;
import org.apache.commons.lang.StringUtils;
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
import java.util.stream.Stream;

/**
 * Created by xiao on 2016/10/18.
 */
@Controller
@RequestMapping("sample/")
public class SendSampleController {

    @Autowired
    SendSampleService sendSampleService;

    @Autowired
    CommodityService commodityService;


    @Autowired
    SampleTrackingService sampleTrackingService;

    @Autowired
    TrackingDetailService trackingDetailService;

    @Autowired
    SampleAddressService sampleAddressServie;

    @Autowired
    UserDetailService userDetailServer;

    @Autowired
    HistoryCommodityService historyCommodityService;


    /**
     * 根据查询条件获取寄样列表
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
        if(sendSampleVo.getAbandon()==null){
            sendSampleVo.setAbandon(0);
        }
        PageInfo<SendSampleVo> sendSampleVoPageInfo = sendSampleService.findByParams(sendSampleVo,pageNum,pageSize);

        model.put("sendSampleVoPageInfo",sendSampleVoPageInfo);
        model.put("sendSampleVo",sendSampleVo);
        model.put("sendSampleVoParams", Reflection.serialize(sendSampleVo));

        return "sample_list";
    }

    /**
     * 寄样单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, ModelMap model)
    {
        //寄样单信息
        SendSampleVo sendSampleVo=sendSampleService.findDetailById(id);


        //历史寄样单信息(需指定最多多少条，显示问题)
        SendSampleVo historyParam=new SendSampleVo();
        historyParam.setPhone(sendSampleVo.getPhone());
        PageInfo<SendSampleVo> historySend = sendSampleService.findByParams(historyParam,1,10);

        //用户详情
        UserDetailVo userDetail=userDetailServer.findByUserId(sendSampleVo.getUserId());
        if(userDetail==null){
            userDetail=new UserDetailVo();
        }

        //地址信息
        SampleAddressVo sampleAdderss= sampleAddressServie.findBySendId(sendSampleVo.getId());
        if(sampleAdderss==null){
            sampleAdderss=new SampleAddressVo();
        }

        //寄样单跟踪状态
        SampleTrackingVo sampleTrackingVo=new SampleTrackingVo();
        sampleTrackingVo.setSendId(sendSampleVo.getId());
        List<SampleTrackingVo> trackingList=sampleTrackingService.findAllByParams(sampleTrackingVo);

        model.put("sendSampleVo",sendSampleVo);
        model.put("historySend",historySend);
        model.put("userDetail",userDetail);
        model.put("sampleAdderss",sampleAdderss);
        model.put("trackingList",trackingList);


        return "sample_detail";
    }

    /**
     * 废弃或恢复
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(SendSample sendSample) {
        sendSampleService.update(sendSample);
        return Result.success().msg("修改成功");
    }

    @RequestMapping(value = "userComplete",method = RequestMethod.POST)
    @ResponseBody
    public Result userComplete(UserDetail userDetail){
        userDetailServer.save(userDetail);
        return Result.success().msg("保存成功");
    }

    @RequestMapping(value = "addressSave",method=RequestMethod.POST)
    @ResponseBody
    public Result addressSave(SampleAddress address,String intention){
        sampleAddressServie.save(address);
        SendSample sendSample=new SendSample();
        sendSample.setId(address.getSendId());

        List<CommodityVo> commodityList =commodityService.findByIds(intention);

        List<Integer> ids=new ArrayList<>();

        commodityList.forEach(c->{
            HistoryCommodity historyCommodity=historyCommodityService.saveCommodity(c);
            ids.add(historyCommodity.getId());
        });

        sendSample.setIntention(StringUtils.join(ids,","));
        sendSampleService.update(sendSample);
        return Result.success().msg("保存成功");
    }







}
