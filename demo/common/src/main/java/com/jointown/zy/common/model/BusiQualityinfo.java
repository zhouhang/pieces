package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

public class BusiQualityinfo implements Serializable {
    /**
     * ZYC.BUSI_QUALITYINFO.QUALITYINFOID (质检信息表ID)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private Long qualityinfoid;

    /**
     * ZYC.BUSI_QUALITYINFO.WLID (仓单ID)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String wlid;

    /**
     * ZYC.BUSI_QUALITYINFO.MAINPARACTER (主要性状)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String mainparacter;

    /**
     * ZYC.BUSI_QUALITYINFO.GRADE (等级规格)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String grade;

    /**
     * ZYC.BUSI_QUALITYINFO.DETAILED (具体描述)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String detailed;

    /**
     * ZYC.BUSI_QUALITYINFO.WATER (水分)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String water;

    /**
     * ZYC.BUSI_QUALITYINFO.NUMBEROFJC (质检数量)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String numberofjc;

    /**
     * ZYC.BUSI_QUALITYINFO.ASH (灰分)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String ash;

    /**
     * ZYC.BUSI_QUALITYINFO.ACCEPTCHECKTIME (收检日期)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private Date acceptchecktime;

    /**
     * ZYC.BUSI_QUALITYINFO.SULFURDIOXIDEIN (二氧化硫残余量)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String sulfurdioxidein;

    /**
     * ZYC.BUSI_QUALITYINFO.REPORTDATE (报告日期)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private Date reportdate;

    /**
     * ZYC.BUSI_QUALITYINFO.CONTENTCHECK (含量测定)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private String contentcheck;

    /**
     * ZYC.BUSI_QUALITYINFO.CREATETIME (创建日期)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private Date createtime;

    /**
     * ZYC.BUSI_QUALITYINFO.UPDATETIME (修改日期)
     * @ibatorgenerated 2014-12-25 19:15:46
     */
    private Date updatetime;

    public Long getQualityinfoid() {
        return qualityinfoid;
    }

    public void setQualityinfoid(Long qualityinfoid) {
        this.qualityinfoid = qualityinfoid;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public String getMainparacter() {
        return mainparacter;
    }

    public void setMainparacter(String mainparacter) {
        this.mainparacter = mainparacter;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getNumberofjc() {
        return numberofjc;
    }

    public void setNumberofjc(String numberofjc) {
        this.numberofjc = numberofjc;
    }

    public String getAsh() {
        return ash;
    }

    public void setAsh(String ash) {
        this.ash = ash;
    }

    public Date getAcceptchecktime() {
        return acceptchecktime;
    }

    public void setAcceptchecktime(Date acceptchecktime) {
        this.acceptchecktime = acceptchecktime;
    }

    public String getSulfurdioxidein() {
        return sulfurdioxidein;
    }

    public void setSulfurdioxidein(String sulfurdioxidein) {
        this.sulfurdioxidein = sulfurdioxidein;
    }

    public Date getReportdate() {
        return reportdate;
    }

    public void setReportdate(Date reportdate) {
        this.reportdate = reportdate;
    }

    public String getContentcheck() {
        return contentcheck;
    }

    public void setContentcheck(String contentcheck) {
        this.contentcheck = contentcheck;
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
}