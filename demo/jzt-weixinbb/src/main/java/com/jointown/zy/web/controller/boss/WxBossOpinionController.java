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

import com.jointown.zy.common.dto.WxOpinionSearchDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxOpinion;
import com.jointown.zy.common.service.WxOpinionService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxOpinionVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台管理：微信系统-意见管理
 *
 * @author aizhengdong
 * @date 2015年8月24日
 */
@Controller
@RequestMapping(value="/WxBoss/opinionManager")
public class WxBossOpinionController extends WxUserBaseController {
	@Autowired
	private WxOpinionService wxOpinionService;
	
	/**
	 * 查询广告
	 *
	 * @author aizhengdong
	 * @date 2015年8月24日
	 */
	@RequestMapping(value = "/getOpinion", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOpinion(@ModelAttribute WxOpinionSearchDto wxOpinionSearchDto, HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		
		//查询条件
		Page<WxOpinionVo> page = new Page<WxOpinionVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxOpinionSearchDto", wxOpinionSearchDto);
		page.setParams(params);
		page.setPageNo(Integer.parseInt(pageNo));
		
		//查询结果
		List<WxOpinionVo> wxOpinions = wxOpinionService.findWxOpinionByCondition(page);
				
		// 返回结果
		page.setResults(wxOpinions);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", page);
		return resultMap;
	}
	
	/**
	 * 处理意见
	 *
	 * @author aizhengdong
	 * @date 2015年8月26日
	 */
	@RequestMapping(value = "/checkOpinion", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkOpinion(@ModelAttribute WxOpinion wxOpinion){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			wxOpinion.setStatus((short)1);
			wxOpinion.setUpdateTime(new Date());
			wxOpinion.setUpdater(bossUser.getId());
			
			int ok = wxOpinionService.updateWxOpinion(wxOpinion);
			if (ok > 0) {
				map.put("msg", "处理成功！");
				map.put("ok", true);
			} else {
				map.put("ok", false);
				map.put("msg", "处理失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if (e instanceof WxErrorException) {
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			} else {
				map.put("msg", "处理失败！");
			}
		}
		
		return map;
	}
	
	/**
	 * 删除意见
	 *
	 * @author aizhengdong
	 * @date 2015年8月27日
	 */
	@RequestMapping(value = "/deleteOpinion", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOpinion(@ModelAttribute("opId") Long opId){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			int ok = wxOpinionService.deleteWxOpinion(opId);
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
	 * 根据id查询意见
	 *
	 * @author aizhengdong
	 * @date 2015年8月26日
	 */
	@RequestMapping(value = "/getOpinionById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getOpinionById(@ModelAttribute("opId") Long opId){
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			WxOpinion wxOpinion = wxOpinionService.findWxOpinionById(opId);
			if(wxOpinion != null){
				map.put("ok", true);
				map.put("obj", wxOpinion);
			}else{
				map.put("ok", false);
				map.put("msg", "获取失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
