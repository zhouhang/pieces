package com.jointown.zy.common.solr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class SolrListingVo implements Serializable {
	private static final long serialVersionUID = 6107528073820114278L;
	
	/** 挂牌ID,  Solr文档uniqueKey */
	@Field
	private String listingId;
	/** 挂牌标题 */
	@Field
	private String title;
	/** 是否要发票， 是/否 */
	@Field
	private String hasBill;
	/** 挂牌状态（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌） */
	@Field
	private Integer listingFlag;
	/** 创建日期 */
	@Field
	private Date createTime;
	/** 挂牌时间(BOSS审核) */
	@Field
	private Date examineTime;
	/** 最低起订数量 */
	@Field
    private Double minSales;
    /** 挂牌剩余量 */
	@Field
    private Double listingSurplus;
    /** 挂牌单价 */
	@Field
    private Double price;
    /** 成交笔数 */
	@Field
    private Integer dealCount;
    /** 挂牌图片 */
	@Field
    private String listingPic;
    
    /** 仓单ID */
	@Field
    private String wlid;
    /** 产地 */
	@Field
    private String origin;
    /** 所在仓库 */
	@Field
    private String warehouseName;
    /** 等级规格 */
	@Field
    private String grade;
    
    
    /** 品种ID */
	@Field
    private String breedId;
    /** 品种名称 */
	@Field
    private String breedName;
    /** 品种别名 */
	@Field
    private String breedCname;
	
	
	 /** 类目ID */
	@Field
    private String categoryId;
    /** 类目名称 */
	@Field
    private String categoryName;
   
    
    /** 计量单位编码 */
	@Field
    private String wlunit;
    /** 计量单位名称 */
	@Field
    private String wlunitName;
	/** 已下单数量*/
	@Field
	private Integer ordersCount;
	
	
	/**
	 * 初始化
	 */
	public SolrListingVo() {
		
	}
	
    
	/**
	 * 取得 挂牌ID,  Solr文档uniqueKey
	 * @return 挂牌ID,  Solr文档uniqueKey
	 */
	public String getListingId() {
		return listingId;
	}
	/**
	 * 設定 挂牌ID,  Solr文档uniqueKey
	 * @param listingId 挂牌ID,  Solr文档uniqueKey
	 */
	public SolrListingVo setListingId(String listingId) {
		this.listingId = listingId;
		return this;
	}
	/**
	 * 取得 挂牌标题
	 * @return 挂牌标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 設定 挂牌标题
	 * @param title 挂牌标题
	 */
	public SolrListingVo setTitle(String title) {
		this.title = title;
		return this;
	}
	/**
	 * 取得 是否要发票， 是/否
	 * @return 是否要发票， 是/否
	 */
	public String getHasBill() {
		return hasBill;
	}
	
	/**
	 * 設定 是否要发票， 是/否
	 * @param hasBill 是否要发票， 是/否
	 */
	public SolrListingVo setHasBill(String hasBill) {
		this.hasBill = hasBill;
		return this;
	}
	/**
	 * 取得 挂牌状态（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌）
	 * @return 挂牌状态（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌）
	 */
	public Integer getListingFlag() {
		return listingFlag;
	}
	/**
	 * 設定 挂牌状态（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌）
	 * @param listingFlag 挂牌状态（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌）
	 */
	public SolrListingVo setListingFlag(Integer listingFlag) {
		this.listingFlag = listingFlag;
		return this;
	}
	/**
	 * 取得 创建日期
	 * @return 创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 設定 创建日期
	 * @param createTime 创建日期
	 */
	public SolrListingVo setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	/**
	 * 取得 挂牌时间(BOSS审核)
	 * @return 挂牌时间(BOSS审核)
	 */
	public Date getExamineTime() {
		return examineTime;
	}
	/**
	 * 設定 挂牌时间(BOSS审核)
	 * @param examineTime 挂牌时间(BOSS审核)
	 */
	public SolrListingVo setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
		return this;
	}
	/**
	 * 取得 最低起订数量
	 * @return 最低起订数量
	 */
	public Double getMinSales() {
		return minSales;
	}
	/**
	 * 設定 最低起订数量
	 * @param minSales 最低起订数量
	 */
	public SolrListingVo setMinSales(Double minSales) {
		this.minSales = minSales;
		return this;
	}
	/**
	 * 取得 挂牌剩余量
	 * @return 挂牌剩余量
	 */
	public Double getListingSurplus() {
		return listingSurplus;
	}
	/**
	 * 設定 挂牌剩余量
	 * @param listingSurplus 挂牌剩余量
	 */
	public SolrListingVo setListingSurplus(Double listingSurplus) {
		this.listingSurplus = listingSurplus;
		return this;
	}
	/**
	 * 取得 挂牌单价
	 * @return 挂牌单价
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 設定 挂牌单价
	 * @param price 挂牌单价
	 */
	public SolrListingVo setPrice(Double price) {
		this.price = price;
		return this;
	}
	/**
	 * 取得 成交笔数
	 * @return 成交笔数
	 */
	public Integer getDealCount() {
		return dealCount;
	}
	/**
	 * 設定 成交笔数
	 * @param dealCount 成交笔数
	 */
	public SolrListingVo setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
		return this;
	}
	/**
	 * 取得 挂牌图片
	 * @return 挂牌图片
	 */
	public String getListingPic() {
		return listingPic;
	}
	/**
	 * 設定 挂牌图片
	 * @param listingPic 挂牌图片
	 */
	public SolrListingVo setListingPic(String listingPic) {
		this.listingPic = listingPic;
		return this;
	}
	/**
	 * 取得 仓单ID
	 * @return 仓单ID
	 */
	public String getWlid() {
		return wlid;
	}
	/**
	 * 設定 仓单ID
	 * @param wlid 仓单ID
	 */
	public SolrListingVo setWlid(String wlid) {
		this.wlid = wlid;
		return this;
	}
	/**
	 * 取得 产地
	 * @return 产地
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * 設定 产地
	 * @param origin 产地
	 */
	public SolrListingVo setOrigin(String origin) {
		this.origin = origin;
		return this;
	}
	/**
	 * 取得 所在仓库
	 * @return 所在仓库
	 */
	public String getWarehouseName() {
		return warehouseName;
	}
	/**
	 * 設定 所在仓库
	 * @param warehouseName 所在仓库
	 */
	public SolrListingVo setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
		return this;
	}
	/**
	 * 取得 等级规格
	 * @return 等级规格
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * 設定 等级规格
	 * @param grade 等级规格
	 */
	public SolrListingVo setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	/**
	 * 取得 品种ID
	 * @return 品种ID
	 */
	public String getBreedId() {
		return breedId;
	}
	/**
	 * 設定 品种ID
	 * @param breedCode 品种ID
	 */
	public SolrListingVo setBreedId(String breedId) {
		this.breedId = breedId;
		return this;
	}
	/**
	 * 取得 品种名称
	 * @return 品种名称
	 */
	public String getBreedName() {
		return breedName;
	}
	/**
	 * 設定 品种名称
	 * @param breedName 品种名称
	 */
	public SolrListingVo setBreedName(String breedName) {
		this.breedName = breedName;
		return this;
	}
	/**
	 * 取得 品种别名
	 * @return 品种别名
	 */
	public String getBreedCname() {
		return breedCname;
	}
	/**
	 * 設定 品种别名
	 * @param breedCname 品种别名
	 */
	public SolrListingVo setBreedCname(String breedCname) {
		this.breedCname = breedCname;
		return this;
	}
	/**
	 * 取得 计量单位编码
	 * @return 计量单位编码
	 */
	public String getWlunit() {
	    return wlunit;
	}
	/**
	 * 設定 计量单位编码
	 * @param wlunit 计量单位编码
	 */
	public SolrListingVo setWlunit(String wlunit) {
	    this.wlunit = wlunit;
	    return this;
	}
	/**
	 * 取得 计量单位名称
	 * @return 计量单位名称
	 */
	public String getWlunitName() {
	    return wlunitName;
	}
	/**
	 * 設定 计量单位名称
	 * @param wlunitName 计量单位名称
	 */
	public SolrListingVo setWlunitName(String wlunitName) {
	    this.wlunitName = wlunitName;
	    return this;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public SolrListingVo setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public SolrListingVo setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}


	public Integer getOrdersCount() {
		return ordersCount;
	}


	public void setOrdersCount(Integer ordersCount) {
		this.ordersCount = ordersCount;
	}
    
}
