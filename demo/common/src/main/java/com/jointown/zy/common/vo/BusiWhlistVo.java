package com.jointown.zy.common.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiQualityItem;
import com.jointown.zy.common.model.BusiQualitypic;

/**
 * 仓单列表、仓单详情公用vo
 * @author Mr.songwei
 * 2014-12-24
 */
public class BusiWhlistVo  {
	/** 仓单ID */
	private String wlid;
	
	/** 品种名称 */
	private String breedname;
	
	/** 类目ID */
	private Long categoryid;
	
	/** 品种ID */
	private Long breedid;
	
	/** 用户ID */
	private Long userid;
	
	/** 产地 */
	private String origin;
	
	/** 等级规格 */
	private String grade;
	
	/** 质押状态 */
	private int wlstate ;
	
	/** 可挂牌数量 */
	private BigDecimal wlsurplus;
	
	/** 总量 */
	private BigDecimal wltotal;
	
	/** 重量单位 */
	private String dictvalue;
	
	/** 仓库所在地 */
	private String warehousename;
	
	/** 仓库ID */
	private String warehouseid;
	
	/** 区域ID */
	private String areaid;
	
	/** 入库时间 */
	private Date wlrkdate;
		
	/** 用户账号 */
	private String username;
	
	/** 包装规格 */
	private String packingway;
	
	/** 合同编号 */
	private String contractnum;
	
	/** 收检日期 */
	private Date acceptchecktime;
	
	/** 报告日期 */
	private Date reportdate;
	
	/** 送检数量 */
	private String numberofjc;
	
	/** 图片集合 */
	private List<BusiQualitypic> piclist;
	
	/** 等级评定 */
	private String levelEva;
	
	/** 质检信息ID */
	private Long qualityInfoId;
	
	/** 质检报告图片 */
	private String zjReportPic;
	
	/** 质检项信息 */
	private List<BusiQualityItem> busiQualityItem;
	
	/** 质检项信息分组 */
	private Map<String, List<BusiQualityItem>> itemMap = new LinkedHashMap<String, List<BusiQualityItem>>();
	
	/** 区域0 */
	private String areaid0;
	
	/** 区域1 */
	private String areaid1;
	
	/** 区域2 */
	private String areaid2;
	
	/** 是否有挂牌信息的仓单ID */
	private String haslisting;

	/**
	 * 获取仓单ID
	 * @return 仓单ID
	 */
	public String getWlid() {
	    return wlid;
	}

	/**
	 * 设定仓单ID
	 * @param wlid 仓单ID
	 */
	public void setWlid(String wlid) {
	    this.wlid = wlid;
	}

	/**
	 * 获取品种名称
	 * @return 品种名称
	 */
	public String getBreedname() {
	    return breedname;
	}

	/**
	 * 设定品种名称
	 * @param breedname 品种名称
	 */
	public void setBreedname(String breedname) {
	    this.breedname = breedname;
	}

	/**
	 * 获取类目ID
	 * @return 类目ID
	 */
	public Long getCategoryid() {
	    return categoryid;
	}

	/**
	 * 设定类目ID
	 * @param categoryid 类目ID
	 */
	public void setCategoryid(Long categoryid) {
	    this.categoryid = categoryid;
	}

	/**
	 * 获取品种ID
	 * @return 品种ID
	 */
	public Long getBreedid() {
	    return breedid;
	}

	/**
	 * 设定品种ID
	 * @param breedid 品种ID
	 */
	public void setBreedid(Long breedid) {
	    this.breedid = breedid;
	}

	/**
	 * 获取用户ID
	 * @return 用户ID
	 */
	public Long getUserid() {
	    return userid;
	}

	/**
	 * 设定用户ID
	 * @param userid 用户ID
	 */
	public void setUserid(Long userid) {
	    this.userid = userid;
	}

	/**
	 * 获取产地
	 * @return 产地
	 */
	public String getOrigin() {
	    return origin;
	}

	/**
	 * 设定产地
	 * @param origin 产地
	 */
	public void setOrigin(String origin) {
	    this.origin = origin;
	}

	/**
	 * 获取等级规格
	 * @return 等级规格
	 */
	public String getGrade() {
	    return grade;
	}

	/**
	 * 设定等级规格
	 * @param grade 等级规格
	 */
	public void setGrade(String grade) {
	    this.grade = grade;
	}

	/**
	 * 获取质押状态
	 * @return 质押状态
	 */
	public int getWlstate() {
	    return wlstate;
	}

	/**
	 * 设定质押状态
	 * @param wlstate 质押状态
	 */
	public void setWlstate(int wlstate) {
	    this.wlstate = wlstate;
	}

	/**
	 * 获取可挂牌数量
	 * @return 可挂牌数量
	 */
	public BigDecimal getWlsurplus() {
	    return wlsurplus;
	}

	/**
	 * 设定可挂牌数量
	 * @param wlsurplus 可挂牌数量
	 */
	public void setWlsurplus(BigDecimal wlsurplus) {
	    this.wlsurplus = wlsurplus;
	}

	/**
	 * 获取总量
	 * @return 总量
	 */
	public BigDecimal getWltotal() {
	    return wltotal;
	}

	/**
	 * 设定总量
	 * @param wltotal 总量
	 */
	public void setWltotal(BigDecimal wltotal) {
	    this.wltotal = wltotal;
	}

	/**
	 * 获取重量单位
	 * @return 重量单位
	 */
	public String getDictvalue() {
	    return dictvalue;
	}

	/**
	 * 设定重量单位
	 * @param dictvalue 重量单位
	 */
	public void setDictvalue(String dictvalue) {
	    this.dictvalue = dictvalue;
	}

	/**
	 * 获取仓库所在地
	 * @return 仓库所在地
	 */
	public String getWarehousename() {
	    return warehousename;
	}

	/**
	 * 设定仓库所在地
	 * @param warehousename 仓库所在地
	 */
	public void setWarehousename(String warehousename) {
	    this.warehousename = warehousename;
	}

	/**
	 * 获取仓库ID
	 * @return 仓库ID
	 */
	public String getWarehouseid() {
	    return warehouseid;
	}

	/**
	 * 设定仓库ID
	 * @param warehouseid 仓库ID
	 */
	public void setWarehouseid(String warehouseid) {
	    this.warehouseid = warehouseid;
	}

	/**
	 * 获取区域ID
	 * @return 区域ID
	 */
	public String getAreaid() {
	    return areaid;
	}

	/**
	 * 设定区域ID
	 * @param areaid 区域ID
	 */
	public void setAreaid(String areaid) {
	    this.areaid = areaid;
	}

	/**
	 * 获取入库时间
	 * @return 入库时间
	 */
	public Date getWlrkdate() {
	    return wlrkdate;
	}

	/**
	 * 设定入库时间
	 * @param wlrkdate 入库时间
	 */
	public void setWlrkdate(Date wlrkdate) {
	    this.wlrkdate = wlrkdate;
	}

	/**
	 * 获取用户账号
	 * @return 用户账号
	 */
	public String getUsername() {
	    return username;
	}

	/**
	 * 设定用户账号
	 * @param username 用户账号
	 */
	public void setUsername(String username) {
	    this.username = username;
	}

	/**
	 * 获取包装规格
	 * @return 包装规格
	 */
	public String getPackingway() {
	    return packingway;
	}

	/**
	 * 设定包装规格
	 * @param packingway 包装规格
	 */
	public void setPackingway(String packingway) {
	    this.packingway = packingway;
	}

	/**
	 * 获取合同编号
	 * @return 合同编号
	 */
	public String getContractnum() {
	    return contractnum;
	}

	/**
	 * 设定合同编号
	 * @param contractnum 合同编号
	 */
	public void setContractnum(String contractnum) {
	    this.contractnum = contractnum;
	}

	/**
	 * 获取收检日期
	 * @return 收检日期
	 */
	public Date getAcceptchecktime() {
	    return acceptchecktime;
	}

	/**
	 * 设定收检日期
	 * @param acceptchecktime 收检日期
	 */
	public void setAcceptchecktime(Date acceptchecktime) {
	    this.acceptchecktime = acceptchecktime;
	}

	/**
	 * 获取报告日期
	 * @return 报告日期
	 */
	public Date getReportdate() {
	    return reportdate;
	}

	/**
	 * 设定报告日期
	 * @param reportdate 报告日期
	 */
	public void setReportdate(Date reportdate) {
	    this.reportdate = reportdate;
	}

	/**
	 * 获取送检数量
	 * @return 送检数量
	 */
	public String getNumberofjc() {
	    return numberofjc;
	}

	/**
	 * 设定送检数量
	 * @param numberofjc 送检数量
	 */
	public void setNumberofjc(String numberofjc) {
	    this.numberofjc = numberofjc;
	}

	/**
	 * 获取图片集合
	 * @return 图片集合
	 */
	public List<BusiQualitypic> getPiclist() {
	    return piclist;
	}

	/**
	 * 设定图片集合
	 * @param piclist 图片集合
	 */
	public void setPiclist(List<BusiQualitypic> piclist) {
	    this.piclist = piclist;
	}

	/**
	 * 获取等级评定
	 * @return 等级评定
	 */
	public String getLevelEva() {
	    return levelEva;
	}

	/**
	 * 设定等级评定
	 * @param levelEva 等级评定
	 */
	public void setLevelEva(String levelEva) {
	    this.levelEva = levelEva;
	}

	/**
	 * 获取质检信息ID
	 * @return 质检信息ID
	 */
	public Long getQualityInfoId() {
	    return qualityInfoId;
	}

	/**
	 * 设定质检信息ID
	 * @param qualityInfoId 质检信息ID
	 */
	public void setQualityInfoId(Long qualityInfoId) {
	    this.qualityInfoId = qualityInfoId;
	}

	/**
	 * 获取质检报告图片
	 * @return 质检报告图片
	 */
	public String getZjReportPic() {
	    return zjReportPic;
	}

	/**
	 * 设定质检报告图片
	 * @param zjReportPic 质检报告图片
	 */
	public void setZjReportPic(String zjReportPic) {
	    this.zjReportPic = zjReportPic;
	}

	/**
	 * 获取质检项信息
	 * @return 质检项信息
	 */
	public List<BusiQualityItem> getBusiQualityItem() {
	    return busiQualityItem;
	}

	/**
	 * 设定质检项信息
	 * @param busiQualityItem 质检项信息
	 */
	public void setBusiQualityItem(List<BusiQualityItem> busiQualityItem) {
	    this.busiQualityItem = busiQualityItem;
	}

	/**
	 * 获取质检项信息分组
	 * @return 质检项信息分组
	 */
	public Map<String,List<BusiQualityItem>> getItemMap() {
	    return itemMap;
	}

	/**
	 * 设定质检项信息分组
	 * @param itemMap 质检项信息分组
	 */
	public void setItemMap(Map<String,List<BusiQualityItem>> itemMap) {
	    this.itemMap = itemMap;
	}

	/**
	 * 获取区域0
	 * @return 区域0
	 */
	public String getAreaid0() {
	    return areaid0;
	}

	/**
	 * 设定区域0
	 * @param areaid0 区域0
	 */
	public void setAreaid0(String areaid0) {
	    this.areaid0 = areaid0;
	}

	/**
	 * 获取区域1
	 * @return 区域1
	 */
	public String getAreaid1() {
	    return areaid1;
	}

	/**
	 * 设定区域1
	 * @param areaid1 区域1
	 */
	public void setAreaid1(String areaid1) {
	    this.areaid1 = areaid1;
	}

	/**
	 * 获取区域2
	 * @return 区域2
	 */
	public String getAreaid2() {
	    return areaid2;
	}

	/**
	 * 设定区域2
	 * @param areaid2 区域2
	 */
	public void setAreaid2(String areaid2) {
	    this.areaid2 = areaid2;
	}

	/**
	 * 获取是否有挂牌信息的仓单ID
	 * @return 是否有挂牌信息的仓单ID
	 */
	public String getHaslisting() {
	    return haslisting;
	}

	/**
	 * 设定是否有挂牌信息的仓单ID
	 * @param haslisting 是否有挂牌信息的仓单ID
	 */
	public void setHaslisting(String haslisting) {
	    this.haslisting = haslisting;
	}
	
}
