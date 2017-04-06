package com.pieces.biz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pieces.dao.model.LogisticalTrace;
import com.pieces.service.LogisticalTraceService;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xc on 2017/4/5.
 * 快递订阅消息的回调
 */
@Controller
@RequestMapping("/kd/")
public class KdCallBackController {



    @Autowired
    LogisticalTraceService logisticalTraceService;

    @RequestMapping(value = "notify" )
    public void kdCallBack(HttpServletResponse response,
                             HttpServletRequest request
                             )throws Exception{

        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        String data=params.get("RequestData");
        JSONObject result = JSONObject.parseObject(data);
        String eBusinessID="";
        if(result.getInteger("Count")!=0){
            JSONArray retData=result.getJSONArray("Data");
            for(int j=0;j<retData.size();j++) {
                String logisticCode=retData.getJSONObject(j).getString("LogisticCode");
                String shipperCode=retData.getJSONObject(j).getString("ShipperCode");
                eBusinessID=retData.getJSONObject(j).getString("EBusinessID");
                JSONArray trace = retData.getJSONObject(j).getJSONArray("Traces");
                if (trace.size() != 0) {
                    for (int i = 0; i < trace.size(); i++) {
                        JSONObject jsonObject = trace.getJSONObject(i);
                        LogisticalTrace logisticalTrace = new LogisticalTrace();
                        logisticalTrace.setLogisticCode(logisticCode);
                        logisticalTrace.setShipperCode(shipperCode);
                        logisticalTrace.setAcceptStation(jsonObject.getString("AcceptStation"));
                        logisticalTrace.setAcceptTime(jsonObject.getDate("AcceptTime"));
                        logisticalTraceService.create(logisticalTrace);
                    }

                }
            }
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Map<String, String > push = new HashMap<String, String>();
        push.put("EBusinessID",eBusinessID);
        push.put("Success","true");
        push.put("Reason","");
        push.put("UpdateTimes",format.format(new Date()));


        WebUtil.print(response, push);

        }




}
