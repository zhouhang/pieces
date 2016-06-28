package com.jointown.zy.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.jointown.zy.common.dto.BusiQuoteDto;
import com.jointown.zy.common.enums.JsLoginStatusEnum;
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

import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiQuoteService;
import com.jointown.zy.common.service.BusiPurchaseService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.trace.Trace;
import com.jointown.zy.common.vo.BusiPurchaseVo;
import com.jointown.zy.common.vo.JsLoginMessageVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.BusiQuote;

/**
 * 
 * @ClassName: BusiPurQuoteController
 * @Description: 采购报价
 * @Author: shangcuijuan
 * @Date: 2015年10月14日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/busiPur")
public class BusiPurQuoteController  extends UserBaseController {
	private static final Logger log = LoggerFactory
			.getLogger(BusiPurQuoteController.class);

	@Autowired
	private BusiPurchaseService busiPurchaseService;
	@Autowired
	private BusiQuoteService busiQuoteService;

	@Autowired 
	private IndexService indexService;
	
	@Autowired
	private SortListService sortListService;
/**
	 * 
	 * @Description: 获得采购详情
	 * @Author: shangcuijuan
	 * @Date: 2015年10月14日
	 * @param 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/PurchaseDetail")
	public String getPurchaseDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@Param("purchaseId")String purchaseId) {
		log.info("BusiPurQuoteController.getPurchaseDetail controller");
		
		String purchaseid=request.getParameter("purchaseId");
		if (StringUtils.isBlank(purchaseid)) {
			return null;
		}
		
		// 采购信息
		BusiPurchaseVo purInfo = busiPurchaseService
				.selectPurchaseInfo(purchaseid);
		String salename="";
		String salephone="";
		//String daydiff="";
		String saleflag;
		//非空判断，若是空值，直接跳转到错误提示界面
		if(purInfo==null){
				 return null; //"redirect:/detail/getBusiGoodsDetailError";  
		}
		else{
			//业务员
			Map<String,String> saleInfo=new HashMap<String,String>();
			saleInfo=busiPurchaseService.getSalesOfPurchase(purInfo.getPurchaser());
			salename=saleInfo.get("name");
			salephone=saleInfo.get("phone");
			saleflag=saleInfo.get("issale");//是否绑定了交易员（绑定：Y，没绑定：N；若会员没有绑定业务员则系统默认指定业务员）
			
		}
		
		// 交易员促成交易
		int Busicou = 0;
		//if(salename!=null&& !"".equals(salename)){
		if(saleflag=="Y"){
			 List<BusiPurchase> busPur= busiPurchaseService.selectPurOfSalerInfo(salename);
			 if(busPur!=null){
			 Busicou=busPur.size();
			 }		
		}
		else{
			 List<BusiPurchase> busPur= busiPurchaseService.selectPurOfSalerInfo("");
			 if(busPur!=null){
			 Busicou=busPur.size();
			 }	
		}
	// 同类采购
		//List<BusiPurchaseVo> SameBreedList = busiPurchaseService
		//		.selectSameBreedList(purInfo.getBreedId());
		List<BusiPurchaseVo> SameBreedList = busiPurchaseService
				.selectSameBreedNameList(purInfo.getBreedName());
		
		model.put("purInfo", purInfo);
		model.put("salename",salename);
		model.put("salephone",salephone);
		model.put("Busicou", Busicou);
		model.put("SameBreedList", SameBreedList);
        //model.put("daydiff", daydiff);
		//记录印记
		if(isUserLogin()){
			Trace trace = new Trace(getUserInfo().getUserId().toString())
							.setForEnterListingDetail(purchaseId)
							.syslogForBusiness();
			model.put("trace", trace);
		}
		
		String tunnage = indexService.getWarrantsTunnage();
		model.put("tunnage", tunnage);
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		model.put("categorylist", categorylist);
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		model.put("sortList", sortList);
		model.put("url", WebUtils.getPathWithinApplication((HttpServletRequest)request));
		return "purchase/purchaseQuote";
	}
	
	

	/**
	 * 
	 * @Description: 添加报价
	 * @Author: shangcuijuan
	 * @Date: 2015年10月14日
	 * @param 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addQuote")
	@ResponseBody
	public String addBusiQuote(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("QuoteForm") BusiQuoteDto busiQuoteDto, ModelMap model) {
		log.info("BusiPurQuoteController.addBusiQuote controller");
		/** 修改异步请求认证方式返回值   */		
		JsLoginMessageVo messageVo = new JsLoginMessageVo();
		/** 修改异步请求认证方式返回值*/
		Gson gson = new Gson();
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(
				RedisEnum.SESSION_USER_UC.getValue());
		/** form绑定，start*/
		String _purId = request.getParameter("purchaseId");
		String purId=(_purId!=null && !"".equals(_purId))?_purId:"0";
		String _purcodeId = request.getParameter("purchaseCode");
		String purcodeId=(_purcodeId!=null && !"".equals(_purcodeId))?_purcodeId:"0";
		
		busiQuoteDto.setPurchaseId(purId);
		busiQuoteDto.setPurchaseCode(purcodeId);
		busiQuoteDto.setPurchaseStatus(Integer.valueOf(request.getParameter("purchaseStatus")));   //记录采购状态
		busiQuoteDto.setQuoter(user.getUserName());//报价人用户名
		busiQuoteDto.setCompanyName(user.getCompanyName());
		busiQuoteDto.setPhone(user.getMobile());//报价人联系电话
		
		BusiQuote busQuote=busiQuoteService.quoteReturn(busiQuoteDto,request);
		//int QuoteFlag=busiQuoteService.insertQuote(busiQuoteDto,request);
		/** form绑定，end*/

		if (busQuote!= null) {
			/** 修改异步请求认证方式返回值  */
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_OK.getStatus());
			messageVo.setNext(request.getRequestURI());
			//messageVo.setNext("/buyorder/placeOrder?orderid="+order.getOrderid()+"&listingId="+order.getListingid());
	
		} else {
			messageVo.setLoginStatus(JsLoginStatusEnum.STATUS_ERROR.getStatus());
			messageVo.setExtra("报价失败");
		}
		return gson.toJson(messageVo);
		/** 修改异步请求认证方式返回值*/
	}
 
}