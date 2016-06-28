package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.exception.CommonErrorException;
import com.jointown.zy.common.exception.ErrorRepository;

public class BusiListing implements Serializable {
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
     * ZYC.BUSI_LISTING.CREATETIME (创建日期)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Date createtime;

    /**
     * ZYC.BUSI_LISTING.UPDATETIME (修改日期)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Date updatetime;

    /**
     * ZYC.BUSI_LISTING.EXAMINETIME (挂牌时间(BOSS审核))
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Date examinetime;

    /**
     * ZYC.BUSI_LISTING.ISRECOMMEND (是否平台推荐（0默认，1表示平台推荐）)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Short isrecommend;

    /**
     * ZYC.BUSI_LISTING.RECOMMEND (平台推荐日期)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private Date recommend;

    /**
     * ZYC.BUSI_LISTING.EXAMINECONTENT (BOSS审核不通过的提示语)
     * @ibatorgenerated 2015-01-02 14:52:44
     */
    private String examinecontent;
    
    /**
     * 关联的仓单实体
     */
    private BusiWhlist whListEntity;
    
    private void avoidWhListEmpty() throws Exception{
    	if(getWhListEntity()==null){
    		throw new CommonErrorException(ErrorRepository.NOT_EXIST,"仓单");
    	}
    }
    
    /**
     * 
     * @Description: 扣减挂牌可摘数量
     * @Author: robin.liu
     * @Date: 2015年8月26日
     * @param toMinus
     * @return
     */
    public boolean minusSurplus(BigDecimal toMinus){
    	BigDecimal surplus = getSurpluses().subtract(toMinus);
		if(surplus.compareTo(BigDecimal.ZERO)>0){
			setSurpluses(surplus);
			return true;
		}
		return false;
    }
    
    /**
	 * 
	 * @Description: 添加挂牌可摘数量
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 * @param toAdd
	 * @return
	 */
	public boolean addSurplus(BigDecimal toAdd){
		BigDecimal surplus = getSurpluses().add(toAdd);
		if(surplus.compareTo(getListingamount())<=0){
			setSurpluses(surplus);
			return true;
		}
		return false;
	}
    
    /**
     * 
     * @Description: 挂牌surplus是否大于某个值，默认是0
     * @Author: robin.liu
     * @Date: 2015年8月25日
     * @return
     */
    public boolean surplusBiggerThan(BigDecimal... decimal){
    	return getSurpluses().compareTo(ArrayUtils.isEmpty(decimal)?BigDecimal.ZERO:decimal[0])>0;
    }
    
    /**
     * 
     * @Description: 新增挂牌
     * @Author: robin.liu
     * @Date: 2015年8月26日
     * @return
     */
    public BusiListing add() throws Exception{
    	avoidWhListEmpty();
		whListEntity.minusSurplus(this.getListingamount())
					.renewUpdateTime();
		Date date = new Date();
		this.setIsrecommend(Short.parseShort("0"))//设置待推荐
			.setSurpluses(getListingamount())//设置可摘数量为挂牌总量
			.setCreatetime(date)
			.setUpdatetime(date)
			.setListingflag(Short.valueOf(BusiListingFlagEnum.AUDIT_WAITING.getCode()));
		return this;
	}
    
    /**
     * 
     * @Description: 修改挂牌
     * @Author: robin.liu
     * @Date: 2015年8月26日
     * @return
     * @throws Exception
     */
    public BusiListing update() throws Exception{
    	if(isAuditFailure()){//审核失败
    		avoidWhListEmpty();
			whListEntity.minusSurplus(this.getListingamount())
						.renewUpdateTime();
			this.setSurpluses(getListingamount());
    	}else{//挂牌中，待审核
    		this.setListingamount(null)
    			.setSurpluses(null)
    			.setMinsales(null)
    			.setHasbill(null);
    	}
    	return this.setIsrecommend(Short.parseShort("0"))
    			   .renewUpdateTime()
    			   .setListingflag(Short.valueOf(BusiListingFlagEnum.AUDIT_WAITING.getCode()));
	}
    
    /**
     * 
     * @Description: 下架挂牌
     * @Author: robin.liu
     * @Date: 2015年8月26日
     * @return
     */
    public BusiListing cancel() throws Exception{
    	avoidWhListEmpty();
		whListEntity.addSurplus(getSurpluses())
					.renewUpdateTime();
		this.setListingflag(Short.valueOf(BusiListingFlagEnum.LISTING_CANCEL.getCode()))
			.setSurpluses(BigDecimal.ZERO)
			.renewUpdateTime();
		return this;
	}
    
    /**
     * 
     * @Description: 审核失败挂牌
     * @Author: robin.liu
     * @Date: 2015年8月26日
     * @return
     * @throws Exception
     */
    public BusiListing auditFail() throws Exception{
    	avoidWhListEmpty();
    	whListEntity.addSurplus(this.getSurpluses())
					.renewUpdateTime();
    	Date date = new Date();
    	this.setListingflag(Short.valueOf(BusiListingFlagEnum.AUDIT_FAILURE.getCode()))
    		.setExaminetime(date).setUpdatetime(date);
		return this;
	}
    
    
    /**
     * 
     * @Description: 判断是否是审核失败
     * @Author: robin.liu
     * @Date: 2015年8月26日
     * @return
     */
    public boolean isAuditFailure(){
    	return BusiListingFlagEnum.AUDIT_FAILURE.getCode().equals(getListingflag().toString());
    }
    
    public String getListingid() {
        return listingid;
    }

    public BusiListing setListingid(String listingid) {
        this.listingid = listingid;
        return this;
    }

    public Long getUserid() {
        return userid;
    }

    public BusiListing setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public Long getBreedid() {
        return breedid;
    }

    public BusiListing setBreedid(Long breedid) {
        this.breedid = breedid;
        return this;
    }

    public String getWlid() {
        return wlid;
    }

    public BusiListing setWlid(String wlid) {
        this.wlid = wlid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BusiListing setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getLowunitprice() {
        return lowunitprice;
    }

    public BusiListing setLowunitprice(BigDecimal lowunitprice) {
        this.lowunitprice = lowunitprice;
        return this;
    }

    public BigDecimal getBillprice() {
        return billprice;
    }

    public BusiListing setBillprice(BigDecimal billprice) {
        this.billprice = billprice;
        return this;
    }

    public BigDecimal getListingamount() {
        return listingamount;
    }

    public BusiListing setListingamount(BigDecimal listingamount) {
        this.listingamount = listingamount;
        return this;
    }

    public BigDecimal getSurpluses() {
		return surpluses;
	}

	public BusiListing setSurpluses(BigDecimal surpluses) {
		this.surpluses = surpluses;
		return this;
	}
	
    public BigDecimal getMinsales() {
        return minsales;
    }

    public BusiListing setMinsales(BigDecimal minsales) {
        this.minsales = minsales;
        return this;
    }

	public Short getListingtimelimit() {
        return listingtimelimit;
    }

    public BusiListing setListingtimelimit(Short listingtimelimit) {
        this.listingtimelimit = listingtimelimit;
        return this;
    }

    public Short getHasbill() {
        return hasbill;
    }

    public BusiListing setHasbill(Short hasbill) {
        this.hasbill = hasbill;
        return this;
    }

    public Short getListingflag() {
        return listingflag;
    }

    public BusiListing setListingflag(Short listingflag) {
        this.listingflag = listingflag;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public BusiListing setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public BusiListing setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
        return this;
    }
    
    public BusiListing renewUpdateTime() {
        this.updatetime = new Date();
        return this;
    }

    public Date getExaminetime() {
        return examinetime;
    }

    public BusiListing setExaminetime(Date examinetime) {
        this.examinetime = examinetime;
        return this;
    }

    public Short getIsrecommend() {
        return isrecommend;
    }

    public BusiListing setIsrecommend(Short isrecommend) {
        this.isrecommend = isrecommend;
        return this;
    }

    public Date getRecommend() {
        return recommend;
    }

    public BusiListing setRecommend(Date recommend) {
        this.recommend = recommend;
        return this;
    }

    public String getExaminecontent() {
        return examinecontent;
    }

    public BusiListing setExaminecontent(String examinecontent) {
        this.examinecontent = examinecontent;
        return this;
    }

	public BusiWhlist getWhListEntity() {
		return whListEntity;
	}

	public BusiListing setWhListEntity(BusiWhlist whListEntity) {
		this.whListEntity = whListEntity;
		return this;
	}
}