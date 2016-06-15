package com.jointown.zy.common.constant;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.enums.AmtTypeEnum;
import com.jointown.zy.common.enums.PayStatusEnum;
import com.jointown.zy.common.enums.RefundRemitStatusEnum;
import com.jointown.zy.common.enums.RemitStatusEnum;
import com.jointown.zy.common.util.PropertiesUtils;

/**
 * 银联参数配置
 * @author biran
 *
 */
public class BankConfigConstant {

	private static final String BANK_CONFIG_FILE = "com/jointown/zy/common/properties/bankInfo.properties";
	
	/**
	 * 银联B2B参数配置
	 */
	public static String UNION_B2B_MER_ID;
	public static String UNION_B2B_FRONT_URL;
	public static String UNION_B2B_BACK_URL;
	public static String UNION_B2B_VERSION;
	public static String UNION_ENCODING;
	public static String UNION_B2B_SIGNMETHOD;
	public static String UNION_B2B_TXNTYPE;
	public static String UNION_B2B_TXNSUBTYPE;
	public static String UNION_B2B_BIZTYPE;
	public static String UNION_B2B_CHANNELTYPE;
	public static String UNION_B2B_ACCESSTYPE;
	public static String UNION_CURRENCYCODE;
	
	/**
	 * 代收参数配置
	 */
	public static String UNION_B2C_MERID;
	public static String UNION_B2C_FRONT_URL;
	public static String UNION_B2C_BACK_URL;
	public static String UNION_B2C_VERSION;
	public static String UNION_B2C_ENCODING;
	public static String UNION_B2C_SIGNMETHOD;
	public static String UNION_B2C_TXNTYPE;
	public static String UNION_B2C_TXNSUBTYPE;
	public static String UNION_B2C_BIZTYPE;
	public static String UNION_B2C_CHANNELTYPE;
	public static String UNION_B2C_ACCESSTYPE;
	
	/**
	 * 招行公用参数配置
	 */
	public static String UCS_MALLID;
	public static String UCS_PRIVATE_USRTYPE;
	public static String UCS_COMPANY_USRTYPE;
	
	/**
	 * 珍药宝单点登录参数配置
	 */
	public static String UCS_PAYLOGIN_OPTTYPE;
	public static String UCS_PAYLOGIN_USRTYPE;
	public static String UCS_PAYLOGIN_ACTION;
	
	/**
	 * [招行]珍药宝支付配置
	 */
	public static String UCS_PAYORDER_OPTTYPE;
	public static String UCS_PAY_CHANGE_STATUS_OPTTYPE;
	public static String UCS_PAYORDER_ACTION;
	public static String UCS_PAYORDER_FONTURL;
	
	public static String UCS_REMITACCOUNT_OPTTYPE;
	public static String UCS_REMITACCOUNTS_OPTTYPE;
	public static String UCS_REMITACCOUNT_UCSCREDITPAY_SERVICEURL;
	
	
	/**
	 * 交易系统与支付系统对接时签名Key
	 */
	public static String TX_PAY_MD5KEY;
	
	/**
	 * 交易系统与支付系统对接时签名Key
	 */
	public static String TX_PAY_DESKEY;
	
	static{
		/**
		 * 银联B2B参数初始化
		 */
		UNION_B2B_MER_ID = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_merId");
		UNION_B2B_FRONT_URL = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_front_url");
		UNION_B2B_BACK_URL = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_back_url");
		UNION_B2B_VERSION = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_version");
		UNION_ENCODING = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_encoding");
		UNION_B2B_SIGNMETHOD = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_signMethod");
		UNION_B2B_TXNTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_txnType");
		UNION_B2B_TXNSUBTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_txnSubType");
		UNION_B2B_BIZTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_bizType");
		UNION_B2B_CHANNELTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_channelType");
		UNION_B2B_ACCESSTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_accessType");
		UNION_CURRENCYCODE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2B_currencyCode");
		
		UNION_B2C_MERID = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_merId");
		UNION_B2C_FRONT_URL = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_front_url");
		UNION_B2C_BACK_URL = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_back_url");
		UNION_B2C_VERSION = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_version");
		UNION_B2C_ENCODING = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_encoding");
		UNION_B2C_SIGNMETHOD = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_signMethod");
		UNION_B2C_TXNTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_txnType");
		UNION_B2C_TXNSUBTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_txnSubType");
		UNION_B2C_BIZTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_bizType");
		UNION_B2C_CHANNELTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_channelType");
		UNION_B2C_ACCESSTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "unionpay_B2C_accessType");
		
		//珍药宝公用配置
		UCS_PRIVATE_USRTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_private_usrtype");
		UCS_COMPANY_USRTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_company_usrtype");
		
		//珍药宝单点登录
		UCS_MALLID = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_MallId");
		UCS_PAYLOGIN_OPTTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_paylogin_optType");
		UCS_PAYLOGIN_USRTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_paylogin_usrType");
		UCS_PAYLOGIN_ACTION = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_paylogin_action");
		
		//[招行]珍药宝支付
		UCS_PAYORDER_OPTTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_payOrder_optType");
		UCS_PAY_CHANGE_STATUS_OPTTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_pay_change_status_opttype");
		UCS_PAYORDER_ACTION = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "ucs_payOrder_action");
		UCS_PAYORDER_FONTURL = PropertiesUtils.getProperty(BANK_CONFIG_FILE,"ucs_payorder_fonturl");
		
		UCS_REMITACCOUNT_OPTTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE,"ucs_remitAccount_optType");
		UCS_REMITACCOUNTS_OPTTYPE = PropertiesUtils.getProperty(BANK_CONFIG_FILE,"ucs_remitAccounts_optType");
		UCS_REMITACCOUNT_UCSCREDITPAY_SERVICEURL = PropertiesUtils.getProperty(BANK_CONFIG_FILE,"ucs_serviceurl");
		
		TX_PAY_MD5KEY = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "tx.pay.md5Key");
		TX_PAY_DESKEY = PropertiesUtils.getProperty(BANK_CONFIG_FILE, "tx.pay.desKey");
		
		
	}
	
	/**
	 * 
	 * @ClassName:StatusDesc
	 * @author:Calvin.Wangh
	 * @date:2015-6-16上午9:52:17
	 * @version V1.0
	 * @Description:支付导出Excel对类型描述的使用
	 */
	public static class StatusDesc{
		
		public static final int PAY_TYPE = 0;//金额类别
		
		public static final int PAY_STATUS = 1;//支付类别
		
		public static final int REMIT_ACCOUNT_STATUS = 2;//划账状态 
		
		public static final int REFUND_STATUS = 3;//退款状态
	}
	
	/**
	 * 真药材用户身份认证
	 * @ClassName:UcsCertify
	 * @author:Calvin.Wangh
	 * @Description:
	 * @date:2015-5-14上午10:27:01
	 * @version V1.0
	 */
	public static class UcsUserCertify{
		
		public static final int PRIVATE_CERTIFY_STATUS = 1;//个人认证
		
		public static final int COMPANY_CERTIFY_STATUS = 2;//企业认证
	}
	
	/**
	 * 支付渠道
	 * @ClassName:PayChannel
	 * @author:Calvin.Wangh
	 * @Description:
	 * @date:2015-5-14上午10:38:10
	 * @version V1.0
	 */
	public static class PayChannel {
		// 招行
		public static final int CMB = 0;
		// 银联B2B
		public static final int UNION_B2B = 1;
		// 银联B2C
		public static final int UNION_B2C = 2;
		//银行转账
		public static final int BANK_TRANSFER = 3;

		public static final String getName(int iCode) {
			String strReturn = "";
			switch ((int) iCode) {
			case (int) CMB:
				strReturn = "招行";
				break;
			case (int) UNION_B2B:
				strReturn = "银联B2B";
				break;
			case (int) UNION_B2C:
				strReturn = "银联B2C";
				break;
			case (int) BANK_TRANSFER :
				strReturn = "线下支付";
			}
			return strReturn;
		}
		
		public static final List showList(String constantName,String payChannel, String resultList){
			return Constant.getConstantResult.getList(constantName,payChannel, resultList);
		}
	}
	
	/**
	 * 珍药宝支付返回状态
	 * @ClassName:UcsRespPayState
	 * @author:Calvin.Wangh
	 * @Description:
	 * @date:2015-5-12下午4:03:18
	 * @version V1.0
	 */
	public static class UcsRespPayState {
		//支付失败
		public static final String PAY_FAILED = "0";
		//支付成功
		public static final String PAY_SUCCESS = "1";
		//支付处理中
		public static final String PAY_DISPONSE = "2";
	}
	
	/**
	 * 珍药宝响应码
	 * 做记录payflowlog日志使用
	 * @ClassName:UcsRespCode
	 * @author:Calvin.Wangh
	 * @Description:
	 * @date:2015-5-12下午4:03:01
	 * @version V1.0
	 */
	public static class UcsRespCode{
		//成功受理请求
		public static final String UCS_RESPCODE_100 ="UCS100";
		//处理失败
		public static final String UCS_RESPCODE_999 = "UCS999";
	}
	
	/**
	 * 珍药宝交易类型 (具体描述参照技术最新珍药宝技术文档)
	 * @ClassName:TradeType
	 * @author:Calvin.Wangh
	 * @Description:
	 * @date:2015-5-20下午5:22:20
	 * @version V1.0
	 */
	public static class TradeType{
		//商城用户单点登录到珍药宝
		public static final int TRADE_TYPE_1000 = 1000;
		//商城订单支付
		public static final int TRADE_TYPE_1001 = 1001;
		//商城订单支付状态变更通知
		public static final int TRADE_TYPE_1002 = 1002;
		//商城订单支付查询
		public static final int TRADE_TYPE_1003 = 1003;
		//商城订单退款
		public static final int TRADE_TYPE_1004 = 1004;
		//商城退款状态变更通知
		public static final int TRADE_TYPE_1005 = 1005;
		//商城退款状态查询
		public static final int TRADE_TYPE_1006 = 1006;
		//资金批量划账
		public static final int TRADE_TYPE_1009 = 1009;
		//批量划账查询
		public static final int TRADE_TYPE_1010 = 1010;
		//企业身份认证结果通知
		public static final int TRADE_TYPE_1011 = 1011;
	}
	
	/**
	 * 银行机构代码对应名称
	 * @ClassName:BankCode
	 * @author:Calvin.Wangh
	 * @Description:
	 * @date:2015-5-15下午2:16:24
	 * @version V1.0
	 */
	public static class BankCode {
		public static final String ICBC = "ICBC";
		
		public static final String ABC = "ABC";
		
		public static final String BOC = "BOC";
		
		public static final String CCB = "CCB";
		
		public static final String CMB = "CMB";
		
		public static final String BOCOM = "BOCOM";
		
		public static final String CMBC = "CMBC";
		
		public static final String HXBC = "HXBC";
		
		public static final String PAB = "PAB";
		
		public static final String BOBJ = "BOBJ";
		
		public static final String CITIC = "CITIC";
		
		public static final String CEB = "CEB";
		
		public static final String GDB = "GDB";
		
		public static final String CIB = "CIB";
		
		public static final String SPDB = "SPDB";
		
		public static final String SPBC = "SPBC";
		
		public static final String BOS = "BOS";
		
		public static final String NBCB = "NBCB";
		
		public static final String getName (String bankCode){
			String name = "";
			if(StringUtils.equals("ICBC", bankCode))
				name = "中国工商银行";
			else if(StringUtils.equals("ABC", bankCode))
				name = "中国农业银行";
			else if(StringUtils.equals("BOC", bankCode))
				name = "中国银行";
			else if(StringUtils.equals("CCB", bankCode))
				name = "中国建设银行";
			else if(StringUtils.equals("CMB", bankCode))
				name = "招商银行";
			else if(StringUtils.equals("BOCOM", bankCode))
				name = "交通银行";
			else if(StringUtils.equals("CMBC", bankCode))
				name = "中国民生银行";
			else if(StringUtils.equals("HXBC", bankCode))
				name = "华夏银行";
			else if(StringUtils.equals("PAB", bankCode))
				name = "平安银行";
			else if(StringUtils.equals("BOBJ", bankCode))
				name = "北京银行";
			else if(StringUtils.equals("CITIC", bankCode))
				name = "中信银行";
			else if(StringUtils.equals("CEB", bankCode))
				name = "光大银行";
			else if(StringUtils.equals("GDB", bankCode))
				name = "广东发展银行";
			else if(StringUtils.equals("CIB", bankCode))
				name = "兴业银行";
			else if(StringUtils.equals("SPDB", bankCode))
				name = "上海浦东发展银行";
			else if(StringUtils.equals("SPBC", bankCode))
				name = "中国邮政储蓄银行";
			else if(StringUtils.equals("BOS", bankCode))
				name = "上海银行";
			else if(StringUtils.equals("NBCB", bankCode))
				name = "宁波银行";
			else 
				name = "未知银行";
			return name;
		}
	}
	
	
	/**
	 * 根据类型描述 获取对应的状态描述
	 * @param type
	 * @param v
	 * @return
	 */
	public static String getStatusDesc(Integer type,String v){
		String retStr = "";
		switch (type) {
		case BankConfigConstant.StatusDesc.PAY_TYPE:
			Map<String,String> amtTypeMap = AmtTypeEnum.toMap();
			for (Entry<String, String> entry : amtTypeMap.entrySet()) {  
	            if(entry.getKey().equals(v)){
	            	retStr = entry.getValue();
	            }
	        }  
			break;
		case BankConfigConstant.StatusDesc.PAY_STATUS:
			Map<String,String> payStatusEnumMap = PayStatusEnum.toMap();
			for (Entry<String, String> entry : payStatusEnumMap.entrySet()) {  
	            if(entry.getKey().equals(v)){
	            	retStr = entry.getValue();
	            }
	        }  
			break;
		case BankConfigConstant.StatusDesc.REMIT_ACCOUNT_STATUS:
			Map<String,String> remitStatusMap = RemitStatusEnum.toMap();
			for (Entry<String, String> entry : remitStatusMap.entrySet()) {  
	            if(entry.getKey().equals(v)){
	            	retStr = entry.getValue();
	            }
	        }  
			break;
		case BankConfigConstant.StatusDesc.REFUND_STATUS:
			Map<String,String> refundStatusMap = RefundRemitStatusEnum.toMap();
			for (Entry<String, String> entry : refundStatusMap.entrySet()) {  
	            if(entry.getKey().equals(v)){
	            	retStr = entry.getValue();
	            }
	        }  
			break;
		default:
			retStr = "";
			break;
		}	
		return retStr;
	}
	
	
	
	//PayChanner 使用实例
	public static void main(String[] args) {
		//根据code 返回 codeDesc
		System.out.println(PayChannel.getName(PayChannel.UNION_B2B));
		
		
		List result = BankConfigConstant.PayChannel.showList(BankConfigConstant.class.getName(),"PayChannel", null);
		for (int i = 0; i < result.size(); i++) {
			ConstantPO po = (ConstantPO) result.get(i);
			System.out.println(po.getKey() + " " +po.getName());
		}
	}
	
	
}
