package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EastGongqiu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * WEIXIN.EAST_GONGQIU.GQID
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private BigDecimal gqid;

    /**
     * WEIXIN.EAST_GONGQIU.SHOPID
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private BigDecimal shopid;

    /**
     * WEIXIN.EAST_GONGQIU.YHID
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private BigDecimal yhid;

    /**
     * WEIXIN.EAST_GONGQIU.GQFLG
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String gqflg;

    /**
     * WEIXIN.EAST_GONGQIU.GQTYP
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String gqtyp;

    /**
     * WEIXIN.EAST_GONGQIU.YCNAM
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String ycnam;

    /**
     * WEIXIN.EAST_GONGQIU.GUIGE
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String guige;

    /**
     * WEIXIN.EAST_GONGQIU.CHANDI
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String chandi;

    /**
     * WEIXIN.EAST_GONGQIU.SHUL
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private BigDecimal shul;

    /**
     * WEIXIN.EAST_GONGQIU.DANW
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String danw;

    /**
     * WEIXIN.EAST_GONGQIU.PRI
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String pri;

    /**
     * WEIXIN.EAST_GONGQIU.HUOYUANDI
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String huoyuandi;

    /**
     * WEIXIN.EAST_GONGQIU.MARKET
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private BigDecimal market;

    /**
     * WEIXIN.EAST_GONGQIU.JISHOU
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String jishou;

    /**
     * WEIXIN.EAST_GONGQIU.FROMCHANDI
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String fromchandi;

    /**
     * WEIXIN.EAST_GONGQIU.PICURL
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String picurl;

    /**
     * WEIXIN.EAST_GONGQIU.XIANGXI
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String xiangxi;

    /**
     * WEIXIN.EAST_GONGQIU.ENDTM
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Date endtm;

    /**
     * WEIXIN.EAST_GONGQIU.DTM
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Date dtm;

    /**
     * WEIXIN.EAST_GONGQIU.PXDTM
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Date pxdtm;

    /**
     * WEIXIN.EAST_GONGQIU.UPDTM
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Date updtm;

    /**
     * WEIXIN.EAST_GONGQIU.CLICKNUM
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private BigDecimal clicknum;

    /**
     * WEIXIN.EAST_GONGQIU.LASTIP
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String lastip;

    /**
     * WEIXIN.EAST_GONGQIU.FKTYPE
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String fktype;

    /**
     * WEIXIN.EAST_GONGQIU.TONGZHI
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String tongzhi;

    /**
     * WEIXIN.EAST_GONGQIU.SHFLG
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private String shflg;

    /**
     * WEIXIN.EAST_GONGQIU.STATUS (状态：未处理-0、有效-1、无效-2、用户撤销-3)
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Short status;

    /**
     * WEIXIN.EAST_GONGQIU.APPROVE_TIME (审核时间（处理时间）)
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Date approveTime;

    /**
     * WEIXIN.EAST_GONGQIU.APPROVER (审核人ID（处理人）)
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    private Long approver;

    public BigDecimal getGqid() {
        return gqid;
    }

    public void setGqid(BigDecimal gqid) {
        this.gqid = gqid;
    }

    public BigDecimal getShopid() {
        return shopid;
    }

    public void setShopid(BigDecimal shopid) {
        this.shopid = shopid;
    }

    public BigDecimal getYhid() {
        return yhid;
    }

    public void setYhid(BigDecimal yhid) {
        this.yhid = yhid;
    }

    public String getGqflg() {
        return gqflg;
    }

    public void setGqflg(String gqflg) {
        this.gqflg = gqflg;
    }

    public String getGqtyp() {
        return gqtyp;
    }

    public void setGqtyp(String gqtyp) {
        this.gqtyp = gqtyp;
    }

    public String getYcnam() {
        return ycnam;
    }

    public void setYcnam(String ycnam) {
        this.ycnam = ycnam;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getChandi() {
        return chandi;
    }

    public void setChandi(String chandi) {
        this.chandi = chandi;
    }

    public BigDecimal getShul() {
        return shul;
    }

    public void setShul(BigDecimal shul) {
        this.shul = shul;
    }

    public String getDanw() {
        return danw;
    }

    public void setDanw(String danw) {
        this.danw = danw;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getHuoyuandi() {
        return huoyuandi;
    }

    public void setHuoyuandi(String huoyuandi) {
        this.huoyuandi = huoyuandi;
    }

    public BigDecimal getMarket() {
        return market;
    }

    public void setMarket(BigDecimal market) {
        this.market = market;
    }

    public String getJishou() {
        return jishou;
    }

    public void setJishou(String jishou) {
        this.jishou = jishou;
    }

    public String getFromchandi() {
        return fromchandi;
    }

    public void setFromchandi(String fromchandi) {
        this.fromchandi = fromchandi;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getXiangxi() {
        return xiangxi;
    }

    public void setXiangxi(String xiangxi) {
        this.xiangxi = xiangxi;
    }

    public Date getEndtm() {
        return endtm;
    }

    public void setEndtm(Date endtm) {
        this.endtm = endtm;
    }

    public Date getDtm() {
        return dtm;
    }

    public void setDtm(Date dtm) {
        this.dtm = dtm;
    }

    public Date getPxdtm() {
        return pxdtm;
    }

    public void setPxdtm(Date pxdtm) {
        this.pxdtm = pxdtm;
    }

    public Date getUpdtm() {
        return updtm;
    }

    public void setUpdtm(Date updtm) {
        this.updtm = updtm;
    }

    public BigDecimal getClicknum() {
        return clicknum;
    }

    public void setClicknum(BigDecimal clicknum) {
        this.clicknum = clicknum;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getFktype() {
        return fktype;
    }

    public void setFktype(String fktype) {
        this.fktype = fktype;
    }

    public String getTongzhi() {
        return tongzhi;
    }

    public void setTongzhi(String tongzhi) {
        this.tongzhi = tongzhi;
    }

    public String getShflg() {
        return shflg;
    }

    public void setShflg(String shflg) {
        this.shflg = shflg;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }
}