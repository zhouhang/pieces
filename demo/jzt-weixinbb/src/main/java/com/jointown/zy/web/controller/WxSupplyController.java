/**
 * @author lichenxiao
 * 2015年3月31日 上午11:24:10
 */
package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.service.WxSupplyPicService;
import com.jointown.zy.common.service.WxSupplyService;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.WxDemandVo;
import com.jointown.zy.common.vo.WxPriceVo;
import com.jointown.zy.common.vo.WxSupplyVo;
import com.google.gson.Gson;

/**
 * @author lichenxiao 2015年3月31日 上午11:24:10
 */
@Controller
public class WxSupplyController extends WxUserBaseController {

	@Autowired
	private WxSupplyService wxSupplyService;
	@Autowired
	private WxSupplyPicService wxSupplyPicService;
	@Autowired
	private WxDemandService wxDemandService;
	
	/**
	 * 跳转到供求信息页面
	 * 
	 * @author lichenxiao 2015年3月31日上午11:39:30
	 */
	@RequestMapping("supply_info")
	public String ycPrice(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String type = request.getParameter("type");
		String yc = request.getParameter("yc");
		model.put("type", type);
		model.put("yc", yc);
		return "supply";
	}

	/**
	 * 默认查询供应信息
	 * @author lichenxiao 2015年4月13日上午12:02
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("supply_default_ajax")
	@ResponseBody
	public String supplyPrice(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		String yc = request.getParameter("yc");//页面输入品种的值
		String pageNo = request.getParameter("pageNo");//当前页码值，默认为null

		// 添加查询需要的参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yc", yc);//品种
		
		//分页开始
		if (null == pageNo || pageNo.equals("")) {//默认显示按发布时间的5条
			map.put("start", 1);
			map.put("end", 5);
		} else {//翻页后的5条:(n-1)*5+1 ~ n*5
			map.put("start", (Integer.parseInt(pageNo) -1) * 5 + 1);
			map.put("end", Integer.parseInt(pageNo) * 5);
		}
		
		//默认查询珍药材的挂牌信息（与今日价格功能里的珍药材挂牌信息只差别于时间上的限制）
		List<WxPriceVo> wx_supplyPrices = new ArrayList<WxPriceVo>();
//		List<WxPriceVo> wx_supplyPrices = this.wxSupplyService.selectPriceByAll(map);
		

		//查询东网与珍药材的供应信息（翻页，每次显示5条）
		List<WxSupplyVo> wx_supplyInfo = this.wxSupplyService.selectInfoBySupply(map);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("wx_supplyPrices", wx_supplyPrices);//默认查询珍药材的挂牌信息
		result.put("wx_supplyInfo", wx_supplyInfo);//查询东网与珍药材的供应信息
		
		if (null == pageNo || "".equals(pageNo)) {
			result.put("pageNo", 1);
		} else {
			result.put("pageNo", Integer.parseInt(pageNo)+1);
		}

		Gson gson = GsonFactory.createGson();//使用Json框架并返回
		String supplyPrice = gson.toJson(result);//使用Json框架存数据
		response.setCharacterEncoding("utf-8");//解决页面中文乱码
		return supplyPrice;
	}	
	
	/**
	 * 默认查询求购信息
	 * @author lichenxiao 2015年4月28日上午10:08
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("purchase_default_ajax")
	@ResponseBody
	public String purchasePrice(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		String yc = request.getParameter("yc");//页面输入品种的值
		String pageNo = request.getParameter("pageNo");//当前页码值，默认为null

		// 添加查询需要的参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yc", yc);//品种
		
		//分页开始
		if (null == pageNo || pageNo.equals("")) {//默认显示按发布时间的5条
			map.put("start", 1);
			map.put("end", 5);
		} else {//翻页后的5条:(n-1)*5+1 ~ n*5
			map.put("start", (Integer.parseInt(pageNo) -1) * 5 + 1);
			map.put("end", Integer.parseInt(pageNo) * 5);
		}
		
		//默认查询珍药材的挂牌信息（与今日价格功能里的珍药材挂牌信息只差别于时间上的限制）
		List<WxPriceVo> wx_supplyPrices = new ArrayList<WxPriceVo>();
//		List<WxPriceVo> wx_supplyPrices = this.wxSupplyService.selectPriceByAll(map);
		
		//查询东网与珍药材的供应信息（翻页，每次显示5条）
		List<WxDemandVo> wx_demandInfo = this.wxDemandService.selectInfoByDemand(map);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("wx_supplyPrices", wx_supplyPrices);//默认查询珍药材的挂牌信息
		result.put("wx_demandInfo", wx_demandInfo);//查询东网与珍药材的求购信息
		
		if (null == pageNo || "".equals(pageNo)) {
			result.put("pageNo", 1);
		} else {
			result.put("pageNo", Integer.parseInt(pageNo)+1);
		}

		Gson gson = GsonFactory.createGson();//使用Json框架并返回
		String supplyPrice = gson.toJson(result);//使用Json框架存数据
		response.setCharacterEncoding("utf-8");//解决页面中文乱码
		return supplyPrice;
	}	
	
	
	/**
	 * 跟据第一张图片查询东网的供应信息的图片或者查询珍药材供应信息的图片
	 * @author lichenxiao 2015年5月8日上午16:56
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("supply_pic_ajax")
	@ResponseBody
	public String supplyPic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String picOne = request.getParameter("picOne");
		result.put("wx_supplyPic",wxSupplyPicService.selectAllBySupplyPic(picOne));
		
		Gson gson = GsonFactory.createGson();//使用Json框架并返回
		String supplyPic = gson.toJson(result);//使用Json框架存数据
		response.setCharacterEncoding("utf-8");//解决页面中文乱码
		return supplyPic;
	}
}
