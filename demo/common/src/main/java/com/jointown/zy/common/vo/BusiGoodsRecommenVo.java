package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * 描述： 挂牌商品推荐信息VO<br/>
 * 
 * 日期： 2015年1月8日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public class BusiGoodsRecommenVo implements Serializable{

	private static final long serialVersionUID = -5224475662083034494L;

	/** 推荐商品ID */
	private String listingid;
	
	/** 推荐商品标题 */
	private String title;
	
	/** 推荐商品价格 */
	private BigDecimal price;
	
	/** 推荐商品单位 */
	private String unitName;
	
	/** 推荐商品图片（散货） */
	private String picPath;

	/**
	 * 取得推荐商品ID
	 * @return 推荐商品ID
	 */
	public String getListingid() {
	    return listingid;
	}

	/**
	 * 设定推荐商品ID
	 * @param listingid 推荐商品ID
	 */
	public void setListingid(String listingid) {
	    this.listingid = listingid;
	}

	/**
	 * 取得推荐商品标题
	 * @return 推荐商品标题
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * 设定推荐商品标题
	 * @param title 推荐商品标题
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 取得推荐商品价格
	 * @return 推荐商品价格
	 */
	public BigDecimal getPrice() {
	    return price;
	}

	/**
	 * 设定推荐商品价格
	 * @param price 推荐商品价格
	 */
	public void setPrice(BigDecimal price) {
	    this.price = price;
	}

	/**
	 * 取得推荐商品单位
	 * @return 推荐商品单位
	 */
	public String getUnitName() {
	    return unitName;
	}

	/**
	 * 设定推荐商品单位
	 * @param unitName 推荐商品单位
	 */
	public void setUnitName(String unitName) {
	    this.unitName = unitName;
	}

	/**
	 * 取得推荐商品图片（散货）
	 * @return 推荐商品图片（散货）
	 */
	public String getPicPath() {
	    return picPath;
	}

	/**
	 * 设定推荐商品图片（散货）
	 * @param picPath 推荐商品图片（散货）
	 */
	public void setPicPath(String picPath) {
	    this.picPath = picPath;
	}
}
