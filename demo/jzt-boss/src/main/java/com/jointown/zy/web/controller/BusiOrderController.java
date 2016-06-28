package com.jointown.zy.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jointown.zy.common.dto.BusiOrderSearchDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.vo.BusiOrderVo;
import com.jointown.zy.common.vo.MessageVo;

/**
 * 订单管理Controller
 * @author wangjunhu
 * 2015-1-4
 */
@Controller(value="busiOrderController")
public class BusiOrderController {

	private final static Logger logger = LoggerFactory.getLogger(BusiOrderController.class);
	
	@Autowired
	private BusiOrderService busiOrderService;
	
	@RequestMapping(value="busiOrderApply",method=RequestMethod.GET)
	public String getBusiOrderManage(@ModelAttribute BusiOrderSearchDto busiOrderSearchDto, ModelMap model) throws Exception {
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setPageNo(busiOrderSearchDto.getPageNo());
		//搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiOrderSearchDto", busiOrderSearchDto);
		page.setParams(params);	
		//订单状态列表
		Map<String,String> orderstateMap = BusiOrderStateEnum.toMap();
		//订单列表
		List<BusiOrderVo> busiOrders = busiOrderService.findOrderListByCondition(page);
		page.setResults(busiOrders);
		
		model.put("page", page);
		model.put("orderstateMap", orderstateMap);
		
		return "/public/buy_application";
	}
	
	/**
	 * 完成交易
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiOrderApply/finishBusiOrder",method=RequestMethod.GET)
	@ResponseBody
	public String finishBusiOrder(@ModelAttribute("orderid") String orderid) throws Exception {
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setOrderid(orderid);
//		busiOrder.setOrderstate(Integer.valueOf(BusiOrderStateEnum.FINISHED.getCode()));
		busiOrder.setOrderstate(1);//TODO 订单状态待修改
		int updateNum = busiOrderService.updateOrderState(busiOrder);
		MessageVo message = new MessageVo();
		if(updateNum==1){
			message.setOk(true);
			message.setObj(orderid);
		}else{
			message.setOk(false);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * 关闭交易
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiOrderApply/cancelBusiOrder",method=RequestMethod.GET)
	@ResponseBody
	public String cancelBusiOrder(@ModelAttribute("orderid") String orderid) throws Exception {
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setOrderid(orderid);
//		busiOrder.setOrderstate(Integer.valueOf(BusiOrderStateEnum.CANCELED.getCode()));
		busiOrder.setOrderstate(2);//TODO 订单状态待修改
		int updateNum = busiOrderService.updateOrderState(busiOrder);
		MessageVo message = new MessageVo();
		if(updateNum==1){
			message.setOk(true);
			message.setObj(orderid);
		}else{
			message.setOk(false);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * 修改交易总价
	 * @param orderid
	 * @param discountprice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiOrderApply/alterDiscountPrice",method=RequestMethod.GET)
	@ResponseBody
	public String alterDiscountPrice(@ModelAttribute("orderid") String orderid,@ModelAttribute("discountprice") String discountprice) throws Exception {
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setOrderid(orderid);
		busiOrder.setDiscountprice(new BigDecimal(discountprice.replace(",", "")));
		int updateNum = busiOrderService.updateDiscountPrice(busiOrder);
		MessageVo message = new MessageVo();
		if(updateNum==1){
			message.setOk(true);
			message.setObj(orderid);
		}else{
			message.setOk(false);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
}
