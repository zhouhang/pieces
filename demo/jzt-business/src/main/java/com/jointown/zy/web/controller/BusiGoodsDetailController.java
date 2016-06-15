package com.jointown.zy.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
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
import com.jointown.zy.common.dto.BusiBuyGoodsDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.enums.JsLoginStatusEnum;
import com.jointown.zy.common.enums.SeoHeaderEnum;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiGoodsDetailsService;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.service.BusiQualityPicService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.trace.Trace;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SysLogUtil;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiGoodsOrderVo;
import com.jointown.zy.common.vo.BusiGoodsRecommenVo;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;
import com.jointown.zy.common.vo.JsLoginMessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 
 * 描述： 商品详情页操作<br/>
 * 
 * 日期： 2015年1月4日<br/>
 * 
 * 作者： fanyuna<br/>
 *
 * 版本： V1.0<br/>
 */
@Controller
@RequestMapping(value = "/detail")
public class BusiGoodsDetailController extends UserBaseController {
	private static final Logger log = LoggerFactory
			.getLogger(BusiGoodsDetailController.class);

	@Autowired
	private BusiGoodsDetailsService busiGoodsDetailsService;

	@Autowired
	private SortListService sortListService;

	@Autowired
	private BusiQualityPicService busiQualityPicService;

	@Autowired
	private IndexService indexService;
	
	@Autowired
	private BusiOrderService busiOrderService;
	
	// 离开商品详情页
	@RequestMapping(value = "/unload",method={RequestMethod.POST, RequestMethod.GET})
	public void unload(@ModelAttribute Trace dto) {
		if(isUserLogin()){
			new Trace(getUserInfo().getUserId().toString(), dto.getId())
			.setForExitListingDetail(dto)
			.syslogForBusiness();
		}
	}
	
	// 商品详情页
	@RequestMapping(value = "/getBusiGoodsDetail")
	public String getBusiGoodsDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@Param("listingId")String listingId) {
		log.info("BusiOrderController.getBusiGoodsDetail controller");
		String tunnage = indexService.getWarrantsTunnage();
		model.put("tunnage", tunnage);

		if (StringUtils.isBlank(listingId)) {
			return null;
		}
		// 商品信息
		BusiGoodsInfoVo goodsInfo = busiGoodsDetailsService.selectGoodsInfo(listingId);
		//非空判断，若是空值，直接跳转到错误提示界面
		if(goodsInfo==null){
			 return "redirect:/detail/getBusiGoodsDetailError";  
		}
		// 第一张商品图片
		BusiQualitypic firstGoodsPic = null;
		if (goodsInfo != null) {
			firstGoodsPic = (goodsInfo.getGoodsPicList() != null && goodsInfo
					.getGoodsPicList().size() > 0) ? goodsInfo
					.getGoodsPicList().get(0) : null;
		}
		// 卖家信息
		BusiGoodsSellerVo sellerInfo = busiGoodsDetailsService
				.selectGoodsSellerInfo(listingId);
		// 平台推荐
		List<BusiGoodsRecommenVo> recommendGoodsList = busiGoodsDetailsService
				.selectGoodsRecommenList(5);
		// 质检报告
		String zjPic = "";
		if(null!=goodsInfo){
			BusiQualitypic busiQualitypic = busiQualityPicService
					.findZJPicByWLID(goodsInfo.getWlid());
			if (busiQualitypic != null) {
				zjPic = busiQualitypic.getPath();
			}
		}
		model.put("goodsInfo", goodsInfo);
		model.put("sellerInfo", sellerInfo);
		model.put("recommendGoodsList", recommendGoodsList);
		model.put("firstGoodsPic", firstGoodsPic);
		model.put("zjPic", zjPic);
		// header全部药材分类
		List<Map<Object, Object>> sortList = sortListService
				.queryAllMedicinal();
		model.put("sortList", sortList);
		List<Map<Object, Object>> categorylist = sortListService
				.queryMedicinalByClassName("搜索关键字");
		model.put("categorylist", categorylist);
		//记录印记
		if(isUserLogin()){
			Trace trace = new Trace(getUserInfo().getUserId().toString())
							.setForEnterListingDetail(listingId)
							.syslogForBusiness();
			model.put("trace", trace);
		}
		
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
			model.put("hearder_type", SeoHeaderEnum.PRODUCT_DETAIL_HEADER.getValue());
		/** * add by Calvin.wh * seo 优化 * 2015.10.27 */
		return "business/detail";
	}

	/**
	 * @Description: 获得挂牌的交易记录
	 * @Author: ---
	 * @update guoyb
	 * @Date: 2015年4月22日
	 * @param request
	 * @param response
	 * @param listingId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getTradeRecordBy")
	@ResponseBody
	public Map<String, Object> getTradeRecordBy(HttpServletRequest request,
			HttpServletResponse response,@Param("listingId")String listingId,ModelMap model) {
		log.info("BusiOrderController.getTradeRecordBy controller");
		Page<BusiGoodsOrderVo> page = new Page<BusiGoodsOrderVo>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listingid", listingId);
		page.setPageSize(5); // 每页5条
		page.setPageNo(StringUtils.isNotBlank(request.getParameter("pageNo")) ? Integer
				.parseInt(request.getParameter("pageNo")) : 1);
		page.setParams(map);
		List<BusiGoodsOrderVo> buyRecordList = busiGoodsDetailsService
				.selectGoodsOrderList(page);
		map.put("status", BusiOrderStateEnum.toMap());
		page.setResults(buyRecordList);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("page", page);
		return resMap;
	}

	/**
	 * 购买
	 * @author fanyuna 
	 * @updater guoyb 添加跳转逻辑
	 * 2015年4月7日 下午4:05:18
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/buyGoods")
	@ResponseBody
	public String buyGoods(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("buyForm") BusiBuyGoodsDto busiBuyGoodsDto, ModelMap model) {
		log.info("BusiOrderController.buyGoods controller");
		/** 修改异步请求认证方式返回值   update by guoyb*/
		//Map<String, Object> resMap = new HashMap<String, Object>();
		JsLoginMessageVo messageVo = new JsLoginMessageVo();
		/** 修改异步请求认证方式返回值*/
		Gson gson = new Gson();
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(
				RedisEnum.SESSION_USER_UC.getValue());
		/** 认证start,shiro认证   close by guoyb*/
		// 判断用户是否认证
		//		if(user.getCertifyStatus()==0){
		//			resMap.put("status", "s");
		//			return gson.toJson(resMap);
		//		}
		/** 认证end*/
		
		/** 改为直接form绑定，start*/
		String _isneedBill = request.getParameter("isneedBill");
		String isneedBill = (_isneedBill != null && !"".equals(_isneedBill)) ? _isneedBill : "0";
		busiBuyGoodsDto.setIsneedBill(isneedBill);
		busiBuyGoodsDto.setBuyerId(user.getUserId());
		
		BusiOrder order = busiGoodsDetailsService.buyGoodsOrderReturnOrder(busiBuyGoodsDto, request);
		/** 改为直接form绑定，end*/

		if (order != null) {
			/** 修改异步请求认证方式返回值   update by guoyb*/
//			resMap.put("status", "y");
//			resMap.put("info", busiBuyGoodsDto.getAmount());
//			resMap.put("listingId", busiBuyGoodsDto.getListingId());
//			resMap.put("orderid", order.getOrderid());
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_OK.getStatus());
			messageVo.setNext("/buyorder/placeOrder?orderid="+order.getOrderid()+"&listingId="+order.getListingid());
			
			// 获取卖家和买家的信息进行封装
//			Map<String, Object> sellInfoMap = busiGoodsDetailsService
//					.getSellerInfo(busiBuyGoodsDto.getWlid());
//			Map<String, Object> paraMap = new HashMap<String, Object>();
//			paraMap.putAll(sellInfoMap);
//			String buyPhone = user.getMobile();
//			String buyName = user.getUserName();
//			paraMap.put("buyPhone", buyPhone);
//			paraMap.put("buyName", buyName);
//			paraMap.put("listingId", busiBuyGoodsDto.getListingId());
		} else {
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_ERROR.getStatus());
			messageVo.setExtra("下单失败");
		}
		return gson.toJson(messageVo);
		/** 修改异步请求认证方式返回值*/
	}

	/**
	 * @Description: ajax详情页收藏
	 * @Updater: guoyb
	 * @Date: 2015年6月15日
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return
	 */
	@RequestMapping(value = "/collectGoods")
	@ResponseBody
	public String collectGoods(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.info("BusiOrderController.collectGoods controller");
		String breedcode = request.getParameter("breedcode");
		String listingId = request.getParameter("listingId");
		String wlid = request.getParameter("wlid");

		BusiBuyGoodsDto busiBuyGoodsDto = new BusiBuyGoodsDto();
		if (breedcode!=null||"".equals(breedcode)) {
			busiBuyGoodsDto.setBreedcode(Long.valueOf(breedcode));
		}
		busiBuyGoodsDto.setListingId(listingId);
		busiBuyGoodsDto.setWlid(wlid);
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(
				RedisEnum.SESSION_USER_UC.getValue());
		busiBuyGoodsDto.setBuyerId(user.getUserId());
		String orderResult = busiGoodsDetailsService
				.saveGoodsCollection(busiBuyGoodsDto);
		/** 修改异步请求认证方式返回值   update by guoyb*/
//		MessageVo message = new MessageVo();
//		if (orderResult != null && orderResult.equals("y")) {
//			message.setOk(true);
//		} else {
//			message.setOk(false);
//			ErrorMessage msg = new ErrorMessage(null, orderResult);
//			List<ErrorMessage> msgList = new ArrayList<ErrorMessage>();
//			msgList.add(msg);
//			message.setErrorMessages(msgList);
//		}
//		return message;
		JsLoginMessageVo messageVo = new JsLoginMessageVo();
		//收藏成功
		if (orderResult != null && orderResult.equals("y")) {
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_OK.getStatus());
			messageVo.setExtra("收藏成功");
		} else {
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_ERROR.getStatus());
			messageVo.setExtra(orderResult);
		}
		Gson gson = GsonFactory.createGson();
		return gson.toJson(messageVo);
		/** 修改异步请求认证方式返回值 */
	}

	/**
	 * @author guoyb 2015年4月7日 上午11:41:40
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getDeposit")
	@ResponseBody
	public String getDeposit(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("BusiOrderController.getDeposit controller");
		String sTotalPrice = request.getParameter("totalPrice");
		if (sTotalPrice == null && "".equals(sTotalPrice)
				&& "0".equals(sTotalPrice)) {
			return "error";
		}
		try {
			BigDecimal totalPrice = new BigDecimal(sTotalPrice);
			//totalPrice应该大于0
			if (totalPrice.compareTo(new BigDecimal(0)) == 1) {
				BigDecimal deposite = totalPrice.multiply(new BigDecimal(
						BusiParamEnum.BUSI_DEPOSIT_RATE.getInfo()));
				deposite = deposite.setScale(2, BigDecimal.ROUND_DOWN);
				return deposite.toString();
			}else if(totalPrice.compareTo(new BigDecimal(0)) == 0){
				return "0.00";
			}else {
				return "error";
			}
		} catch (Exception e) {
			log.error("BusiOrderController.getDeposit error==>totalPrice:"
					+ sTotalPrice);
			return "error";
		}
	}
		
		
	@RequestMapping("/getBusiGoodsDetailError")
	public String getProcurement(HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		modelMap.put("sortList", sortList);
		modelMap.put("tunnage", tunnage);
		modelMap.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		modelMap.put("url", url);
		return "business/detailError";
	}  
}