package com.pieces.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pieces.dao.model.LogisticalTrace;
import com.pieces.dao.vo.LogisticalTraceVo;
import com.pieces.service.LogisticalTraceService;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.httpclient.HttpClientUtil;
import com.pieces.tools.utils.httpclient.common.HttpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.pieces.service.utils.KdniaoUtils;

import static com.pieces.service.utils.KdniaoUtils.encrypt;
import static com.pieces.service.utils.KdniaoUtils.urlEncoder;

/**
 * Created by xc on 2017/4/5.
 */
@Component
public class KdApiService {

    @Value("${kdniao.EBusinessID}")
    private String eBusinessID;

    @Value("${kdniao.AppKey}")
    private String appKey;

    private final String trackQueryUrl = "http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

    private final String subscribeUrl = "http://api.kdniao.cc/api/dist";



    /**
     * 实时获取物流跟踪
     * @param shipperCode 快递公司编码
     * @param logisticCode 快递单号
     * @return
     */
    public List<LogisticalTraceVo> getOrderTraces (String shipperCode, String  logisticCode) throws Exception{

        Map<String, String > data = new HashMap<String, String>();
        data.put("ShipperCode",shipperCode);
        data.put("LogisticCode",logisticCode);

        String requestData= GsonUtil.toJson(data);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", eBusinessID);
        params.put("RequestType", "1002");
        String dataSign=encrypt(requestData, appKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=HttpClientUtil.post(HttpConfig.custom().url(trackQueryUrl).map(params));
        // TODO: 2017/4/5 结果转换为跟踪list
        JSONObject jsStr = JSONObject.parseObject(result);
        JSONArray trace = jsStr.getJSONArray("Traces");
        List<LogisticalTraceVo> logisticalTraces=new ArrayList<LogisticalTraceVo>();
        if(trace.size()!=0){
            for(int i=0;i<trace.size();i++) {
                JSONObject jsonObject = trace.getJSONObject(i);
                LogisticalTraceVo logisticalTrace = new LogisticalTraceVo();
                logisticalTrace.setLogisticCode(logisticCode);
                logisticalTrace.setShipperCode(shipperCode);
                logisticalTrace.setAcceptStation(jsonObject.getString("AcceptStation"));
                logisticalTrace.setAcceptTime(jsonObject.getDate("AcceptTime"));
                logisticalTraces.add(logisticalTrace);
            }


        }


        return logisticalTraces;
    }

    /**
     * 订阅物流轨迹
     * @return
     */
    public Boolean orderTracesSub(String shipperCode, String  logisticCode) throws Exception {
        Map<String, String > data = new HashMap<String, String>();
        data.put("ShipperCode",shipperCode);
        data.put("LogisticCode",logisticCode);

        String requestData= GsonUtil.toJson(data);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", eBusinessID);
        params.put("RequestType", "1008");
        String dataSign=encrypt(requestData, appKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=HttpClientUtil.post(HttpConfig.custom().url(subscribeUrl).map(params));
       //TODO 根据订阅成功与否返回
        JSONObject jsStr = JSONObject.parseObject(result);
        if(!jsStr.getBoolean("Success")){
            return false;
        }

        return true;
    }




}
