package com.jointown.zy.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
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

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.PayFlowListDto;
import com.jointown.zy.common.enums.AmtTypeEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.PayFlowListVo;

/**
 * 资金管理
 * 
 * @author Calvin.Wang
 * 
 */
@Controller(value="payManagerController")
@RequestMapping("/payManager")
public class PayManagerController {

	private final static Logger logger = LoggerFactory.getLogger(PayManagerController.class);
	
	@Autowired
	private PayOrderService payOrderService;

	/**
	 * 资金流水列表
	 * 
	 * @param model
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET,RequestMethod.POST })
	public String payFlowList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute PayFlowListDto payFlowListDto, ModelMap model) {
		//added by biran 20150518 第一次查询默认为“支付完成”的流水
		String golableMenuPath=request.getParameter("golableMenuPath");
		if(golableMenuPath!=null&&!golableMenuPath.equals("")){
			payFlowListDto.setPayStatus("1");
		}
		//add end
		Page<PayFlowListVo> page = new Page<PayFlowListVo>();
		page.setPageNo(payFlowListDto.getPageNo());
		// 搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("payFlowListDto", payFlowListDto);
		page.setParams(params);
		
		//支付状态列表
		Map<String,String> PayStatusEnumMap = PayStatusEnum.getListQueryMap();
		//获取支付渠道列表
		List payChannelList = BankConfigConstant.PayChannel.showList(BankConfigConstant.class.getName(),"PayChannel", null);
		//金额款项
		Map<String,String> amtTypeMap = AmtTypeEnum.toMap();
		
		// 资产流水列表
		List<PayFlowListVo> results = payOrderService.getPageList(page);
		this.calculateMoney(results);
		
		page.setResults(results);
		
		model.addAttribute("page", page);
		model.addAttribute("PayStatusEnumMap", PayStatusEnumMap);
		model.addAttribute("payChannelList", payChannelList);
		model.addAttribute("amtTypeMap", amtTypeMap);
		
		return "/public/payflowlist";
	}
	
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportPayFlow", method = {RequestMethod.GET,RequestMethod.POST })
	public void exportPayFlow(PayFlowListDto payFlowListDto,HttpServletRequest request,HttpServletResponse response) {
		List<PayFlowListVo> results = payOrderService.getPayFlowList(payFlowListDto);
		this.calculateMoney(results);
		if(CollectionUtils.isNotEmpty(results)){
			this.exportExcel(response,results);
		}else{
			this.exportExcel(response, new ArrayList<PayFlowListVo>());
		}
	}
	
	public HttpServletResponse exportExcel(HttpServletResponse response,List<PayFlowListVo> results) {
		try {
			
			ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("ZJLS"));
			
			OutputStream os = response.getOutputStream();
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			
			WritableCellFormat wcfTitle = ExcelUtil.getTitleFormat();
			WritableCellFormat wcfContentCenter = ExcelUtil.getContentFormat();
			WritableCellFormat wcfContentRightTwo = ExcelUtil.getNumberFormat();
			
			WritableSheet wsheet = wbook.createSheet("支付流水", 0); // sheet名称
			//设置单元格宽度
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 25);
			wsheet.setColumnView(2, 25);
			wsheet.setColumnView(3, 25);
			wsheet.setColumnView(4, 25);
			wsheet.setColumnView(5, 20);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 20);
			wsheet.setColumnView(8, 30);
			wsheet.setColumnView(9, 30);
			wsheet.setColumnView(10, 30);
			wsheet.setColumnView(11, 20);
			wsheet.setColumnView(12, 20);
			wsheet.mergeCells(0, 0, 13, 1);
			wsheet.addCell(new Label(0,0,"支付流水对账报表",wcfTitle));
			
			// 设置单元格标题内容
			wsheet.addCell(new Label(0, 2, "编号", wcfTitle));	
			wsheet.addCell(new Label(1, 2, "收款人账号", wcfTitle));
			wsheet.addCell(new Label(2, 2, "收款人名称", wcfTitle));
			wsheet.addCell(new Label(3, 2, "付款人账号", wcfTitle));
			wsheet.addCell(new Label(4, 2, "付款人名称", wcfTitle));
			wsheet.addCell(new Label(5, 2, "金额", wcfTitle));
			wsheet.addCell(new Label(6, 2, "手续费", wcfTitle));
			wsheet.addCell(new Label(7, 2, "应收金额", wcfTitle));
			wsheet.addCell(new Label(8, 2, "支付时间", wcfTitle));
			wsheet.addCell(new Label(9, 2, "类型", wcfTitle));
			wsheet.addCell(new Label(10, 2, "订单号", wcfTitle));
			wsheet.addCell(new Label(11, 2, "支付流水号", wcfTitle));
			wsheet.addCell(new Label(12, 2, "支付状态", wcfTitle));
			wsheet.addCell(new Label(13, 2, "支付渠道", wcfTitle));
			int row = 3;//行
			int cell;//列
			// 生成报表内容
			if(results.size()<0)
				return null;
			
			for(PayFlowListVo pv : results){
				cell = 0;
				wsheet.addCell(new Label(cell++, row, row-2+ "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayeeAccount()) ? "" :
					pv.getPayeeAccount() + "", wcfContentCenter));//收款人账号
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayeeName()) ? "" :
					pv.getPayeeName() + "", wcfContentCenter));//收款人名称
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayerAccount()) ? "" :
					pv.getPayerAccount() + "", wcfContentCenter));//付款人账号
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayerName()) ? "" :
					pv.getPayerName() + "", wcfContentCenter));//付款人名称
				//金额
				//wsheet.addCell(new Label(cell++, row, pv.getAmount() == null ? "" : (StringUtils.isBlank(pv.getAmount().toString()) ? "":
				//(pv.getAmount().doubleValue()==0?"0.00":BigDecimalUtil.formateBigDecimal(pv.getAmount()))) + "", wcfContentCenter));
				wsheet.addCell(new Number(cell++, row, pv.getAmount()==null ? new Double(0): pv.getAmount().doubleValue(),wcfContentRightTwo));
				//手续费
				wsheet.addCell(new Number(cell++, row, pv.getHandlingFee()==null ? new Double(0): pv.getHandlingFee().doubleValue(),wcfContentRightTwo));
				//应收金额
				wsheet.addCell(new Number(cell++, row, pv.getReceivable()==null ? new Double(0): pv.getReceivable().doubleValue(),wcfContentRightTwo));
				
				wsheet.addCell(new Label(cell++, row, pv.getPayDate()==null ? "" : (StringUtils.isBlank(pv.getPayDate().toString()) ? "" :
					TimeUtil.getYMDHMS(pv.getPayDate())) + "", wcfContentCenter));//支付时间
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayType()) ? "" : BankConfigConstant.getStatusDesc(BankConfigConstant.StatusDesc.PAY_TYPE, pv.getPayType()) + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getOrderId()) ? "" : pv.getOrderId() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayFlowId()) ? "" : pv.getPayFlowId() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayStatus()) ? "" : BankConfigConstant.getStatusDesc(BankConfigConstant.StatusDesc.PAY_STATUS, pv.getPayStatus()) + "", wcfContentCenter));
				//支付频道
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(String.valueOf(pv.getPayChannel())) ? "" : BankConfigConstant.PayChannel.getName(pv.getPayChannel()) + "", wcfContentCenter));
				
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
	
	
	/**
	 * 计算应收金额
	 * 购买金额 - 手续费 =  应收金额
	 * @param results
	 * @return
	 */
	public List<PayFlowListVo> calculateMoney(List<PayFlowListVo> results){
		for(PayFlowListVo pv : results){
			if(pv.getHandlingFee()==null || BigDecimal.ZERO.equals(pv.getHandlingFee())){
				pv.setReceivable(pv.getAmount());
			}else{
				pv.setReceivable(BigDecimalUtil.subtract(pv.getAmount(), pv.getHandlingFee()));
			}
		}
		return results;
	}
}
