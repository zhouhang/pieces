package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.model.PayResult;
import com.jointown.zy.common.model.RefuseRemitFlow;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.pay.RemitResultResp;
import com.jointown.zy.common.pay.ucs.service.RemitAccountService;
import com.jointown.zy.common.pay.ucs.service.UcsService;
import com.jointown.zy.common.service.PayResultService;
import com.jointown.zy.common.service.PayVoucherService;
import com.jointown.zy.common.service.RemitResultService;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.IPUtil;

/**
 * @ClassName: PayApiContorller
 * @Description: 提供支付结果查询接口，修改接口
 * @Author: ldp
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
@Controller
public class PayApiContorller extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(PayApiContorller.class);
	@Autowired
	private PayResultService payResultService;
	@Autowired
	private UcsService ucsService;
	@Autowired
	private RemitResultService remitResultService;
	@Autowired
	private RemitAccountService remitAccountService;
	@Autowired
	private PayVoucherService payVoucherService;
	/**
	 * 支付结果查询接口
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/queryPayResult")
	@ResponseBody
	public String payResultQuery(@RequestBody String data,HttpServletRequest request,ModelMap modelMap){
		log.info("query req data is :" + data);
		if (StringUtils.isBlank(data)) {
			log.info("query payResult req data is null");
			return "101";
		}
		String reqData = null;
		List<PayResult> payResults = null;
		String returnData = null;
		try {
			//解密,获取解密数据
			reqData = PayUtil.getDesDecryptData(data);
			Map<String, String> reqDataMap = BeanUtil.jsonToMap(reqData);
			String sourceSys = reqDataMap.get("sourceSys");
			String num = reqDataMap.get("num");
			//获取支付结果集
			payResults = payResultService.selectPayResults(Integer.parseInt(sourceSys), num);
			if (payResults == null || payResults.size()==0) {
				log.info("query payResult is null");
				return "102";
			}
			returnData = PayUtil.getDesEncryptData(BeanUtil.objectToJson(payResults));
		} catch (Exception e) {
			log.error("pay result query api error is",e);
			return "101";
		}
		return returnData;
	}
	
	/**
	 * 支付结果处理状态，修改接口
	 * @Author: ldp
	 * @Date: 2015年4月14日
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/donePayResult")
	@ResponseBody
	public String payResultDone(@RequestBody String data,HttpServletRequest request,ModelMap modelMap){
		log.info("done payResult req data is :" + data);
		if (StringUtils.isBlank(data)) {
			log.info("done payResult req data is null");
			return "101";
		}
		String payResultId = null;
		String status = null;
		int flag = 0;
		try {
			//解密,获取解密数据
			String reqPayResult = PayUtil.getDesDecryptData(data);
			Map<String, String> reqPayResultMap = BeanUtil.jsonToMap(reqPayResult);
			payResultId = reqPayResultMap.get("resultId");
			status = reqPayResultMap.get("status");
			flag = payResultService.updatePayResultByResultId(Integer.parseInt(payResultId),Integer.parseInt(status));
		} catch (Exception e) {
			log.error("pay result update api error is",e);
			return "101";
		}
		return String.valueOf(flag);
	}
	/**
	 * 调用划账接口
	 * @param json
	 * @return
	 * @throws Exception
	 * @author zhouji
	 */
	@RequestMapping(value="/remitAccount")
	@ResponseBody
	public String remitAccount(@RequestBody String json) throws Exception{
		Map params =  new HashMap();
		if(json==null){
			params.put("code", "0");
			params.put("msg", "FAILURE");
			params.put("remitno", "");
			Gson gson = new Gson();
	        String msg = gson.toJson(params);
	        return msg;
		}
		RemitFlow remitFlow =GsonFactory.fromJson(json, RemitFlow.class);
		String signdata = PayUtil.toRemitAccountSignData(remitFlow);
		//验签
		if (!signdata.equals(remitFlow.getSigndata())) {
			params.put("code", "0");
			params.put("msg", "FAILURE");
			params.put("remitno", "");
			Gson gson = new Gson();
	        String msg = gson.toJson(params);
	        return msg;
		}
		log.info("调用划账接口");
		//调用批量划账接口
		String msg=ucsService.addRemitFlow(remitFlow);
		log.info(msg);
		return msg;
	}
	/**
	 * 调用珍药宝批量划账接口
	 * @param json
	 * @return
	 * @throws Exception
	 * @author zhouji
	 */
	@RequestMapping(value="/v1/batchRemitAccount")
	@ResponseBody
	public String batchRemitAccount(@RequestBody String json) throws Exception{
		RemitFlow remitFlow =GsonFactory.fromJson(json, RemitFlow.class,"yyyy-MM-dd HH:mm:ss");
		return ucsService.batchRemitAccount(remitFlow);
	}
	
	/**
	 * @Description: 划账结果查询接口
	 * @Author: ldp
	 * @Date: 2015年7月3日
	 * @param data
	 * @return
	 * 		  返回json加密数据	
	 */
	@RequestMapping(value="/v1/queryRemitResult")
	@ResponseBody
	public String queryRemitResult(@RequestBody String data){
		log.info("queryRemitResult request data is：" + data);
		JsonObject jsonObject = new JsonObject();
		Gson gson = new Gson();
		String respData = null;
		try {
			//参数校验
			if (StringUtils.isBlank(data)) {
				jsonObject.addProperty("resultCode", "102");
				jsonObject.addProperty("codeMessage", "queryRemitResult request params is null!");
				return PayUtil.getDesEncryptData(gson.toJson(jsonObject));
			}
			//解密请求参数
			String decData = PayUtil.getDesDecryptData(data);
			Map<String, String> decDataMap = BeanUtil.jsonToMap(decData);
			String source_sys = decDataMap.get("source_sys").trim();
			String num = decDataMap.get("num").trim();
			//结果查询
			List<RemitResultResp> rrrespLists = remitResultService.queryRemitResult(Integer.parseInt(source_sys), Integer.parseInt(num));
			if (rrrespLists != null && rrrespLists.size() != 0) {
				jsonObject.addProperty("resultCode", "100");
				jsonObject.addProperty("codeMessage", "SUCCESS");
				jsonObject.addProperty("resultInfo", GsonFactory.toJson(rrrespLists));
			}else {
				jsonObject.addProperty("resultCode", "100");
				jsonObject.addProperty("codeMessage", "无结果记录");
				jsonObject.addProperty("resultInfo", GsonFactory.toJson(rrrespLists));
			}
			//加密响应参数
			respData = PayUtil.getDesEncryptData(gson.toJson(jsonObject));
		} catch (Exception e) {
			log.error("queryRemitResult error is:",e);
			jsonObject.addProperty("resultCode", "104");
			jsonObject.addProperty("codeMessage", e.getMessage());
			try {
				return PayUtil.getDesEncryptData(gson.toJson(jsonObject));
			} catch (Exception e1) {
				log.error("error is :",e1);
			}
		}
		return respData;
	}
	
	/**
	 * @Description: 划账结果处理接口（修改画张流水结果记录状态，待处理修改为已处理）
	 * @Author: ldp
	 * @Date: 2015年7月3日
	 * @return
	 * 		返回接口 0-修改失败  1-修改成功
	 */
	@RequestMapping(value="/v1/doneRemitResult")
	@ResponseBody
	public String doneRemitResult(@RequestBody String data){
		JsonObject jsonObject = new JsonObject();
		Gson gson = new Gson();
		String respData = null;
		try {
			//参数校验
			if (StringUtils.isBlank(data)) {
				jsonObject.addProperty("resultCode", "102");
				jsonObject.addProperty("codeMessage", "doneRemitResult request params is null!");
				return PayUtil.getDesEncryptData(gson.toJson(jsonObject));
			}
			//解密请求参数
			String reqData = PayUtil.getDesDecryptData(data);
			Map<String, String> reqDataMap = BeanUtil.jsonToMap(reqData);
			String remitResultId = reqDataMap.get("remitResultId");
			//结果处理
			String upFlag = remitResultService.doneRemitResult(Integer.parseInt(remitResultId));
			jsonObject.addProperty("resultCode", "100");
			jsonObject.addProperty("codeMessage", "SUCCESS");
			jsonObject.addProperty("rStatus", upFlag);
			respData = PayUtil.getDesEncryptData(gson.toJson(jsonObject));
		} catch (Exception e) {
			log.error("doneRemitResult error is:",e);
			jsonObject.addProperty("resultCode", "104");
			jsonObject.addProperty("codeMessage", e.getMessage());
			try {
				return PayUtil.getDesEncryptData(gson.toJson(jsonObject));
			} catch (Exception e1) {
				log.error("error is :",e1);
			}
		}
		return respData;
	}
	
	
	
	/**
	 * @Description: 划账拒绝信息查询接口
	 * @Author: biran
	 * @Date: 2015年7月3日
	 * @param data
	 * @return
	 * 		  返回json加密数据	
	 */
	@RequestMapping(value="/v1/queryRefuseRemitFlow")
	@ResponseBody
	public String queryRefuseRemitFlow(@RequestBody String data){
		log.info("入参:" + data);
		if (StringUtils.isBlank(data)) {
			log.error("入参为空！");
			return "101";
		}
		String reqData = null;
		List<RefuseRemitFlow> refuseRemitFlows = null;//返回值
		String returnData = null;
		try {
			//解密,获取解密数据
			reqData = PayUtil.getDesDecryptData(data);
			Map<String, String> reqDataMap = BeanUtil.jsonToMap(reqData);
			String sourceSys = reqDataMap.get("sourceSys");//来源系统
			String orderId = reqDataMap.get("orderId");//订单号
			String remitType = reqDataMap.get("remitType");//划账类型
			RemitFlow remitFlow=new RemitFlow();
			remitFlow.setSourcesys(Integer.valueOf(sourceSys));
			remitFlow.setOrderId(orderId);
			remitFlow.setRemitType(Integer.valueOf(remitType));
			//获取订单拒绝信息
			refuseRemitFlows = remitAccountService.getRefuseRemitFlowsByOrderId(remitFlow);
			log.info("返回列数:" + refuseRemitFlows.size());
			if (refuseRemitFlows == null || refuseRemitFlows.size()==0) {
				log.info("没有查询到数据！");
				return "102";
			}
			Gson gson = GsonFactory.createGson("yyyy-MM-dd HH:mm:ss");
			String gsonResult = gson.toJson(refuseRemitFlows);
			returnData = PayUtil.getDesEncryptData(gsonResult);
			log.info("返回值:" + returnData);
		} catch (Exception e) {
			log.error("错误！原因：",e.getMessage());
			return "101";
		}
		return returnData;
	}
	
	/**
	 * @Description: 微信使用，线下支付插入支付流水
	 * @Author: biran
	 * @Date: 2015-09-29
	 * @param data
	 * @return
	 * 	返回json加密数据	
	 */
	@RequestMapping(value="/v1/PayOffLine4WeiXin")
	@ResponseBody
	public String PayOffLine4WeiXin(@RequestBody String data,HttpServletRequest request)throws Exception{
			log.info("入参:" + data);
			if (StringUtils.isBlank(data)) {
				log.error("入参为空！");
				return "101";
			}
			String reqData = null;
			try {
				//解密,获取解密数据
				reqData = PayUtil.getDesDecryptData(data);
				Map<String, String> reqDataMap = BeanUtil.jsonToMap(reqData);
				PayReqDto payReqDto=new PayReqDto();
				payReqDto.setOrderId(reqDataMap.get("orderId"));
				payReqDto.setAmount(reqDataMap.get("Amount"));
				payReqDto.setSourceSys(reqDataMap.get("sourceSys"));
				payReqDto.setUserId(reqDataMap.get("UserId"));//付款人
				payReqDto.setRecieveId(reqDataMap.get("RecieveId"));//收款人
				payReqDto.setAmtType(reqDataMap.get("AmtType"));
				payReqDto.setOrderTitle(reqDataMap.get("OrderTitle"));//商品名
				String clientIp = reqDataMap.get("clientIp");
				String picPath=reqDataMap.get("picPath");
				//插入流水信息
				payVoucherService.insertPayflowOffLine4WeiXin(payReqDto, clientIp, picPath);
			} catch (Exception e) {
				log.error("错误！原因：",e.getMessage());
				return "101";
			}
			return "100";
		}
	
}
