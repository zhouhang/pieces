package com.jointown.zy.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CmsSite implements Serializable {
    /**
     * ZYC.CMS_SITE.ID (编号)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private Long id;

    /**
     * ZYC.CMS_SITE.NAME (站点名称)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String name;

    /**
     * ZYC.CMS_SITE.TITLE (站点简称)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String title;

    /**
     * ZYC.CMS_SITE.LOGO (站点Logo)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String logo;

    /**
     * ZYC.CMS_SITE.DOMAIN (站点域名)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String domain;

    /**
     * ZYC.CMS_SITE.DESCRIPTION (描述)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String description;

    /**
     * ZYC.CMS_SITE.KEYWORDS (关键字)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String keywords;

    /**
     * ZYC.CMS_SITE.THEME (主题)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String theme;

    /**
     * ZYC.CMS_SITE.CUSTOM_INDEX_VIEW (自定义站点首页视图)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String customIndexView;

    /**
     * ZYC.CMS_SITE.CREATE_BY (创建者)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String createBy;

    /**
     * ZYC.CMS_SITE.CREATE_DATE (创建时间)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private Timestamp createDate;

    /**
     * ZYC.CMS_SITE.UPDATE_BY (更新者)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String updateBy;

    /**
     * ZYC.CMS_SITE.UPDATE_DATE (更新时间)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private Timestamp updateDate;

    /**
     * ZYC.CMS_SITE.REMARKS (备注信息)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String remarks;

    /**
     * ZYC.CMS_SITE.DEL_FLAG (删除标记(0表示正常，1表示假删除))
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private Short delFlag;

    /**
     * ZYC.CMS_SITE.COPYRIGHT (版权信息)
     * @ibatorgenerated 2014-11-25 12:26:47
     */
    private String copyright;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCustomIndexView() {
        return customIndexView;
    }

    public void setCustomIndexView(String customIndexView) {
        this.customIndexView = customIndexView;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}