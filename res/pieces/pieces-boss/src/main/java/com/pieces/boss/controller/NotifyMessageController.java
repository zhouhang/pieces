package com.pieces.boss.controller;

import com.pieces.dao.enums.NotHandleTypeEnum;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by xiao on 2016/11/30.
 * 用于处理消息的controller，目前仅显示消息数目
 */

@Controller
@RequestMapping(value = "/message")
public class NotifyMessageController {

     @Autowired
     private AccountBillService accountBillService;

     @Autowired
     private AnonEnquiryService anonEnquiryService;

     @Autowired
     private CertifyRecordService certifyRecordService;

     @Autowired
     private EnquiryBillsService enquiryBillsService;

     @Autowired
     private PayRecordService payRecordService;

    @RequestMapping(value = "/notHandle",method = RequestMethod.POST)
    @ResponseBody
    public Result notHandle(){

        HashMap<String,Integer> result=new HashMap<String,Integer>();
        result.put(NotHandleTypeEnum.ACCOUNT_BILL_NUM.getValue().toString(),accountBillService.getNotHandleCount());
        result.put(NotHandleTypeEnum.ANON_ENQUIRY_NUM.getValue().toString(),anonEnquiryService.getNotHandleCount());
        result.put(NotHandleTypeEnum.CERTIFY_RECORD_NUM.getValue().toString(),certifyRecordService.getNotHandleCount());
        result.put(NotHandleTypeEnum.ENQUIRYBILL_NUM.getValue().toString(),enquiryBillsService.getNotHandleCount());
        result.put(NotHandleTypeEnum.PAY_RECORD_NUM.getValue().toString(),payRecordService.getNotHandleCount());

        return new Result(true).data(result);
    }

}
