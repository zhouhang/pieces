package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 描述： 挂牌收藏的对象模型<br/>
 * 
 * 日期： 2015年1月9日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public class BusiCollection implements Serializable{

	private static final long serialVersionUID = -4712084193331745598L;
	
	/** 收藏ID */
	private Long cindex;
	
	/** 收藏者ID */
	private Long userid;
	
	/** 品种ID add by Mr.songwei 2015.1.13 16:38 方便solr搜索*/  
	private Long breedcode;
	
	public Long getBreedcode() {
		return breedcode;
	}

	public void setBreedcode(Long breedcode) {
		this.breedcode = breedcode;
	}

	/** 仓单ID */
	private String wlid;
	
	/** 挂牌ID */
	private String listingid;
	
	/** 收藏标识 */
	private Short cstate;
	
	/** 创建时间 */
	private Date createtime;
	
	/** 更新时间 */
	private Date updatetime;

	/**
	 * 取得收藏ID
	 * @return 收藏ID
	 */
	public Long getCindex() {
	    return cindex;
	}

	/**
	 * 设定收藏ID
	 * @param cindex 收藏ID
	 */
	public void setCindex(Long cindex) {
	    this.cindex = cindex;
	}

	/**
	 * 取得收藏者ID
	 * @return 收藏者ID
	 */
	public Long getUserid() {
	    return userid;
	}

	/**
	 * 设定收藏者ID
	 * @param userid 收藏者ID
	 */
	public void setUserid(Long userid) {
	    this.userid = userid;
	}

	/**
	 * 取得仓单ID
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
	 * 取得挂牌ID
	 * @return 挂牌ID
	 */
	public String getListingid() {
	    return listingid;
	}

	/**
	 * 设定挂牌ID
	 * @param listingid 挂牌ID
	 */
	public void setListingid(String listingid) {
	    this.listingid = listingid;
	}

	/**
	 * 取得收藏标识
	 * @return 收藏标识
	 */
	public Short getCstate() {
	    return cstate;
	}

	/**
	 * 设定收藏标识
	 * @param cstate 收藏标识
	 */
	public void setCstate(Short cstate) {
	    this.cstate = cstate;
	}

	/**
	 * 取得创建时间
	 * @return 创建时间
	 */
	public Date getCreatetime() {
	    return createtime;
	}

	/**
	 * 设定创建时间
	 * @param createtime 创建时间
	 */
	public void setCreatetime(Date createtime) {
	    this.createtime = createtime;
	}

	/**
	 * 取得更新时间
	 * @return 更新时间
	 */
	public Date getUpdatetime() {
	    return updatetime;
	}

	/**
	 * 设定更新时间
	 * @param updatetime 更新时间
	 */
	public void setUpdatetime(Date updatetime) {
	    this.updatetime = updatetime;
	}

}
