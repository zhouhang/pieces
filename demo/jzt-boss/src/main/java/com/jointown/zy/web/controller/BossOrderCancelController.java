/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.BossOrderCancelListDto;
import com.jointown.zy.common.enums.BusiAppealTypeEnum;
import com.jointown.zy.common.enums.ExamineStateEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossOrderCancelService;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.BossOrderCancelInfoVo;
import com.jointown.zy.common.vo.BossOrderCancelListVo;

/**
 * @ClassName: BossOrderCancelController
 * @Description: 后台交易取消相关操作
 * @Author: 赵航
 * @Date: 2015年4月14日
 * @Version: 1.0
 */
@Controller
@RequestMapping("ordercancel")
public class BossOrderCancelController {
	
private static final Logger logger = LoggerFactory.getLogger(BossOrderCancelController.class);
	
	@Autowired
	private BossOrderCancelService bossOrderCancelService;

	/**
	 * @Description: 后台-交易取消列表查询
	 * @Author: 赵航
	 * @Date: 2015年4月14日
	 * @param orderCancelQuery 查询条件
	 * @param model 
	 * @return
	 */
	@RequestMapping("")
	public String selectOrderCancelList(@ModelAttribute("orderCancelQuery") BossOrderCancelListDto orderCancelQuery, ModelMap model){
		logger.info("BossOrderCancelController.selectOrderCancelList");
		try {
			if(orderCancelQuery == null){
				orderCancelQuery = new BossOrderCancelListDto();
			}
			orderCancelQuery.setPageSize(10); // 一页10条记录
			Page<BossOrderCancelListVo> page = bossOrderCancelService.selectOrderCancelList(orderCancelQuery);
			model.addAttribute("page", page);
			//审核状态
			model.addAttribute("stateMap", ExamineStateEnum.toMap());
			//申诉类型
			model.addAttribute("appealTypeMap", BusiAppealTypeEnum.toMap());
			return "public/business_checkOff";
		} catch (Exception e) {
			logger.error("BossOrderCancelController.selectOrderCancelList, error is " + e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 退款申请详情查询
	 * @Author: 赵航
	 * @Date: 2015年4月14日
	 * @param orderId
	 * @return
	 */
	@RequestMapping("info")
	public @ResponseBody String selectOrderCancelInfo(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderCancelController.selectOrderCancelInfo");
		JsonObject json = new JsonObject();
		try {
			BossOrderCancelInfoVo orderCancelInfo = bossOrderCancelService.selectOrderCancelInfoById(orderId);
			if(orderCancelInfo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("退款申请"));
			} else {
				orderCancelInfo.setAppealTypeName(BusiAppealTypeEnum.toMap().get(orderCancelInfo.getAppealType()));
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(orderCancelInfo));
			}
		} catch (Exception e) {
			logger.error("BossOrderCancelController.selectOrderCancelInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 通过退款申请
	 * @Author: 赵航
	 * @Date: 2015年4月15日
	 * @param orderId 订单编号
	 * @return
	 */
	@RequestMapping("finish")
	public @ResponseBody String finishAppeal(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderCancelController.finishAppeal");
		JsonObject json = new JsonObject();
		try {
			String result = bossOrderCancelService.appealFinishById(orderId);
			if ("success".equals(result)){
				json.addProperty("state", "success");
				json.addProperty("result", "通过退款申请成功！");
			} else {
				json.addProperty("state", "failure");
				json.addProperty("result", "通过退款申请失败！");
			}
		} catch (Exception e) {
			logger.error("BossOrderCancelController.finishAppeal, error is " + e.getMessage());
			json.addProperty("state", "failure");
			if(e instanceof BossErrorException){
				json.addProperty("result", e.getMessage());
			} else {
				json.addProperty("result", "通过退款申请失败！");
			}
		}
		return json.toString();
	}
	
	/**
	 * @Description: 驳回申请
	 * @Author: 赵航
	 * @Date: 2015年4月14日
	 * @param rejectReason 驳回原因
	 * @param orderId 订单编号
	 * @return
	 */
	@RequestMapping("reject")
	public @ResponseBody String rejectAppeal(@RequestParam(value="rejectReason", required=true) String rejectReason,
			@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderCancelController.rejectAppeal");
		JsonObject json = new JsonObject();
		try {
			if (rejectReasonCheck(rejectReason, json)){
				String result = bossOrderCancelService.updateAppealRejectedById(orderId, rejectReason);
				if ("success".equals(result)){
					json.addProperty("state", "success");
					json.addProperty("result", "驳回成功！");
				} else {
					json.addProperty("state", "failure");
					json.addProperty("result", "驳回失败，请重新提交！");
				}
			}
		} catch (Exception e) {
			logger.error("BossOrderCancelController.rejectAppeal, error is " + e.getMessage());
			json.addProperty("state", "failure");
			if(e instanceof BossErrorException){
				json.addProperty("result", e.getMessage());
			} else {
				json.addProperty("result", "驳回失败，请重新提交！");
			}
		}
		return json.toString();
	}
	
	private boolean rejectReasonCheck(String rejectReason, JsonObject json){
		if(StringUtils.isEmpty(rejectReason)){
			json.addProperty("state", "failure");
			json.addProperty("result", "驳回原因不能为空！");
			return false;
		} else if(rejectReason.length() > 400){
			json.addProperty("state", "failure");
			json.addProperty("result", "驳回原因字符长度超出限制！");
			return false;
		}
		return true;
	}
}
