package com.jointown.zy.common.enums;

/**
 * @ClassName: PayErrorCode
 * @Description: 支付接口错误码枚举
 * @Author: ldp
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
public enum PayErrorCode {
		
		/******************支付请求时错误码***********************/
		PAY_REQ_DATA_IS_NOT_NULL("001","支付请求数据不能为空!"),
		PAY_REQDATA_SIGNDATA_IS_NOT_NULL("002","签名不能为空!"),
		PAY_REQDATA_ORDERID_IS_NOT_NULL("003","订单号不能为空!"),
		PAY_REQDATA_AMOUNT_IS_NOT_NULL("004","订单金额不能为空!"),
		PAY_REQDATA_AMOUNT_IS_ILLAGEL("005","订单金额不合法!"),
		PAY_REQDATA_AMTTYPE_IS_NOT_NULL("006","金额款项不能为空!"),
		PAY_REQDATA_SOURCESYS_IS_NOT_NULL("007","系统标识不能为空!"),
		PAY_REQDATA_USERID_IS_NOT_NULL("009","付款人不能为空!"),
		PAY_REQDATA_RECIEVEID_IS_NOT_NULL("010","收款人不能为空!"),
		PAY_REQDATA_ORDERTITLE_IS_NOT_NULL("011","商品名称不能为空!"),
		PAY_BANKCODE_IS_NOT_NULL("012","请选择一家银行支付!"),
		PAY_UN_LOGIN("013","未登录"),
		PAYUSER_AND_LOGIN_USER_DISCORD("014","登录用户名与付款用户名不一致!"),
		PAY_FLOW_EXCEPTION("015","支付流水异常!"),
		PAY_SIGN_ERROR("008","签名错误!"),
		
		
		/********************返回时错误码**************************/
		PAY_FLOW_NOT_EXIST("R001","支付流水不存在"),
		PAY_AMT_AND_BANK_AMT_DISCORD("R002","银行返回金额与支付流水金额不一致!"),
		PAY_RESULT_IS_NULL("R003","返回数据为空!"),
		PAY_RESULT_DONE_EXCEPTION("R004","返回处理异常!"),
		;
		
		  
		private String code;
		private String codeDes;  
		
		
		private PayErrorCode(String code, String codeDes) {
			this.code = code;
			this.codeDes = codeDes;
		}
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getCodeDes() {
			return codeDes;
		}
		public void setCodeDes(String codeDes) {
			this.codeDes = codeDes;
		}
	  
	  
}
