package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 挂牌日志表
 * 
 * @author Mr.song
 *
 * @date 2015-3-13
 */
@SuppressWarnings("serial")
public class BusiListingLog implements Serializable  {
	
	public BusiListingLog(){
		
	}
	
    /**
     * ZYC.BUSI_LISTING_LOG.ID (挂牌日志编号)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private Long id;

    /**
     * ZYC.BUSI_LISTING_LOG.USERID (当前操作者ID)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private Long userid;


    /**
     * ZYC.BUSI_LISTING_LOG.WLID (仓单ID)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private String wlid;

    /**
     * ZYC.BUSI_LISTING_LOG.LISTINGID (挂牌ID)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private String listingid;

    /**
     * ZYC.BUSI_LISTING_LOG.OPTYPE (操作类型(添加，修改，异常))
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private Short optype;

    /**
     * ZYC.BUSI_LISTING_LOG.CREATETIME (创建日期)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private Date createtime;

    /**
     * ZYC.BUSI_LISTING_LOG.UPDATETIME (修改日期)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private Date updatetime;


    /**
     * ZYC.BUSI_LISTING_LOG.REMARKS (日志描述)
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    private String remarks;
    
    /**
     * ZYC.BUSI_LISTING_LOG.LISTING_SNAPSHOT (挂牌快照)
     */
    private String listingSnapshot;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    
    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public String getListingid() {
        return listingid;
    }

    public void setListingid(String listingid) {
        this.listingid = listingid;
    }

    public Short getOptype() {
        return optype;
    }

    public void setOptype(Short optype) {
        this.optype = optype;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getListingSnapshot() {
		return listingSnapshot;
	}

	public void setListingSnapshot(String listingSnapshot) {
		this.listingSnapshot = listingSnapshot;
	}
	
}