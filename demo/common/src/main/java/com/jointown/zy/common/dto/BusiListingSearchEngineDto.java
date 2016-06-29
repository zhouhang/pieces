package com.jointown.zy.common.dto;


/**
 * 挂牌搜索Dto
 */
public class BusiListingSearchEngineDto extends BaseDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/************************************************************** 查询条件************************************************** */
	/** 分类名称*/
	private String categoryName;
	
	/** 分类ID*/
	private String categoryId;
	
    /** 品种名称 */
    private String breedId;
    
	/** 分类ID*/
	private String breedName;
	
    /** 品种别名 */
    private String breedCname;
    /** 所在仓库 */
    private String warehouseName;
    /** 等级规格 */
    private String grade;
    /** 产地 */
    private String origin;
	
    /************************************************************** 范围查询************************************************** */
	/** 挂牌时间范围(BOSS审核) */
	private String[] examineTimeRange;
    /** 挂牌单价 范围*/
    private String[] priceRange;
    /** 产地 字符串*/
    private String originText;
    
    /************************************************************** 排序条件************************************************** */
    /** 库存量*/
    private String sortListingSurplus;
    /** 单价*/
    private String sortPrice;
    /** 挂牌日期*/
    private String sortExamineTime;

    
	/** 挂牌ID*/
	private String listingId;
	/** 挂牌标题 */
	private String title;
    
	/************************************************************** 关键字查询************************************************** */
	/** 关键字*/
	private String keyWords;
	
	/**查询类型:  BREED,CATEGORY,KEYWORDS*/
	private String searchType;
	
	/**结果类型:  BREED,CATEGORY,*/
	private String resultType;
	
	/**页码号*/
	private Integer pageNo;
	
	/**查询ID:  BREED,CATEGORY的ID*/
	private String searchId;
	
	/**关键字查询模式*/
	private String mode;
	
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}


	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	/**
	 * @return the breedId
	 */
	public String getBreedId() {
		return breedId;
	}


	/**
	 * @param breedId the breedId to set
	 */
	public void setBreedId(String breedId) {
		this.breedId = breedId;
	}

	public String getBreedName() {
		return breedName;
	}


	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}


	public String getBreedCname() {
		return breedCname;
	}


	public void setBreedCname(String breedCname) {
		this.breedCname = breedCname;
	}


	public String getWarehouseName() {
		return warehouseName;
	}


	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String[] getExamineTimeRange() {
		return examineTimeRange;
	}


	public void setExamineTimeRange(String[] examineTimeRange) {
		this.examineTimeRange = examineTimeRange;
	}


	public String[] getPriceRange() {
		return priceRange;
	}


	public void setPriceRange(String[] priceRange) {
		this.priceRange = priceRange;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getSortListingSurplus() {
		return sortListingSurplus;
	}


	public void setSortListingSurplus(String sortListingSurplus) {
		this.sortListingSurplus = sortListingSurplus;
	}


	public String getSortPrice() {
		return sortPrice;
	}


	public void setSortPrice(String sortPrice) {
		this.sortPrice = sortPrice;
	}


	public String getSortExamineTime() {
		return sortExamineTime;
	}


	public void setSortExamineTime(String sortExamineTime) {
		this.sortExamineTime = sortExamineTime;
	}


	public String getListingId() {
		return listingId;
	}


	public void setListingId(String listingId) {
		this.listingId = listingId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getKeyWords() {
		return keyWords;
	}


	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}


	public String getResultType() {
		return resultType;
	}


	public void setResultType(String resultType) {
		this.resultType = resultType;
	}


	public String getSearchId() {
		return searchId;
	}


	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}


	public String getOriginText() {
		return originText;
	}


	public void setOriginText(String originText) {
		this.originText = originText;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}
    
}
