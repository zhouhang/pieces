package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.QueryCollectDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiGoodsDetailsService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.vo.UcUserVo;


/**
 * 
 * 描述： 收藏列表操作<br/>
 * 
 * 日期： 2015年1月26日<br/>
 * 
 * 作者： 范玉娜<br/>
 *
 * 版本： V1.0<br/>
 */
@Controller
@RequestMapping(value="/collect")
public class CollectController {
	@Autowired
	private SortListService sortListService;
	@Autowired BusiGoodsDetailsService busiGoodsDetailsService;
	@Autowired 
	private IndexService indexService;
	
	private static final Logger log = LoggerFactory.getLogger(CollectController.class);
	
	/**
	 * @description 用户点击后显示该用户的收藏列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryMyCollect")
	public String queryCollecting(@ModelAttribute QueryCollectDto queryCollectDto,
			ModelMap model ,HttpServletResponse response,HttpServletRequest request) throws Exception {
		log.info("CollectController.myCollect controller");
		String tunnage = indexService.getWarrantsTunnage();
		model.put("tunnage", tunnage);
		
		try{
			Subject subject = SecurityUtils.getSubject();
			UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			//获取前台传递过来的参数
			//挂牌时间
			long userid=user.getUserId();
			/*String datetimepicker1 = request.getParameter("datetimepicker1");
			String datetimepicker2 = request.getParameter("datetimepicker2");
			String lowPrice = request.getParameter("lowPrice");
			String highPrice = request.getParameter("highPrice");
			String origin = request.getParameter("origin");
			String place = request.getParameter("place");
			String pageNo = request.getParameter("pageNo");*/
			
			Map <String, Object> params = new HashMap<String, Object>();
			params.put("datetimepicker1", queryCollectDto.getDatetimepicker1());
			params.put("datetimepicker2", queryCollectDto.getDatetimepicker2());
			params.put("lowPrice", queryCollectDto.getLowPrice());
			params.put("highPrice", queryCollectDto.getHighPrice());
			params.put("origin", queryCollectDto.getOrigin());
			params.put("breed", queryCollectDto.getBreed());
			String order = queryCollectDto.getOrder();
			if(!StringUtils.isEmpty(order)){
				params.put("order", order);
			}else{
				params.put("order", " EXAMINETIME desc ");
			}
			params.put("userid", userid);
			
			Page <Map<String,Object>> page = new Page <Map<String,Object>>();
			String  pageNo=request.getParameter("pageNo");
			if(pageNo!=null&&!"".equals(pageNo)){
				page.setPageNo(Integer.parseInt(pageNo));
			}else{
				page.setPageNo(1);
			}
			page.setPageSize(10);
			page.setParams(params);
			List busiCollectionVos=busiGoodsDetailsService.selectCollentionsByUserId(page);
			//List  breeds= breedService.selectBreedBy(page);
			if (busiCollectionVos!=null&&busiCollectionVos.size()>0) {
				page.setResults(busiCollectionVos);
			}
			model.put("page", page);
		}catch(NullPointerException e){
			log.error("CollectController.myCollect NullPointerException");
		}
		//header全部药材分类
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		model.put("sortList", sortList);
		/***add remark by Mr.song 2015.5.12               首页关键字**** start****/
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		model.put("categorylist", categorylist);
		/**********************匹配菜单*******************/
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		model.addAttribute("userinfo", userinfo);
		/**********************匹配菜单*******************/
		return "business/collect";
	}
	
	/**
	 * add by Mr.song 2015.1.27
	 * 我的收藏列表-我的品种收藏
	 * @return Map<String,String> 
	 * 返回json格式
	 */
	@RequestMapping(value="/mybreed")
	@ResponseBody
	public List<HashMap<String,String>> mybreed(){
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		if(null ==user) {
			log.error("CollectController.mybreed user is null!");
		}
		Long userid=(user!=null)?user.getUserId():0L;
		List<HashMap<String, String>> list = busiGoodsDetailsService.selectCollectionBread(userid);
		return list;
	}
	
	@RequestMapping(value="/selectHotBusiListing")
	@ResponseBody
	public List<HashMap<String,Object>> selectHotBusiListing(){
		return busiGoodsDetailsService.selectHotBusiListing();
	}
	
	/**
	 *取消我的收藏
	 */
	@RequestMapping(value="/cancelMyCollect")
	public String cancelMyCollect(
			ModelMap model ,HttpServletResponse response,HttpServletRequest request,String cindex) throws Exception {
		log.info("CollectController.cancelMyCollect controller");
		if(StringUtils.isNotBlank(cindex)){
			int num = busiGoodsDetailsService.cancelCollect(Long.valueOf(cindex));
			if(num>0){
				return "redirect:queryMyCollect";
			}
		}
		return null;
	}
	
}