package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.BusiWarehouseApplyDto;
import com.jointown.zy.common.enums.InfoWarehousSourceEnum;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiWarehouseApply;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.InfoWarehousService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 
 * @ClassName: WxWarehouseApplyController
 * @Description: 入仓申请
 * @Author: wangjunhu
 * @Date: 2015年7月15日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/wxWarehouseApply")
public class WxWarehouseApplyController extends WxUserBaseController {

	private final static Logger logger = LoggerFactory
			.getLogger(WxWarehouseApplyController.class);

	@Autowired
	private SortListService sortListService;

	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;

	@Autowired
	public ThreadPoolTaskExecutor threadPoolTaskExecutor;

	@Autowired
	public InfoWarehousService infoWarehousService;

	/**
	 * 
	 * @Description: 入仓申请页
	 * @Author: wangjunhu
	 * @Date: 2015年7月15日
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/iWillWarehousing", method = RequestMethod.GET)
	public String iWillWarehousing(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		// 查询地区-省市
		AreaVo record = new AreaVo();
		record.setType("1");// 状态：申请中
		List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
		model.put("areas", areas);
		UcUserVo user = getUserInfo(request);
		if (user == null) {
			user = new UcUserVo();
		}
		model.put("user", user);
		return "/in-store";
	}

	/**
	 * 
	 * @Description: 新增入仓申请
	 * @Author: wangjunhu
	 * @Date: 2015年7月15日
	 * @param busiWarehouseApplyDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addBusiWarehouseApply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addBusiWarehouseApply(
			@ModelAttribute BusiWarehouseApplyDto busiWarehouseApplyDto)
			throws Exception {
		logger.info("WxWarehouseApplyController.addBusiWarehouseApply");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<BusiWarehouseApply> busiWarehouseApplies = busiWarehouseApplyDto
					.getBusiWarehouseApplies();
			// 验证参数
			if (busiWarehouseApplies == null
					|| busiWarehouseApplies.size() == 0) {
				throw new Exception("参数错误！");
			}
			String applyName = busiWarehouseApplyDto.getApplyName();
			Long applyMobile = busiWarehouseApplyDto.getApplyMobile();
			String warehouseAddress = busiWarehouseApplyDto
					.getWarehouseAddress();
			String expectedService = busiWarehouseApplyDto.getExpectedService();
			for (Iterator<BusiWarehouseApply> iterator = busiWarehouseApplies
					.iterator(); iterator.hasNext();) {
				BusiWarehouseApply busiWarehouseApply = (BusiWarehouseApply) iterator
						.next();
				String breedName = busiWarehouseApply.getBreedName();
				if (breedName == null || breedName.isEmpty()) {
					iterator.remove();
				} else {
					// **********************wanghao add by 2015.06.17 Start
					// **********************
					// 查询品种信息 品种不存在 则品种id为0
					// busiWarehouseApply = getBreedId(busiWarehouseApply);
					// **********************wanghao add by 2015.06.17 End
					// **********************
					busiWarehouseApply.setBreedId(0);
					busiWarehouseApply.setApplyName(applyName);
					busiWarehouseApply.setApplyMobile(applyMobile);
					busiWarehouseApply.setWarehouseAddress(warehouseAddress);
					busiWarehouseApply.setExpectedService(expectedService);

					busiWarehouseApply
							.setApplyResource(InfoWarehousSourceEnum.WEIXIN
									.getStatusDesc());
				}
			}

			warehouseApplyService.addWarehouseApply(busiWarehouseApplies);

			map.put("ok", true);
			map.put("msg", "小珍已收到您的入仓申请，会在1个工作日内联系您，请保持电话畅通！");

			// 发送邮件通知运营人员
			threadPoolTaskExecutor.execute(new EmailTaskSend(
					GetEmailContext.EMAIL_APPLY_WAREHOUSE, GetEmailContext
							.getXuQiuOperatorEmail(), GetEmailContext
							.getApplyWarehouseMsg(busiWarehouseApplyDto))
					.setLogErrorPrefiex("入仓申请[" + applyName
							+ "]发送邮件通知出错！错误信息是："));
		} catch (Exception e) {
			logger.error("WxWarehouseApplyController.addBusiWarehouseApply："
					+ e.getMessage());

			map.put("ok", false);
			map.put("msg", "申请失败！");
		}

		return map;
	}

	/**
	 * 查询地区
	 * 
	 * @param firstCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getAreasByCode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAreasByCode(@RequestParam("code") String code)
			throws Exception {
		logger.info("WxWarehouseApplyController.getAreasByCode");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 验证参数
			if (code == null || code.isEmpty()) {
				throw new Exception("参数错误！");
			}
			// 查询地区-市区
			AreaVo record = new AreaVo();
			record.setFirstcode(code);
			List<AreaVo> areas = warehouseApplyService
					.findAreasByCondition(record);
			if (areas.size() > 0) {
				map.put("ok", true);
				map.put("obj", areas);
			} else {
				map.put("ok", false);
			}
		} catch (Exception e) {
			map.put("ok", false);
			logger.error("WxWarehouseApplyController.getAreasByCode："
					+ e.getMessage());
		}
		return map;
	}

	/**
	 * 自动补全,获取品种名称,品种别名 query/suggestions 参数 为返回json数据必备
	 * 
	 * @param query
	 *            搜索参数必备
	 * @return
	 */
	@RequestMapping(value = "/checkBreedName", produces = "text/html;charset=UTF-8", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String checkBreedName(
			@RequestParam(value = "query", defaultValue = "") String query) {
		//Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Breed> list = infoWarehousService.getTypeNames(query);
		String data = CollectionUtils.isEmpty(list) ? "false" : "true";
		return data;
	}

	/**
	 * 获取品种id 目前暂不使用 保留
	 * 
	 * @author Wanghao
	 * @param busiWarehouseApply
	 * @return
	 */
	public BusiWarehouseApply getBreedId(BusiWarehouseApply busiWarehouseApply) {
		Breed breed = warehouseApplyService.findBreedName(busiWarehouseApply
				.getBreedName());
		if (null == breed) {
			busiWarehouseApply.setBreedId(0);
		} else {
			int breedId = Integer.valueOf(breed.getBreedId().toString());
			busiWarehouseApply.setBreedId(breedId);
		}
		return busiWarehouseApply;
	}
}
