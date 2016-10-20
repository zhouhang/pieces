package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.enums.SampleEnum;
import com.ms.dao.model.Commodity;
import com.ms.dao.model.SendSample;
import com.ms.dao.vo.SendSampleVo;
import com.ms.service.CommodityService;
import com.ms.service.SendSampleService;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        PageInfo<SendSampleVo> sendSampleVoPageInfo = sendSampleService.findByParams(sendSampleVo,pageNum,pageSize);
        //意向商品转化为显示字符串
        for(SendSampleVo s:sendSampleVoPageInfo.getList())
        {
            List<Commodity> commodityList = commodityService.findByIds(s.getIntention());
            s.setCommodityList(commodityList);

        }
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
        return "sample_detail";
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
        sendSample.setStatus(SampleEnum.SAMPLE_DELETED.getValue());
        sendSampleService.update(sendSample);
        return Result.success().msg("修改成功");
    }





}
