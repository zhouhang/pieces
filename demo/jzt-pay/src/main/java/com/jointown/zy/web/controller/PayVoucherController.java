package com.jointown.zy.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.PayVoucherService;
import com.jointown.zy.common.util.IPUtil;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 线下支付
 * @ClassName:PayVoucherController
 * @author:Calvin.Wangh
 * @Description:
 * @date:2015-5-18上午10:04:24
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/payVoucherController")
public class PayVoucherController extends UserBaseController {
	
	private static final Logger log = LoggerFactory.getLogger(PayVoucherController.class);
	@Autowired
	private PayVoucherService payVoucherService;
	
	private static final String IS_SEND_SMS = "1";
	
	/**
	 * 汇款凭证上传，并生成支付流水
	 * @Author: Calvin.Wangh
	 * @Date: 2015年5月14日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/payVoucherUpload",method={RequestMethod.GET, RequestMethod.POST})
	public String payVoucherUpload(PayReqDto payReqDto,HttpServletRequest request){
		if(ServletFileUpload.isMultipartContent(request)){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("file");
			if(null!=file && file.getSize()>0){
				log.error("[线下支付前台]上传回单开始");
				try {
					String clientIp = IPUtil.getIP(request);
					payVoucherService.payVoucherUpload(file.getInputStream(),payReqDto,clientIp);
				} catch (IOException e) {
					e.printStackTrace();
				}
				log.error("[线下支付前台]上传回单成功");
			}else{
				log.error("[线下支付前台]上传回单为空！");
			}
		}
		return "redirect:http://uc.54315.com/order/listinfo";
	}
	
	/**
	 * 银行汇款账号,短信发送
	 * @Author: Calvin.Wangh
	 * @Date: 2015年5月14日
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/smsSend",method={RequestMethod.GET, RequestMethod.POST})
	public Map<String,Object> smsSend(HttpServletRequest request){
		String smsFlag = request.getParameter("smsFlag");
		String bankCode = request.getParameter("bankCode");
		String orderId = request.getParameter("orderId");
		Map<String,Object> result = new HashMap<String,Object>();
		
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		if(null!=ucUserVo){
			if(StringUtils.isNotBlank(orderId)){
				if(IS_SEND_SMS.equals(smsFlag)){
					boolean msgState = payVoucherService.smsAndEmailSend(ucUserVo,orderId,bankCode);
					result.put("state", msgState);
					result.put("retMsg", "短信发送成功！");
				}
			}else{
					result.put("state", false);
					result.put("retMsg", "交易订单号不能为空！");
			}
		}else{
			result.put("state", false);
			result.put("retMsg", "用户未登陆!");
		}
		return result;
	}
}
