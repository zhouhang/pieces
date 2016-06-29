package com.jointown.zy.common.vo;

/**
 * 涨跌Top10
 *
 * @author aizhengdong
 *
 * @data 2015年3月5日
 */
public class EastJgPaiHangVo {

	/** 药材名称 */
	private String ycName;
	
	/** 规格 */
	private String guiGe;
	
	/** 今日价格 */
	private String price;
	
	/** 对比价格 */
	private String yuanPrice;
	
	/** 产地 */
	private String chanDi;
	
	/** 对比时间 */
	private String yuanDtm;
	
	/** 行情涨跌幅度 */
	private String hangQing;
	
	/** 药材图片 */
	private String pic;

	public String getYcName() {
		return ycName;
	}

	public void setYcName(String ycName) {
		this.ycName = ycName;
	}

	public String getGuiGe() {
		return guiGe;
	}

	public void setGuiGe(String guiGe) {
		this.guiGe = guiGe;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getYuanPrice() {
		return yuanPrice;
	}

	public void setYuanPrice(String yuanPrice) {
		this.yuanPrice = yuanPrice;
	}

	public String getChanDi() {
		return chanDi;
	}

	public void setChanDi(String chanDi) {
		this.chanDi = chanDi;
	}
	
	public String getYuanDtm() {
		return yuanDtm;
	}

	public void setYuanDtm(String yuanDtm) {
		this.yuanDtm = yuanDtm;
	}

	public String getHangQing() {
		return hangQing;
	}

	public void setHangQing(String hangQing) {
		this.hangQing = hangQing;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
