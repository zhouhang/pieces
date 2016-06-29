/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiGoodsDetailsService;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: BuyOrderController
 * @Description: 买家订单
 * @Author: guoyb
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/buyorder")
public class BuyOrderController {
	private static final Logger log = LoggerFactory
			.getLogger(BuyOrderController.class);
	
	@Autowired
	private BusiOrderService busiOrderService;
	
	@Autowired
	private BusiGoodsDetailsService busiGoodsDetailsService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
	
	@Autowired
	private SortListService sortListService;
	
	@Autowired
	private IndexService indexService;

	/**
	 * @Description: 摘牌详情页
	 * @Author: guoyb
	 * @Date: 2015年4月8日
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/placeOrder")
	public String placeOrder(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.info("BuyOrderController.placeOrder controller");
		String orderId = request.getParameter("orderid");
		String listingId = request.getParameter("listingId");
		UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession()
				.getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		if (null == listingId || "".equals(listingId)) {
			return "error";
		}
		if (null == orderId || "".equals(orderId)) {
			return "error";
		}
		
		BusiOrder order = this.busiOrderService.findBusiOrderById(orderId);
		BusiGoodsInfoVo goodsInfo = busiGoodsDetailsService.selectGoodsInfo(listingId);
		if(goodsInfo!=null){
			// 图片缩放
			String path = goodsInfo.getGoodsPicList().get(0).getPath();
			if (path != null && !path.isEmpty()) {
			path = path.substring(0, path.lastIndexOf(".")) + "_"
					+ sftpConfigInfo.getSftpMiddleWidth()
					+ path.substring(path.lastIndexOf("."));
			model.put("path", path);
			}
		}else {
			return "error";
		}
		
		//判断当前登陆的用户是否是订单买家
		if (!user.getUserId().toString().equals(order.getBuyer().toString())) {
			return "error";
		}
		//判断order的状态是否为 买家已下单
		if (!BusiOrderStateEnum.PlACED_ORDER.getCode().equals(order.getOrderstate().toString())) {
			return "error";
		}
		/** header全部药材分类 start*/
		List<Map<Object, Object>> sortList = sortListService
				.queryAllMedicinal();
		List<Map<Object, Object>> categorylist = sortListService
				.queryMedicinalByClassName("搜索关键字");
		model.put("sortList", sortList);
		model.put("categorylist", categorylist);
		/** header全部药材分类 end*/
		
		/**在仓药材（吨）add ldp 2015-11-18 start***/
		String tunnage = indexService.getWarrantsTunnage();
		model.put("tunnage", tunnage);
		/**在仓药材（吨）add ldp 2015-11-18 end***/
		
		model.put("orderInfo", order);
		model.put("goodsInfo", goodsInfo);
		model.put("userId", user.getUserId().toString());
		return "business/detail-pickcards";
	}
	
}
