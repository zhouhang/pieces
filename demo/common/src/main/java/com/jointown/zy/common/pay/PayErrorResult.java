package com.jointown.zy.common.pay;

/**
 * @ClassName: PayErrorResult
 * @Description: 支付接口返回错误信息
 * @Author: ldp
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
public class PayErrorResult {

	/**返回错误代码*/
	private String code;
	/**错误代码描述*/
	private String codeDes;
	
	public PayErrorResult() {
		super();
	}
	public PayErrorResult(String code, String codeDes) {
		super();
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
