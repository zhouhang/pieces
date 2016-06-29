package com.jointown.zy.web.controller.boss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.WxAdSearchDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxAd;
import com.jointown.zy.common.service.WxAdService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxAdVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台管理：微信系统-广告管理
 *
 * @author aizhengdong
 * @date 2015年8月20日
 */
@Controller
@RequestMapping(value="/WxBoss/adManager")
public class WxBossAdController extends WxUserBaseController {
	@Autowired
	private WxAdService wxAdService;
	
	/**
	 * 查询广告
	 *
	 * @author aizhengdong
	 * @date 2015年8月20日
	 */
	@RequestMapping(value = "/getAd", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAd(@ModelAttribute WxAdSearchDto wxAdSearchDto,  HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		
		//查询条件
		Page<WxAdVo> page = new Page<WxAdVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxAdSearchDto", wxAdSearchDto);
		page.setParams(params);
		page.setPageNo(Integer.parseInt(pageNo));
		
		//查询结果
		List<WxAdVo> wxAds = wxAdService.findWxAdsByCondition(page);
				
		// 返回结果
		page.setResults(wxAds);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", page);
		return resultMap;
	}
	
	/**
	 * 添加广告
	 *
	 * @author aizhengdong
	 * @date 2015年8月21日
	 */
	@RequestMapping(value = "/addAd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addAd(@ModelAttribute WxAd wxAd) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			Date date = new Date();
			wxAd.setCreateTime(date);
			wxAd.setUpdateTime(date);
			wxAd.setCreater(bossUser.getId());
			wxAd.setUpdater(bossUser.getId());
			
			int ok = wxAdService.addAdByWxBoss(wxAd);
			if (ok > 0) {
				map.put("msg", "添加成功！");
				map.put("ok", true);
			} else {
				map.put("ok", false);
				map.put("msg", "添加失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "添加失败！");
			}
		}
		
		return map;
	}
	
	/**
	 * 修改广告
	 *
	 * @author aizhengdong
	 * @date 2015年8月21日
	 */
	@RequestMapping(value="/updateAd", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateAd(@ModelAttribute WxAd wxAd) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			wxAd.setUpdateTime(new Date());
			wxAd.setUpdater(bossUser.getId());
			
			int ok = wxAdService.updateAdByWxBoss(wxAd);
			if(ok > 0){
				map.put("ok", true);
				map.put("type", "update");
				map.put("msg", "修改成功！");
			}else{
				map.put("ok", false);
				map.put("msg", "修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "修改失败！");
			}
		}
		
		return map;
	}
	
	/**
	 * 删除广告
	 *
	 * @author aizhengdong
	 * @date 2015年8月24日
	 */
	@RequestMapping(value = "/deleteAd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteAd(@ModelAttribute WxAd wxAd) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			wxAd.setUpdateTime(new Date());
			wxAd.setUpdater(bossUser.getId());
			
			int ok = wxAdService.deleteAdByWxBoss(wxAd);
			if (ok > 0) {
				map.put("ok", true);
				map.put("msg", "删除成功！");
			} else {
				map.put("ok", false);
				map.put("msg", "删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "删除失败！");
			}
		}
		
		return map;
	}
	
	/**
	 * 根据id查询广告
	 * 
	 * @author aizhengdong
	 * @date 2015年8月21日
	 */
	@RequestMapping(value="/getAdById", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAdById(@ModelAttribute("adId") Long adId) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			WxAdVo wxAd = wxAdService.findWxAdById(adId);
			if(wxAd != null){
				map.put("ok", true);
				map.put("obj", wxAd);
			}else{
				map.put("ok", false);
				map.put("msg", "获取失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "获取失败！");
			}
		}
		
		return map;
	}
	
}
