package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiWhlistSearchDto;
import com.jointown.zy.common.enums.BusiWhlistStateEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
*   
* 项目名称：jzt-user-center  
* 类名称：BusiWhlistController  
* 类描述：  仓单管理
* 创建人：Mr.songwei  
* 创建时间：2014-12-24 下午4:17:36  
* 修改人：  
* 修改时间：2014-12-24 下午4:17:36  
* 修改备注：  
* @version v1.0   
*
 */
@Controller
@RequestMapping(value="/whlist")
public class BusiWhlistController {
	private static final Logger log = LoggerFactory.getLogger(BusiWhlistController.class);
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	//我的仓单列表查询
	@RequestMapping(value="/manager",method=RequestMethod.GET)
	public String myWhListManager(HttpServletRequest request,@RequestParam(value="pageNo", required = false) String pageNo, @ModelAttribute BusiWhlistSearchDto busiWhlistSearchDto, ModelMap model) throws Exception {
		log.info("BusiWhlistController.myWhListManager controller");
		Page<BusiWhlistVo> page = new Page<BusiWhlistVo>();
		if(pageNo!=null && !"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(ConfigConstant.USER_CENTER_PAGE_SIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiWhlistSearchDto", busiWhlistSearchDto);
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		params.put("userId", (user!=null && user.getUserId()!=0L)?user.getUserId():0L);
		page.setParams(params);
		
		List<BusiWhlistVo> busiWhlists = busiWhlistService.findBusiWhlistsByCondition(page);
		page.setResults(busiWhlists);
		List<BusiWareHouseVo> warehouseslist =busiWareHouseService.findBusiWareHousesByTree();
		model.put("warehouseslist", warehouseslist);
		model.put("page", page);
		/**********************匹配菜单*******************/
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		model.addAttribute("userinfo", userinfo);
		/**********************匹配菜单*******************/
		return "busiWhlistManager";
	}
	
	//仓单详情
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String whListDetail(@RequestParam(value="wlid", required = false) String wlid,ModelMap model) throws Exception {
		log.info("BusiWhlistController.whListDetail controller");
		try{
			Subject subject = SecurityUtils.getSubject();
			UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			BusiWhlistVo vo = busiWhlistService.findBusiWhlistById(wlid,user.getUserId());
			if(vo == null){
				model.addAttribute("error", "仓单[" + wlid + "]不存在！");
				return "error";
			}
			
			model.addAttribute("i320", sftpConfigInfo.getSftpXBigWidth());
			model.addAttribute("i640", sftpConfigInfo.getSftpXXBigWidth());
			model.addAttribute("whlistvo", vo);
			model.addAttribute("whlistStateMap", BusiWhlistStateEnum.toMap());
			/**********************匹配菜单*******************/
			//header登录名称使用
			UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			model.addAttribute("userinfo", userinfo);
			/**********************匹配菜单*******************/
			return "busiWhlistDetail";
		}catch (Exception e) {
			log.error("BusiListingController.addListing error is " + e.getMessage());
			return "error";
		}	
	}
}
