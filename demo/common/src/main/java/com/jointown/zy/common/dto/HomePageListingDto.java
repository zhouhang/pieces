/**
 * @author guoyb
 * 2015年3月19日 下午4:20:18
 */
package com.jointown.zy.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author guoyb 2015年3月19日 下午4:20:18
 */
public class HomePageListingDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private Integer listing_Id;

	// 标题
	private String title;

	// 挂牌用户
	private String username;

	// 用户id
	private String userId;

	/**
	 * @return the dictvalue
	 */
	public String getDictvalue() {
		return dictvalue;
	}

	/**
	 * @param dictvalue
	 *            the dictvalue to set
	 */
	public void setDictvalue(String dictvalue) {
		this.dictvalue = dictvalue;
	}

	// 挂牌id
	private String listingid;

	// 药材名称
	private String name;

	// 价格
	private double price;

	// 单位
	private String dictvalue;

	// 产地
	private String origin;

	// 规格
	private String grade;

	// 类型，代表位置
	private Integer type;

	// 挂牌数量
	private double listingamount;

	// 排序
	private Integer sortno;

	// 图片url
	private String picurl;

	// 图片描述
	private String alt;

	// 交付地
	private String area;

	// 状态 0-使用,1-删除
	private Integer status;

	// 创建人id
	private Integer creater_id;

	// 更新人id
	private Integer updater_id;

	// 创建时间
	private Date create_time;

	// 更新时间
	private Date update_time;

	/** 首頁2.0全国大仓数据新增  **/
	// 库存量
	private String stockAmount;
	// 所在仓库
	private String inWarehouse;
	// 日期
	private Date updateTime;
	/** 首頁2.0全国大仓数据新增  **/

	public Integer getListing_Id() {
		return listing_Id;
	}

	public void setListing_Id(Integer listing_Id) {
		this.listing_Id = listing_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getListingid() {
		return listingid;
	}

	public void setListingid(String listingid) {
		this.listingid = listingid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSortno() {
		return sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCreater_id() {
		return creater_id;
	}

	public void setCreater_id(Integer creater_id) {
		this.creater_id = creater_id;
	}

	public Integer getUpdater_id() {
		return updater_id;
	}

	public void setUpdater_id(Integer updater_id) {
		this.updater_id = updater_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public double getListingamount() {
		return listingamount;
	}

	public void setListingamount(double listingamount) {
		this.listingamount = listingamount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}

	public String getInWarehouse() {
		return inWarehouse;
	}

	public void setInWarehouse(String inWarehouse) {
		this.inWarehouse = inWarehouse;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
