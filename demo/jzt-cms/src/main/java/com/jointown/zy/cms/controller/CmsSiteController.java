package com.jointown.zy.cms.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jointown.zy.common.model.CmsSite;
import com.jointown.zy.common.service.CmsSiteService;

/**
 * 
 * @author seven
 *
 */

@Controller(value = "cmsSiteController")
public class CmsSiteController {
	@Autowired
	private CmsSiteService cmsSiteService;
	private static final Logger logger = LoggerFactory
			.getLogger(CmsSiteController.class);
	@RequestMapping(value = "/ftl/cmsSite/queryCmsSite", method = RequestMethod.GET)
	public ModelAndView queryCmsSite() {
		logger.info("queryCmsSite");
		ModelAndView mav = new ModelAndView();
		List<CmsSite> cmsSites = cmsSiteService.selectAllCmsSite();
		if (cmsSites != null&&cmsSites.size()>0) {
			mav.addObject("cmsSites", cmsSites);
		}
		mav.setViewName("/ftl/cmsSite/queryCmsSite");
		return mav;
	}

	/**
	 * 点击页面跳转到addCmsSite上面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ftl/cmsSite/toAddCmsSite", method = RequestMethod.GET)
	public ModelAndView toAddCmsSite() {
		logger.info("toAddCmsSite");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ftl/cmsSite/addCmsSite");
		return mav;
	}

	/**
	 * 添加cmsSite
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/ftl/cmsSite/addCmsSite", method = RequestMethod.POST)
	public ModelAndView addCmsSite(@ModelAttribute("cmsSite") CmsSite cmsSite,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		logger.info("addCmsSite");
		Date createDate=cmsSite.getCreateDate();
		cmsSite.setDelFlag(Short.parseShort("0"));
		cmsSiteService.addCmsSite(cmsSite);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/public/vv");
		return mav;
	}
	/**
	 * 跳转到修改和删除的cmsSite的界面
	 * @return
	 */
	@RequestMapping(value = "/cmsSite/toModifyAndDeleteCmsSite", method = RequestMethod.GET)
	public ModelAndView toModifyAndDeleteCmsSite() {
		logger.info("toModifyAndDeleteCmsSite");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ftl/cmsSite/modifyAndDeleteCmsSite");
		return mav;
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value = "/cmsSite/queryDynamicCmsSite", method = RequestMethod.POST)
	public ModelAndView queryDynamicCmsSite( HttpServletRequest request, HttpServletResponse response ) {
		logger.info("queryDynamicCmsSite");
		String name = request.getParameter("name");
		String theme = request.getParameter("theme");
		String keywords = request.getParameter("keywords");
		Map queryMap = new HashMap();
		if(request.getParameter("datetimepicker1")!=null&&request.getParameter("datetimepicker1")!=""){
			Timestamp datetimepicker1= Timestamp.valueOf(request.getParameter("datetimepicker1"));
			queryMap.put("datetimepicker1", datetimepicker1);
		}
		if(request.getParameter("datetimepicker2")!=null&&request.getParameter("datetimepicker2")!=""){
			Timestamp datetimepicker2= Timestamp.valueOf(request.getParameter("datetimepicker2"));
			queryMap.put("datetimepicker2", datetimepicker2);
		}
		queryMap.put("name", name);
		queryMap.put("theme", theme);
		queryMap.put("keywords", keywords);
		List<CmsSite> cmsSites= cmsSiteService.selectDynamicCmsSite(queryMap);
		ModelAndView mav = new ModelAndView();
		if(cmsSites!=null&&cmsSites.size()>0){
			mav.addObject("cmsSites", cmsSites);
		}
		mav.addObject("queryMap", queryMap);
		mav.setViewName("/ftl/cmsSite/modifyAndDeleteCmsSite");
		return mav;
	}
	
	/**
	 * 通过id查询出cmsSite，返回的json格式显示在页面上
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cmsSite/queryCmsSiteById",method=RequestMethod.POST)
	@ResponseBody
	public String queryCmsSiteById(HttpServletRequest request,HttpServletResponse response){
		String id=request.getParameter("id");
		CmsSite cmsSiteByid= null;
		if(""!=id&&id!=null){
			cmsSiteByid=cmsSiteService.selectByPrimaryKey(Long.parseLong(id));
		}
		Gson gson = new Gson(); 
		String cmsSiteStr=gson.toJson(cmsSiteByid);
		return cmsSiteStr;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cmsSite/modifyCmsSiteById",method=RequestMethod.POST)
	@ResponseBody
	public String modifCmsSiteById(HttpServletRequest request ,HttpServletResponse response){
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		String keywords=request.getParameter("keywords");
		String description=request.getParameter("description");
		//先修改然后查询数据显示在页面上
		CmsSite cmsSite =null;
		Gson gson = new Gson();
		String result="";
		int rerult=0;
		if(""!=id&&id!=null){
			cmsSite=new CmsSite();
			cmsSite.setId(Long.parseLong(id));
			cmsSite.setName(name);
			cmsSite.setDescription(description);
			cmsSite.setTitle(title);
			cmsSite.setKeywords(keywords);
			rerult=cmsSiteService.updateByPrimaryKeySelective(cmsSite);
			//result>0表示修改成功
			if(rerult>0){
				//将修改后的cmsSite显示在前台
				cmsSite=cmsSiteService.selectByPrimaryKey(Long.parseLong(id));
				result = gson.toJson(cmsSite);
			}
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/cmsSite/deleteCmsSiteById")
	public Map deleteCmsSiteById(HttpServletRequest request ,HttpServletResponse response){
		String id= request.getParameter("id");
		CmsSite cmsSite = null;
		int result=0;
		if(""!=id&&id!=null){
			cmsSite = new CmsSite();
			cmsSite.setId(Long.parseLong(id));
			cmsSite.setDelFlag(Short.parseShort("1"));
			result= cmsSiteService.deleteCmsSiteById(cmsSite);
		}
		Map resultMap = new HashMap();
		if(result>0){
			resultMap.put("flag", true);
		}else{
			resultMap.put("flag", false);
		}
		return resultMap;
	}
}
