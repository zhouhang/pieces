package com.jointown.zy.common.pay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.unionpay.acp.sdk.CertUtil;
import com.unionpay.acp.sdk.HttpClient;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;
import com.unionpay.acp.sdk.SecureUtil;

/**
 * 名称： 基础参数<br>
 * 功能： 提供基础数据<br>
 */
public class UnionPayBase {

	private static final Logger log = LoggerFactory.getLogger(UnionPayBase.class);
	static{
		/*** 参数初始化*/
		SDKConfig.getConfig().loadPropertiesFromSrc();
	}

	public UnionPayBase() {
		super();
	}

	/**
	 * 构造HTTP POST交易表单
	 * @param action
	 *            表单提交地址
	 * @param hiddens
	 *            以MAP形式存储的表单键值
	 * @return 构造好的HTTP POST交易表单
	 */
	public static String createHtml(String action, Map<String, String> hiddens) {
		StringBuffer sf = new StringBuffer();
		sf.append("<form id = \"pay_form\" action=\"" + action
				+ "\" method=\"post\">\n");
		if (null != hiddens && 0 != hiddens.size()) {
			Set<Entry<String, String>> set = hiddens.entrySet();
			Iterator<Entry<String, String>> it = set.iterator();
			while (it.hasNext()) {
				Entry<String, String> ey = it.next();
				String key = ey.getKey();
				String value = ey.getValue();
				sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
						+ key + "\" value=\"" + value + "\"/>\n");
			}
		}
		sf.append("</form>\n");
		return sf.toString();
	}

	/**
	 * 对数据进行签名
	 * @param contentData
	 * @return　签名后的map对象
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> signData(Map<String, ?> contentData) {
		Entry<String, String> obj = null;
		Map<String, String> submitFromData = new HashMap<String, String>();
		for (Iterator<?> it = contentData.entrySet().iterator(); it.hasNext();) {
			obj = (Entry<String, String>) it.next();

			if (null != obj.getValue()
					&& StringUtils.isNotEmpty(String.valueOf(obj.getValue()))) {
				submitFromData
						.put(obj.getKey(), String.valueOf(obj.getValue()));
				log.info(obj.getKey() + "-->" + String.valueOf(obj.getValue()));
			}
		}
		/**签名*/
		sign(submitFromData, BankConfigConstant.UNION_ENCODING);
		return submitFromData;
	}

	/**
	 * 签名方法
	 * @param data
	 * @param encoding
	 * @return
	 */
	public static boolean sign(Map<String, String> data, String encoding)
	  {
	    log.info("签名处理开始.");
	    if (StringUtils.isEmpty(encoding)) {
	      encoding = "UTF-8";
	    }

	    data.put("certId", CertUtil.getSignCertId());
	    String stringData = SDKUtil.coverMap2String(data);

	    log.info("报文签名之前的字符串(不含signature域)=[" + stringData + "]");

	    byte[] byteSign = (byte[])null;
	    String stringSign = null;
	    try
	    {
	      byte[] signDigest = SecureUtil.sha1X16(stringData, encoding);
	      log.info("SHA1->16进制转换后的摘要=[" + new String(signDigest) + 
	        "]");
	      byteSign = SecureUtil.base64Encode(SecureUtil.signBySoft(
	        CertUtil.getSignCertPrivateKey(), signDigest));
	      stringSign = new String(byteSign);
	      log.info("报文签名之后的字符串=[" + stringSign + "]");

	      data.put("signature", stringSign);
	      log.info("签名处理结束.");
	      return true;
	    } catch (Exception e) {
	      log.info("签名异常", e);
	    }
	    return false;
	  }

	/**
	 * 数据提交 提交到后台
	 * @param contentData
	 * @return 返回报文 map
	 */
	public static Map<String, String> submitUrl(Map<String, String> submitFromData,String requestUrl) {
		String resultString = "";
		log.info("requestUrl is:" + requestUrl);
		log.info("submitFromData is:" + submitFromData.toString());
		/** 发送请求*/
		HttpClient hc = new HttpClient(requestUrl, 30000, 30000);
		try {
			int status = hc.send(submitFromData, BankConfigConstant.UNION_ENCODING);
			if (200 == status) {
				resultString = hc.getResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> resData = new HashMap<String, String>();
		/** 验证签名*/
		if (null != resultString && !"".equals(resultString)) {
			// 将返回结果转换为map
			resData = SDKUtil.convertResultStringToMap(resultString);
			if (SDKUtil.validate(resData, BankConfigConstant.UNION_ENCODING)) {
				log.info("验证签名成功");
			} else {
				log.info("验证签名失败");
			}
			// 打印返回报文
			log.info("打印返回报文：" + resultString);
		}
		return resData;
	}

	/**
	 * 解析返回文件
	 */
	public static void deCodeFileContent(Map<String, String> resData) {
		// 解析返回文件
		String fileContent = resData.get(SDKConstants.param_fileContent);
		if (null != fileContent && !"".equals(fileContent)) {
			try {
				byte[] fileArray = SecureUtil.inflater(SecureUtil
						.base64Decode(fileContent.getBytes(BankConfigConstant.UNION_ENCODING)));
				String root = "D:\\";
				String filePath = null;
				if (SDKUtil.isEmpty(resData.get("fileName"))) {
					filePath = root + File.separator + resData.get("merId")
							+ "_" + resData.get("batchNo") + "_"
							+ resData.get("txnTime") + ".txt";
				} else {
					filePath = root + File.separator + resData.get("fileName");
				}
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
				file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write(fileArray, 0, fileArray.length);
				out.flush();
				out.close();

			} catch (UnsupportedEncodingException e) {
				log.error("unionpay unsupportedEncodingException:",e);
			} catch (IOException e) {
				e.printStackTrace();
				log.error("unionpay anlysis error is:",e);
			}
		}
	}

	/**
	 * 数据组装进行提交 包含签名
	 * @param contentData
	 * @return 返回报文 map
	 */
	public static Map<String, String> submitDate(Map<String, ?> contentData,String requestUrl) {
		Map<String, String> submitFromData = (Map<String, String>) signData(contentData);
		return submitUrl(submitFromData,requestUrl);
	}
	
	
}