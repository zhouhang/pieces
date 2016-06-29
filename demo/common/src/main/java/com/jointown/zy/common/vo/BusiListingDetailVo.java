package com.jointown.zy.common.vo;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.jointown.zy.common.model.BusiQualitypic;

/**
 * 单个挂牌详情vo
 * @author Mr.songwei
 * 2015-1-2
 */
@XmlRootElement
public class BusiListingDetailVo  {
	//仓单ID
	private String wlid;
	//挂牌标识
	private int listingflag;
	//用户ID
	private Long userid;
	//可挂数量
	private double wlsurplus;
	//质量单位
	private String dictvalue;
	//仓单总量
	private double wltotal;
	//包装规格
	private String packingway;
	//挂牌ID
	private String listingid;
	//挂牌标题
	private String title;
	//挂牌数量
	private double listingamount;
	//未开发票的单价
	private double lowunitprice;
	//最低起订数量
	private double minsales;
	//挂牌期限
	private int listingtimelimit;
	//是否开具发票
	private int hasbill;
	//开具发票的价格
	private double billprice;
	//药材详情
	private String content;
	//挂牌过期日期
	private Date expiretime;
	//数据库系统日期
	private Date sysdate;
	//图片集合
  	private List<BusiQualitypic> piclist;
  	//品种
  	private String breedname;
	public int getListingflag() {
		return listingflag;
	}
	public void setListingflag(int listingflag) {
		this.listingflag = listingflag;
	}
    public Date getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}
	public Date getSysdate() {
		return sysdate;
	}
	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
	public String getWlid() {
		return wlid;
	}
	public void setWlid(String wlid) {
		this.wlid = wlid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public double getWlsurplus() {
		return wlsurplus;
	}
	public void setWlsurplus(double wlsurplus) {
		this.wlsurplus = wlsurplus;
	}
	public String getDictvalue() {
		return dictvalue;
	}
	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}
	public double getWltotal() {
		return wltotal;
	}
	public void setWltotal(double wltotal) {
		this.wltotal = wltotal;
	}
	public String getPackingway() {
		return packingway;
	}
	public void setPackingway(String packingway) {
		this.packingway = packingway;
	}
	public String getListingid() {
		return listingid;
	}
	public void setListingid(String listingid) {
		this.listingid = listingid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getListingamount() {
		return listingamount;
	}
	public void setListingamount(double listingamount) {
		this.listingamount = listingamount;
	}
	public double getLowunitprice() {
		return lowunitprice;
	}
	public void setLowunitprice(double lowunitprice) {
		this.lowunitprice =lowunitprice;
	}
	public double getMinsales() {
		return minsales;
	}
	public void setMinsales(double minsales) {
		this.minsales = minsales;
	}
	public int getListingtimelimit() {
		return listingtimelimit;
	}
	public void setListingtimelimit(int listingtimelimit) {
		this.listingtimelimit = listingtimelimit;
	}
	public int getHasbill() {
		return hasbill;
	}
	public void setHasbill(int hasbill) {
		this.hasbill = hasbill;
	}
	public double getBillprice() {
		return billprice;
	}
	public void setBillprice(double billprice) {
		this.billprice = billprice;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<BusiQualitypic> getPiclist() {
		return piclist;
	}
	public void setPiclist(List<BusiQualitypic> piclist) {
		this.piclist = piclist;
	}
	public String getBreedname() {
		return breedname;
	}
	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}
}
