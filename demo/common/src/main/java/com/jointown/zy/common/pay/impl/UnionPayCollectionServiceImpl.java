package com.jointown.zy.common.pay.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jointown.zy.common.pay.UnionPayBase;
import com.unionpay.acp.sdk.SDKConfig;

public class UnionPayCollectionServiceImpl extends UnionPayBase {
	public static void main(String[] args) {

		String merId = "802290049000180";// --商户代码
		String txnTime = "20140820145625";// --订单发送时间
		String orderId = "201408201508395217";// --商户订单号
		/**
		 * 初始化证书
		 */
	/**
		 * 参数初始化
		 * 在java main 方式运行时必须每次都执行加载
		 * 如果是在web应用开发里,这个方写在可使用监听的方式写入缓存,无须在这出现
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
		/**
		 * 交易请求url 从配置文件读取
		 */
		 String requestFrontUrl = SDKConfig.getConfig()
				.getFrontRequestUrl();
		/**
		 * 组装请求报文
		 */
		Map<String, Object> contentData = new HashMap<String, Object>();
		// 固定填写
		contentData.put("version", "5.0.0");// M

		// 默认取值：UTF-8
		contentData.put("encoding", "UTF-8");// M
		//
		// //通过MPI插件获取
		// contentData.put("certId", certId);//M
		//
		// //填写对报文摘要的签名
		// contentData.put("signature", signature);//M

		// 01：RSA02： MD5 (暂不支持)
		contentData.put("signMethod", "01");// M

		// 取值:72
		contentData.put("txnType", "72");// M

		// 02：免验建立绑定关系
		contentData.put("txnSubType", "02");// M

		contentData.put("bizType", "00");// M

		contentData.put("channelType", "07");// M

		// 前台交易需填写
		contentData.put("frontUrl", "");// C

		// 后台返回商户结果时使用，如上送，则发送商户后台交易结果通知
		contentData.put("backUrl", "");// C

		// 0：普通商户直连接入2：平台类商户接入
		contentData.put("accessType", "0");// M

		//商户代码 　
		contentData.put("merId", merId);// M

		// //商户类型为平台类商户接入时必须上送
		// contentData.put("subMerId", subMerId);//C
		//
		// //商户类型为平台类商户接入时必须上送
		// contentData.put("subMerName", subMerName);//C
		//
		// //商户类型为平台类商户接入时必须上送
		// contentData.put("subMerAbbr", subMerAbbr);//C

		// 　
		contentData.put("orderId", orderId);// M

		// 　
		contentData.put("txnTime", txnTime);// M

		// //　
		// contentData.put("accType", accType);//O
		//
		// //对于前台类交易，返回卡号后4位，后台类交易，原样返回
		// contentData.put("accNo", accNo);//O
		//
		// //　
		// contentData.put("customerInfo", Common.getCustomer(encoding));//O
		//
		// //商户自定义保留域，交易应答时会原样返回
		// contentData.put("reqReserved", reqReserved);//O
		//
		// //格式如下：{子域名1=值&子域名2=值&子域名3=值} 移动支付参考消费绑定关系信息 {bindInfo=XXXXX} 特殊商户上送
		// contentData.put("reserved", reserved);//O
		//
		// //格式如下：{子域名1=值&子域名2=值&子域名3=值}有风险级别要求的商户必填 风险级别 {riskLevel=XX}
		// contentData.put("riskRateInfo", riskRateInfo);//O
		//
		// //当使用银联公钥加密密码等信息时，需上送加密证书的CertID
		// contentData.put("encryptCertId", encryptCertId);//C
		//
		// //移动支付业务需要上送
		// contentData.put("userMac", userMac);//O
		//
		// //需做建立绑定关系交易时填写
		// contentData.put("bindId", bindId);//C
		//
		// //用于填写关联业务类型01：消费02：代收
		// contentData.put("relTxnType", relTxnType);//C
		//
		// //　
		// contentData.put("payCardType", payCardType);//O
		//
		// //　
		// contentData.put("issInsCode", issInsCode);//O
		//
		// //渠道类型为语音支付时使用用法见VPC交易信息组合域子域用法
		// contentData.put("vpcTransData", vpcTransData);//C

		Map<String, String> submitFromData = signData(contentData);
		String html = createHtml(requestFrontUrl,submitFromData);

		Map<String, String> resMap = submitUrl(submitFromData,requestFrontUrl);

		System.out.println(html);
		/**
		 * 
		 * 创建表单
		 */

		System.out.println(resMap.toString());

	}

	/**
	 * 构造HTTP POST交易表单的方法示例
	 * 
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(String action, Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body>");
		sf.append("<form id = \"pay_form\" action=\"" + action
				+ "\" method=\"post\">");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>");
			}
		}
		sf.append("</form>");
		sf.append("</body>");
		sf.append("<script type=\"text/javascript\">");
		sf.append("document.all.pay_form.submit();");
		sf.append("</script>");
		sf.append("</html>");
		return sf.toString();
	}
}
