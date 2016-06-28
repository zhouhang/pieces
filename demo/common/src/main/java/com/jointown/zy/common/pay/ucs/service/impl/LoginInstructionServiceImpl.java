package com.jointown.zy.common.pay.ucs.service.impl;

import java.nio.charset.Charset;

import org.springframework.stereotype.Service;

import ucs.creditpay.entities.requests.Request1007;
import ucs.creditpay.security.SignatureFactory;
import ucs.creditpay.utils.Base64Utils;
import ucs.creditpay.utils.GsonUtils;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.pay.ucs.service.UcsInstructionService;
import com.jointown.zy.common.pay.ucs.util.UcsBase;
@Service
public class LoginInstructionServiceImpl extends UcsBase implements UcsInstructionService {

	@Override
	public String payLogin(Request1007 loginRequest) throws Exception {
		String jsonResult = GsonUtils.toJson(loginRequest);
		String data = Base64Utils.encode(jsonResult.getBytes(Charset
				.forName("UTF-8")));
		String signdata = SignatureFactory.getSigner().signature(
				jsonResult);
		String html = createHtml(BankConfigConstant.UCS_PAYLOGIN_ACTION,data,signdata);
		return html;
	}
	
	/**
	 * 构造HTTP POST交易表单
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(String action, String data,String signdata) {
		StringBuffer sf = new StringBuffer();
		sf.append("<form name = \"form1\" action=\"" + action
				+ "\" method=\"post\">\n");
		sf.append("<input type=\"hidden\" name=\"data\" value=\"" + data + "\"/>\n");
		sf.append("<input type=\"hidden\" name=\"signdata\" value=\"" + signdata + "\"/>\n");
		sf.append("</form>\n");
		return sf.toString();
	}

}
