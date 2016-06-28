package com.jointown.zy.web.controller;

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

import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.EastArticleService;
import com.jointown.zy.common.util.DeleteHtmlTag;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.EastArticleVo;

/**
 * 品种资讯与行情资讯的跳转
 *
 * @author lichenxiao
 *
 * @data 2015年6月25日
 */
@Controller(value = "wxEastNewsInfoController")
public class WxEastNewsInfoController extends WxUserBaseController {
	
	@Autowired
	private EastArticleService eastArticleService;

	/**
	 * 市场动态默认跳转
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/marketNews")
	public String findMaketNews(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		// 市场动态的lmid=2;研究报告的lmid=9
				int lmid = 2;
				String pz = request.getParameter("pz");

				// 添加查询需要的参数
				Map<String, Object> params = new HashMap<String, Object>();

				// 分页查询
				Page<Map<String, Object>> page = new Page<Map<String, Object>>();
				page.setPageNo(1);
				page.setPageSize(10);

				// 查询市场动态
				params.put("lmid", WxConstant.SCDT_ARTICLE_TYPE);
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


		return "/marketNews";
	}
	
	
	/**
	 * 查询和加载更多市场动态
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * @author lichenxiao
	 *
	 * @data 2015年6月25日
	 */
	@RequestMapping(value = "/marketNews_ajax")
	@ResponseBody
	public String findMaketNewsAJAXByPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		// 市场动态的lmid=2;研究报告的lmid=9
		int lmid = 2;
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
	 * 行业新闻默认跳转
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/industryNews")
	public String findNewsPrice(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		// 市场动态的lmid=11;研究报告的lmid=9
		int lmid = 11;
		String pz = request.getParameter("pz");

		// 添加查询需要的参数
		Map<String, Object> params = new HashMap<String, Object>();

		// 分页查询
		Page<Map<String, Object>> page = new Page<Map<String, Object>>();
		page.setPageNo(1);
		page.setPageSize(10);

		// 查询行业新闻
		params.put("lmid", WxConstant.HY_NEWS_ARTICLE_TYPE);
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
		return "/industryNews";
	}
	
	/**
	 * 查询和加载更多行业新闻
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * @author lichenxiao
	 *
	 * @data 2015年6月25日
	 */
	@RequestMapping(value = "/industryNews_ajax")
	@ResponseBody
	public String findIndustryNewsAJAXByPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		// 行业新闻的lmid=11;研究报告的lmid=9
		int lmid = 11;
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
	
}
