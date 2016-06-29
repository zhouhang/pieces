package com.jointown.zy.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.dto.CustomerAccountDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.CustomerAccountService;
import com.jointown.zy.common.service.FinanceReportService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.CustomerAccountVo;

/**
 * @ClassName: CustomerAccountController
 * @Description: 客户账务报表controller
 * @Author: ldp
 * @Date: 2015年10月9日
 * @Version: 1.0
 */
@RequestMapping(value="/customerAccount")
@Controller
public class CustomerAccountController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerAccountController.class);
	
	@Autowired
	private CustomerAccountService customerAccountService;
	@Autowired
	private FinanceReportService financeReportService;
	
	/**
	 * @Description: 客户账务报表
	 * @Author: ldp
	 * @Date: 2015年10月9日
	 * @return
	 */
	@RequestMapping(value="")
	public String getCustomAccReport(@ModelAttribute CustomerAccountDto cAccountDto,ModelMap modelMap){
		log.info("CustomerAccountController.getCustomAccReport" + cAccountDto);
		if (StringUtils.isBlank(cAccountDto.getStartDate()) || StringUtils.isBlank(cAccountDto.getEndDate()) ) {
			cAccountDto.setStartDate(TimeUtil.getYMDHMS(TimeUtil.firstDayOfLastMonth()));
			cAccountDto.setEndDate(TimeUtil.getYMDHMS(TimeUtil.lastDayOfLastMonth()));
		}
		Page<CustomerAccountVo> page = new Page<CustomerAccountVo>();
		page.setPageNo(cAccountDto.getPageNo());
		//搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cAccountDto", cAccountDto);
		page.setParams(params);
		//获取报表
		List<CustomerAccountVo> results = new ArrayList<CustomerAccountVo>();
		try {
			results = customerAccountService.getCustomerAccountList(page);
			page.setResults(results);
			modelMap.addAttribute("page", page);
			//获取客户账务统计总量
			modelMap.addAttribute("caTotals", customerAccountService.getCustomerAccountTotals(cAccountDto));
			//合并单元格的个数
			modelMap.addAttribute("rowspan", customerAccountService.getUserNumByUnit(results));
			modelMap.addAttribute("orgList", financeReportService.getOrgListCache());
		} catch (Exception e) {
			log.error("CustomerAccountController.getCustomAccReport error:",e);;
		}
		return "public/customerAccount";
	}
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportCustomerAccount", method = {RequestMethod.GET,RequestMethod.POST })
	public void exportCustomerAccount(@ModelAttribute CustomerAccountDto cAccountDto,HttpServletRequest request,HttpServletResponse response) {
		Page<CustomerAccountVo> page = new Page<CustomerAccountVo>();
		page.setPageSize(65536);
		//导出条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cAccountDto", cAccountDto);
		page.setParams(params);
		//结果集
		List<CustomerAccountVo> results;
		try {
			results = customerAccountService.getCustomerAccountList(page);
			if(CollectionUtils.isNotEmpty(results)){
				this.exportExcel(response,results);
			}else{
				this.exportExcel(response, new ArrayList<CustomerAccountVo>());
			}
		} catch (Exception e) {
			log.error("CustomerAccountController.exportCustomerAccount is error:",e);;
		}
	}
	
	public HttpServletResponse exportExcel(HttpServletResponse response,List<CustomerAccountVo> results) {
		try {
			ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("KHZW"));
			OutputStream os = response.getOutputStream();
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			
			WritableCellFormat wcfTitle = ExcelUtil.getTitleFormat();
			WritableCellFormat wcfContentCenter = ExcelUtil.getContentFormat();
			//WritableCellFormat wcfContentRightTwo = ExcelUtil.getNumberFormat();
			WritableCellFormat numberFormat = ExcelUtil.getNumberFormat();
			
			WritableSheet wsheet = wbook.createSheet("客户账务统计", 0); // sheet名称
			//设置单元格宽度
			wsheet.setColumnView(0, 8);
			wsheet.setColumnView(1, 15);
			wsheet.setColumnView(2, 25);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 15);
			wsheet.setColumnView(5, 15);
			wsheet.setColumnView(6, 15);
			wsheet.setColumnView(7, 15);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 15);
			wsheet.setColumnView(10, 25);
			wsheet.setColumnView(11, 15);
			wsheet.setColumnView(12, 15);
			wsheet.setColumnView(13, 15);
			wsheet.setColumnView(14, 15);
			wsheet.setColumnView(15, 15);
			wsheet.setColumnView(16, 15);
			wsheet.setColumnView(17, 15);
			wsheet.setColumnView(18, 15);
			wsheet.setColumnView(19, 15);
			wsheet.setColumnView(20, 15);
			wsheet.setColumnView(21, 15);
			wsheet.setColumnView(22, 15);
			wsheet.mergeCells(0, 0, 22, 2);
			wsheet.addCell(new Label(0,0,"客户账务统计报表",wcfTitle));
			//分组标题
			wsheet.mergeCells(0, 3, 0, 4);
			wsheet.addCell(new Label(0, 3, "", wcfTitle));
			wsheet.mergeCells(1, 3, 3, 4);
			wsheet.addCell(new Label(1, 3, "", wcfTitle));
			wsheet.mergeCells(4, 3, 7, 4);
			wsheet.addCell(new Label(4, 3, "入库情况", wcfTitle));
			wsheet.mergeCells(8, 3, 10, 4);
			wsheet.addCell(new Label(8, 3, "挂牌情况", wcfTitle));
			wsheet.mergeCells(11, 3, 15, 4);
			wsheet.addCell(new Label(11, 3, "销售情况", wcfTitle));
			wsheet.mergeCells(16, 3, 20, 4);
			wsheet.addCell(new Label(16, 3, "采购情况", wcfTitle));
			wsheet.mergeCells(16, 3, 20, 4);
			wsheet.addCell(new Label(16, 3, "采购情况", wcfTitle));
			wsheet.mergeCells(21, 3, 22, 4);
			wsheet.addCell(new Label(21, 3, "", wcfTitle));
			// 设置单元格标题行
			int titleRow = 5;
			wsheet.addCell(new Label(0, titleRow, "编号", wcfTitle));	
			wsheet.addCell(new Label(1, titleRow, "会员名", wcfTitle));
			wsheet.addCell(new Label(2, titleRow, "公司/真实姓名", wcfTitle));
			wsheet.addCell(new Label(3, titleRow, "单位", wcfTitle));
			
			wsheet.addCell(new Label(4, titleRow, "仓单总量", wcfTitle));
			wsheet.addCell(new Label(5, titleRow, "入库总量", wcfTitle));
			wsheet.addCell(new Label(6, titleRow, "仓单笔数(笔)", wcfTitle));
			wsheet.addCell(new Label(7, titleRow, "仓单品种数(个)", wcfTitle));
			
			wsheet.addCell(new Label(8, titleRow, "挂牌总量", wcfTitle));
			wsheet.addCell(new Label(9, titleRow, "挂牌笔数(笔)", wcfTitle));
			wsheet.addCell(new Label(10, titleRow, "挂牌品种数(个)", wcfTitle));
			
			wsheet.addCell(new Label(11, titleRow, "销售总量", wcfTitle));
			wsheet.addCell(new Label(12, titleRow, "销售品种数(个)", wcfTitle));
			wsheet.addCell(new Label(13, titleRow, "销售笔数(笔)", wcfTitle));
			wsheet.addCell(new Label(14, titleRow, "销售金额(元)", wcfTitle));
			wsheet.addCell(new Label(15, titleRow, "销售总金额(元)", wcfTitle));
			
			wsheet.addCell(new Label(16, titleRow, "采购总量", wcfTitle));
			wsheet.addCell(new Label(17, titleRow, "采购品种数(个)", wcfTitle));
			wsheet.addCell(new Label(18, titleRow, "采购笔数(笔)", wcfTitle));
			wsheet.addCell(new Label(19, titleRow, "采购金额(元)", wcfTitle));
			wsheet.addCell(new Label(20, titleRow, "采购总金额(元)", wcfTitle));
			
			wsheet.addCell(new Label(21, titleRow, "大区", wcfTitle));
			wsheet.addCell(new Label(22, titleRow, "业务人员", wcfTitle));
			
			int row = 6;//行
			int cell;//列
			// 生成报表内容
			if(results.size()<0)
				return null;
			
			Map<String, Object> rowSpan = customerAccountService.getUserNumByUnit(results);//获取需要合并的单元格
			Map<String, Object> flagMap = new HashMap<String, Object>();//判断是否是第一次合并
			Map<String, Object> userNameFlagMap = new HashMap<String, Object>();//姓名合并标识
			Map<String, Object> orderAmtFlagMap = new HashMap<String, Object>();//销售总额合并标识
			Map<String, Object> purchaseFlagMap = new HashMap<String, Object>();//采购合并标识
			Map<String, Object> orgNameFlagMap = new HashMap<String, Object>();//大区合并标识
			Map<String, Object> salsManFlagMap = new HashMap<String, Object>();//业务员合并标识
			
			for(CustomerAccountVo pv : results){
				cell = 0;
				wsheet.addCell(new Label(cell++, row, row-titleRow + "", wcfContentCenter));
				if (getRowSpan(rowSpan, pv.getUserName())>1) {
					if (flagMap.containsKey(pv.getUserName())) {
						log.info("rowspan:" + getRowSpan(rowSpan, pv.getUserName()));
					}else{
						wsheet.mergeCells(cell, row, cell, row + getRowSpan(rowSpan, pv.getUserName())-1);
						flagMap.put(pv.getUserName(), 1);
					}
				}
				wsheet.addCell(new Label(cell++, row, pv.getUserName(), wcfContentCenter));//会员名
				//姓名合并单元格
				if (getRowSpan(rowSpan, pv.getUserName())>1) {
					if (userNameFlagMap.containsKey(pv.getUserName())) {
						log.info("rowspan:" + getRowSpan(rowSpan, pv.getUserName()));
					}else{
						wsheet.mergeCells(cell, row, cell, row + getRowSpan(rowSpan, pv.getUserName())-1);
						userNameFlagMap.put(pv.getUserName(), 1);
					}
				}
				wsheet.addCell(new Label(cell++, row, pv.getRealName(), wcfContentCenter));//真实名称
				wsheet.addCell(new Label(cell++, row, pv.getUnit(), wcfContentCenter));//单位
				
				wsheet.addCell(new Number(cell++, row, deal(pv.getWlTotal()), numberFormat));//仓单总量
				wsheet.addCell(new Number(cell++, row, deal(pv.getInWLTotal()), numberFormat));//入库总量
				wsheet.addCell(new Number(cell++, row, deal(pv.getWlNums()), wcfContentCenter));//仓单笔数
				wsheet.addCell(new Number(cell++, row, deal(pv.getWlBreeds()), wcfContentCenter));//仓单品种数
				
				wsheet.addCell(new Number(cell++, row, deal(pv.getListingAmount()), numberFormat));//挂牌总量
				wsheet.addCell(new Number(cell++, row, deal(pv.getListingNum()), wcfContentCenter));//挂牌笔数
				wsheet.addCell(new Number(cell++, row, deal(pv.getListingBreedNum()), wcfContentCenter));//挂牌品种数
				
				wsheet.addCell(new Number(cell++, row, deal(pv.getOrderTotalAmt()), numberFormat));//销售总量
				wsheet.addCell(new Number(cell++, row, deal(pv.getOrderBreedNum()), wcfContentCenter));//销售品种数
				wsheet.addCell(new Number(cell++, row, deal(pv.getOrderNum()), wcfContentCenter));//销售笔数
				wsheet.addCell(new Number(cell++, row, deal(pv.getOrderPayment()), numberFormat));//销售金额
				if (getRowSpan(rowSpan, pv.getUserName())>1) {
					if (orderAmtFlagMap.containsKey(pv.getUserName())) {
						log.info("rowspan:" + getRowSpan(rowSpan, pv.getUserName()));
					}else{
						wsheet.mergeCells(cell, row, cell, row + getRowSpan(rowSpan, pv.getUserName())-1);
						orderAmtFlagMap.put(pv.getUserName(), 1);
					}
				}
				wsheet.addCell(new Number(cell++, row, deal(getTotalAmt(results, pv.getUserName(), 1)), numberFormat));//销售总金额
				
				wsheet.addCell(new Number(cell++, row, deal(pv.getPurchaseAmount()), numberFormat));//采购总量
				wsheet.addCell(new Number(cell++, row, deal(pv.getPurchaseBreedNum()), wcfContentCenter));//采购品种数
				wsheet.addCell(new Number(cell++, row, deal(pv.getPurchaseOrderNum()), wcfContentCenter));//采购笔数
				wsheet.addCell(new Number(cell++, row, deal(pv.getPurchaseOrderAmt()), numberFormat));//采购金额
				if (getRowSpan(rowSpan, pv.getUserName())>1) {
					if (purchaseFlagMap.containsKey(pv.getUserName())) {
						log.info("rowspan:" + getRowSpan(rowSpan, pv.getUserName()));
					}else{
						wsheet.mergeCells(cell, row, cell, row + getRowSpan(rowSpan, pv.getUserName())-1);
						purchaseFlagMap.put(pv.getUserName(), 1);
					}
				}
				wsheet.addCell(new Number(cell++, row, deal(getTotalAmt(results, pv.getUserName(), 2)), numberFormat));//采购总金额
				
				if (getRowSpan(rowSpan, pv.getUserName())>1) {
					if (orgNameFlagMap.containsKey(pv.getUserName())) {
						log.info("rowspan:" + getRowSpan(rowSpan, pv.getUserName()));
					}else{
						wsheet.mergeCells(cell, row, cell, row + getRowSpan(rowSpan, pv.getUserName())-1);
						orgNameFlagMap.put(pv.getUserName(), 1);
					}
				}
				wsheet.addCell(new Label(cell++, row, pv.getOrgName(), wcfContentCenter));//大区
				if (getRowSpan(rowSpan, pv.getUserName())>1) {
					if (salsManFlagMap.containsKey(pv.getUserName())) {
						log.info("rowspan:" + getRowSpan(rowSpan, pv.getUserName()));
					}else{
						wsheet.mergeCells(cell, row, cell, row + getRowSpan(rowSpan, pv.getUserName())-1);
						salsManFlagMap.put(pv.getUserName(), 1);
					}
				}
				wsheet.addCell(new Label(cell++, row, pv.getSalsManName(), wcfContentCenter));//业务人员
				row++;
			}
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception ex) {
			log.error("exportExcel:", ex);
		}
		return response;
	}
	
	/**
	 * @Description:获取合并单元格个数
	 * @Author: ldp
	 * @Date: 2015年10月13日
	 * @param map
	 * @param userName
	 * @return
	 */
	public int getRowSpan(Map<String, Object> map, String userName){
		if (map == null || map.size()==0) {
			return 0;
		}
		if (!map.containsKey(userName)) {
			return 0;
		}
		return Integer.parseInt(String.valueOf(map.get(userName)));
	}
	
	
	/**
	 * @Description: 根据用户名获取总金额
	 * @Author: ldp
	 * @Date: 2015年10月14日
	 * @param results
	 * @param userName 
	 * @param type 1 销售总金额 2采购总金额
	 * @return
	 */
	public String getTotalAmt(List<CustomerAccountVo> results,String userName,int type){
		if (results == null || results.size() == 0) {
			return "0";
		}
		BigDecimal amtDecimal = new BigDecimal(0);
		for(CustomerAccountVo cvo:results){
			if (userName.equals(cvo.getUserName())) {
				if (1 == type) {
					amtDecimal = BigDecimalUtil.add(amtDecimal, new BigDecimal(StringUtils.isBlank(cvo.getOrderPayment())?"0":cvo.getOrderPayment()));
				}else if (2 == type) {
					amtDecimal = BigDecimalUtil.add(amtDecimal, new BigDecimal(StringUtils.isBlank(cvo.getPurchaseOrderAmt())?"0":cvo.getPurchaseOrderAmt()));
				}
			}
		}
		return BigDecimalUtil.formateBigDecimal(amtDecimal);
	}
	
	/**
	 * @Description: 数字字符为空时给默认值0
	 * @Author: ldp
	 * @Date: 2015年10月14日
	 * @param dealStr
	 * @return
	 */
	public double deal(String dealStr){
		return Double.parseDouble(StringUtils.isBlank(dealStr)?"0":dealStr);
	}
	
}
