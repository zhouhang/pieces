/**
 * @author guoyb
 * 2015年3月19日 下午4:20:18
 */
package com.jointown.zy.common.dto;

/**
 * @author Mr.song 2015年3月20日 下午4:20:18
 */
public class HomePageAdDto extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4153361601561223527L;

	//广告id
	private Integer adid;
	
	public Integer getAdid() {
		return adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	//类型
	private int type;
	
	//类型
	private int sortno;
	
	private String url;

	private String picurl;
	
	//图片描述
	private String alt;

	public int getType() {
		return type;
	}

	public int getSortno() {
		return sortno;
	}

	public void setSortno(int sortno) {
		this.sortno = sortno;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
}
