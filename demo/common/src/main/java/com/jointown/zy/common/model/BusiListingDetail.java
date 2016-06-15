package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiListingDetail implements Serializable {
    /**
     * ZYC.BUSI_LISTING_DETAIL.DETAILID (挂牌详情ID)
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    private Long detailid;

    /**
     * ZYC.BUSI_LISTING_DETAIL.LISTINGID (挂牌ID)
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    private String listingid;

    /**
     * ZYC.BUSI_LISTING_DETAIL.CREATETIME (创建日期)
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    private Date createtime;

    /**
     * ZYC.BUSI_LISTING_DETAIL.UPDATETIME (修改日期)
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    private Date updatetime;

    /**
     * ZYC.BUSI_LISTING_DETAIL.CONTENT (商品详情)
     * @ibatorgenerated 2014-12-27 13:22:22
     */
    private String content;
    
    public BusiListingDetail build(String listingId,String content){
    	Date date = new Date();
    	setListingid(listingId);
		setContent(content);
		setCreatetime(date);
		setUpdatetime(date);
		return this;
    }

    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
    }

    public String getListingid() {
        return listingid;
    }

    public void setListingid(String listingid) {
        this.listingid = listingid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}