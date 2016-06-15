package com.jointown.zy.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.wms.WmsApi;
import com.jointown.zy.common.wms.WmsApiCommon;

@Controller
public class ApiController {

	private static final Logger log = LoggerFactory.getLogger(ApiController.class);
	
	@Resource
	private WmsApi wmsApi;
	
	@RequestMapping("/api/call")
	public @ResponseBody String callInterface(@RequestParam(value="type", required=true) String type,@RequestParam(value="json", required=true) String json){
		String url ="";
		if(type.equalsIgnoreCase(ApiFlagEnums.WL_ADD.name())){
			url = WmsApiCommon.WMS_WL_ADD_URL;
		}else if(type.equalsIgnoreCase(ApiFlagEnums.WL_UPDATE.name())){
			url = WmsApiCommon.WMS_WL_UPDATE_URL;
		}else if(type.equalsIgnoreCase(ApiFlagEnums.WARE_HOUSE_ADD.name())){
			url = WmsApiCommon.WMS_WAREHOUSE_ADD_URL;
		}else if(type.equalsIgnoreCase(ApiFlagEnums.WARE_HOUSE_UPDATE.name())){
			url = WmsApiCommon.WMS_WAREHOUSE_ADD_URL;
		}else if(type.equalsIgnoreCase(ApiFlagEnums.WL_FREEZE_SUCCESS.name())){
			url = WmsApiCommon.WMS_WL_FREEZE_SUCCESS_URL;
		}else if(type.equalsIgnoreCase(ApiFlagEnums.WL_SPLIT_SUCCESS.name())){
			url = WmsApiCommon.WMS_WL_SPLIT_SUCCESS_URL;
		}else if(type.equalsIgnoreCase(ApiFlagEnums.WL_EX_THRESHOLD_QUERY.name())){
			url = WmsApiCommon.WMS_WL_EXMAXCOUNT_URL;
		}
		try {
			String statusCode = wmsApi.sendWmsApiReq(url,json);
			return statusCode;
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
}
