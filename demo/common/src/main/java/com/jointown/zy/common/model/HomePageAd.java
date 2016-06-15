/**
 * @author guoyb
 * 2015年3月18日 下午12:21:22
 */
package com.jointown.zy.common.model;

import java.util.Date;

/**
 * @author guoyb
 * 2015年3月18日 下午12:21:22
 */
public class HomePageAd {
	//广告id
	private Integer adid;
	
	//大分类
	private String category;
	
	//类型
	private int type;
	
	//类型
	private int sortno;
	
	private String url;

	private String picurl;
	
	//图片描述
	private String alt;
	
	//状态 0-正式 1-删除
	private Short status;
	
	//创建时间
	private Date createtime;
	
	//更新时间
	private Date updatetime;
	
	//创建人Id
	private Integer creater;
	
	//更新人Id
	private Integer updater;

	/**
	 * @return the adid
	 */
	public Integer getAdid() {
		return adid;
	}

	/**
	 * @param adid the adid to set
	 */
	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the picurl
	 */
	public String getPicurl() {
		return picurl;
	}

	/**
	 * @param picurl the picurl to set
	 */
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * @param alt the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
}
