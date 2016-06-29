/**
 * @author guoyb
 * 2015年3月3日 上午11:44:56
 */
package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.EastArticleService;
import com.jointown.zy.common.util.DeleteHtmlTag;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.EastArticleVo;

/**
 * 行情快讯和市场动态的控制器
 * 
 * @author guoyb 2015年3月3日 上午11:44:56
 */
@Controller(value = "wxEastArticleController")
public class WxEastArticleController extends WxUserBaseController {

	private final static Logger logger = LoggerFactory.getLogger(WxEastArticleController.class);
	
	@Autowired
	private EastArticleService eastArticleService;

	/**
	 * 行情新闻和市场动态的详细查询页面
	 * 
	 * @author guoyb 2015年3月3日 上午11:44:56
	 */
	@RequestMapping(value = "/articleDetail")
	public String findDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		Integer acid = Integer.parseInt(request.getParameter("acid"));

		// 如果没有接收到参数acid，返回null
		if (null == acid) {
			return null;
		} else {
			try {
				eastArticleService.updateEastArticleTipByACid(acid);
				EastArticleVo article = eastArticleService.findOneEastArticle(acid);
				// 如果查询到article的内容传送到detail界面，否则返回null
				if (null != article) {
					modelMap.addAttribute("article", article);
					return "/detail";
				} else {
					return null;
				}
			} catch (Exception e) {
				logger.error("WxEastArticleController.articleDetail"+e.getMessage());
				return null;
			}
		}
	}

	/**
	 * 第一次查询品种分析和研究报告
	 * 
	 * @author guoyb 2015年3月4日 下午3:43:24
	 */
	/*
	 * @RequestMapping(value = "/analysis") public String
	 * findAnalysisByPage(HttpServletRequest request, HttpServletResponse
	 * response, ModelMap modelMap) { // 品种分析的lmid=1;研究报告的lmid=9 int lmid =
	 * Integer.parseInt(request.getParameter("lmid")); String pz =
	 * request.getParameter("pz");
	 * 
	 * // lmid非法或者不存在 if (lmid != WxConstant.PZ_ANALYSIS_TYPE && lmid !=
	 * WxConstant.RESEARCH_PAPER_TYPE) { return null; }
	 * 
	 * // 添加查询需要的参数 Map<String, Object> params = new HashMap<String, Object>();
	 * 
	 * // 分页查询 Page<Map<String, Object>> page = new Page<Map<String, Object>>();
	 * page.setPageNo(1); page.setPageSize(10);
	 * 
	 * // 查询品种分析 params.put("lmid", WxConstant.PZ_ANALYSIS_TYPE);
	 * params.put("pz", pz); page.setParams(params); List<EastArticleVo>
	 * articles_1 = eastArticleService .findEastArticleByPage(page);
	 * 
	 * params.clear();
	 * 
	 * // 查询研究报告 params.put("lmid", WxConstant.RESEARCH_PAPER_TYPE);
	 * params.put("pz", pz); page.setParams(params); List<EastArticleVo>
	 * articles_9 = eastArticleService .findEastArticleByPage(page);
	 * 
	 * for (EastArticleVo eastArticleVo : articles_1) { String sourceContent =
	 * DeleteHtmlTag.delHTMLTag(eastArticleVo .getCont()); if
	 * (sourceContent.length() < WxConstant.LENGTH_ABSTRACT) {
	 * eastArticleVo.setAc_abstract(sourceContent); }else {
	 * eastArticleVo.setAc_abstract(sourceContent.substring(0,
	 * WxConstant.LENGTH_ABSTRACT)); } } for (EastArticleVo eastArticleVo :
	 * articles_9) { String sourceContent =
	 * DeleteHtmlTag.delHTMLTag(eastArticleVo .getCont()); if
	 * (sourceContent.length() < WxConstant.LENGTH_ABSTRACT) {
	 * eastArticleVo.setAc_abstract(sourceContent); }else {
	 * eastArticleVo.setAc_abstract(sourceContent.substring(0,
	 * WxConstant.LENGTH_ABSTRACT)); } }
	 * 
	 * modelMap.put("articles_1", articles_1); modelMap.put("articles_9",
	 * articles_9); modelMap.put("lmid", lmid);
	 * 
	 * // 设置pageNo为第一页 modelMap.put("pageNo_1", 1); modelMap.put("pageNo_9", 1);
	 * 
	 * return "/analysis"; }
	 */

	/*
	 * @RequestMapping(value = "/analysis_ajax")
	 * 
	 * @ResponseBody public String findAnalysisAJAXByPage(HttpServletRequest
	 * request, HttpServletResponse response, ModelMap modelMap) { //
	 * 品种分析的lmid=1;研究报告的lmid=9 Integer lmid =
	 * Integer.parseInt(request.getParameter("lmid")); String pz =
	 * request.getParameter("pz"); String pageNo_string =
	 * request.getParameter("pageNo");
	 * 
	 * // lmid非法或者不存在 if (lmid != WxConstant.PZ_ANALYSIS_TYPE && lmid !=
	 * WxConstant.RESEARCH_PAPER_TYPE) { return null; }
	 * 
	 * // 添加查询需要的参数 Map<String, Object> params = new HashMap<String, Object>();
	 * params.put("lmid", lmid); params.put("pz", pz);
	 * 
	 * // 分页查询 Page<Map<String, Object>> page = new Page<Map<String, Object>>();
	 * int pageNo;
	 * 
	 * // 设置当前页 if (null == pageNo_string || pageNo_string == "") { pageNo = 1;
	 * } else { pageNo = Integer.parseInt(pageNo_string); pageNo++; }
	 * 
	 * page.setPageSize(10); page.setParams(params); page.setPageNo(pageNo);
	 * List<EastArticleVo> articles = eastArticleService
	 * .findEastArticleByPage(page); for (EastArticleVo eastArticleVo :
	 * articles) { String sourceContent = DeleteHtmlTag.delHTMLTag(eastArticleVo
	 * .getCont()); if (sourceContent.length() < WxConstant.LENGTH_ABSTRACT) {
	 * eastArticleVo.setAc_abstract(sourceContent); }else {
	 * eastArticleVo.setAc_abstract(sourceContent.substring(0,
	 * WxConstant.LENGTH_ABSTRACT)); } }
	 * 
	 * modelMap.put("articles", articles); modelMap.put("lmid", lmid);
	 * modelMap.put("pageNo", pageNo);
	 * 
	 * Gson gson = GsonFactory.createGson(); String actJson =
	 * gson.toJson(articles);
	 * 
	 * return actJson; }
	 */

	/**
	 * 行情快讯
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/eastArticleNews", method = RequestMethod.GET)
	 * public String DelHtmlTag(HttpServletRequest request,HttpServletResponse
	 * response) throws Exception { return "eastArticleNews"; }
	 */

	/*@RequestMapping(value = "/getEastArticleNews", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getEastArticleNews1(@RequestParam(value="pageNo",required=false,defaultValue="1") String pageNo,@RequestParam(value="ycnam",required=false,defaultValue="") String ycnam)
			throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<EastArticleVo> page = new Page<EastArticleVo>();
		//搜索页数
		page.setPageNo(Integer.parseInt(pageNo));
		//搜索数量
		page.setPageSize(10);
		//搜索品种
		Map<String, Object> params = new HashMap<String, Object>();
//		if(ycnam.isEmpty()){
//			params.put("ycnam", ycnam);
//			page.setParams(params);
//			//搜索结果
//			List<EastArticleVo> eastArticles = eastArticleService.findEastArticleNewsByCondition(page);
//			map.put("ok", eastArticles.size());
//			map.put("eastArticles", eastArticles);
//		}else{
//			String breedName = eastPzDanganService.findBreedNameByName(ycnam);
//			if(breedName.isEmpty()){
//				map.put("ok", 0);
//			}else{
//				params.put("ycnam", breedName);
//				page.setParams(params);
//				//搜索结果
//				List<EastArticleVo> eastArticles = eastArticleService.findEastArticleNewsByCondition(page);
//				map.put("ok", eastArticles.size());
//				map.put("eastArticles", eastArticles);
//			}
//		}
		params.put("ycnam", ycnam);
		page.setParams(params);
		//搜索结果
		List<EastArticleVo> eastArticles = eastArticleService.findEastArticleNewsByCondition(page);
		map.put("ok", eastArticles.size());
		map.put("eastArticles", eastArticles);
		
		return map;
	}*/

	/**
	 * 第一次查询品种分析
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月16日
	 */
	@RequestMapping(value = "/analysis")
	public String findAnalysisByPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		// 品种分析的lmid=1;研究报告的lmid=9
		String lmid = WxConstant.PZ_ANALYSIS_TYPE +","+WxConstant.RESEARCH_PAPER_TYPE;
		String pz = request.getParameter("pz");

		// 添加查询需要的参数
		Map<String, Object> params = new HashMap<String, Object>();

		// 分页查询
		Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		page.setPageNo(1);
		page.setPageSize(10);

		// 查询品种分析
		params.put("lmid", lmid);
		params.put("pz", pz);
		page.setParams(params);
		List<EastArticleVo> articles_1 = eastArticleService
				.findEastArticleByPage(page);

		for (EastArticleVo eastArticleVo : articles_1) {
			String sourceContent = DeleteHtmlTag.delHTMLTag(eastArticleVo
					.getCont());
			if (sourceContent.length() < WxConstant.LENGTH_ABSTRACT) {
				eastArticleVo.setAc_abstract(sourceContent);
			} else {
				eastArticleVo.setAc_abstract(sourceContent.substring(0,
						WxConstant.LENGTH_ABSTRACT));
			}
		}

		modelMap.put("articles_1", articles_1);
		modelMap.put("lmid", lmid);

		// 设置pageNo为第一页
		modelMap.put("pageNo_1", 1);

		return "/analysis";
	}

	/**
	 * 查询和加载更多品种分析
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月17日
	 */
	@RequestMapping(value = "/analysis_ajax")
	@ResponseBody
	public String findAnalysisAJAXByPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		// 品种分析的lmid=1;研究报告的lmid=9
		String lmid = WxConstant.PZ_ANALYSIS_TYPE +","+WxConstant.RESEARCH_PAPER_TYPE;
		String pz = request.getParameter("pz");
		String pageNo_string = request.getParameter("pageNo");

		// 添加查询需要的参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lmid", lmid);
		params.put("pz", pz);

		// 设置当前页
		int pageNo = (pageNo_string == null || pageNo_string.equals("")) ? 1
				: (Integer.parseInt(pageNo_string) + 1);
		// 分页查询
		Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		page.setPageSize(10);
		page.setParams(params);
		page.setPageNo(pageNo);
		List<EastArticleVo> articles = eastArticleService
				.findEastArticleByPage(page);
		for (EastArticleVo eastArticleVo : articles) {
			String sourceContent = DeleteHtmlTag.delHTMLTag(eastArticleVo
					.getCont());
			if (sourceContent.length() < WxConstant.LENGTH_ABSTRACT) {
				eastArticleVo.setAc_abstract(sourceContent);
			} else {
				eastArticleVo.setAc_abstract(sourceContent.substring(0,
						WxConstant.LENGTH_ABSTRACT));
			}
		}

		return GsonFactory.createGson().toJson(articles);
	}

	/**
	 * 行情快讯，第一次加载
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月20日
	 */
	@RequestMapping(value = "/eastArticleNews", method = RequestMethod.GET)
	public String DelHtmlTag(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		// 查询市场快讯
		Page<EastArticleVo> marketPage = new Page<EastArticleVo>();
		marketPage.setPageNo(1);
		marketPage.setPageSize(10);
		List<EastArticleVo> marketArticles = eastArticleService
				.findEastArticleNewsByCondition(marketPage);

		// 查询产地快讯
		Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		page.setPageNo(1);
		page.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lmid", WxConstant.PLACE_ARTICLE_TYPE);
		page.setParams(params);
		List<EastArticleVo> placeArticles = eastArticleService
				.findEastArticleByPage(page);

		modelMap.put("market_articles", marketArticles);
		modelMap.put("place_articles", placeArticles);

		// 设置pageNo为第一页
		modelMap.put("market_pageNo", 1);
		modelMap.put("place_pageNo", 1);
		modelMap.put("type", "market");
		
		return "eastArticleNews";
	}

	/**
	 * 行情快讯，查询和加载更多
	 *
	 * @param pageNo
	 * @param ycnam
	 * @param type
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年5月8日
	 */
	@RequestMapping(value = "/getEastArticleNews", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getEastArticleNews(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
			@RequestParam(value = "ycnam", required = false, defaultValue = "") String ycnam,
			@RequestParam(value = "type", required = false, defaultValue = "market") String type)
			throws Exception {
		// 市场快讯 type："market" 产地快讯 type："place"
		List<EastArticleVo> eastArticles;
		Map<String, Object> params = new HashMap<String, Object>();
		
		if("market".equals(type)){
			// 查询市场快讯
			Page<EastArticleVo> page = new Page<EastArticleVo>();
			page.setPageNo(Integer.parseInt(pageNo));
			page.setPageSize(10);
			params.put("ycnam", ycnam);
			page.setParams(params);
			eastArticles = eastArticleService
					.findEastArticleNewsByCondition(page);
		}else{
			// 查询产地快讯
			Page<Map<String, Object>> page = new Page<Map<String, Object>>();
			page.setPageNo(Integer.parseInt(pageNo));
			page.setPageSize(10);
			params.put("pz", ycnam);
			params.put("lmid", WxConstant.PLACE_ARTICLE_TYPE);
			page.setParams(params);
			eastArticles = eastArticleService
					.findEastArticleByPage(page);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();	
		map.put("ok", eastArticles.size());
		map.put("eastArticles", eastArticles);

		return map;
	}
}
