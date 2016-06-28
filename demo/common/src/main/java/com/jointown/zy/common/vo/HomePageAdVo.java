package com.jointown.zy.common.vo;

import java.util.Date;

import com.jointown.zy.common.enums.HomepageAdEnum;

/**
 * @author guoyb
 * 2015年3月18日 上午11:30:55
 */
public class HomePageAdVo {
	
	//广告id
	private Integer adid;
	
	//大分类
	private String category;
	
	//类型，代表具体的位置
	private String type;
	
	//排序
	private String sortno;
	
	//中文类型
	private String chinesetype;
	
	//更新时间
	private Date updatetime;
	
	//图片url
	private String picurl;
	
	//最后更新人
	private String updater;
	
	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	//图片描述
	private String alt;
	
	//文章url
	private String url;
	
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getChinesetype() {
		return chinesetype;
	}

	public void setChinesetype(String chinesetype) {
		String chinese = HomepageAdEnum.toMap().get(chinesetype);
		this.chinesetype = chinese;
	}

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getSortno() {
		return sortno;
	}

	public void setSortno(String sortno) {
		this.sortno = sortno;
	}
	

}
