package com.jointown.zy.common.enums;

/**
 * 珍药宝响应码
 * @ClassName:UcsCodeEnum
 * @author:Calvin.Wangh
 * @Description:
 * @date:2015-5-13下午12:12:13
 * @version V1.0
 */
public enum UcsCodeEnum {
	
		CODE_100("100","成功受理请求"),
		CODE_101 ("101","系统内部错误"),
		CODE_102("102","验证签名失败"),
		CODE_103("103","解析报文错误"),
		CODE_104("104","报文格式错误"),
		CODE_105("105","无效交易类型 "),
		CODE_106("106","无效日期"),
		CODE_107("107","参数不正确"),
		CODE_108("108","交易证书过期"),
		CODE_1000("1000","不存在此机构"),
		CODE_1001("1001","不存在此订单"),
		CODE_1002("1002","不存在此支付交易"),
		CODE_1003("1003","订单号长度不正确"),
		CODE_1004("1004","交易流水号长度不正确"),
		CODE_1005("1005","交易流水号重复"),
		CODE_1007("1007","金额格式不正确"),
		CODE_1008("1008","备注过长"),
		CODE_1009("1009","金额必须大于0"),
		CODE_1012("1012","交易流水号为空"),
		CODE_999("999","处理失败");
		
		private String code;
		private String codeDesc;
		
		private UcsCodeEnum() {}
		private UcsCodeEnum(String code, String codeDesc) {
			this.code = code;
			this.codeDesc = codeDesc;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getCodeDesc() {
			return codeDesc;
		}
		public void setCodeDesc(String codeDesc) {
			this.codeDesc = codeDesc;
		}

}
