package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/* added by biran 
 * 20150706
 * 退款划账拒绝信息使用
 * 继承划账流水
 * */
public class RefuseRemitFlow extends RemitFlow{


	//后台操作人CODE
	private String opraterCode;
	//后台操作人NAME
	private String opraterName;

	public RefuseRemitFlow() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOpraterCode() {
		return opraterCode;
	}
	public void setOpraterCode(String opraterCode) {
		this.opraterCode = opraterCode;
	}
	public String getOpraterName() {
		return opraterName;
	}
	public void setOpraterName(String opraterName) {
		this.opraterName = opraterName;
	}
	
	
}
