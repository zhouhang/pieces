package com.jointown.zy.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.RemitDto;
import com.jointown.zy.common.enums.RefundRemitStatusEnum;
import com.jointown.zy.common.enums.RemitStatusEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.pay.PayVoucherUpload;
import com.jointown.zy.common.service.RemitMangeService;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.RemitVo;

/**
 * @ClassName: RemitManageController
 * @Description: 划账(交易订单分润，退款)流水管理Controller
 * @Author: ldp
 * @Date: 2015年6月30日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/getRemitManage")
public class RemitManageController {
	
	private static final Logger logger = LoggerFactory.getLogger(RemitManageController.class);
	
	@Autowired
	public RemitMangeService remitMangeService;
	@Autowired
	private PayVoucherUpload payVoucherUpload;
	 
	@RequestMapping(value="")
	public String searchRemitFlow(@ModelAttribute RemitDto remitDto ,HttpServletRequest request,ModelMap modelMap){
		logger.info("remitDto is:" + remitDto.toString());
		remitDto.setRemitListType(RefundRemitStatusEnum.RemitType.REMIT_TYPE);
		Page<RemitVo> page = new Page<RemitVo>();
		page.setPageNo(remitDto.getPageNo());//设置页码
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("remitDto", remitDto);
		page.setParams(params);
		try {
			List<RemitVo> remitVoLists = remitMangeService.searchRemitFlow(page);
			page.setResults(remitVoLists);
		} catch (Exception e) {
			logger.error("RemitManageController searchRemitFlow error is:",e);
		}
		
		@SuppressWarnings("rawtypes")
		List payChannelList = BankConfigConstant.PayChannel.showList(BankConfigConstant.class.getName(),"PayChannel", null);
		//划账状态
		modelMap.addAttribute("RemitStatusEnumMap", RemitStatusEnum.toMap());
		//支付渠道
		modelMap.addAttribute("payChannelList", payChannelList);
		modelMap.addAttribute("page", page);
		return "remitManagerList";
	}
	
	/**
	 * 
	 * @param flowId
	 * @param type 根据type返回值做页面编辑/显示控制
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewRemitInfo")
	public String viewRefundInfo(@RequestParam String flowId,@RequestParam int type,Model model){
		if(StringUtils.isNotBlank(flowId)){
			RemitFlow remitFlow = remitMangeService.getRemitFlowInfo(flowId);
			//获取支付渠道列表
			List payChannelList = BankConfigConstant.PayChannel.showList(BankConfigConstant.class.getName(),"PayChannel", null);
			//划账状态
			Map<String,String> remitStatusEnumMap =  RemitStatusEnum.toMap();
			model.addAttribute("remitFlow", remitFlow);
			model.addAttribute("payChannelList", payChannelList);
			model.addAttribute("remitStatusEnumMap", remitStatusEnumMap);
			model.addAttribute("type", type);
		}
		return "/public/remitViewInfo";
	}
	
	/**
	 * 处理成功/拒绝业务 返回json格式数据
	 * 0:操作失败   1:操作成功  msg:错误消息 
	 * @param remitFlow
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doRemit",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, String>  doRemit(RemitFlow remitFlow,HttpServletRequest request){
		int type = Integer.valueOf(request.getParameter("type"));
		String remitTime = request.getParameter("rTime");
		String result = "";
		try {
			if(StringUtils.isNotBlank(remitTime)){
				remitFlow.setRemitTime(TimeUtil.parseYMDHM(remitTime));
			}
			result = remitMangeService.processRemit(remitFlow,type);
		} catch (Exception e) {
			logger.error("RemitManageController doRefund error:"+e);
		}
		return BeanUtil.jsonToMap(result);
	}
	
	/**
	 * 异步文件上传
	 * @param fileId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/uploadFile",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String uploadFile(@RequestParam String fileId,Model model,HttpServletRequest request){
		Map<String,Object> fileMap = new HashMap<String,Object>();
		if(ServletFileUpload.isMultipartContent(request)){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			try {
				if("file1".equals(fileId)){
					MultipartFile sellerVoucher = multipartRequest.getFile("file1");
					String sellerVoucherUrl = payVoucherUpload.uploadPic(sellerVoucher.getInputStream());
					fileMap.put("sellerVoucherUrl", sellerVoucherUrl);
					fileMap.put("fileStatus", true);
				}else if("file2".equals(fileId)){
					MultipartFile buyerVoucher = multipartRequest.getFile("file2");
					String buyerVoucherUrl = payVoucherUpload.uploadPic(buyerVoucher.getInputStream());
					fileMap.put("buyerVoucherUrl", buyerVoucherUrl);
					fileMap.put("fileStatus", true);
				}
			} catch (IOException e) {
				logger.error("RemitManageController uploadFile error:"+e);
				fileMap.put("fileStatus", false);
			}
		}else{
			logger.error("RemitManageController uploadFile error:file type error");
			fileMap.put("fileStatus", false);
		}
		return BeanUtil.mapToJson(fileMap);
	}
	
	
	
	@RequestMapping(value = "/exportRemitFlow", method = {RequestMethod.GET,RequestMethod.POST })
	public void exportRemitFlow(RemitDto remitDto,HttpServletRequest request,HttpServletResponse response){
		List<RemitFlow>  results = remitMangeService.getRemitFlowList(remitDto);
		if(CollectionUtils.isNotEmpty(results)){
			this.exportExcel(response,results);
		}else{
			this.exportExcel(response, new ArrayList<RemitFlow>());
		}
	}
	
	
	public HttpServletResponse exportExcel(HttpServletResponse response,List<RemitFlow> results) {
		try {
			String fileName="FRLS_"+TimeUtil.formatDatetime(new Date(), "yyyyMMddHHmmss") + ".xls";
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="+ fileName);
			response.setContentType("application/vnd.ms-excel");
			OutputStream os = response.getOutputStream();
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			
			// 标题字体及样式--居中
			WritableFont writableFont = new WritableFont(WritableFont.createFont("宋体"), 10, 
					WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wcfTitle = new WritableCellFormat(writableFont);
			
			wcfTitle.setAlignment(Alignment.CENTRE);//水平居中
			wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直居中
			wcfTitle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
			wcfTitle.setWrap(true);//自动换行
			
			// 内容字体及样式--居中
			WritableFont wfContentCenter = new WritableFont(WritableFont.createFont("宋体"), 10, 
					WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wcfContentCenter = new WritableCellFormat(wfContentCenter);
			wcfContentCenter.setAlignment(Alignment.CENTRE);
			wcfContentCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfContentCenter.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
			wcfContentCenter.setWrap(true);
			
			// 格式化数字
			WritableFont wfFont = new WritableFont(WritableFont.createFont("宋体"), 10, 
					WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			NumberFormat numberFormatTwo = new NumberFormat("0.00");
			WritableCellFormat wcfContentRightTwo = new WritableCellFormat(wfFont, numberFormatTwo);
			wcfContentRightTwo.setAlignment(Alignment.RIGHT);
			wcfContentRightTwo.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfContentRightTwo.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
			wcfContentRightTwo.setWrap(true);
			
			WritableSheet wsheet = wbook.createSheet("货款分润流水", 0); // sheet名称
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
			wsheet.mergeCells(0, 0, 9, 1);
			wsheet.addCell(new Label(0,0,"货款分润流水对账报表",wcfTitle));
			
			// 设置单元格标题内容
			wsheet.addCell(new Label(0, 2, "编号", wcfTitle));
			wsheet.addCell(new Label(1, 2, "流水号", wcfTitle));	
			wsheet.addCell(new Label(2, 2, "订单编号", wcfTitle));
			wsheet.addCell(new Label(3, 2, "分润金额", wcfTitle));
			wsheet.addCell(new Label(4, 2, "买方金额", wcfTitle));
			wsheet.addCell(new Label(5, 2, "卖方金额", wcfTitle));
			wsheet.addCell(new Label(6, 2, "平台金额", wcfTitle));
			wsheet.addCell(new Label(7, 2, "分润完成时间", wcfTitle));
			wsheet.addCell(new Label(8, 2, "状态", wcfTitle));
			wsheet.addCell(new Label(9, 2, "支付渠道", wcfTitle));
			int row = 3;//行
			int cell;//列
			// 生成报表内容
			if(results.size()<0)
				return null;
			
			for(RemitFlow rf : results){
				cell = 0;
				wsheet.addCell(new Label(cell++, row, row-2+ "", wcfContentCenter));
				//流水号
				wsheet.addCell(new Label(cell++, row, rf.getFlowId() == null ? "" : rf.getFlowId() + "", wcfContentCenter));
				//订单编号
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(rf.getOrderId()) ? "" : rf.getOrderId() + "", wcfContentCenter));
				//保证金金额
				wsheet.addCell(new Number(cell++, row, rf.getTotalAmount()==null ? new Double(0) : rf.getTotalAmount().doubleValue(), wcfContentRightTwo));
				//买方金额
				wsheet.addCell(new Number(cell++, row, rf.getBuyerAmount()==null ? new Double(0) : rf.getBuyerAmount().doubleValue(),wcfContentRightTwo));
				//卖方金额
				wsheet.addCell(new Number(cell++, row, rf.getSellerAmount()==null ? new Double(0) : rf.getSellerAmount().doubleValue(),wcfContentRightTwo));
				//平台金额
				wsheet.addCell(new Number(cell++, row, rf.getPlatformAmount()==null ? new Double(0) : rf.getPlatformAmount().doubleValue(),wcfContentRightTwo));
				//退款完成时间
				wsheet.addCell(new Label(cell++, row, rf.getRemitTime()==null ? "" : (StringUtils.isBlank(rf.getRemitTime().toString()) ? "" :
					TimeUtil.getYMDHMS(rf.getRemitTime())) + "", wcfContentCenter));
				//退款状态
				wsheet.addCell(new Label(cell++, row, rf.getStatus() == null ? "" : BankConfigConstant.getStatusDesc(BankConfigConstant.StatusDesc.REMIT_ACCOUNT_STATUS, rf.getStatus() + ""), wcfContentCenter));
				//支付渠道
				wsheet.addCell(new Label(cell++, row, rf.getRemitChannel() == null ? "" : BankConfigConstant.PayChannel.getName(rf.getRemitChannel()) + "", wcfContentCenter));
				
				row++;
			}
			wbook.write(); // 写入文件
			wbook.close();
			os.close();
			response.flushBuffer();
		} catch (Exception ex) {
			logger.error("RemitManageController exportExcel:", ex);
		}
		return response;
	}
}
