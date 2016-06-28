package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 挂牌检索vo
 * @author LiuPiao
 */
@XmlRootElement
public class BusiListingSearchVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4175167728651826799L;
	/** 挂牌ID */
	
	private String listingId;
	/** 挂牌标题 */
	
	private String title;
	/** 是否要发票， 是/否 */
	
	private String hasBill;
	/** 挂牌状态（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌） */
	
	private Integer listingFlag;
	/** 创建日期 */
	
	private Date createTime;
	
	private String createTimeString;
	/** 挂牌时间(BOSS审核) */
	
	private Date examineTime;
	
	private String examineTimeString;
	/** 最低起订数量 */
	
    private Double minSales;
    /** 挂牌剩余量 */
	
    private Double listingSurplus;
    /** 挂牌单价 */
	
    private Double price;
    /** 成交笔数 */
	
    private Integer dealCount;
    /** 挂牌图片 */
	
    private String listingPic;
    
    /** 仓单ID */
	
    private String wlid;
    /** 产地 */
	
    private String origin;
    /** 所在仓库 */
	
    private String warehouseName;
    /** 等级规格 */
	
    private String grade;
    
    
    /** 品种ID */
	
    private String breedId;
    /** 品种名称 */
	
    private String breedName;
    /** 品种别名 */
	
    private String breedCname;
	
    /** 类目ID */
   private String categoryId;
   /** 类目名称 */
   private String categoryName;
    
    
    /** 计量单位编码 */
	
    private String wlunit;
    /** 计量单位名称 */
	
    private String wlunitName;
    
    /** suggest的结果**/
    private String suggestedValue;
    
    /** 已下单数量*/
	private Integer ordersCount;
    
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
	public BusiListingSearchVo setListingId(String listingId) {
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
	public BusiListingSearchVo setTitle(String title) {
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
	public BusiListingSearchVo setHasBill(String hasBill) {
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
	public BusiListingSearchVo setListingFlag(Integer listingFlag) {
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
	public BusiListingSearchVo setCreateTime(Date createTime) {
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
	public BusiListingSearchVo setExamineTime(Date examineTime) {
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
	public BusiListingSearchVo setMinSales(Double minSales) {
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
	public BusiListingSearchVo setListingSurplus(Double listingSurplus) {
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
	public BusiListingSearchVo setPrice(Double price) {
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
	public BusiListingSearchVo setDealCount(Integer dealCount) {
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
	public BusiListingSearchVo setListingPic(String listingPic) {
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
	public BusiListingSearchVo setWlid(String wlid) {
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
	public BusiListingSearchVo setOrigin(String origin) {
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
	public BusiListingSearchVo setWarehouseName(String warehouseName) {
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
	public BusiListingSearchVo setGrade(String grade) {
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
	public BusiListingSearchVo setBreedId(String breedId) {
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
	public BusiListingSearchVo setBreedName(String breedName) {
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
	public BusiListingSearchVo setBreedCname(String breedCname) {
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
	public BusiListingSearchVo setWlunit(String wlunit) {
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
	public BusiListingSearchVo setWlunitName(String wlunitName) {
	    this.wlunitName = wlunitName;
	    return this;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public BusiListingSearchVo setCategoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public BusiListingSearchVo setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}
	public String getCreateTimeString() {
		return createTimeString;
	}
	public BusiListingSearchVo setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
		return this;
	}
	public String getExamineTimeString() {
		return examineTimeString;
	}
	public BusiListingSearchVo setExamineTimeString(String examineTimeString) {
		this.examineTimeString = examineTimeString;
		return this;
	}
	public String getSuggestedValue() {
		return suggestedValue;
	}
	public BusiListingSearchVo setSuggestedValue(String suggestedValue) {
		this.suggestedValue = suggestedValue;
		return this;
	}
	public Integer getOrdersCount() {
		return ordersCount;
	}
	public void setOrdersCount(Integer ordersCount) {
		this.ordersCount = ordersCount;
	}
	    
}
