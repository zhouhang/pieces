package com.jointown.zy.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.JXLException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.FinanceReportDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.FinancingReportEnums;
import com.jointown.zy.common.model.FinanceReportModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.FinanceReportService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 财务账表
 * @ClassName:FinanceReportController
 * @author:Calvin.Wangh
 * @date:2015-8-5下午3:19:19
 * @version V1.0
 * @Description:
 */
@Controller
@RequestMapping(value="/financeReport")
public class FinanceReportController extends UserBaseController {
	
	private static final Log logger = LogFactory.getLog(FinanceReportController.class);
	
	
	@Autowired
	private FinanceReportService financeReportService; 
	
	@RequestMapping(value="",method = {RequestMethod.POST,RequestMethod.GET})
	public String getPageList(FinanceReportDto dto,HttpServletRequest request,Model model){
		Page<FinanceReportModel> page = new Page<FinanceReportModel>();
		page.setPageNo(dto.getPageNo());
		//默认查询条件为 前一整天数据
		String golableMenuPath=request.getParameter("golableMenuPath");
		if(golableMenuPath!=null&&!golableMenuPath.equals("")){
			dto.setStartTime(TimeUtil.formate_YYYY_MM_DD_HH_MI_SS.format(TimeUtil.getBeforeDayStart(1)));
			dto.setEndTime(TimeUtil.formate_DAY_END_YMDHMS.format(TimeUtil.getBeforeDayEnd(1)));
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dto", dto);
		page.setParams(params);
		
		List<FinanceReportModel> result = financeReportService.getPageList(page);
		if (result == null)
			page.setResults(Collections.<FinanceReportModel> emptyList());
		else
			page.setResults(result);
		
		
		model.addAttribute("page", page);
		model.addAttribute("totleMap", getTotle(result));
		model.addAttribute("busiOrderStateMap", BusiOrderStateEnum.toMap());
		model.addAttribute("financingReportMap", FinancingReportEnums.toMap());
		model.addAttribute("payChannelList", BankConfigConstant.PayChannel.showList(BankConfigConstant.class.getName(),"PayChannel", null));
		model.addAttribute("orgList", financeReportService.getOrgListCache());
		
		return "/public/financereport_list";
	}
	
	/**
	 * 计算收益合计金额
	 * @param result
	 * @return
	 */
	public Map<String,Object> getTotle(List<FinanceReportModel> result){
		Map<String,Object> totleMap = new LinkedHashMap<String,Object>();
		BigDecimal buyerTotle = new BigDecimal(0);//买家收益
		BigDecimal sellerTotle = new BigDecimal(0);//卖家收益
		BigDecimal platformTotle = new BigDecimal(0);//平台收益
		for(FinanceReportModel frm : result){
			if(frm.getBuyerAmount()!=null){
				buyerTotle = BigDecimalUtil.add(buyerTotle, frm.getBuyerAmount());
			}
			if(frm.getSellerAmount()!=null){
				sellerTotle = BigDecimalUtil.add(sellerTotle, frm.getSellerAmount());
			}
			if(frm.getPlatformAmount()!=null){
				platformTotle = BigDecimalUtil.add(platformTotle, frm.getPlatformAmount());
			}
		}
		totleMap.put("buyerTotle", buyerTotle);
		totleMap.put("sellerTotle", sellerTotle);
		totleMap.put("platformTotle", platformTotle);
		return totleMap;
	}
	
	/**
	 * Excel导出
	 * @param dto
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/export", method = {RequestMethod.GET,RequestMethod.POST })
	public void exportPayFlow(FinanceReportDto dto,HttpServletRequest request,HttpServletResponse response) {
		List<FinanceReportModel> results = financeReportService.getList(dto);
		if(CollectionUtils.isNotEmpty(results)){
			this.exportExcel(response,results);
		}else{
			this.exportExcel(response, new ArrayList<FinanceReportModel>());
		}
	}
	
	public HttpServletResponse exportExcel(HttpServletResponse response,List<FinanceReportModel> results){
		try {
			//设置response
			response = ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("CWZB"));
			OutputStream os = response.getOutputStream();
			//创建一个工作簿
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			//获取表头格式
			WritableCellFormat titleFormat = ExcelUtil.getTitleFormat();
			WritableCellFormat contentFormat = ExcelUtil.getContentFormat();
			WritableCellFormat numberFormat = ExcelUtil.getNumberFormat();
			
			//创建一个sheet以及表头内容格式
			WritableSheet wsheet = setTableHead("订单账务报表", wbook, titleFormat);
			
			// 生成报表内容
			int row = 5;//行 开始写入数据位置
			int cell;//列
			//合计
			double buyerTotle = new Double(0);
			double sellerTotle = new Double(0);
			double platformTotle = new Double(0);
			
			if(results.size()<0)
				return null;	
			
			for(FinanceReportModel frm : results){
				cell=0;
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getOrderId()) ? "" : frm.getOrderId() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getOrderState()) ? "" : BusiOrderStateEnum.obtainCodeName(frm.getOrderState()) + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getListingid()) ? "" : frm.getListingid() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getWlid()) ? "" : frm.getWlid() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getBreedName()) ? "" : frm.getBreedName() + "/" +frm.getBreedCode(), contentFormat));
				wsheet.addCell(new Number(cell++, row, frm.getAmount()==null ? new Double(0): frm.getAmount().doubleValue(),numberFormat));
				wsheet.addCell(new Number(cell++, row, frm.getVolume()==null ? new Double(0): frm.getVolume().doubleValue(),numberFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getUnit()) ? "" : frm.getUnit() + "", contentFormat));
				wsheet.addCell(new Number(cell++, row, frm.getUnitPrice()==null ? new Double(0): frm.getUnitPrice().doubleValue(),numberFormat));
				wsheet.addCell(new Number(cell++, row, frm.getTotalPrice()==null ? new Double(0): frm.getTotalPrice().doubleValue(),numberFormat));
				//买方
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getBuyerCode()) ? "" : frm.getBuyerCode() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getBuyerName()) ? "" : frm.getBuyerName() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getBuyerFlowId()) ? "" : frm.getBuyerFlowId() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, frm.getBuyerType()==null ? "" : FinancingReportEnums.obtainCodeName(frm.getBuyerType()) + "", contentFormat));
				if(frm.getBuyerAmount()==null)
					wsheet.addCell(new Label(cell++, row , "" , contentFormat));
				else
					wsheet.addCell(new Number(cell++, row, frm.getBuyerAmount()==null ? new Double(0) : frm.getBuyerAmount().doubleValue(),numberFormat));
				if(frm.getBuyerAmount()!=null){
					buyerTotle += frm.getBuyerAmount().doubleValue();
				}
				wsheet.addCell(new Label(cell++, row, frm.getBuyerPayTime()==null ? "" : (StringUtils.isBlank(frm.getBuyerPayTime().toString()) ? "" : TimeUtil.getYMDHMS(frm.getBuyerPayTime())) + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, frm.getBuyerPayChannel()==null ? "" : BankConfigConstant.PayChannel.getName(frm.getBuyerPayChannel()) + "", contentFormat));
				//卖方
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getSellerCode()) ? "" : frm.getSellerCode() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getSellerName()) ? "" : frm.getSellerName() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getSellerFlowId()) ? "" : frm.getSellerFlowId() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, frm.getSellerType()==null ? "" : FinancingReportEnums.obtainCodeName(frm.getSellerType()) + "", contentFormat));
				if(frm.getSellerAmount()==null)
					wsheet.addCell(new Label(cell++, row , "" , contentFormat));
				else
					wsheet.addCell(new Number(cell++, row, frm.getSellerAmount()==null ? new Double(0) : frm.getSellerAmount().doubleValue(),numberFormat));	
				if(frm.getSellerAmount()!=null){
					sellerTotle += frm.getSellerAmount().doubleValue();
				}
				wsheet.addCell(new Label(cell++, row, frm.getSellerPayTime()==null ? "" : (StringUtils.isBlank(frm.getSellerPayTime().toString()) ? "" : TimeUtil.getYMDHMS(frm.getSellerPayTime())) + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, frm.getSellerPayChannel()==null ? "" : BankConfigConstant.PayChannel.getName(frm.getSellerPayChannel()) + "", contentFormat));
				//平台
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getPlatformFlowId()) ? "" : frm.getPlatformFlowId() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, frm.getPlatformType()==null ? "" : FinancingReportEnums.obtainCodeName(frm.getPlatformType()) + "", contentFormat));
				if(frm.getPlatformAmount()==null)
					wsheet.addCell(new Label(cell++, row,"",contentFormat));
				else
					wsheet.addCell(new Number(cell++, row, frm.getPlatformAmount()==null ? new Double(0) : frm.getPlatformAmount().doubleValue(),numberFormat));
				if(frm.getPlatformAmount()!=null){
					platformTotle += frm.getPlatformAmount().doubleValue();
				}
				wsheet.addCell(new Label(cell++, row, frm.getPlatformPayTime()==null ? "" : (StringUtils.isBlank(frm.getPlatformPayTime().toString()) ? "" : TimeUtil.getYMDHMS(frm.getPlatformPayTime())) + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, frm.getPlatformPayChannel()==null ? "" : BankConfigConstant.PayChannel.getName(frm.getPlatformPayChannel()) + "", contentFormat));
				//收益
				if(frm.getHandlingCharge()==null)
					wsheet.addCell(new Label(cell++, row,"",contentFormat));
				else
					wsheet.addCell(new Number(cell++, row, frm.getHandlingCharge()==null ? new Double(0) : frm.getHandlingCharge().doubleValue(),numberFormat));
				//业务员信息
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getBuyerOrg()) ? "" : frm.getBuyerOrg() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getBuyerSalesMan()) ? "" : frm.getBuyerSalesMan() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getSellerOrg()) ? "" : frm.getSellerOrg() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getSellerSalesMan()) ? "" : frm.getSellerSalesMan() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getFollower()) ? "" : frm.getFollower() + "", contentFormat));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(frm.getAccountant()) ? "" : frm.getAccountant() + "", contentFormat));

				row++;
			}
			//合计 
			int lastRow = wsheet.getRows();//定位最后一行
			wsheet.addCell(new Label(0, lastRow, "合计", ExcelUtil.contentFormatNoBorder()));
			wsheet.addCell(new Number(14, lastRow, buyerTotle, ExcelUtil.numberFormatNoBorder()));
			wsheet.addCell(new Number(21, lastRow, sellerTotle, ExcelUtil.numberFormatNoBorder()));
			wsheet.addCell(new Number(26, lastRow, platformTotle, ExcelUtil.numberFormatNoBorder()));
			
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("ReportListingController exportExcel error:" + e);
		}
		return response;
	}
	
	/**
	 * 定义表头内容格式
	 * @param sheetName
	 * @param wbook
	 * @param titleFormat
	 * @return
	 * @throws WriteException
	 * @throws JXLException
	 */
	public  WritableSheet setTableHead(String sheetName,WritableWorkbook wbook,WritableCellFormat titleFormat) throws WriteException, JXLException{
		WritableSheet wsheet = wbook.createSheet(sheetName, 0); // sheet名称
		//设置单元格宽度
		wsheet.setColumnView(0, 20);
		wsheet.setColumnView(1, 10);
		wsheet.setColumnView(2, 20);
		wsheet.setColumnView(3, 20);
		wsheet.setColumnView(4, 30);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 15);
		wsheet.setColumnView(9, 15);
		//买家
		wsheet.setColumnView(10, 15);
		wsheet.setColumnView(11, 15);
		wsheet.setColumnView(12, 20);
		wsheet.setColumnView(13, 20);
		wsheet.setColumnView(14, 15);
		wsheet.setColumnView(15, 20);
		wsheet.setColumnView(16, 15);
		//卖家
		wsheet.setColumnView(17, 15);
		wsheet.setColumnView(18, 15);
		wsheet.setColumnView(19, 20);
		wsheet.setColumnView(20, 20);
		wsheet.setColumnView(21, 15);
		wsheet.setColumnView(22, 20);
		wsheet.setColumnView(23, 15);
		//平台
		wsheet.setColumnView(24, 20);
		wsheet.setColumnView(25, 15);
		wsheet.setColumnView(26, 15);
		wsheet.setColumnView(27, 20);
		wsheet.setColumnView(28, 15);
		//其他
		wsheet.setColumnView(29, 12);
		wsheet.setColumnView(30, 12);
		wsheet.setColumnView(31, 12);
		wsheet.setColumnView(32, 12);
		wsheet.setColumnView(33, 12);
		wsheet.setColumnView(34, 12);
		wsheet.setColumnView(35, 12);
		
		wsheet.mergeCells(0, 0, 35, 1);
		wsheet.addCell(new Label(0,0,"订单账务报表",titleFormat));
		/**
		 * mergeCells(a,b,c,d) 单元格合并函数
		 * a 单元格的列号
		 * b 单元格的行号
		 * c 从a单元格列号开始 向右边移动的目标列号的值 比如 从第10列 合并6个单元格 那么就是10-16合并
		 * d 向下合并的行数
		 */
		wsheet.mergeCells(0, 2, 9, 3);
		wsheet.addCell(new Label(0, 2, "订单相关信息", titleFormat));
		
		wsheet.mergeCells(10, 2, 16 , 3);
		wsheet.addCell(new Label(10, 2, "买方", titleFormat));
		
		wsheet.mergeCells(17, 2, 23 , 3);
		wsheet.addCell(new Label(17, 2, "卖方", titleFormat));
		
		wsheet.mergeCells(24, 2, 28 , 3);
		wsheet.addCell(new Label(24, 2, "平台", titleFormat));
		
		wsheet.mergeCells(29, 2, 29 , 3);
		wsheet.addCell(new Label(29, 2, "手续费", titleFormat));
		
		
		wsheet.mergeCells(30, 2, 31 , 3);
		wsheet.addCell(new Label(30, 2, "买方业务人员", titleFormat));
		
		wsheet.mergeCells(32, 2, 33 , 3);
		wsheet.addCell(new Label(32, 2, "卖方业务人员", titleFormat));
		
		wsheet.mergeCells(34, 2, 35 , 3);
		wsheet.addCell(new Label(34, 2, "资金操作人", titleFormat));
		
		//订单相关信息
		wsheet.addCell(new Label(0, 4, "订单号", titleFormat));	
		wsheet.addCell(new Label(1, 4, "订单状态", titleFormat));	
		wsheet.addCell(new Label(2, 4, "挂牌编号", titleFormat));
		wsheet.addCell(new Label(3, 4, "仓单编号", titleFormat));	
		wsheet.addCell(new Label(4, 4, "品种/编码", titleFormat));
		wsheet.addCell(new Label(5, 4, "订单数量", titleFormat));
		wsheet.addCell(new Label(6, 4, "成交数量", titleFormat));
		wsheet.addCell(new Label(7, 4, "单位", titleFormat));
		wsheet.addCell(new Label(8, 4, "单价（元）", titleFormat));
		wsheet.addCell(new Label(9, 4, "成交总价（元）", titleFormat));
		//买方
		wsheet.addCell(new Label(10, 4, "付款人账号", titleFormat));	
		wsheet.addCell(new Label(11, 4, "付款人名称", titleFormat));	
		wsheet.addCell(new Label(12, 4, "流水号", titleFormat));
		wsheet.addCell(new Label(13, 4, "收入类型", titleFormat));	
		wsheet.addCell(new Label(14, 4, "支出金额（元）", titleFormat));
		wsheet.addCell(new Label(15, 4, "支出时间", titleFormat));
		wsheet.addCell(new Label(16, 4, "支付渠道", titleFormat));
		//卖方
		wsheet.addCell(new Label(17, 4, "收款人账号", titleFormat));	
		wsheet.addCell(new Label(18, 4, "收款人名称", titleFormat));	
		wsheet.addCell(new Label(19, 4, "流水号", titleFormat));
		wsheet.addCell(new Label(20, 4, "收入类型", titleFormat));	
		wsheet.addCell(new Label(21, 4, "收入金额（元）", titleFormat));
		wsheet.addCell(new Label(22, 4, "收入时间", titleFormat));
		wsheet.addCell(new Label(23, 4, "支付渠道", titleFormat));
		//平台
		wsheet.addCell(new Label(24, 4, "流水号", titleFormat));	
		wsheet.addCell(new Label(25, 4, "收入类型", titleFormat));	
		wsheet.addCell(new Label(26, 4, "收入金额（元）", titleFormat));
		wsheet.addCell(new Label(27, 4, "收入时间", titleFormat));	
		wsheet.addCell(new Label(28, 4, "支付渠道", titleFormat));
		//其他
		wsheet.addCell(new Label(29, 4, "手续费", titleFormat));
		wsheet.addCell(new Label(30, 4, "大区", titleFormat));
		wsheet.addCell(new Label(31, 4, "业务人员", titleFormat));
		wsheet.addCell(new Label(32, 4, "大区", titleFormat));
		wsheet.addCell(new Label(33, 4, "业务人员", titleFormat));
		wsheet.addCell(new Label(34, 4, "账务发起人", titleFormat));
		wsheet.addCell(new Label(35, 4, "账务处理人", titleFormat));
		return wsheet;
	}
}
