package com.jointown.zy.common.pay;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.dto.PayReqDto;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.util.DES2;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * @ClassName: PayUtil
 * @Description: 支付接口
 * @Author: ldp
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
public class PayUtil {
	
	private static final Logger log = LoggerFactory.getLogger(PayUtil.class);
	
	/**
	 * 支付验签
	 * @Author: ldp
	 * @Date: 2015年4月8日
	 * @param payReqDto
	 * @return
	 * 		验签通过返回true,验签不通过返回false
	 */
	public static boolean sign(PayReqDto payReqDto){
		String signData = payReqDto.getSignData();
		if (null == signData) {
			log.info("signData is null");
			return false;
		}
		StringBuilder orderInfo = new StringBuilder();
		orderInfo.append("orderId=").append(payReqDto.getOrderId());
		orderInfo.append("&");
		orderInfo.append("amount=").append(payReqDto.getAmount());
		orderInfo.append("&");
		orderInfo.append("amtType=").append(payReqDto.getAmtType());
		orderInfo.append("&");
		orderInfo.append("userId=").append(payReqDto.getUserId());
		orderInfo.append("&");
		orderInfo.append("recieveId=").append(payReqDto.getRecieveId());
		orderInfo.append("&");
		orderInfo.append("sourceSys=").append(payReqDto.getSourceSys());
		orderInfo.append("&");
		orderInfo.append("orderTitle=").append(payReqDto.getOrderTitle());
		//签名防止数据篡改
		String orderKey = EncryptUtil.getMD5(orderInfo.toString() + BankConfigConstant.TX_PAY_MD5KEY, "UTF-8");
		if (signData.equals(orderKey)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断金额格式是否合法(最多两位小数),且金额大于0
	 * @Author: ldp
	 * @Date: 2015年4月8日
	 * @param amt
	 * @return boolean
	 */
	public static boolean isIegalAmt(String amt){
		if (StringUtils.isNotBlank(amt)) {
			//判断两位小数
			boolean flag = Pattern.compile("([1-9]+[0-9]*|0)(\\.[0-9]{1,2})?").matcher(amt).matches();
			//判断金额大于0
			if (flag) {
				return amtBigZero(amt);
			} 
		}
		return false;
	}
	
	/**
	 * 判断金额是否大于0
	 * @Author: ldp
	 * @Date: 2015年4月22日
	 * @return
	 */
	public static boolean amtBigZero(String amt){
		if (StringUtils.isNotBlank(amt)) {
			if (new BigDecimal(amt).compareTo(new BigDecimal(0))>0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 生成返回的验签数据
	 * @Author: ldp
	 * @Date: 2015年4月11日
	 * @param paramMap
	 * @return
	 * 		返回生成的验签数据	
	 */
	public static String generateVerityData(Map<String, String> paramMap){
		if (null == paramMap|| paramMap.size() == 0) {
			return null;
		}
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("orderId=").append(paramMap.get("orderId"));
		sbBuilder.append("&");
		sbBuilder.append("amount=").append(paramMap.get("amount"));
		sbBuilder.append("&");
		sbBuilder.append("amtType=").append(paramMap.get("amtType"));
		sbBuilder.append("&");
		sbBuilder.append("payStatus=").append(paramMap.get("payStatus"));
		sbBuilder.append("&");
		sbBuilder.append("payRemark=").append(paramMap.get("payRemark"));
		sbBuilder.append("&");
		sbBuilder.append("payTime=").append(paramMap.get("payTime"));
		sbBuilder.append("&");
		sbBuilder.append("sourceSys=").append(paramMap.get("sourceSys"));
		return EncryptUtil.getMD5(sbBuilder.toString() + BankConfigConstant.TX_PAY_MD5KEY, "UTF-8");
	}
	
	/**
	 * 同步生成单号（18位固定）
	 * @return
	 * @throws
	 */
	public static String getSeqence(){
		synchronized (UnionPayBase.class) {
			String result = "";
			try {
				Calendar calendar = Calendar.getInstance();
				Thread.sleep(1);
				String str = System.currentTimeMillis() + "";
				result =  TimeUtil.formatDatetime(calendar.getTime(), "yyyyMMddHHmmss") + str.substring(str.length()-4,str.length());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
	
	/**
	 * 获取加密数据
	 * @Author: ldp
	 * @Date: 2015年4月16日
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String getDesEncryptData(String data) throws Exception{
		if (null == data) {
			return null;
		}
		DES2 des2 = new DES2(BankConfigConstant.TX_PAY_DESKEY);
		return des2.encrypt(data);
	}
	/**
	 * 获取解密数据
	 * @Author: ldp
	 * @Date: 2015年4月16日
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String getDesDecryptData(String data) throws Exception{
		if (null == data) {
			return null;
		}
		DES2 des2 = new DES2(BankConfigConstant.TX_PAY_DESKEY);
		return des2.decrypt(data);
	}
	
	/**
	 * 生成form表单
	 * @param formName 表单Name
	 * @param action   请求url
	 * @param data	         请求加密参数
	 * @param signdata 请求验签
	 * @return
	 */
	public static String submitForm(String formName,String action, String data,String signdata) {
		StringBuffer sf = new StringBuffer();
		sf.append("<form name = \"" + formName +"\" action=\"" + action
				+ "\" method=\"post\">\n");
		sf.append("<input type=\"hidden\" name=\"data\" value=\"" + data + "\"/>\n");
		sf.append("<input type=\"hidden\" name=\"signdata\" value=\"" + signdata + "\"/>\n");
		sf.append("</form>\n");
		return sf.toString();
	}
	
	/**
	 * 划账验签
	 * @param remitAccountInfo
	 * @return
	 */
	public static String toRemitAccountSignData(RemitFlow remitFlow) {
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append("orderId=").append(remitFlow.getOrderId());
		sbBuilder.append("&");
		sbBuilder.append("platformId=").append(remitFlow.getPlatformId());
		sbBuilder.append("&");
		sbBuilder.append("platformAmount=").append(remitFlow.getPlatformAmount());
		sbBuilder.append("&");
		sbBuilder.append("sellerId=").append(remitFlow.getSellerId());
		sbBuilder.append("&");
		sbBuilder.append("sellerAmount=").append(remitFlow.getSellerAmount());
		sbBuilder.append("&");
		sbBuilder.append("buyerId=").append(remitFlow.getBuyerId());
		sbBuilder.append("&");
		sbBuilder.append("buyerAmount=").append(remitFlow.getBuyerAmount());
		sbBuilder.append("&");
		sbBuilder.append("depositAmount=").append(remitFlow.getTotalAmount());
		sbBuilder.append("&");
		sbBuilder.append("remit_type=").append(remitFlow.getRemitType());
		sbBuilder.append("&");
		sbBuilder.append("createrid=").append(remitFlow.getCreaterid());
		sbBuilder.append("&");
		sbBuilder.append("source_sys=").append(remitFlow.getSourcesys());
		sbBuilder.append("&");
		sbBuilder.append("client_ip=").append(remitFlow.getClientIp());
		return EncryptUtil.getMD5(sbBuilder.toString() + BankConfigConstant.TX_PAY_MD5KEY, "UTF-8");
	}
}
