package com.pieces.dao.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;



public class OrderInvoice  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//单位名称
	private String name;
	
	//1.个人发票 2.增值税发票
	private Integer type;
	
	//发票内容
	private String content;
	
	//纳税人识别码
	private String identifier;
	
	//注册地址
	private String registeredAddress;
	
	//注册电话
	private String registeredTel;
	
	//开户银行
	private String bankName;
	
	//银行账户
	private String bankAccount;
	
	public OrderInvoice(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public String getTypeText() {
		if (type==null) {
			return "";
		}
		return Integer.valueOf(1).equals(this.type)?"普通发票":"增值税专用发票";
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	
	public String getRegisteredTel() {
		return registeredTel;
	}

	public void setRegisteredTel(String registeredTel) {
		this.registeredTel = registeredTel;
	}
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
}