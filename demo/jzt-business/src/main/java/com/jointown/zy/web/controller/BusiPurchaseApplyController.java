package com.jointown.zy.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.dto.BusiPurchaseApplyDto;
import com.jointown.zy.common.enums.InfoWarehousSourceEnum;
import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.service.BusiPurchaseApplyService;
import com.jointown.zy.common.service.IndexService;
import com.jointown.zy.common.service.SortListService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;

/**
 * 
 * @ClassName: BusiPurchaseApplyController
 * @Description: 采购申请
 * @Author: wangjunhu
 * @Date: 2015年5月20日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/busiPurchaseApply")
public class BusiPurchaseApplyController {
	
	private final static Logger logger = LoggerFactory.getLogger(BusiWarehouseApplyController.class);
	
	@Autowired 
	private IndexService indexService;
	
	@Autowired
	private SortListService sortListService;
	
	@Autowired
	private BusiPurchaseApplyService busiPurchaseApplyService;
	
	@Autowired
	public ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	/**
	 * 
	 * @Description: 采购申请页
	 * @Author: wangjunhu
	 * @Date: 2015年6月5日
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/iWillProcurement",method=RequestMethod.GET)
	public String iWillProcurement(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception {
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		model.put("sortList", sortList);
		model.put("tunnage", tunnage);
		model.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		model.put("url", url);
		
		return "/homepage/iWillProcurement";
	}
	
	/**
	 * 
	 * @Description: 采购申请成功
	 * @Author: wangjunhu
	 * @Date: 2015年6月5日
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addBusiPurchaseApplySuccess",method=RequestMethod.POST)
	public String addBusiPurchaseApplySuccess(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception {
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		model.put("sortList", sortList);
		model.put("tunnage", tunnage);
		model.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		model.put("url", url);
		
		return "/homepage/submit-procurement";
	}
	
	/**
	 * 
	 * @Description: 采购申请失败
	 * @Author: wangjunhu
	 * @Date: 2015年6月5日
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addBusiPurchaseApplyFailure",method=RequestMethod.POST)
	public String addBusiPurchaseApplyFailure(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception {
		List <Map<Object,Object>> sortList=sortListService.queryAllMedicinal();
		String tunnage = indexService.getWarrantsTunnage();
		List<Map<Object, Object>> categorylist = sortListService.queryMedicinalByClassName("搜索关键字");
		model.put("sortList", sortList);
		model.put("tunnage", tunnage);
		model.put("categorylist", categorylist);
		String url=WebUtils.getPathWithinApplication((HttpServletRequest)request);
		model.put("url", url);
		
		return "error";
	}
	
	/**
	 * 
	 * @Description: 新增采购申请
	 * @Author: wangjunhu
	 * @Date: 2015年6月5日
	 * @param busiPurchaseApplyDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addBusiPurchaseApply",method=RequestMethod.POST)
	public String addBusiPurchaseApply(@ModelAttribute BusiPurchaseApplyDto busiPurchaseApplyDto,ModelMap model) throws Exception {
		logger.info("BusiPurchaseApplyController.addBusiPurchaseApply");
		try {
			List<BusiPurchaseApply> busiPurchaseApplies = busiPurchaseApplyDto.getBusiPurchaseApplies();
			//验证参数
			if(busiPurchaseApplies==null || busiPurchaseApplies.size()==0){
				throw new Exception("参数错误！");
			}
			String applyName = busiPurchaseApplyDto.getApplyName();
			Long applyMobile = busiPurchaseApplyDto.getApplyMobile();
			for (Iterator<BusiPurchaseApply> iterator = busiPurchaseApplies.iterator(); iterator.hasNext();) {
				BusiPurchaseApply busiPurchaseApply = (BusiPurchaseApply) iterator.next();
				String breedName = busiPurchaseApply.getBreedName();
				if(breedName==null || breedName.isEmpty()){
					iterator.remove();
				}else{
					busiPurchaseApply.setApplyName(applyName);
					busiPurchaseApply.setApplyMobile(applyMobile);
					
					String applyResourceZYC = InfoWarehousSourceEnum.ZHENYC.getStatus();
					busiPurchaseApply.setApplyResource(applyResourceZYC);
				}
			}
			
			busiPurchaseApplyService.addPurchaseApply(busiPurchaseApplies);
			
			model.put("busiPurchaseApplyVo", busiPurchaseApplyDto);
			
			//发送邮件通知运营人员
			threadPoolTaskExecutor.execute(
					new EmailTaskSend(GetEmailContext.EMAIL_APPLY_PURCHASE, GetEmailContext.getXuQiuOperatorEmail(), 
					GetEmailContext.getApplyPurchaseMsg(busiPurchaseApplyDto))
					.setLogErrorPrefiex("采购申请[" + applyName + "]发送邮件通知出错！错误信息是："));
		} catch (Exception e) {
			logger.error("BusiPurchaseApplyController.addBusiPurchaseApply："+e.getMessage());
			
			return "forward:/busiPurchaseApply/addBusiPurchaseApplyFailure";
		}
		
		return "forward:/busiPurchaseApply/addBusiPurchaseApplySuccess";
	}
}
