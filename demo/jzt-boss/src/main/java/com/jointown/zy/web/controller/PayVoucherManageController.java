package com.jointown.zy.web.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.PayFlowListDto;
import com.jointown.zy.common.dto.PayVoucherAddDto;
import com.jointown.zy.common.enums.AmtTypeEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.pay.PayVoucherUpload;
import com.jointown.zy.common.service.PayOrderService;
import com.jointown.zy.common.service.PayVoucherService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.ExcelUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.PayFlowListVo;

/**
 * @ClassName: PayVoucherManageController
 * @Description: 线下支付（银行汇款支付凭证）管理
 * @Author: ldp
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
@Controller(value="payVoucherManageController")
@RequestMapping(value="/payVoucherManage")
public class PayVoucherManageController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(PayVoucherManageController.class);
	
	@Autowired
	private PayVoucherService payVoucherService;
	@Autowired
	private PayVoucherUpload payVoucherUpload;
	@Autowired
	private PayOrderService payOrderService;
	/**
	 * 支付凭证列表管理
	 * @Author: ldp
	 * @Date: 2015年5月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="",method={RequestMethod.GET,RequestMethod.POST})
	public String getPayVoucherManage(@ModelAttribute PayFlowListDto payFlowListDto,HttpServletRequest request,ModelMap modelMap){
		payFlowListDto.setPayChannel(String.valueOf(3));
		Page<PayFlowListVo> page = new Page<PayFlowListVo>();
		page.setPageNo(payFlowListDto.getPageNo());
		// 搜索条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("payFlowListDto", payFlowListDto);
		page.setParams(params);
		
		// 资产流水列表
		List<PayFlowListVo> results = payOrderService.getPageList(page);
		page.setResults(results);
		modelMap.addAttribute("page", page);
		//支付状态
		Map<String,String> PayStatusEnumMap = PayStatusEnum.toMap();
		modelMap.addAttribute("PayStatusEnumMap", PayStatusEnumMap);
		//款项类型
		Map<String, String> amtTypeMap = AmtTypeEnum.toMap();
		modelMap.addAttribute("amtTypeMap", amtTypeMap);
		return "public/payVoucherManage";
	}
	
	/**
	 * 跳转到支付凭证新加页面
	 * @Author: ldp
	 * @Date: 2015年5月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forwardAddPayVoucher")
	public String forwardAddPayVoucher(HttpServletRequest request,ModelMap modelMap){
		Map<String, String> amtTypeMap = AmtTypeEnum.toMap();
		modelMap.addAttribute("AmtTypes", amtTypeMap);
		return "addPayVoucher";
	}
	
	/**
	 * 根据交易订单号和金额款项获取交易订单信息
	 * @Author: ldp
	 * @Date: 2015年5月18日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOrderInfo")
	@ResponseBody
	public MessageVo getTradeByOrderIdAndAmtType(HttpServletRequest request){
		MessageVo mVo = new MessageVo();
		String amtType = request.getParameter("amtType");
		String orderId = request.getParameter("orderId");
		if (StringUtils.isBlank(amtType)) {
			mVo.addError("error01","款项类型不能为空!");
			mVo.setOk(false);
			return mVo;
		}
		if (StringUtils.isBlank(orderId)) {
			mVo.setOk(false);
			mVo.addError("error02","交易订单号不能为空");
			return mVo;
		}
		PayVoucherAddDto payVoucherAddDto = payVoucherService.getBusiOrderInfo(orderId, amtType);
		if (null == payVoucherAddDto) {
			mVo.addError("error03", "交易订单不存在,请重新输入交易订单号!");
			mVo.setOk(false);
			return mVo;
		}
		mVo.setObj(payVoucherAddDto);
		mVo.setOk(true);
		return mVo;
	}
	
	/**
	 * 添加支付记录前，校验交易订单状态
	 * @Author: ldp
	 * @Date: 2015年5月22日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/verifyOrder")
	@ResponseBody
	public MessageVo verifyOrder(HttpServletRequest request){
		MessageVo mVo = new MessageVo();
		String orderId = request.getParameter("orderId");
		String amtType = request.getParameter("amtType");
		try {
			mVo = payVoucherService.verifyOrder(orderId, amtType);
		} catch (Exception e) {
			mVo.setOk(false);
			mVo.addError("error03", e.getMessage());
			return mVo;
		}
		return mVo;
	}
	
	/**
	 * boss后台添加支付凭证
	 * @Author: ldp
	 * @Date: 2015年5月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addPayVoucher")
	public String addPayVoucher(@ModelAttribute() PayVoucherAddDto payVoucherAddDto,@RequestParam("file") MultipartFile file,HttpServletRequest request){
		log.info("payVoucherAddDto is :" + payVoucherAddDto);
		String payVoucher = null;
		try {
			if (null != file && file.getSize() > 0) {
				payVoucher = payVoucherUpload.uploadPic(file.getInputStream());
			}
			
			payVoucherAddDto.setPayVoucher(payVoucher);
			//线下支付渠道3
			payVoucherAddDto.setPayChannel(String.valueOf(3));
			payVoucherService.payVoucherAdd(payVoucherAddDto);
		} catch (IOException e) {
			log.error("IOException msg is:",e.getMessage());;
		} catch (Exception e) {
			log.error("Exception msg is:",e.getMessage());;
		}
		return "redirect:/payVoucherManage";
	}
	
	/**
	 * 根据付款凭证，确认付款
	 * @Author: ldp
	 * @Date: 2015年5月15日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkPayVoucher")
	@ResponseBody
	public MessageVo checkPayVoucher(HttpServletRequest request){
		String flowId = request.getParameter("flowId");
		String payStatus = request.getParameter("payStatus");
		String payDate = request.getParameter("payDate");
		MessageVo mvo = new MessageVo();
		if (StringUtils.isBlank(flowId)) {
			mvo.setOk(false);
			mvo.addError("error01","flowId is not null");
			return mvo;
		}
		if (StringUtils.isBlank(payStatus)) {
			mvo.setOk(false);
			mvo.addError("error02","payStatus is not null");
			return mvo;
		}
		if (StringUtils.isBlank(payDate)) {
			mvo.setOk(false);
			mvo.addError("error03","payDate is not null");
			return mvo;
		}
		try {
			int flag = payVoucherService.payConfirm(flowId,payDate,payStatus);
			if (flag > 0) {
				mvo.setOk(true);
			}else {
				mvo.setOk(false);
			}
		} catch (Exception e) {
			log.error("error msg is:",e.getMessage());
			mvo.addError("error04", e.getMessage());
			mvo.setOk(false);
			return mvo;
		}
		return mvo;
	}
	
	/**
	 * 根据支付流水号获取,支付流水信息
	 * @Author: ldp
	 * @Date: 2015年5月20日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFlowInfo")
	public String getPayFlowByFlowId(HttpServletRequest request,ModelMap modelMap){
		String flowId = request.getParameter("flowId");
		PayFlowListVo payFlowListVo = null;
		try {
			payFlowListVo = payVoucherService.getPayFlowVoByFlowId(flowId);
		} catch (Exception e) {
			log.error("method[getPayFlowByFlowId] error is:",e.getMessage());
		}
		modelMap.addAttribute("payFlowListVo", payFlowListVo);
		//款项类型
		Map<String, String> amtTypeMap = AmtTypeEnum.toMap();
		modelMap.addAttribute("amtTypeMap", amtTypeMap);
		return "checkPayVoucher";
	}
	
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportPayVoucher", method = {RequestMethod.GET,RequestMethod.POST })
	public void exportPayFlow(@ModelAttribute PayFlowListDto payFlowListDto,HttpServletRequest request,HttpServletResponse response) {
		payFlowListDto.setPayChannel(String.valueOf(BankConfigConstant.PayChannel.BANK_TRANSFER));
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
			
			ExcelUtil.setResponseProperties(response, ExcelUtil.createExcelName("XXZF"));
			
			OutputStream os = response.getOutputStream();
			WritableWorkbook  wbook = Workbook.createWorkbook(os);
			
			WritableCellFormat wcfTitle = ExcelUtil.getTitleFormat();
			WritableCellFormat wcfContentCenter = ExcelUtil.getContentFormat();
			WritableCellFormat wcfContentRightTwo = ExcelUtil.getNumberFormat();
			
			WritableSheet wsheet = wbook.createSheet("线下支付流水", 0); // sheet名称
			//设置单元格宽度
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 25);
			wsheet.setColumnView(2, 25);
			wsheet.setColumnView(3, 25);
			wsheet.setColumnView(4, 25);
			wsheet.setColumnView(5, 15);
			wsheet.setColumnView(6, 25);
			wsheet.setColumnView(7, 15);
			wsheet.setColumnView(8, 15);
			wsheet.setColumnView(9, 25);
			wsheet.setColumnView(10, 25);
			wsheet.setColumnView(11, 15);
			wsheet.setColumnView(12, 15);
			wsheet.setColumnView(13, 15);
			wsheet.setColumnView(14, 30);
			wsheet.mergeCells(0, 0, 14, 1);
			wsheet.addCell(new Label(0,0,"线下支付流水对账报表",wcfTitle));
			
			// 设置单元格标题内容
			wsheet.addCell(new Label(0, 2, "编号", wcfTitle));	
			wsheet.addCell(new Label(1, 2, "收款人账号", wcfTitle));
			wsheet.addCell(new Label(2, 2, "收款人名称", wcfTitle));
			wsheet.addCell(new Label(3, 2, "付款人账号", wcfTitle));
			wsheet.addCell(new Label(4, 2, "付款人名称", wcfTitle));
			wsheet.addCell(new Label(5, 2, "金额", wcfTitle));
			wsheet.addCell(new Label(6, 2, "创建时间", wcfTitle));
			wsheet.addCell(new Label(7, 2, "支付时间", wcfTitle));
			wsheet.addCell(new Label(8, 2, "类型", wcfTitle));
			wsheet.addCell(new Label(9, 2, "订单号", wcfTitle));
			wsheet.addCell(new Label(10, 2, "支付流水号", wcfTitle));
			wsheet.addCell(new Label(11, 2, "支付状态", wcfTitle));
			
			wsheet.addCell(new Label(12, 2, "支付渠道", wcfTitle));
			wsheet.addCell(new Label(13, 2, "操作人", wcfTitle));
			wsheet.addCell(new Label(14, 2, "操作时间", wcfTitle));
//			wsheet.addCell(new Label(13, 2, "付款回单", wcfTitle));
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
				/*wsheet.addCell(new Number(cell++, row, pv.getHandlingFee()==null ? new Double(0): pv.getHandlingFee().doubleValue(),wcfContentRightTwo));
				//应收金额
				wsheet.addCell(new Number(cell++, row, pv.getReceivable()==null ? new Double(0): pv.getReceivable().doubleValue(),wcfContentRightTwo));*/
				//创建时间
				wsheet.addCell(new Label(cell++, row, pv.getCreateTime() == null ? "" : (StringUtils.isBlank(pv.getCreateTime().toString()) ? "" :
					TimeUtil.getYMDHMS(pv.getCreateTime())) + "", wcfContentCenter));//支付时间
				wsheet.addCell(new Label(cell++, row, pv.getPayDate()==null ? "" : (StringUtils.isBlank(pv.getPayDate().toString()) ? "" :
					TimeUtil.getYMD(pv.getPayDate())) + "", wcfContentCenter));//支付时间
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayType()) ? "" : BankConfigConstant.getStatusDesc(BankConfigConstant.StatusDesc.PAY_TYPE, pv.getPayType()) + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getOrderId()) ? "" : pv.getOrderId() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayFlowId()) ? "" : pv.getPayFlowId() + "", wcfContentCenter));
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getPayStatus()) ? "" : BankConfigConstant.getStatusDesc(BankConfigConstant.StatusDesc.PAY_STATUS, pv.getPayStatus()) + "", wcfContentCenter));
				//支付渠道(线下支付)
				wsheet.addCell(new Label(cell++, row, "线下支付",wcfContentCenter));
				//操作人
				wsheet.addCell(new Label(cell++, row, StringUtils.isBlank(pv.getConfirmor()) ? "":pv.getConfirmor() + "" ,wcfContentCenter));
				//操作时间
				wsheet.addCell(new Label(cell++, row, pv.getConfirmTime() == null ? "":TimeUtil.getYMDHMS(pv.getConfirmTime()) + "",wcfContentCenter));
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
