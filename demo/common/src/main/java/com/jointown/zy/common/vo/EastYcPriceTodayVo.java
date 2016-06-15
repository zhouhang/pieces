/**
 * @author guoyb
 * 2015年3月9日 下午12:28:42
 */
package com.jointown.zy.common.vo;

/**
 * 药材今日价格
 * 
 * @author guoyb 2015年3月9日 下午12:28:42
 */
public class EastYcPriceTodayVo {

	//价格
	private String price;
	
	//规格
	private String guige;
	
	//产地
	private String chandi;
	
	//药材名
	private String ycnam;
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}

	public String getYcnam() {
		return ycnam;
	}

	public void setYcnam(String ycnam) {
		this.ycnam = ycnam;
	}
}
