package com.jointown.zy.web.controller;


import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.dto.BreedAccountDto;
import com.jointown.zy.common.model.BreedAccountModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BreedAccountService;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BreedAccountCountVo;

@Controller
@RequestMapping(value="/breedAccount")

/**
 * 品种账务统计
 * @ClassName:BreedAccountController
 * @author:Calvin.Wangh
 * @date:2015-10-16下午3:06:58
 * @version V1.0
 * @Description:
 */
public class BreedAccountController extends UserBaseController {
	
	private static final Log logger = LogFactory.getLog(BreedAccountController.class);
	@Autowired
	private BreedAccountService breedAccountService;
	
	@RequestMapping(value="",method = {RequestMethod.POST,RequestMethod.GET})
	public String getPageList(BreedAccountDto dto, HttpServletRequest request,Model model){
		Page<BreedAccountModel> page = new Page<BreedAccountModel>();
		page.setPageNo(dto.getPageNo());
		
		String golableMenuPath=request.getParameter("golableMenuPath");
		if(golableMenuPath!=null&&!golableMenuPath.equals("")){
			dto.setStartTime(TimeUtil.formate_YYYY_MM_DD_HH_MI_SS.format(TimeUtil.firstDayOfLastMonth()));
			dto.setEndTime(TimeUtil.formate_DAY_END_YMDHMS.format(TimeUtil.lastDayOfLastMonth()));
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dto", dto);
		page.setParams(params);
		//列表
		List<BreedAccountModel> result = breedAccountService.getPageList(page);
		page.setResults(result);
		//统计查询结果
		BreedAccountCountVo count = breedAccountService.getAccountCountVo(dto);
		model.addAttribute("count", count);
		
		model.addAttribute("page", page);
		model.addAttribute("alikeMap", breedAccountService.countAlikeBreeds(result));
		return "/public/breedAccount_list";
	}
	
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value ="/export")
	public void export(@ModelAttribute BreedAccountDto dto, HttpServletResponse response) throws Exception {
		Page<BreedAccountModel> page = new Page<BreedAccountModel>();
		page.setPageNo(dto.getPageNo());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dto", dto);
		page.setParams(params);
		page.setPageSize(Integer.MAX_VALUE-1);
	
		List<BreedAccountModel> result = breedAccountService.getPageList(page);
		BreedAccountCountVo count =  breedAccountService.getAccountCountVo(dto);
		if(CollectionUtils.isNotEmpty(result)){
			this.exportExcel(response,result,count);
		}else{
			this.exportExcel(response, new ArrayList<BreedAccountModel>(),new BreedAccountCountVo());
		}
		return ;
	}
	
	public HttpServletResponse exportExcel(HttpServletResponse response,List<BreedAccountModel> results,BreedAccountCountVo count) {
		try {
			//设置response
			response = ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("PZZW"));
			OutputStream os = response.getOutputStream();
			//创建一个工作簿
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			//获取表头格式
			WritableCellFormat titleFormat = ExcelUtil.getTitleFormat();
			WritableCellFormat contentFormat = ExcelUtil.getContentFormat();
			WritableCellFormat numberFormat = ExcelUtil.getNumberFormat();
			
			//创建一个sheet以及表头内容格式
			WritableSheet wsheet = setTableHead("品种账务统计报表", wbook, titleFormat);
			
			// 生成报表内容
			int row = 3;//行 开始写入数据位置
			int cell;//列
			
			if(results.size()<0)
				return null;	
			
			for(BreedAccountModel bam: results){
				cell=0;
				//品种
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getBreedName()) ? "" : bam.getBreedName() + "", contentFormat));
				//单位
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getUnit()) ? "" : bam.getUnit() + "", contentFormat));
				//仓单总量
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getWhlistTotal()) ? "" : bam.getWhlistTotal() + "", contentFormat));
				//入仓用户
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getWhlistUserAmount()) ? "" : bam.getWhlistUserAmount() + "", contentFormat));
				//仓单笔数
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getWhlistAmount()) ? "" : bam.getWhlistAmount() + "", contentFormat));
				//挂牌总量
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getListingTotal()) ? "" : bam.getListingTotal() + "", contentFormat));
				//挂牌用户
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getListingUserAmount()) ? "" : bam.getListingUserAmount() + "", contentFormat));
				//挂牌笔数
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getListingAmount()) ? "" : bam.getListingAmount() + "", contentFormat));
				//交易总量
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getOrderTotal()) ? "" : bam.getOrderTotal() + "", contentFormat));
				//交易总金额
				wsheet.addCell(new Number(cell++, row, bam.getOrderTotalMoney()==null ? new Double(0): bam.getOrderTotalMoney().doubleValue(),numberFormat));
				//交易用户
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getOrderUserAmount()) ? "" : bam.getOrderUserAmount() + "", contentFormat));
				//交易笔数
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(bam.getOrderAmount()) ? "" : bam.getOrderAmount() + "", contentFormat));

				row++;
			}
			//合计 
			int lastRow = wsheet.getRows();//定位最后一行
			
			wsheet.addCell(new Label(0, lastRow, "合计", ExcelUtil.contentFormatNoBorder()));
			wsheet.mergeCells(0, lastRow, 1, 0);
			
			wsheet.addCell(new Label(2, lastRow, "仓单总量", ExcelUtil.contentFormatNoBorder()));
			wsheet.mergeCells(3, lastRow, 4, 0);
			wsheet.addCell(new Label(3, lastRow, StringUtils.isBlank(count.getWhlistCount()) ? "" : count.getWhlistCount()	+ " 公斤", ExcelUtil.contentFormatNoBorder()));
			
			wsheet.addCell(new Label(5, lastRow, "挂牌总量", ExcelUtil.contentFormatNoBorder()));
			wsheet.mergeCells(6, lastRow, 7, 0);
			wsheet.addCell(new Label(6, lastRow, StringUtils.isBlank(count.getListingCount()) ? "" : count.getListingCount() + " 公斤", ExcelUtil.contentFormatNoBorder()));
			
			wsheet.addCell(new Label(8, lastRow, "交易总量", ExcelUtil.contentFormatNoBorder()));
			wsheet.mergeCells(9, lastRow, 11, 0);
			wsheet.addCell(new Label(9, lastRow, StringUtils.isBlank(count.getOrderCount()) ? "" : count.getOrderCount() + " 公斤", ExcelUtil.contentFormatNoBorder()));
			
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("BreedAccountController exportExcel error:" + e);
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
		wsheet.setColumnView(0, 30);//品种
		wsheet.setColumnView(1, 15);//单位
		wsheet.setColumnView(2, 20);//仓单总量
		wsheet.setColumnView(3, 15);//入仓用户
		wsheet.setColumnView(4, 15);//仓单笔数
		wsheet.setColumnView(5, 20);//挂牌总量
		wsheet.setColumnView(6, 15);//挂牌用户
		wsheet.setColumnView(7, 15);//挂牌笔数
		wsheet.setColumnView(8, 20);//交易总量
		wsheet.setColumnView(9, 20);//交易总金额
		wsheet.setColumnView(10, 15);//交易用户
		wsheet.setColumnView(11, 15);//交易笔数
		
		wsheet.mergeCells(0, 0, 11, 1);
		wsheet.addCell(new Label(0,0,"品种账务统计",titleFormat));
		
		wsheet.addCell(new Label(0, 2, "品种", titleFormat));	
		wsheet.addCell(new Label(1, 2, "单位", titleFormat));	
		wsheet.addCell(new Label(2, 2, "仓单总量（公斤）", titleFormat));
		wsheet.addCell(new Label(3, 2, "入仓用户（个）", titleFormat));	
		wsheet.addCell(new Label(4, 2, "仓单笔数（笔）", titleFormat));
		wsheet.addCell(new Label(5, 2, "挂牌总量（公斤）", titleFormat));
		wsheet.addCell(new Label(6, 2, "挂牌用户（个）", titleFormat));
		wsheet.addCell(new Label(7, 2, "挂牌笔数（笔）", titleFormat));
		wsheet.addCell(new Label(8, 2, "交易总量（公斤）", titleFormat));
		wsheet.addCell(new Label(9, 2, "交易总金额（元）", titleFormat));
		wsheet.addCell(new Label(10, 2, "交易用户（个）", titleFormat));	
		wsheet.addCell(new Label(11, 2, "交易笔数（笔）", titleFormat));	
		return wsheet;
	}
}
