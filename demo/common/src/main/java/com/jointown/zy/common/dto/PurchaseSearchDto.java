/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: PurchaseSearchDto
 * @Description: 采购信息搜索Dto
 * @Author: 赵航
 * @Date: 2015年10月15日
 * @Version: 1.0
 */
public class PurchaseSearchDto extends BaseDto{
	
	private static final long serialVersionUID = 2741242570085668305L;
	
	/** 品种名称 */
	private String breedName;
	
	/** 品种匹配方式 */
	private String breedNameMatchType;
	
	/** 过期时间排序 */
	private String expireTimeSort;

	/**
	 * 获取品种名称
	 * @return 品种名称
	 */
	public String getBreedName() {
	    return breedName;
	}

	/**
	 * 设定品种名称
	 * @param breedName 品种名称
	 */
	public void setBreedName(String breedName) {
	    this.breedName = breedName;
	}

	/**
	 * 获取品种匹配方式
	 * @return 品种匹配方式
	 */
	public String getBreedNameMatchType() {
	    return breedNameMatchType;
	}

	/**
	 * 设定品种匹配方式
	 * @param breedNameMatchType 品种匹配方式
	 */
	public void setBreedNameMatchType(String breedNameMatchType) {
	    this.breedNameMatchType = breedNameMatchType;
	}

	/**
	 * 获取过期时间排序
	 * @return 过期时间排序
	 */
	public String getExpireTimeSort() {
	    return expireTimeSort;
	}

	/**
	 * 设定过期时间排序
	 * @param expireTimeSort 过期时间排序
	 */
	public void setExpireTimeSort(String expireTimeSort) {
	    this.expireTimeSort = expireTimeSort;
	}
}
