package com.ms.boss.controller;

import com.github.pagehelper.PageInfo;
import com.ms.dao.enums.SampleEnum;
import com.ms.dao.enums.TrackingDetailEnum;
import com.ms.dao.enums.TrackingEnum;
import com.ms.dao.enums.TrackingTypeEnum;
import com.ms.dao.model.Admin;
import com.ms.dao.model.SampleTracking;
import com.ms.dao.model.SendSample;
import com.ms.dao.model.TrackingDetail;
import com.ms.dao.vo.SampleTrackingVo;
import com.ms.dao.vo.TrackingDetailVo;
import com.ms.service.AdminService;
import com.ms.service.SampleTrackingService;
import com.ms.service.SendSampleService;
import com.ms.service.TrackingDetailService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    SendSampleService sendSampleService;

    @Autowired
    AdminService adminService;

    /**
     * 创建寄样单跟踪记录
     * @param sampleTracking
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Result createTracking(SampleTracking sampleTracking, TrackingDetail trackingDetail){
        //如果是用户预约或是寄送样品另外保存详细信息
        //要t通过session取后台用户id
        int userId=1;
        Date now=new Date();
        sampleTracking.setOperator(userId);
        sampleTracking.setType(TrackingTypeEnum.TYPE_ADMIN.getValue());
        /*
        Admin admin=adminService.findById(userId);
        sampleTracking.setName(admin.getUsername());
        */
        sampleTracking.setName("测试肖");

        List<Integer> requie=new ArrayList<Integer>();
        requie.add(TrackingEnum.TRACKING_AGREE.getValue());
        requie.add(TrackingEnum.TRACKING_REFUSE.getValue());
        requie.add(TrackingEnum.TRACKING_SEND.getValue());
        requie.add(TrackingEnum.TRACKING_ORDER.getValue());
        requie.add(TrackingEnum.TRACKING_FINISH.getValue());

        Integer recordType=sampleTracking.getRecordType();
        if(requie.indexOf(sampleTracking.getRecordType())!=-1){
            SendSample sendSample=new SendSample();
            sendSample.setId(sampleTracking.getSendId());
            if(recordType.intValue()==TrackingEnum.TRACKING_AGREE.getValue()){
                sendSample.setStatus(SampleEnum.SAMPLE_AGREE.getValue());
            }
            else if(recordType.intValue()==TrackingEnum.TRACKING_REFUSE.getValue()){
                sendSample.setStatus(SampleEnum.SAMPLE_REFUSE.getValue());
            }
            else if(recordType.intValue()==TrackingEnum.TRACKING_SEND.getValue()){
                trackingDetail.setType(TrackingDetailEnum.TYPE_SEND.getValue());
                trackingDetail.setCreateTime(now);
                trackingDetail.setSendId(sampleTracking.getSendId());
                trackingDetailService.create(trackingDetail);
                sendSample.setStatus(SampleEnum.SAMPLE_SEND.getValue());
                sampleTracking.setExtra("快递公司："+trackingDetail.getCompany()+" "+"快递单号："+trackingDetail.getTrackingNo());
            }
            else if(recordType.intValue()==TrackingEnum.TRACKING_ORDER.getValue()){
                trackingDetail.setType(TrackingDetailEnum.TYPE_ORDER.getValue());
                trackingDetail.setCreateTime(now);
                trackingDetail.setSendId(sampleTracking.getSendId());
                trackingDetailService.create(trackingDetail);
                sendSample.setStatus(SampleEnum.SAMPLE_VISTE.getValue());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                sampleTracking.setExtra("预约时间："+dateFormat.format(trackingDetail.getVistorTime())+" "+"来访人："+trackingDetail.getVistor()+" "+"电话："+trackingDetail.getVistorPhone());
            }else{
                sendSample.setStatus(SampleEnum.SAMPLE_FINISH.getValue());
            }


            sendSampleService.update(sendSample);
        }
        if(sampleTracking.getExtra()==null){
            sampleTracking.setExtra("");
        }
        sampleTracking.setCreateTime(now);

        sampleTrackingService.create(sampleTracking);
        return Result.success().data("创建成功");
    }


    //时间转换
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }





}
