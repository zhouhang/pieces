package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.InfoWarehousDto;
import com.jointown.zy.common.enums.InfoManagerColorEnum;
import com.jointown.zy.common.enums.InfoWarehousSourceEnum;
import com.jointown.zy.common.enums.InfoWarehousStateEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.InfoWarehousModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.InfoWarehousService;
import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.InfoWarehousVo;
import com.jointown.zy.common.vo.MessageVo;
/**
 * @ClassName:InfoWarehousController
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:07:42
 * @version V1.0
 * @Description:入仓信息管理
 */
@Controller(value="infoWarehousController")
@RequestMapping("/infoWarehous")
public class InfoWarehousController extends UserBaseController {

	private static final Log log = LogFactory.getLog(InfoWarehousController.class);
	@Autowired
	private InfoWarehousService infoWarehousService;
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	@Autowired
	private WxDemandService wxDemandService;

	/**
	 * 根据条件查询列表，显示列表页面
	 * @param dto 查询条件
	 * @param model 存储页面数据
	 * @return 列表页面
	 */
	@RequestMapping(value = "", method = {RequestMethod.GET,RequestMethod.POST })
	public String listInfoWarehous(@ModelAttribute InfoWarehousDto infoWarehousDto, Model model) {
			Page<InfoWarehousVo> page = new Page<InfoWarehousVo>();
			page.setPageNo(infoWarehousDto.getPageNo());
			// 搜索条件
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("infoWarehousDto", infoWarehousDto);
			page.setParams(params);
			
			//状态
			Map<String,String> statusMap = InfoWarehousStateEnum.toMap();
			//信息来源
			Map<String,String> sourceMap = InfoWarehousSourceEnum.toMap();
			Map<String,String> colorMap = InfoManagerColorEnum.toMap();
			// 资产流水列表
			List<InfoWarehousVo> results = infoWarehousService.getPageList(page);
			page.setResults(results);
			//省市数据
			List<AreaVo> areas = areasInit();
			
			model.addAttribute("page", page);
			model.addAttribute("statusMap", statusMap);
			model.addAttribute("sourceMap", sourceMap);
			model.addAttribute("colorMap", colorMap);
			model.addAttribute("areas", areas);
			
			return "/public/infoWarehousManager";
	}
	
	/**
	 * 省市数据初始化
	 * @param request
	 * @param model
	 * @return
	 */
	public List<AreaVo> areasInit(){
		//查询地区-省市
		AreaVo record = new AreaVo();
		record.setType("1");
		List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
		return areas;
	}
	
	/**
	 * 新增入仓信息
	 * @param infoWarehous
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addInfoWarehous",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInfoWarehous(InfoWarehousModel infoWarehous,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		
			try {
				Subject subject = SecurityUtils.getSubject();
				BossUserVo  bossUser = (BossUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
				if(null == bossUser){
					throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
				}
				
				//验证参数
				String breedName = infoWarehous.getBreedName();
				if(StringUtils.isBlank(breedName)){
					throw new WxErrorException(ErrorRepository.WX_NO_BREED);
				}
				Breed breed = wxDemandService.checkBreedName(breedName);
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
					map.put("msg", "添加失败！");
				}
				log.error("InfoWarehousController addInfoWarehous：" + e);
			}
		return map;
	}
	
	/**
	 * 查看入仓信息
	 * @param infoWarehous
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getInfoWarehous", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getInfoWarehous(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String warehousId = request.getParameter("warehousId");
			
			if(StringUtils.isBlank(warehousId)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			InfoWarehousModel model = new InfoWarehousModel();
			model.setWarehouseId(Integer.valueOf(warehousId));
			InfoWarehousModel vo = infoWarehousService.getInfoWarehous(model);
			if (vo != null) {
				map.put("ok", true);
				map.put("obj", vo);
			} else {
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
			log.error("InfoWarehousController getInfoWarehous error :"+e);
		}
		return map;
	}
	
	/**
	 * 入仓信息审核
	 * @return
	 */
	@RequestMapping(value="/checkInfoWarehous", method = RequestMethod.POST)
	@ResponseBody
	public MessageVo checkInfoWarehous(InfoWarehousModel infoWarehous,HttpServletRequest request){
		BossUserVo  bossUserVo = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		int isOk = 0;
		MessageVo mvo = new MessageVo();
		try {
			InfoWarehousModel vo = infoWarehousService.getInfoWarehous(infoWarehous);
			if(null!=vo){
				infoWarehous.setHandleId(bossUserVo.getId());
				infoWarehous.setHandleTime(new Date());
				infoWarehous.setUpdateTime(new Date());
				infoWarehous.setRemarks(infoWarehous.getRemarks());
				isOk = infoWarehousService.updateInfoWarehous(infoWarehous);
				if(isOk == 1){
					mvo.setOk(true);
					return mvo;
				}else{
					mvo.addError("error02","操作失败，请重试！");
					mvo.setOk(false);
					return mvo;
				}
			}else{
				mvo.addError("error01","数据为空!");
				mvo.setOk(false);
				return mvo;
			}
		} catch (Exception e) {
			log.error("checkInfoWarehous error:"+e);
			mvo.setOk(false);
		}
		return mvo;
	}
	
	/**
	 * 查询地区
	 * @param firstCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getAreasByCode",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAreasByCode(@RequestParam("code") String code) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证参数
			if(code==null || code.isEmpty()){
				throw new Exception("参数错误！");
			}
			//查询地区-市区
			AreaVo record = new AreaVo();
			record.setFirstcode(code);
			List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
			if(areas.size()>0){
				map.put("ok", true);
				map.put("obj", areas);
			}else{
				map.put("ok", false);
			}
		} catch (Exception e) {
			map.put("ok", false);
			log.error("BusiWarehouseApplyController.getAreasByCode："+e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 
	 * @Description: 验证品种名称是否存在
	 * @param breedName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkBreedName",method=RequestMethod.POST)
	public Map<String,Object> checkBreedName(@RequestParam(value="param",defaultValue="",required=true) String breedName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证参数
			if(breedName==null || breedName.isEmpty()){
				throw new Exception("参数错误！");
			}
			Breed breed = wxDemandService.checkBreedName(breedName);
			if(breed != null){
				map.put("ok", true);
				map.put("status", "y");
			}else{
				map.put("ok", false);
				map.put("status", "n");
				map.put("info", "请先在品种管理中添加该品种！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("status", "n");
			map.put("info", "请填写正确的品种名称！");
			log.error("InfoWarehous.checkBreedName："+e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 自动补全,获取品种名称,品种别名
	 * query/suggestions 参数 为返回json数据必备
	 * @param query 搜索参数必备
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTypeName",method = {RequestMethod.GET,RequestMethod.POST })
	public Map<String, Object> getTypeName(@RequestParam(value="query",defaultValue="")String query,HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Breed> breeds = infoWarehousService.getTypeNames(query);
		List<String> data = new ArrayList<String>();
		for (Breed breed : breeds) {
			data.add(breed.getBreedName());
		}
		map.put("query", query);
		map.put("suggestions", data);
		map.put("list", breeds);
		return map;
	}

}