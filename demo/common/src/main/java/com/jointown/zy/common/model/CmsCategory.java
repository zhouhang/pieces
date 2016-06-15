package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CmsCategory implements Serializable {
    /**
     * ZYC.CMS_CATEGORY.ID (编号)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Long id;

    /**
     * ZYC.CMS_CATEGORY.SITE_ID (站点编号)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Long siteId;

    /**
     * ZYC.CMS_CATEGORY.PARENT_ID (父级编号)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Long parentId;

    /**
     * ZYC.CMS_CATEGORY.CAT_LEVEL (栏目层级)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Short catLevel;

    /**
     * ZYC.CMS_CATEGORY.NAME (栏目名称)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private String name;

    /**
     * ZYC.CMS_CATEGORY.SORT (排序（升序）)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Long sort;

    /**
     * ZYC.CMS_CATEGORY.IN_MENU (是否在导航中显示)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Short inMenu;

    /**
     * ZYC.CMS_CATEGORY.IN_LIST (是否在分类页中显示列表)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Short inList;

    /**
     * ZYC.CMS_CATEGORY.ALLOW_COMMENT (是否允许评论)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Short allowComment;

    /**
     * ZYC.CMS_CATEGORY.CUSTOM_LIST_VIEW (自定义列表视图)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private String customListView;

    /**
     * ZYC.CMS_CATEGORY.CUSTOM_CONTENT_VIEW (自定义内容视图)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private String customContentView;

    /**
     * ZYC.CMS_CATEGORY.CREATE_BY (创建者)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private String createBy;

    /**
     * ZYC.CMS_CATEGORY.CREATE_DATE (创建时间)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Date createDate;

    /**
     * ZYC.CMS_CATEGORY.UPDATE_BY (更新者)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private String updateBy;

    /**
     * ZYC.CMS_CATEGORY.UPDATE_DATE (更新时间)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Date updateDate;

    /**
     * ZYC.CMS_CATEGORY.REMARKS (备注信息)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private String remarks;

    /**
     * ZYC.CMS_CATEGORY.DEL_FLAG (删除标记)
     * @ibatorgenerated 2014-11-26 09:46:22
     */
    private Short delFlag;

    private Set<CmsArticle> cmsArticlesCategoryId = new HashSet(0);;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Short getCatLevel() {
        return catLevel;
    }

    public void setCatLevel(Short catLevel) {
        this.catLevel = catLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Short getInMenu() {
        return inMenu;
    }

    public void setInMenu(Short inMenu) {
        this.inMenu = inMenu;
    }

    public Short getInList() {
        return inList;
    }

    public void setInList(Short inList) {
        this.inList = inList;
    }

    public Short getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Short allowComment) {
        this.allowComment = allowComment;
    }

    public String getCustomListView() {
        return customListView;
    }

    public void setCustomListView(String customListView) {
        this.customListView = customListView;
    }

    public String getCustomContentView() {
        return customContentView;
    }

    public void setCustomContentView(String customContentView) {
        this.customContentView = customContentView;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
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

    public void setCmsArticlesCategoryId(Set cmsArticlesCategoryId) {
        this.cmsArticlesCategoryId=cmsArticlesCategoryId;
    }

    public Set<CmsArticle> getCmsArticlesCategoryId() {
        return cmsArticlesCategoryId;
    }
}