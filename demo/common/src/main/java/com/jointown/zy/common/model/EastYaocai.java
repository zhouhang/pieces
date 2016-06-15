package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EastYaocai implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * WEIXIN.EAST_YAOCAI.YCNAM
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    private String ycnam;

    /**
     * WEIXIN.EAST_YAOCAI.YCID
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    private BigDecimal ycid;

    /**
     * WEIXIN.EAST_YAOCAI.PINYIN
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    private String pinyin;

    /**
     * WEIXIN.EAST_YAOCAI.JIANPIN
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    private String jianpin;

    /**
     * WEIXIN.EAST_YAOCAI.LBID
     * @ibatorgenerated 2015-03-05 14:06:59
     */
    private BigDecimal lbid;

    public String getYcnam() {
        return ycnam;
    }

    public void setYcnam(String ycnam) {
        this.ycnam = ycnam;
    }

    public BigDecimal getYcid() {
        return ycid;
    }

    public void setYcid(BigDecimal ycid) {
        this.ycid = ycid;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public BigDecimal getLbid() {
        return lbid;
    }

    public void setLbid(BigDecimal lbid) {
        this.lbid = lbid;
    }
}