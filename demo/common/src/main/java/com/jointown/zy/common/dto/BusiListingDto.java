package com.jointown.zy.common.dto;

import java.math.BigDecimal;

import com.jointown.zy.common.model.BusiListing;


/**
 * 仓单挂牌Dto
 * @author Mr.songwei
 * 2014-12-27
 * 2015-4-25 update by wangjunhu
 */
public class BusiListingDto {
	
    /**
     * ZYC.BUSI_LISTING.LISTINGID (挂牌ID)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private String listingid;

    /**
     * ZYC.BUSI_LISTING.USERID (用户ID)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Long userid;

    /**
     * ZYC.BUSI_LISTING.BREEDID (品种ID)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Long breedid;

    /**
     * ZYC.BUSI_LISTING.WLID (仓单ID)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private String wlid;

    /**
     * ZYC.BUSI_LISTING.TITLE (挂牌内容)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private String title;

    /**
     * ZYC.BUSI_LISTING.LOWUNITPRICE (未开发票的挂牌单价)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private BigDecimal lowunitprice;

    /**
     * ZYC.BUSI_LISTING.BILLPRICE (开发票后的价格)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private BigDecimal billprice;

    /**
     * ZYC.BUSI_LISTING.LISTINGAMOUNT (挂牌数量)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private BigDecimal listingamount;

    /**
     * ZYC.BUSI_LISTING.SURPLUSES (挂牌商品现存量)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private BigDecimal surpluses;
    
    /**
     * ZYC.BUSI_LISTING.MINSALES (最低起订数量)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private BigDecimal minsales;
    
    /**
     * ZYC.BUSI_LISTING.LISTINGTIMELIMIT (挂牌期限)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Short listingtimelimit;

    /**
     * ZYC.BUSI_LISTING.HASBILL (是否开据发票(默认不开据发票,0不提供，1提供))
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Short hasbill;
    
    /**
     * ZYC.BUSI_LISTING.LISTINGFLAG (挂牌标志（0待审核，1审核失败，2审核通过，3挂牌数量已卖完，4取消挂牌）)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Short listingflag;
    
    /**
     * 药材详情
     */
	private String content;
	
	private BusiListing busiListing;
	
	public BusiListingDto() {
		super();
		busiListing = new BusiListing();
	}

	public String getListingid() {
		return listingid;
	}

	public void setListingid(String listingid) {
		this.listingid = listingid;
		busiListing.setListingid(listingid);
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
		busiListing.setUserid(userid);
	}

	public Long getBreedid() {
		return breedid;
	}

	public void setBreedid(Long breedid) {
		this.breedid = breedid;
		busiListing.setBreedid(breedid);
	}

	public String getWlid() {
		return wlid;
	}

	public void setWlid(String wlid) {
		this.wlid = wlid;
		busiListing.setWlid(wlid);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		busiListing.setTitle(title);
	}

	public BigDecimal getLowunitprice() {
		return lowunitprice;
	}

	public void setLowunitprice(BigDecimal lowunitprice) {
		this.lowunitprice = lowunitprice;
		busiListing.setLowunitprice(lowunitprice);
	}

	public BigDecimal getBillprice() {
		return billprice;
	}

	public void setBillprice(BigDecimal billprice) {
		this.billprice = billprice;
		busiListing.setBillprice(billprice);
	}

	public BigDecimal getListingamount() {
		return listingamount;
	}

	public void setListingamount(BigDecimal listingamount) {
		this.listingamount = listingamount;
		busiListing.setListingamount(listingamount);
	}

	public BigDecimal getSurpluses() {
		return surpluses;
	}

	public void setSurpluses(BigDecimal surpluses) {
		this.surpluses = surpluses;
		busiListing.setSurpluses(surpluses);
	}

	public BigDecimal getMinsales() {
		return minsales;
	}

	public void setMinsales(BigDecimal minsales) {
		this.minsales = minsales;
		busiListing.setMinsales(minsales);
	}

	public Short getListingtimelimit() {
		return listingtimelimit;
	}

	public void setListingtimelimit(Short listingtimelimit) {
		this.listingtimelimit = listingtimelimit;
		busiListing.setListingtimelimit(listingtimelimit);
	}

	public Short getHasbill() {
		return hasbill;
	}

	public void setHasbill(Short hasbill) {
		this.hasbill = hasbill;
		busiListing.setHasbill(hasbill);
	}

	public Short getListingflag() {
		return listingflag;
	}

	public void setListingflag(Short listingflag) {
		this.listingflag = listingflag;
		busiListing.setListingflag(listingflag);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BusiListing getBusiListing() {
		return busiListing;
	}
	
}
