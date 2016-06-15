/**
 * @author guoyb
 * 2015年3月9日 下午7:50:10
 */
package com.jointown.zy.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.google.gson.Gson;
import com.jointown.zy.common.service.EastPinzhongService;
import com.jointown.zy.common.service.EastYcPriceHistoryService;
import com.jointown.zy.common.service.EastYcPriceTodayService;
import com.jointown.zy.common.service.WxPriceService;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.EastYcPriceHistoryVo;
import com.jointown.zy.common.vo.EastYcPriceTodayVo;
import com.jointown.zy.common.vo.WxPriceVo;

/**
 * @author guoyb 2015年3月9日 下午7:50:10
 */
@Controller
public class WxYcPriceController extends WxUserBaseController {

	@Autowired
	private EastPinzhongService eastPinzhongService;

	@Autowired
	private WxPriceService wxPriceService;

	@Autowired
	private EastYcPriceTodayService eastYcPriceTodayService;

	@Autowired
	private EastYcPriceHistoryService eastYcPriceHistoryService;

	/**
	 * 跳转到价格页面
	 * 
	 * @author guoyb 2015年3月12日 下午6:32:30
	 */
	@RequestMapping("ycprice")
	public String ycPrice(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String type = request.getParameter("type");
		if (type == null || "".equals(type)) {
			type = "today";
		}
		model.put("type", type);
		return "charge";
	}

	@RequestMapping("ycprice_today_ajax")
	@ResponseBody
	public String ycPriceToday(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// String chandi = request.getParameter("chandi");
		String yc = request.getParameter("yc");
		String pageNo = request.getParameter("pageNo");

		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		// 添加查询需要的参数：产地、药材名称、分页页数
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("chandi", chandi);
		map.put("yc", yc);
		map.put("start", ((Integer.parseInt(pageNo) - 1) * 5) + 1);
		map.put("end", Integer.parseInt(pageNo) * 5);

		// 查询珍药材挂牌的价格
		// Date today = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// map.put("startdate", sdf.format(today));
		// map.put("enddate", sdf.format(today));
		// List<WxPriceVo> wx_todayPrices =
		// this.wxPriceService.selectPriceByAll(map);

		List<WxPriceVo> wx_todayPrices = new ArrayList<WxPriceVo>();
		
		// 查询东网的今日价格
		List<EastYcPriceTodayVo> east_todayPrices = this.eastYcPriceTodayService
				.selectPriceBy(map);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("wx_todayPrices", wx_todayPrices);
		result.put("east_todayPrices", east_todayPrices);
		result.put("pageNo", Integer.parseInt(pageNo) + 1);

		Gson gson = GsonFactory.createGson();
		String todayPrice = gson.toJson(result);
		// 加以下语句：页面中文没有乱码
		response.setCharacterEncoding("utf-8");
		return todayPrice;
	}

	/*
	 * @RequestMapping("ycprice_history_ajax")
	 * 
	 * @ResponseBody public String ycPriceHistory(HttpServletRequest request,
	 * HttpServletResponse response, ModelMap model) { // String chandi =
	 * request.getParameter("chandi"); String yc = request.getParameter("yc");
	 * String pageNo = request.getParameter("pageNo");
	 * 
	 * if (null == pageNo || "".equals(pageNo)) { pageNo = "1"; }
	 * 
	 * // 添加查询需要的参数 Map<String, Object> map = new HashMap<String, Object>(); //
	 * map.put("chandi", chandi); map.put("yc", yc); map.put("start",
	 * ((Integer.parseInt(pageNo) - 1) * 5) + 1); map.put("end",
	 * (Integer.parseInt(pageNo)) * 5);
	 * 
	 * // 查询珍药材挂牌的历史价格 Date today = new Date(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); map.put("startdate", "0001-01-01");
	 * map.put("enddate", sdf.format(today)); List<WxPriceVo> wx_historyPrices =
	 * this.wxPriceService .selectPriceByAll(map);
	 * 
	 * // 查询东网的历史价格，每次查询前一年数据 Calendar calendar = Calendar.getInstance();
	 * calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) -
	 * (Integer.parseInt(pageNo) - 1)); Date date = calendar.getTime();
	 * map.put("startdate", date); List<EastYcPriceHistoryVo> east_historyPrices
	 * = this.eastYcPriceHistoryService .selectPriceBy(map);
	 * 
	 * Map<String, Object> result = new HashMap<String, Object>();
	 * result.put("wx_historyPrices", wx_historyPrices);
	 * result.put("east_historyPrices", east_historyPrices);
	 * result.put("pageNo", Integer.parseInt(pageNo) + 1);
	 * 
	 * Gson gson = GsonFactory.createGson(); String historyPrice =
	 * gson.toJson(result); // 加以下语句：页面中文没有乱码
	 * response.setCharacterEncoding("utf-8"); return historyPrice; }
	 */

	/**
	 * 查询所有历史价格
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月23日
	 */
	@RequestMapping("ycprice_all_history_ajax")
	@ResponseBody
	public String ycPriceAllHistory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String breedName = request.getParameter("yc");

		Map<String, Object> result = findYcPriceHistory(1, breedName);
		List<EastYcPriceHistoryVo> prices = eastYcPriceHistoryService
				.selectAllPriceByBreedName(breedName);

		List<String> series = new ArrayList<String>();
		if (prices.size() > 0) {
			List<String> xAxis = new ArrayList<String>();
			for (EastYcPriceHistoryVo price : prices) {
				series.add(price.getPrice());
				xAxis.add(price.getTtm());
			}

			result.put("xAxis", xAxis);

		}

		result.put("series", series);
		result.put("breedName", breedName);
		Gson gson = GsonFactory.createGson();
		String historyPrice = gson.toJson(result);
		response.setCharacterEncoding("utf-8");
		return historyPrice;
	}

	/**
	 * 查询更多历史价格
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月23日
	 */
	@RequestMapping("ycprice_history_ajax")
	@ResponseBody
	public String ycPriceHistory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		// String chandi = request.getParameter("chandi");
		String yc = request.getParameter("yc");
		String pageNo = request.getParameter("pageNo");

		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		Map<String, Object> result = findYcPriceHistory(
				Integer.parseInt(pageNo), yc);
		Gson gson = GsonFactory.createGson();
		String historyPrice = gson.toJson(result);
		response.setCharacterEncoding("utf-8");
		return historyPrice;
	}

	@RequestMapping("allchandi")
	@ResponseBody
	public List<String> getAllChandi(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> chandis = this.eastPinzhongService.selectAllChandi();
		response.setCharacterEncoding("utf-8");
		return chandis;
	}

	/**
	 * 查询历史价格
	 *
	 * @param pageNo
	 * @param yc
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月23日
	 */
	private Map<String, Object> findYcPriceHistory(int pageNo, String yc) {
		// 添加查询需要的参数
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("chandi", chandi);
		map.put("yc", yc);
		map.put("start", ((pageNo - 1) * 5) + 1);
		map.put("end", pageNo * 5);

		// 查询珍药材挂牌的历史价格
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("startdate", "0001-01-01");
		map.put("enddate", sdf.format(today));
//		List<WxPriceVo> wx_historyPrices = this.wxPriceService.selectPriceByAll(map);
		List<WxPriceVo> wx_historyPrices = new ArrayList<WxPriceVo>();

		// 查询东网的历史价格，每次查询前一年数据
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -(pageNo - 1));
		Date date = calendar.getTime();
		map.put("startdate", date);
		List<EastYcPriceHistoryVo> east_historyPrices = this.eastYcPriceHistoryService
				.selectPriceBy(map);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("wx_historyPrices", wx_historyPrices);
		result.put("east_historyPrices", east_historyPrices);
		result.put("pageNo", pageNo + 1);

		return result;
	}
}
