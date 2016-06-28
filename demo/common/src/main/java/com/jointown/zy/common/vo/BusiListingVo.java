package com.jointown.zy.common.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 我的挂牌vo
 * @author Mr.songwei
 * 2014-12-30
 */
@XmlRootElement
public class BusiListingVo  {
	//仓单ID
	private String wlid;
	//挂牌编号
	private String listingid;
	//用户编号
	private Long userid;
	//用户姓名
	private String username;
	//挂牌标题
	private String title;
	//仓库名称
  	private String warehousename;
  	//计量单位名称
  	private String dictvalue;
    //订单数_销售量
//  	private String ordernumatamount;
  	//订单数
//		private int ordernum=0;
  	private int ordernum;
    //挂牌总量
//  	private double listingamount=0.00d;
  	private double listingamount;
  	//可摘数量
  	private double surpluses;
    //起订数量
//  	private double minsales=0.00d;
  	private double minsales;
    //挂牌时限
//  	private int listingtimelimit=0;
  	private Integer listingtimelimit;
  	
  	private Integer hasbill;
    //未开发票时的单价
//  	private double lowunitprice=0.00d;
  	private double lowunitprice;
  	//开发票时的单价
//  	private double billprice=0.00d;
  	private double billprice;
    //货值
//  	private double totalprice=0.00d;
  	private double totalprice;
    //挂牌状态
  	private int listingflag;
  	//是否推荐
  	private int isrecommend;
  	//提交挂牌时间
  	private Date createtime;
  	//更新挂牌时间
  	private Date updatetime;
  	//审核挂牌时间
  	private Date examinetime;
  	//挂牌过期时间
  	private Date expiretime;
  	//审核不通过评语
  	private String examinecontent;
  	//业务员
  	private String salesman;
  	//品种
  	private String breedname;
  	
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
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getDictvalue() {
		return dictvalue;
	}
	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}
	public Double getListingamount() {
		return listingamount;
	}
	public void setListingamount(Double listingamount) {
		this.listingamount = listingamount;
	}
	public double getSurpluses() {
		return surpluses;
	}
	public void setSurpluses(Double surpluses) {
		this.surpluses = surpluses;
	}
	public Double getMinsales() {
		return minsales;
	}
	public void setMinsales(Double minsales) {
		this.minsales = minsales;
	}
	public Integer getListingtimelimit() {
		return listingtimelimit;
	}
	public void setListingtimelimit(Integer listingtimelimit) {
		this.listingtimelimit = listingtimelimit;
	}
	public Integer getHasbill() {
		return hasbill;
	}
	public void setHasbill(Integer hasbill) {
		this.hasbill = hasbill;
	}
	public Double getLowunitprice() {
		return lowunitprice;
	}
	public void setLowunitprice(Double lowunitprice) {
		this.lowunitprice = lowunitprice;
	}
	public Double getBillprice() {
		return billprice;
	}
	public void setBillprice(Double billprice) {
		this.billprice = billprice;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public int getListingflag() {
		return listingflag;
	}
	public void setListingflag(int listingflag) {
		this.listingflag = listingflag;
	}
	public int getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(int isrecommend) {
		this.isrecommend = isrecommend;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getExaminecontent() {
		return examinecontent;
	}
	public void setExaminecontent(String examinecontent) {
		this.examinecontent = examinecontent;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getExaminetime() {
		return examinetime;
	}
	public void setExaminetime(Date examinetime) {
		this.examinetime = examinetime;
	}
	public Date getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getBreedname() {
		return breedname;
	}
	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}
	
}
