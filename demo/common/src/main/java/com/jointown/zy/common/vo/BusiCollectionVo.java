package com.jointown.zy.common.vo;

import java.util.Date;

public class BusiCollectionVo {
	private Long cindex;  //收藏ID
	private String wlid; //仓单ID
	private String listingid;//挂牌ID
	private int cstate;// 是否收藏 0收藏，1取消收藏
	private String origin;//产地
	private String grade; //等级规格
	private String path; //散货图片路径
	private String title; //商品标题
	private  double minsales; //最小起订数量
	private int hasbill; //是否开具发票
	private  double listingamount; //挂牌总数量
	private  double lowunitprice; //未开发票的单价
	private int listingflag; //挂牌标志
	private double surplus; //商品库存量
	private int tradeNum;//交易笔数
	private Date createtime;//挂牌时间
	private String unit;//计量单位
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(int tradeNum) {
		this.tradeNum = tradeNum;
	}
	public Long getCindex() {
		return cindex;
	}
	public void setCindex(Long cindex) {
		this.cindex = cindex;
	}
	public String getWlid() {
		return wlid;
	}
	public void setWlid(String wlid) {
		this.wlid = wlid;
	}
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public int getCstate() {
		return cstate;
	}
	public void setCstate(int cstate) {
		this.cstate = cstate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHasbill() {
		return hasbill;
	}
	public void setHasbill(int hasbill) {
		this.hasbill = hasbill;
	}
	public int getListingflag() {
		return listingflag;
	}
	public void setListingflag(int listingflag) {
		this.listingflag = listingflag;
	}
	public double getSurplus() {
		return surplus;
	}
	public void setSurplus(double surplus) {
		this.surplus = surplus;
	}
	public double getMinsales() {
		return minsales;
	}
	public double getListingamount() {
		return listingamount;
	}
	public double getLowunitprice() {
		return lowunitprice;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
