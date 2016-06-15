package com.jointown.zy.web.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dto.BusiListingSearchDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.ListingTimeLimitEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiListingDetailVo;
import com.jointown.zy.common.vo.BusiListingVo;
import com.jointown.zy.common.vo.MessageVo;

/**
 * 挂牌管理Controller
 * @author wangjunhu
 * 2014-12-31
 */
@Controller(value="busiListingController")
public class BusiListingController {
	
	private final static Logger logger = LoggerFactory.getLogger(BusiListingController.class);
	
	@Autowired
	private BusiListingService busiListingService;
	
	@Autowired
	private UcUserService ucUserService ;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;	
	
	/**
	 * 挂牌查询
	 * @param busiListingSearchDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiListingQuery",method=RequestMethod.GET)
	public String getBusiListingQuery(@ModelAttribute BusiListingSearchDto busiListingSearchDto, ModelMap model) throws Exception {
		logger.info("boss:::BusiListingController:::getBusiListingQuery");
		if(StringUtils.isEmpty(busiListingSearchDto.getListingflag())){
			busiListingSearchDto.setListingflag("-1");
		}
		Page<BusiListingVo> page = new Page<BusiListingVo>();
		page.setPageNo(busiListingSearchDto.getPageNo());
		//搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiListingSearchDto", busiListingSearchDto);
		page.setParams(params);
		//挂牌期限列表
		Map<String,String> listingtimelimitMap = ListingTimeLimitEnum.toMap();
		//挂牌状态
		Map<String,String> listingStateMap = new LinkedHashMap<String, String>();
		listingStateMap.put(BusiListingFlagEnum.AUDIT_FAILURE.getCode(), BusiListingFlagEnum.AUDIT_FAILURE.getCodeName());
		listingStateMap.put(BusiListingFlagEnum.LISTING.getCode(), BusiListingFlagEnum.LISTING.getCodeName());
		listingStateMap.put(BusiListingFlagEnum.LISTING_CANCEL.getCode(), BusiListingFlagEnum.LISTING_CANCEL.getCodeName());
		//挂牌列表
		List<BusiListingVo> BusiListings = busiListingService.findListingsByNotExaminels(page);
		page.setResults(BusiListings);
		
		model.put("page", page);
		model.put("listingtimelimitMap", listingtimelimitMap);
		model.addAttribute("listingStateMap", listingStateMap);
		
		return "/public/Listed_query";
	}
	
	/**
	 * @Description: 后台-挂牌查询-查看挂牌详情
	 * @Author: 赵航
	 * @Date: 2015年4月13日
	 * @param listingId 挂牌编号
	 * @return 挂牌详情
	 */
	@RequestMapping("busiListingQuery/listingInfo")
	public @ResponseBody String selectListingQueryInfo(@RequestParam(value="listingId", required=true) String listingId){
		logger.info("BusiListingController.selectListingQueryInfo");
		JsonObject json = new JsonObject();
		try {
			BusiListingDetailVo vo = busiListingService.findSingleListingDetail(listingId);
			if(vo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("挂牌"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(vo));
			}
		} catch (Exception e) {
			logger.error("BusiListingController.selectListingQueryInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	/**
	 * 设为推荐
	 * @param listingid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiListingQuery/okrecommendBusiListing",method=RequestMethod.GET)
	@ResponseBody
	public String okrecommendBusiListing(@ModelAttribute("listingid") String listingid) throws Exception {
		BusiListing busiListing = new BusiListing();
		busiListing.setListingid(listingid);
		busiListing.setIsrecommend((short) 1);
		int updateNum = busiListingService.updateListingRecommend(busiListing);
		MessageVo message = new MessageVo();
		if(updateNum==1){
			message.setOk(true);
			message.setObj(listingid);
		}else{
			message.setOk(false);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * 取消推荐
	 * @param listingid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiListingQuery/norecommendBusiListing",method=RequestMethod.GET)
	@ResponseBody
	public String norecommendBusiListing(@ModelAttribute("listingid") String listingid) throws Exception {
		BusiListing busiListing = new BusiListing();
		busiListing.setListingid(listingid);
		busiListing.setIsrecommend((short) 0);
		int updateNum = busiListingService.updateListingRecommend(busiListing);
		MessageVo message = new MessageVo();
		if(updateNum==1){
			message.setOk(true);
			message.setObj(listingid);
		}else{
			message.setOk(false);
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * 下架挂牌
	 * @param listingid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiListingQuery/updateListingFlagDisabled",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> updateListingFlagDisabled(@RequestParam("listingid") String listingid) throws Exception {
		logger.info("BusiListingController.updateListingFlagDisabled");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证参数
			if(listingid==null || listingid.isEmpty()){
				throw new Exception("参数错误！");
			}
			//验证挂牌信息
			BusiListing busiListing = busiListingService.findByPrimaryKey(listingid);
			if(busiListing==null){
				throw new Exception("挂牌不存在！");
			}
			//验证用户信息
			Long userId= busiListing.getUserid();
			UcUser ucUser = ucUserService.findUcUserById(userId.intValue());
			if(ucUser==null){
				throw new Exception("挂牌用户状态错误！");
			}
			//下架挂牌
			busiListing.setListingid(listingid);
			busiListing.setUserid(null);
			busiListingService.updateListingFlagDisabled(busiListing);
			
			map.put("ok", true);
			
			//通知solr
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listingid);
			//发送邮件
			String userName = ucUser.getUserName();
			threadPoolTaskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CANCER_LISTING, GetEmailContext.getListingOperatorEmail(), 
					GetEmailContext.getListingCancelEmailMsg(userName, listingid, new Date())).setLogErrorPrefiex("下架挂牌[" + listingid + "]发送邮件通知出错！错误信息是："));
		} catch (Exception e) {
			map.put("ok", false);
			logger.error("BusiListingController.updateListingFlagDisabled："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 挂牌审核
	 * @param busiListingSearchDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/busiListingCheck",method=RequestMethod.GET)
	public String getBusiListingCheck(@ModelAttribute BusiListingSearchDto busiListingSearchDto, ModelMap model) throws Exception {
		//待审核
		busiListingSearchDto.setListingflag(BusiListingFlagEnum.AUDIT_WAITING.getCode());
		Page<BusiListingVo> page = new Page<BusiListingVo>();
		page.setPageNo(busiListingSearchDto.getPageNo());
		//搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiListingSearchDto", busiListingSearchDto);
		page.setParams(params);	
		//挂牌期限列表
		Map<String,String> listingtimelimitMap = ListingTimeLimitEnum.toMap();
		//挂牌列表
		List<BusiListingVo> BusiListings = busiListingService.findListingsByNotExaminels(page);
		page.setResults(BusiListings);
		
		model.put("page", page);
		model.put("listingtimelimitMap", listingtimelimitMap);
		
		return "/public/Listed_audit";
	}
	
	/**
	 * 审核通过
	 * @param listingid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiListingCheck/checkSuccessBusiListing",method=RequestMethod.GET)
	@ResponseBody
	public String checkSuccessBusiListing(@ModelAttribute("listingid") String listingid,HttpServletRequest req) {
		MessageVo message = new MessageVo();
		BusiListing busiListing = new BusiListing();
		busiListing.setListingid(listingid);
		busiListing.setListingflag(Short.valueOf(BusiListingFlagEnum.LISTING.getCode()));
		try {
			busiListingService.changeListingFlag(busiListing);
			message.setOk(true);
			message.setObj(listingid);
		} catch (Exception e) {
			message.setOk(false);
			logger.error("挂牌审核失败："+e.getMessage());
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * 审核失败
	 * @param listingid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="busiListingCheck/checkFailureBusiListing",method=RequestMethod.POST)
	@ResponseBody
	public String checkFailureBusiListing(@ModelAttribute("listingid") String listingid, @ModelAttribute("examinecontent") String examinecontent,HttpServletRequest req) {
		MessageVo message = new MessageVo();
		BusiListing busiListing = new BusiListing();
		busiListing.setListingid(listingid);
		busiListing.setExaminecontent(examinecontent);
		busiListing.setListingflag(Short.valueOf(BusiListingFlagEnum.AUDIT_FAILURE.getCode()));
		try {
			busiListingService.changeListingFlag(busiListing);
			message.setOk(true);
			message.setObj(listingid);
		} catch (Exception e) {
			message.setOk(false);
			logger.error("挂牌审核失败："+e.getMessage());
		}
		Gson gson = new Gson();
		String jsonObject = gson.toJson(message);
		return jsonObject;
	}
	
	/**
	 * @Description: 后台-挂牌审核-查看挂牌详情
	 * @Author: 赵航
	 * @Date: 2015年4月13日
	 * @param listingId 挂牌编号
	 * @return 挂牌详情
	 */
	@RequestMapping("busiListingCheck/listingInfo")
	public @ResponseBody String selectListingInfo(@RequestParam(value="listingId", required=true) String listingId){
		logger.info("BusiListingController.selectListingInfo");
		JsonObject json = new JsonObject();
		try {
			BusiListingDetailVo vo = busiListingService.findSingleListingDetail(listingId);
			if(vo == null){
				json.addProperty("state", "failure");
				json.addProperty("result", ErrorRepository.NOT_EXIST.getMessage("挂牌"));
			} else {
				json.addProperty("state", "success");
				json.addProperty("result", GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(vo));
			}
		} catch (Exception e) {
			logger.error("BusiListingController.selectListingInfo, error is " + e.getMessage());
			json.addProperty("state", "failure");
			json.addProperty("result", ErrorRepository.UNKNOW_ERROR.getMessage());
		}
		return json.toString();
	}
	
	
	
	
	/**
	 * 导出Excel
	 * biran
	 * 2015-08-27
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "busiListingQuery/export")
	public void exportPayFlow(@ModelAttribute BusiListingSearchDto busiListingSearchDto, HttpServletResponse response) throws Exception {
		if(StringUtils.isEmpty(busiListingSearchDto.getListingflag())){
			busiListingSearchDto.setListingflag("-1");
		}
		Page<BusiListingVo> page = new Page<BusiListingVo>();
		page.setPageNo(1);
		//搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiListingSearchDto", busiListingSearchDto);
		page.setParams(params);
		page.setPageSize(Integer.MAX_VALUE-1);
		//挂牌列表
		List<BusiListingVo> BusiListings = busiListingService.findListingsByNotExaminels(page);
		if(CollectionUtils.isNotEmpty(BusiListings)){
			this.exportExcel(response,BusiListings);
		}else{
			this.exportExcel(response, new ArrayList<BusiListingVo>());
		}
		return ;
	}
	
	
	public HttpServletResponse exportExcel(HttpServletResponse response,List<BusiListingVo> results) {
		try {
			
			ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("LISTING"));
			
			OutputStream os = response.getOutputStream();
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			
			WritableCellFormat wcfTitle = ExcelUtil.getTitleFormat();
			WritableCellFormat wcfContentCenter = ExcelUtil.getContentFormat();
			WritableCellFormat wcfContentRightTwo = ExcelUtil.getNumberFormat();
			
			WritableSheet wsheet = wbook.createSheet("挂牌信息", 0); // sheet名称
			//设置单元格宽度
			wsheet.setColumnView(0, 25);
			wsheet.setColumnView(1, 25);
			wsheet.setColumnView(2, 40);
			wsheet.setColumnView(3, 40);
			wsheet.setColumnView(4, 25);
			wsheet.setColumnView(5, 25);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 20);
			wsheet.setColumnView(8, 20);
			wsheet.setColumnView(9, 30);
			wsheet.setColumnView(10, 30);
			wsheet.setColumnView(11, 30);
			wsheet.setColumnView(12, 20);
			
			// 设置单元格标题内容
			wsheet.addCell(new Label(0, 0, "挂牌编号", wcfTitle));	
			wsheet.addCell(new Label(1, 0, "仓单编号", wcfTitle));
			wsheet.addCell(new Label(2, 0, "标题", wcfTitle));
			wsheet.addCell(new Label(3, 0, "品种", wcfTitle));
			wsheet.addCell(new Label(4, 0, "挂牌单价", wcfTitle));
			wsheet.addCell(new Label(5, 0, "起订数量", wcfTitle));
			wsheet.addCell(new Label(6, 0, "挂牌期限", wcfTitle));
			wsheet.addCell(new Label(7, 0, "挂牌用户", wcfTitle));
			wsheet.addCell(new Label(8, 0, "提供发票单价", wcfTitle));
			wsheet.addCell(new Label(9, 0, "挂牌状态", wcfTitle));
			wsheet.addCell(new Label(10, 0, "挂牌时间", wcfTitle));
			wsheet.addCell(new Label(11, 0, "到期时间", wcfTitle));
			wsheet.addCell(new Label(12, 0, "业务员", wcfTitle));
		
			int row = 1;//行
			int cell;//列
			// 生成报表内容
			if(results.size()<0)
				return null;
			
			for(BusiListingVo bv : results){
				cell = 0;
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bv.getListingid()) ? "" :bv.getListingid() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bv.getWlid()) ? "" :bv.getWlid() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bv.getTitle()) ? "" :bv.getTitle() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bv.getBreedname()) ? "" :bv.getBreedname() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, bv.getLowunitprice()+"元/"+bv.getDictvalue(), wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, bv.getMinsales()+bv.getDictvalue(), wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, bv.getListingtimelimit()+"天", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bv.getUsername()) ? "" :bv.getUsername() + "", wcfContentCenter));
				if(bv.getHasbill()==1){//有发票
					wsheet.addCell(new Label(cell++, row, bv.getBillprice()+"元/"+bv.getDictvalue(), wcfContentCenter));
				}else{
					wsheet.addCell(new Label(cell++, row,  "", wcfContentCenter));
				}
				wsheet.addCell(new Label(cell++, row, BusiListingFlagEnum.getCodeNameByCode(String.valueOf(bv.getListingflag())) + "", wcfContentCenter));
				
				wsheet.addCell(new Label(cell++, row, bv.getCreatetime()==null ? "" : (StringUtils.isBlank(bv.getCreatetime().toString()) ? "" :
					TimeUtil.getYMDHMS(bv.getCreatetime())) + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, bv.getExpiretime()==null ? "" : (StringUtils.isBlank(bv.getExpiretime().toString()) ? "" :
					TimeUtil.getYMDHMS(bv.getExpiretime())) + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bv.getSalesman()) ? "" :bv.getSalesman() + "", wcfContentCenter));
				row++;
			}
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception ex) {
			logger.error("exportExcel:", ex);
		}
		return response;
	}
	
}
