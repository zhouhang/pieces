package com.jointown.zy.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.util.DES2;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.wms.WmsApi;
import com.jointown.zy.common.wms.WmsApiCommon;

/**
 * wms API 
 * @author ldp
 *
 */
@Controller
@RequestMapping(value="/v1")
public class WmsApiController extends UserBaseController {

	private static final Logger logger = LoggerFactory.getLogger(WmsApiController.class);
	
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private BusiWhlistService busiWhlistService;
	@Autowired
	private WmsApiService wmsApiService;
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	
	@Autowired
	private WmsApi wmsApi;
	
	@RequestMapping(value="/bossUser/login",produces = "text/html;charset=UTF-8")
	public @ResponseBody String login(@RequestBody String json,HttpServletRequest request,HttpServletResponse response){
		logger.info("提供给WMS的登录接口-参数："+json);
		String encryJson ="",signJson="";
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		try{
			Map<String,String> map = gson.fromJson(json, new TypeToken<Map<String,String>>(){}.getType());
			encryJson = map.get("data");
			signJson = map.get("digest");
		}catch(Exception e){
			logger.error("提供给WMS的登录接口-解析加密串、加签串时异常"+e.getMessage(),e);
			obj.addProperty("statusCode", 104);
			obj.addProperty("reason", "解析加密串、加签串时异常");
			return obj.toString();
		}
		
		 //检查参数 是否为空
		 if(StringUtils.isBlank(encryJson)||StringUtils.isBlank(signJson)){
			 logger.info("登录接口参数为空，返回：{statusCode:105}");
			 obj.addProperty("statusCode", 105);
			 obj.addProperty("reason", "加密串、加签串为空");
			 return obj.toString();
		 }
		 //验签
		 if(!EncryptUtil.getMD5(encryJson + WmsApiCommon.MD5_KEY, "UTF-8").equals(signJson)){
			 logger.info("登录接口验签失败，返回：{statusCode:103}");
			 obj.addProperty("statusCode", 103);
			 obj.addProperty("reason", "验签失败");
			 return obj.toString();
		 }
		 //解密 目前使用默认的密钥
		 DES2 des1 = null;
		 String decryptJson = "";
		try {
			des1 = new DES2();
			decryptJson = des1.decrypt(encryJson);
			logger.info("解密后的参数json串："+decryptJson);
		} catch (Exception e) {//解密异常
			logger.error("Login Service for WMS:decrypt exception:"+e.getMessage(), e);
		}
		if(decryptJson==""){
			logger.info("登录接口解密参数异常，返回：{statusCode:104}");
			obj.addProperty("statusCode", 104);
			obj.addProperty("reason", "解密参数异常");
			return obj.toString();
		}
		String userName = "";
		String password = "";
		try{
		Map<String,String> userMap = gson.fromJson(decryptJson, new TypeToken<Map<String,String>>(){}.getType());
			userName = userMap.get("userName");
			password = userMap.get("password");
		}catch(Exception e){
			logger.error("Login Service for WMS:analysis userName or password exception:"+e.getMessage(),e);
			logger.info("登录接口解析用户名密码异常，返回：{statusCode:104}");
			obj.addProperty("statusCode", 104);
			obj.addProperty("reason", "解析用户名密码异常");
			return obj.toString(); 
		}
		//根据用户名查找后台用户，用户存在且密码正确则登录成功
		BossUser bossUser = bossUserDao.findBossUserByUserCode(userName);
		if(bossUser!=null){
			if(bossUser.getStatus().intValue()==0){
				if(bossUser.getPassword().equals(EncryptUtil.JointownEncode(password,bossUser.getSalt()).getPassword())){
					logger.info("登录接口登录成功，返回：{statusCode:101}");
					obj.addProperty("statusCode", 101);
					return obj.toString(); 
				}else{
					logger.info("登录接口密码不正确导致登录失败，返回：{statusCode:105}");
					obj.addProperty("statusCode", 105);
					obj.addProperty("reason", "密码不正确");
				}
			}
		    else{
					logger.info("登录接口用户为非有效状态致登录失败，返回：{statusCode:102}");
					obj.addProperty("statusCode", 102);
					obj.addProperty("reason", "用户为非有效状态");
					}
		}else{
			logger.info("登录接口用户名不存在，返回：{statusCode:106}");
			obj.addProperty("statusCode", 106);
			obj.addProperty("reason", "用户不存在");
		}
		
		return obj.toString();  //登录失败
		 
	}
	
	/**
	 * wms仓单新增接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/wl/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody String wmsWlAdd(@RequestBody String json){
		logger.info("wms req data is:" + json);
		String result = wmsApi.receiveWmsApiReq(ApiFlagEnums.WL_ADD, json);
		logger.info("result is :" + result);
		return result;
		//验签、解密
		/*String codeordecry = signAndDecryptJson(json,"仓单新增");
		if(StringUtils.isNotBlank(codeordecry)&&(codeordecry.equals("105")||codeordecry.equals("104")||codeordecry.equals("103"))){
			return "{statusCode:"+codeordecry+"}";
		}
		
		WmsWlDto wlDto = null;
		try{
			wlDto = BeanUtil.jsonToObject(codeordecry, WmsWlDto.class);
		}catch(Exception e){
			logger.error("WMS仓单新增同步接口-json串转WmsWlDto异常："+e.getMessage(), e);
			return "{statusCode:105}";
		}
		//幂等性
		BusiWhlistVo wl = busiWhlistService.findBusiWhlistByWlId(wlDto.getWlId());
		if(wl!=null){
			return "{statusCode:101}";
		}
		int res = wmsApiService.syncWlInfo(wlDto);
		if(res==1){
			wmsApi.wmsApiSynLog(ApiFlagEnums.WL_ADD.name(), wlDto.getWlId(), codeordecry, "", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag()));
			return "{statusCode:101}";
		}else{
			wmsApi.wmsApiSynLog(ApiFlagEnums.WL_ADD.name(), wlDto.getWlId(), codeordecry, "102", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()));
			return "{statusCode:102}";
		}*/
		
	}
	
	/**
	 * wms仓单修改接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/wl/update",produces = "text/html;charset=UTF-8")
	public @ResponseBody String wmsWlUpdate(@RequestBody String json){
		logger.info("wms req data is:" + json);
		String result = wmsApi.receiveWmsApiReq(ApiFlagEnums.WL_UPDATE, json);
		logger.info("result is :" + result);
		return result;
	}
	
	/**
	 * 验签和解密json串
	 * @param json json串
	 * @param des 描述是哪个接口
	 * @return  如有异常则返回状态码，如无 则返回解密后的json串
	 */
	private String signAndDecryptJson(String json,String des){
		logger.info("提供给WMS的"+des+"接口-参数："+json);
		String encryJson ="",signJson="";
		Gson gson = new Gson();
		
		try{
			Map<String,String> map = gson.fromJson(json, new TypeToken<Map<String,String>>(){}.getType());
			encryJson = map.get("data");
			signJson = map.get("digest");
		}catch(Exception e){
			logger.error("提供给WMS的"+des+"接口-解析加密串、加签串时异常"+e.getMessage(),e);
		}
		
		 //检查参数 是否为空
		 if(StringUtils.isBlank(encryJson)||StringUtils.isBlank(signJson)){
			 logger.info("提供给WMS的"+des+"接口参数为空，返回：{statusCode:105}");
			 return "105";
		 }
		 //验签
		 if(!EncryptUtil.getMD5(encryJson + WmsApiCommon.MD5_KEY, "UTF-8").equals(signJson)){
			 logger.info("提供给WMS的"+des+"接口验签失败，返回：{statusCode:103}");
			 return "103";
		 }
		 //解密 目前使用默认的密钥
		 DES2 des1 = null;
		 String decryptJson = "";
		try {
			des1 = new DES2();
			decryptJson = des1.decrypt(encryJson);
			logger.info("提供给WMS的"+des+"接口，解密后的参数json串："+decryptJson);
		} catch (Exception e) {//解密异常
			logger.error("提供给WMS的"+des+"接口，decrypt exception:"+e.getMessage(), e);
		}
		if(decryptJson==""){
			logger.info("提供给WMS的"+des+"接口解密参数异常，返回：{statusCode:104}");
			return "104";
		}
		return decryptJson;
	}
	
	
	/**
	 * wms仓库新增接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/warehouse/add",produces = "text/html;charset=UTF-8")
	public @ResponseBody String wmsWlHouseAdd(@RequestBody String json){
		logger.info("wms req data is:" + json);
		String result = wmsApi.receiveWmsApiReq(ApiFlagEnums.WARE_HOUSE_ADD, json);
		logger.info("result is :" + result);
		return result;
	}
	
	
	/**
	 * wms仓库修改接口
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/warehouse/update",produces = "text/html;charset=UTF-8")
	public @ResponseBody String wmsWlHouseUpdate(@RequestBody String json){
		logger.info("wms req data is:" + json);
		String result = wmsApi.receiveWmsApiReq(ApiFlagEnums.WARE_HOUSE_UPDATE, json);
		logger.info("result is :" + result);
		return result;
	}
	
	/**
	 * 
	 * @Description: 冻结成功接口
	 * @Author: fanyuna
	 * @Date: 2015年4月16日
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/wl/freezeSuccess",produces = "text/html;charset=UTF-8")
	public @ResponseBody String freezeWlSucc(@RequestBody String json){
		logger.info("wms req data is:" + json);
		String result = wmsApi.receiveWmsApiReq(ApiFlagEnums.WL_FREEZE_SUCCESS, json);
		logger.info("result is :" + result);
		return result;
	}
	
	/**
	 * 
	 * @Description: 分割成功接口
	 * @Author: fanyuna
	 * @Date: 2015年4月16日
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/wl/splitSuccess",produces = "text/html;charset=UTF-8")
	public @ResponseBody String wlSplitSucc(@RequestBody String json){
		logger.info("wms req data is:" + json);
		String result = wmsApi.receiveWmsApiReq(ApiFlagEnums.WL_SPLIT_SUCCESS, json);
		logger.info("result is :" + result);
		return result;
	}
	
	/**
	 * 
	 * @Description: 查询仓单出库阈值接口
	 * @Author: fanyuna
	 * @Date: 2015年4月16日
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/wl/queryWlThreshold",produces = "text/html;charset=UTF-8")
	public @ResponseBody String queryWlThreshold(@RequestBody String json){
		logger.info("wms req data is:" + json);
		JsonObject obj = new JsonObject();
		String res = signAndDecryptJson(json,ApiFlagEnums.WL_EX_THRESHOLD_QUERY.getDes());
		String wlId="";
		if(res != null && !res.equals("103")&& !res.equals("104")&& !res.equals("105")){
			try{
				Gson gson = new Gson();
				Map<String,String> map = gson.fromJson(res, new TypeToken<Map<String,String>>(){}.getType());
					wlId = map.get("wlId");
				}catch(Exception e){
					logger.error("queryWlThreshold Service for WMS:analysis wlId exception:"+e.getMessage(),e);
					logger.info("查询仓单出库阈值接口异常，返回：{statusCode:104}");
					obj.addProperty("statusCode", 104);
					obj.addProperty("reason", "解析参数异常");
					wmsApiService.wmsApiSynLog(ApiFlagEnums.WL_EX_THRESHOLD_QUERY.name(), wlId, res, "104 解析参数异常", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
					return obj.toString(); 
				}
			if(StringUtils.isBlank(wlId)){
				logger.info("查询仓单出库阈值接口参数为空，返回：{statusCode:105}");
				obj.addProperty("statusCode", 105);
				obj.addProperty("reason", "接口参数为空");
				wmsApiService.wmsApiSynLog(ApiFlagEnums.WL_EX_THRESHOLD_QUERY.name(), wlId, res, "105 接口参数为空", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
				return obj.toString(); 
			}
				BusiWhlist bl = busiWhlistService.selectWhlistByWlId(wlId);
					obj.addProperty("statusCode", 101);
					obj.addProperty("wlId", wlId);
					obj.addProperty("exMaxCount", bl.getWlSurplus());
				 logger.info("查询仓单出库阈值接口成功，返回："+obj.toString());
				 wmsApiService.wmsApiSynLog(ApiFlagEnums.WL_EX_THRESHOLD_QUERY.name(), wlId, res, obj.toString(), Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
		}else{
			obj.addProperty("statusCode", res);
			String reason = res.equals("103")?"验签失败":res.equals("105")?"参数为空":"其他异常";
			obj.addProperty("reason", reason);
			logger.info("查询仓单出库阈值接口失败，返回："+obj.toString());
			wmsApiService.wmsApiSynLog(ApiFlagEnums.WL_EX_THRESHOLD_QUERY.name(), wlId, res, res, Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
		}
		logger.info("result is :" + obj.toString());
		return obj.toString();
	}
	
	@RequestMapping(value="/wl/applySplit",produces = "text/html;charset=UTF-8")
	public @ResponseBody String applySplit(@RequestBody String json){
		logger.info("WmsApiController applySplit req data is:" + json);
		MessageVo msgVo = wmsApiService.applyWlSplit(json);
		Gson gson = new Gson();
		String res =  gson.toJson(msgVo);
		return res;
	}
	
	
}
