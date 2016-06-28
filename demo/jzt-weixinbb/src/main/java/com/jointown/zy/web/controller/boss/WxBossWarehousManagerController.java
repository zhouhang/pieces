package com.jointown.zy.web.controller.boss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.InfoWarehousDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.InfoWarehousModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.InfoWarehousService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.InfoWarehousVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台入仓信息管理
 *
 * @author aizhengdong
 *
 * @date 2015年7月22日
 */
@Controller
@RequestMapping(value = "/WxBoss/warehousManager")
public class WxBossWarehousManagerController extends WxUserBaseController {

	@Autowired
	private InfoWarehousService infoWarehousService;

	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;

	@Autowired
	private BreedService breedService;
	
	/**
	 * 查询入仓信息
	 *
	 * @author aizhengdong
	 * @date 2015年7月22日
	 */
	@RequestMapping(value = "/getWarehous", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWarehous(HttpServletRequest request, @ModelAttribute InfoWarehousDto infoWarehousDto) {
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		
		// 搜索条件
		Page<InfoWarehousVo> page = new Page<InfoWarehousVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("infoWarehousDto", infoWarehousDto);
		page.setParams(params);
		page.setPageNo(Integer.parseInt(pageNo));

		// 查询结果
		List<InfoWarehousVo> results = infoWarehousService.getPageList(page);

		// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("warehousList", results);
		return resultMap;
	}

	/**
	 * 入仓信息详情
	 *
	 * @author aizhengdong
	 * @date 2015年7月29日
	 */
	@RequestMapping(value = "warehousDetail", method = RequestMethod.GET)
	public String getWarehousDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			// 验证登录
			BossUserVo bossUser = getWxBossUserInfo();
			if (bossUser == null) {
				return "redirect:/WxBoss/wxSystem";
			}
						
			String warehousId = request.getParameter("warehousId");
			
			// 验证参数
			if(StringUtils.isBlank(warehousId)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			InfoWarehousModel infoWarehous = new InfoWarehousModel();
			infoWarehous.setWarehouseId(Integer.valueOf(warehousId));
			InfoWarehousModel infoWarehousVo = infoWarehousService.getInfoWarehous(infoWarehous);
			model.put("warehous", infoWarehousVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/boss/a-instoreDetail";
	}
	
	/**
	 * 审核入仓信息
	 * 
	 * @author aizhengdong
	 * @date 2015年7月29日
	 */
	@RequestMapping(value = "/checkWarehous", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkWarehousValid(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if (bossUser == null) {
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
								
			String warehouseId = request.getParameter("warehouseId");
			String status = request.getParameter("status");
			String remarks = request.getParameter("remarks");
			
			// 验证参数
			if (StringUtils.isBlank(warehouseId) || StringUtils.isBlank(status)) {
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			InfoWarehousModel infoWarehous = new InfoWarehousModel();
			infoWarehous.setWarehouseId(Integer.parseInt(warehouseId));
			
			InfoWarehousModel vo = infoWarehousService.getInfoWarehous(infoWarehous);
			if (null != vo) {
				infoWarehous.setHandleId(bossUser.getId());
				infoWarehous.setHandleTime(new Date());
				infoWarehous.setUpdateTime(new Date());
				infoWarehous.setRemarks(remarks);
				infoWarehous.setStatus(Integer.parseInt(status));
				int ok = infoWarehousService.updateInfoWarehous(infoWarehous);
				if (ok > 0) {
					map.put("msg", "处理成功！");
					map.put("ok", true);
				} else {
					map.put("ok", false);
					map.put("msg", "处理失败！");
				}
			} else {
				map.put("ok", false);
				map.put("msg", "数据为空！");
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
	 * 新增入仓信息
	 * 
	 * @author aizhengdong
	 * @date 2015年8月7日
	 */
	@RequestMapping(value="/addWarehous", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInfoWarehous(InfoWarehousModel infoWarehous){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 验证权限
			BossUserVo  bossUser = getWxBossUserInfo();
			if(null == bossUser){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			 			
			// 验证参数
			String breedName = infoWarehous.getBreedName();
			if(StringUtils.isBlank(breedName)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			// 验证品种名称
			Breed breed = breedService.findBreedByBreedName(breedName);
			if(breed == null){
				throw new WxErrorException(ErrorRepository.WX_NO_BREED);
			}
			
			infoWarehous.setHandleId(bossUser.getId());
			int ok = infoWarehousService.addInfoWarehous(infoWarehous);
			if (ok > 0) {
				map.put("msg", "添加成功！");
				map.put("ok", true);
			} else {
				map.put("ok", false);
				map.put("msg", "添加失败！");
			}
		} catch (Exception e) {
			String msg = e.getMessage();
			map.put("ok", false);
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "添加失败！" + msg);
			}
		}
		
		return map;
	}
}
