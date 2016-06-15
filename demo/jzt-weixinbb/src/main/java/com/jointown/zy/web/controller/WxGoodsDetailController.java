package com.jointown.zy.web.controller;

/**
import java.io.PrintWriter;
*/
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiBuyGoodsDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.enums.JsLoginStatusEnum;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiGoodsDetailsService;
import com.jointown.zy.common.service.BusiQualityPicService;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiGoodsOrderVo;
import com.jointown.zy.common.vo.JsLoginMessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
* 项目名称：jzt-weixinbb  
* 类名称：WxGoodsDetailController  
* 类描述：  小珍现货,挂牌详情
* 创建人：  宋威
* 创建时间：2015-07-21  
* 修改人：  
* 修改时间：  
* 修改备注：  
* @version v1.0  
 */
@Controller
@RequestMapping(value = "/detail")
public class WxGoodsDetailController extends WxUserBaseController {
	private static final Logger log = LoggerFactory.getLogger(WxGoodsDetailController.class);
	//已下单订单72小时过期天数
	private Integer afterDays = Integer.parseInt(SpringUtil.getConfigProperties("jointown.busi.order.expire.days.after"));
	
	@Autowired
	private BusiGoodsDetailsService busiGoodsDetailsService;

	@Autowired
	private BusiQualityPicService busiQualityPicService;
	
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
	public Map<String, Object> getTradeRecordBy(HttpServletRequest request,HttpServletResponse response,@Param("listingId")String listingId,ModelMap model) {
		log.info("BusiOrderController.getTradeRecordBy controller");
		Page<BusiGoodsOrderVo> page = new Page<BusiGoodsOrderVo>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listingid", listingId);
		page.setPageSize(ConfigConstant.WX_BUYINFO_PAGE_SIZE);   //每页2条
		page.setPageNo(StringUtils.isNotBlank(request.getParameter("pageNo")) ? Integer.parseInt(request.getParameter("pageNo")) : 1);
		page.setParams(map);
		List<BusiGoodsOrderVo> buyRecordList = busiGoodsDetailsService.selectGoodsOrderList(page);
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
	public String buyGoods(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("buyForm") BusiBuyGoodsDto busiBuyGoodsDto, ModelMap model) {
		log.info("WxGoodsDetailController.buyGoods controller");
		
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
		Gson gson = new Gson();
		JsLoginMessageVo messageVo = new JsLoginMessageVo();
		if(user ==null){
			messageVo.setLoginStatus(JsLoginStatusEnum.UNLOGIN.getStatus());
			messageVo.setNext("/login?go="+request.getParameter("go"));
			return gson.toJson(messageVo);
		}
		// 判断用户是否认证
		if(user.getCertifyStatus()==0){
			messageVo.setLoginStatus(JsLoginStatusEnum.UNANTHENTICATION.getStatus());
			messageVo.setNext("/info?main=cer");
			return gson.toJson(messageVo);
		}
		/** 改为直接form绑定，start*/
		String _isneedBill = request.getParameter("isneedBill");
		String isneedBill = (_isneedBill != null && !"".equals(_isneedBill)) ? _isneedBill : "0";
		busiBuyGoodsDto.setIsneedBill(isneedBill);
		busiBuyGoodsDto.setBuyerId(user.getUserId());
		
		//下订单	
		BusiOrder order = busiGoodsDetailsService.buyGoodsOrderReturnOrder(busiBuyGoodsDto, request);
		
		if (order != null) {
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_OK.getStatus());
			messageVo.setNext("/delist-succeed");
			Date date =TimeUtil.moveDays(new Date(), afterDays);
			String time = TimeUtil.getYMDHMS(date);
			messageVo.setNext(String.valueOf(afterDays*24)); //过期小时
			messageVo.setExtra(time); //过期日期
		} else {
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_ERROR.getStatus());
			messageVo.setNext("/delist-failure");
			messageVo.setExtra("下单失败");
		}
		return gson.toJson(messageVo);
	}
	
	// 商品详情页
	@RequestMapping(value = "/listingId/{id}/orderNumber/{num}/isneedBill/{isneedBill}")
	public String getBusiGoodsDetail(ModelMap model,@PathVariable(value="id") String id,@PathVariable(value="num") String num,@PathVariable(value="isneedBill") String isneedBill) {
		log.info("WxGoodsDetailController.getBusiGoodsDetail controller");

		if (StringUtils.isBlank(id)) {
			//没有错误界面暂定搜索列表
			return "redirect:/delist-void";
		}
		// 商品信息
		BusiGoodsInfoVo goodsInfo = busiGoodsDetailsService.selectGoodsInfo(id);
		//非空判断，若是空值，直接跳转到错误提示界面
		if(goodsInfo==null){
			 //没有错误界面暂定搜索列表
			 return "redirect:/delist-void"; 
		}
		// 质检报告
		String zjPic = "";
		if(null!=goodsInfo){
			BusiQualitypic busiQualitypic = busiQualityPicService.findZJPicByWLID(goodsInfo.getWlid());
			if (busiQualitypic != null) {
				zjPic = busiQualitypic.getPath();
			}
		}
		model.put("isneedBill", isneedBill);
		model.put("orderNumber", num);
		model.put("goodsInfo", goodsInfo);
		model.put("zjPic", zjPic);
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "sells-detail";
	}
	
	/**
	 * 根据挂牌ID动态实时计算其保证金
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getDeposit")
	@ResponseBody
	public String getDeposit(HttpServletRequest request,HttpServletResponse response) {
		log.info("WxGoodsDetailController.getDeposit controller");
		String sTotalPrice = request.getParameter("totalPrice");
		if (sTotalPrice == null && "".equals(sTotalPrice) && "0".equals(sTotalPrice)) {
			return "error";
		}
		try {
			BigDecimal totalPrice = new BigDecimal((sTotalPrice==null)?"0":sTotalPrice);
			//totalPrice应该大于0
			if (totalPrice.compareTo(new BigDecimal(0)) == 1) {
				BigDecimal deposite = totalPrice.multiply(new BigDecimal(BusiParamEnum.BUSI_DEPOSIT_RATE.getInfo()));
				deposite = deposite.setScale(2, BigDecimal.ROUND_DOWN);
				return deposite.toString();
			}else if(totalPrice.compareTo(new BigDecimal(0)) == 0){
				return "0.00";
			}else {
				return "error";
			}
		} catch (Exception e) {
			log.error("WxGoodsDetailController.getDeposit error==>totalPrice:"+ sTotalPrice);
			return "error";
		}
	}
}
