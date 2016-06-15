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

import com.jointown.zy.common.dto.WxActivitySearchDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.service.WxActivityService;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxActivityVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台管理：微信系统-活动管理
 *
 * @author aizhengdong
 * @date 2015年8月17日
 */
@Controller
@RequestMapping(value="/WxBoss/activityManager")
public class WxBossActivityController extends WxUserBaseController {
	@Autowired
	private WxActivityService wxActivityService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	/**
	 * 查询活动
	 *
	 * @author aizhengdong
	 * @date 2015年8月17日
	 */
	@RequestMapping(value = "/getActivity", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getActivity(@ModelAttribute WxActivitySearchDto wxActivitySearchDto, HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		
		//查询条件
		Page<WxActivityVo> page = new Page<WxActivityVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxActivitySearchDto", wxActivitySearchDto);
		page.setParams(params);
		page.setPageNo(Integer.parseInt(pageNo));
		
		//查询结果
		List<WxActivityVo> wxActivitys = wxActivityService.findWxActivitysByCondition(page);
				
		// 返回结果
		page.setResults(wxActivitys);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", page);
		return resultMap;
	}
	
	/**
	 * 添加活动
	 * 
	 * @author aizhengdong
	 * @date 2015年8月18日
	 */
	@RequestMapping(value="/addActivity", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addActivity(@ModelAttribute WxActivity wxActivity) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			Date date = new Date();
			wxActivity.setCreateTime(date);
			wxActivity.setUpdateTime(date);
			wxActivity.setCreater(bossUser.getId());
			wxActivity.setUpdater(bossUser.getId());
			
			int ok = wxActivityService.addActivityByWxBoss(wxActivity);
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
	 * 修改活动
	 *
	 * @author aizhengdong
	 * @date 2015年8月19日
	 */
	@RequestMapping(value="/updateActivity", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateActivity(@ModelAttribute WxActivity wxActivity) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			wxActivity.setUpdateTime(new Date());
			wxActivity.setUpdater(bossUser.getId());
			
			int ok = wxActivityService.updateActivityByWxBoss(wxActivity);
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
	 * 删除活动
	 *
	 * @author aizhengdong
	 * @date 2015年8月19日
	 */
	@RequestMapping(value="/deleteActivity", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteActivity(@ModelAttribute WxActivity wxActivity) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			wxActivity.setUpdateTime(new Date());
			wxActivity.setUpdater(bossUser.getId());
			
			int ok = wxActivityService.deleteActivityByWxBoss(wxActivity);
			if(ok > 0){
				map.put("ok", true);
				map.put("msg", "删除成功！");
			}else{
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
	 * 根据id查询活动
	 * 
	 * @author aizhengdong
	 * @date 2015年8月18日
	 */
	@RequestMapping(value="/getActivityById", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getActivityById(@ModelAttribute("activityId") Long activityId) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			WxActivityVo wxActivity = wxActivityService.findWxActivityById(activityId);
			if(wxActivity != null){
				map.put("ok", true);
				map.put("obj", wxActivity);
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
	
	/**
	 * 验证活动排序号
	 * 
	 * @author aizhengdong
	 * @date 2015年8月18日
	 */
	@RequestMapping(value="/findByCondition",  method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> findByCondition(@ModelAttribute WxActivity wxActivity) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int ok = wxActivityService.findByCondition(wxActivity);
			if(ok > 0){
				map.put("ok", true);
			}else{
				map.put("ok", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
		}
		
		return map;
	}
	
	
}
