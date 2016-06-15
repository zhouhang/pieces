/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.BossOrderListDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.BusiTermOrderDetail;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossOrderService;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.util.GetBaseInfo;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BossOrderInfoVo;
import com.jointown.zy.common.vo.BossOrderListVo;
import com.jointown.zy.common.vo.BusiListingDetailVo;

/**
 * @ClassName: BossOrderController
 * @Description: 后台-订单操作Controller
 * @Author: 赵航
 * @Date: 2015年4月10日
 * @Version: 1.0
 */
@Controller
@RequestMapping("bossorder")
public class BossOrderController{
	
	private static final Logger logger = LoggerFactory.getLogger(BossOrderController.class);
	
	@Autowired
	private BossOrderService bossOrderService;
	
	@Autowired
	private BusiListingService busiListingService;

	/**
	 * @Description: 后台-订单列表查询
	 * @Author: 赵航
	 * @Date: 2015年4月10日
	 * @param bossOrderQuery
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String selectOrderList(@ModelAttribute("bossOrderQuery") BossOrderListDto bossOrderQuery, ModelMap model){
		logger.info("BossOrderController.selectOrderList");
		try {
			if(bossOrderQuery == null){
				bossOrderQuery = new BossOrderListDto();
			}
			bossOrderQuery.setPageSize(10); // 一页10条记录
			Page<BossOrderListVo> page = bossOrderService.selectOrderListByPage(bossOrderQuery);
			model.addAttribute("page", page);
			model.addAttribute("stateMap", BusiOrderStateEnum.toMap());
			/************add by ldp 2015-08-07 start*******************/
			//获取保证金默认比例
			model.addAttribute("depRate", BusiParamEnum.BUSI_DEPOSIT_RATE.getInfo());
			//获取保证金默认值
			model.addAttribute("depDefault", BusiParamEnum.BUSI_DEPOSIT_DEFAULT.getInfo());
			/************add by ldp 2015-08-07 end*******************/
			return "public/order_demand";
		} catch (Exception e) {
			logger.error("BossOrderController.selectOrderList, error is " + e.getMessage());
			return "error";
		}
	}
	
	/**
	 * @Description: 后台-订单详情查询
	 * @Author: 赵航
	 * @Date: 2015年4月10日
	 * @param orderId 订单编号
	 * @return
	 */
	@RequestMapping("info")
	public @ResponseBody String selectOrderInfo(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderController.selectOrderInfo");
		JsonObject json = new JsonObject();
		try {
			BossOrderInfoVo orderInfo = bossOrderService.selectOrderInfoById(orderId);
			if(orderInfo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("订单"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(orderInfo));
			}
		} catch (Exception e) {
			logger.error("BossOrderController.selectOrderInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-订单查询-挂牌详情查询
	 * @Author: 赵航
	 * @Date: 2015年7月14日
	 * @param listingId
	 * @return
	 */
	@RequestMapping("listingInfo")
	public @ResponseBody String selectListingQueryInfo(@RequestParam(value="listingId", required=true) String listingId){
		logger.info("BossOrderController.selectListingQueryInfo");
		JsonObject json = new JsonObject();
		try {
			BusiListingDetailVo vo = busiListingService.findSingleListingDetail(listingId);
			if(vo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("挂牌"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(vo));
			}
		} catch (Exception e) {
			logger.error("BossOrderController.selectListingQueryInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-订单查询-订单延期
	 * @Author: 赵航
	 * @Date: 2015年8月5日
	 * @param orderId 订单编号
	 * @param expireTime 延期时间
	 * @return
	 */
	@RequestMapping("updateExpireTime")
	public @ResponseBody String updateOrderExpireTime(@RequestParam(value="orderId", required=true) String orderId,
			@RequestParam(value="expireTime", required=true) String expireTime){
		logger.info("BossOrderController.updateOrderExpireTime");
		JsonObject json = new JsonObject();
		try {
			bossOrderService.updateExpireTime(orderId, TimeUtil.parseYMDHMS(expireTime));
			json.addProperty("state", "success");
			json.addProperty("result", "订单延期操作成功");
		} catch (BossErrorException e1) {
			logger.error("BossOrderController.updateOrderExpireTime, error is " + e1.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", e1.getMessage());
		} catch (Exception e2) {
			logger.error("BossOrderController.updateOrderExpireTime, error is " + e2.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", "订单延期操作失败");
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-订单查询-转为账期
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单编号
	 * @param startTime 账期开始日期
	 * @param endTime 账单结束日期
	 * @param remark 备注
	 * @return
	 */
	@RequestMapping("changeTermOrder")
	public @ResponseBody String changeToTermOrder(@RequestParam(value="orderId", required=true) String orderId,
			@RequestParam(value="startTime", required=true) String startTime,
			@RequestParam(value="endTime", required=true) String endTime,
			@RequestParam(value="remark", required=true) String remark){
		logger.info("BossOrderController.changeToTermOrder");
		JsonObject json = new JsonObject();
		try {
			BusiTermOrderDetail termOrder = new BusiTermOrderDetail();
			termOrder.setOrderid(orderId);
			termOrder.setStartTime(TimeUtil.parseYMD(startTime));
			termOrder.setEndTime(TimeUtil.parseYMD(endTime));
			termOrder.setNote(remark);
			termOrder.setOperator(GetBaseInfo.getBossUserId());
			bossOrderService.changeOrderTerm(termOrder);
			json.addProperty("state", "success");
			json.addProperty("result", "账期设置成功");
		} catch (BossErrorException e1) {
			logger.error("BossOrderController.changeToTermOrder, error is " + e1.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", e1.getMessage());
		} catch (Exception e2) {
			logger.error("BossOrderController.changeToTermOrder, error is " + e2.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", "账期设置失败");
		}
		return json.toString();
	}
	
	/**
	 * @Description: 后台-订单查询-账期详情
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单编号
	 * @return
	 */
	@RequestMapping("selectTermOrder")
	public @ResponseBody String selectTermOrder(@RequestParam(value="orderId", required=true) String orderId){
		logger.info("BossOrderController.selectTermOrder");
		JsonObject json = new JsonObject();
		try {
			BusiTermOrderDetail termOrder = bossOrderService.selectTermOrder(orderId);
			if(termOrder == null){
				json.addProperty("state", "failure");
				json.addProperty("result", "该账期详情不存在");
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd").toJson(termOrder));
				json.addProperty("days", TimeUtil.daysBetween(termOrder.getStartTime(), termOrder.getEndTime()));
			}
		} catch (Exception e) {
			logger.error("BossOrderController.selectTermOrder, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * @Description: 修改订单保证金，买家下单后允许修改订单保证金
	 * @Author: ldp
	 * @param orderId 订单号
	 * @param deposit 保证金
	 * @Date: 2015年8月5日
	 * @return
	 */
	@RequestMapping(value="alterOrderDeposit",method = RequestMethod.POST)
	public @ResponseBody String alterOrderDeposit(
			@RequestParam(value = "orderId", required = true) String orderId,
			@RequestParam(value = "deposit", required = true) String deposit) {
		logger.info("BossOrderController.alterOrderDeposit");
		JsonObject json = new JsonObject();
		try {
			bossOrderService.updateOrderDeposit(orderId, deposit);
			json.addProperty("state", "success");
			json.addProperty("result", "订单保证金修改操作成功");
		}catch (BossErrorException e) {
			logger.error("BossOrderController.alterOrderDeposit, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", e.getMessage());
		} catch (Exception e1) {
			logger.error("BossOrderController.alterOrderDeposit, error1 is " + e1.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", "订单保证金修改操作失败");
		}
		return json.toString();
	}
}
