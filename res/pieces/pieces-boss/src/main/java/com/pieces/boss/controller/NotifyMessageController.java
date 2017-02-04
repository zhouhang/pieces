package com.pieces.boss.controller;

import com.pieces.dao.enums.NotHandleTypeEnum;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.service.*;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.RedisEnum;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

     @Autowired
     private RecruitAgentService recruitAgentService;

    @Autowired
    private HttpSession httpSession;


    @RequestMapping(value = "/notHandle",method = RequestMethod.POST)
    @ResponseBody
    public Result notHandle(){

        String sessionId=httpSession.getId();
        HashMap<String,String> lastResult=(HashMap)httpSession.getAttribute(RedisEnum.NOT_HANDLE_ID_MAP.getValue()+sessionId);
        Integer hasNew=0;
        List<Integer> billIds=accountBillService.getNotHandleIds();
        List<Integer> anonIds=anonEnquiryService.getNotHandleIds();
        List<Integer> certifyIds=certifyRecordService.getNotHandleIds();
        List<Integer> equiryBillIds=enquiryBillsService.getNotHandleIds();
        List<Integer> payIds=payRecordService.getNotHandleIds();
        List<Integer> recruitIds=recruitAgentService.getNotHandleIds();

        HashMap<String,String> resultIds=new HashMap<String,String>();
        resultIds.put(NotHandleTypeEnum.ACCOUNT_BILL_NUM.getValue().toString(), StringUtils.join(billIds,","));
        resultIds.put(NotHandleTypeEnum.ANON_ENQUIRY_NUM.getValue().toString(),StringUtils.join(anonIds,","));
        resultIds.put(NotHandleTypeEnum.CERTIFY_RECORD_NUM.getValue().toString(),StringUtils.join(certifyIds,","));
        resultIds.put(NotHandleTypeEnum.ENQUIRYBILL_NUM.getValue().toString(),StringUtils.join(equiryBillIds,","));
        resultIds.put(NotHandleTypeEnum.PAY_RECORD_NUM.getValue().toString(),StringUtils.join(payIds,","));
        resultIds.put(NotHandleTypeEnum.RECRUIT_AGENT_NUM.getValue().toString(),StringUtils.join(recruitIds,","));

        //第一次请求,弹框提示
        if(lastResult==null){
            hasNew=1;
        }
        //比较后得出是否有新任务
        else{
            //循环比较
            for(int i=1;i<7;i++){
                if(NotHandleTypeEnum.ACCOUNT_BILL_NUM.getValue()==i){
                    if(!(StringUtils.join(billIds,",").equals(lastResult.get(Integer.toString(i))))){
                        //如果有处理的也不弹框提示
                        if(!lastResult.get(Integer.toString(i)).contains(StringUtils.join(billIds,","))){
                            hasNew=1;
                            break;//有新任务直接返回
                        }
                    }
                }else if(NotHandleTypeEnum.ANON_ENQUIRY_NUM.getValue()==i){
                    if(!(StringUtils.join(anonIds,",").equals(lastResult.get(Integer.toString(i))))){
                        if(!lastResult.get(Integer.toString(i)).contains(StringUtils.join(anonIds,","))){
                            hasNew=1;
                            break;
                        }
                    }
                }
                else if(NotHandleTypeEnum.CERTIFY_RECORD_NUM.getValue()==i){
                    if(!(StringUtils.join(certifyIds,",").equals(lastResult.get(Integer.toString(i))))){
                        if(!lastResult.get(Integer.toString(i)).contains(StringUtils.join(certifyIds,","))){
                            hasNew=1;
                            break;
                        }
                    }
                }else if(NotHandleTypeEnum.ENQUIRYBILL_NUM.getValue()==i){
                    if(!(StringUtils.join(equiryBillIds,",").equals(lastResult.get(Integer.toString(i))))){
                        if(!lastResult.get(Integer.toString(i)).contains(StringUtils.join(equiryBillIds,","))){
                            hasNew=1;
                            break;
                        }
                    }
                }else if(NotHandleTypeEnum.PAY_RECORD_NUM.getValue()==i){
                    if(!(StringUtils.join(payIds,",").equals(lastResult.get(Integer.toString(i))))){
                        if(!lastResult.get(Integer.toString(i)).contains(StringUtils.join(payIds,","))){
                            hasNew=1;
                            break;
                        }
                    }
                }else if(NotHandleTypeEnum.RECRUIT_AGENT_NUM.getValue()==i){
                    if(!(StringUtils.join(recruitIds,",").equals(lastResult.get(Integer.toString(i))))){
                        if(!lastResult.get(Integer.toString(i)).contains(StringUtils.join(recruitIds,","))){
                            hasNew=1;
                            break;
                        }
                    }
                }

            }


        }
        //更新缓存
        httpSession.setAttribute(RedisEnum.NOT_HANDLE_ID_MAP.getValue()+sessionId,resultIds);


        HashMap<String,Integer> result=new HashMap<String,Integer>();
        result.put(NotHandleTypeEnum.ACCOUNT_BILL_NUM.getValue().toString(),billIds.size());
        result.put(NotHandleTypeEnum.ANON_ENQUIRY_NUM.getValue().toString(),anonIds.size());
        result.put(NotHandleTypeEnum.CERTIFY_RECORD_NUM.getValue().toString(),certifyIds.size());
        result.put(NotHandleTypeEnum.ENQUIRYBILL_NUM.getValue().toString(),equiryBillIds.size());
        result.put(NotHandleTypeEnum.PAY_RECORD_NUM.getValue().toString(),payIds.size());
        result.put(NotHandleTypeEnum.RECRUIT_AGENT_NUM.getValue().toString(),recruitIds.size());
        result.put(NotHandleTypeEnum.HAS_NEW_HANDLE.getValue().toString(),hasNew);

        return new Result(true).data(result);
    }

}
