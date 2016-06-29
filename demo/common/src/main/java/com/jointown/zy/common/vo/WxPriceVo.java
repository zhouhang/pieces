/**
 * @author guoyb
 * 2015年3月11日 上午9:35:07
 */
package com.jointown.zy.common.vo;

/**
 * @author guoyb
 * 2015年3月11日 上午9:35:07
 */
public class WxPriceVo {
	
	//药材名
	private String name;
	
	//市场
	private String area;
		
	//数量
	private Float amount;
	
	//数量单位 
    private String amountUnit;
	
	//价格
	private String price;
	
	//规格
	private String grade;
	
	//时间
	private String dtm;
	
	public String getDtm() {
		return dtm;
	}

	public void setDtm(String dtm) {
		this.dtm = dtm;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	
	
}
