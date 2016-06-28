package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章来源表（爬虫及后台采集待审核信息）
 * @author Mr.songwei 
 * @date 2014年11月7日下午12:16:00
 */
@SuppressWarnings("serial")
public class CmsArticleSource implements Serializable {
    /**
     * ZYC.CMS_ARTICLE_SOURCE.ID (编号)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Long id;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.LINK (文章链接)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String link;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.TITLE (文章标题)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String title;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.CREATE_BY (创建者)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String createBy;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.CREATE_DATE (创建时间)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Date createDate;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.UPDATE_BY (更新者)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String updateBy;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.KEYWORDS (关键字)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String keywords;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.NEWS_PAGING (原文章页面页码)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Short newsPaging;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.CATEGORY_ID (栏目编号)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Long categoryId;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.COLOR (标题颜色)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String color;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.IMAGE (文章图片)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String image;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.DESCRIPTION (摘要，文章正文简要描述)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String description;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.REMARKS (备注信息)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private String remarks;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.TO_FROM (采集类型，默认是1。1爬虫采集；2官方入库；3用户入库。)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Short toFrom;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.STATE (审核类型，0表示完成入库操作，1表示审核通过，2表示审核不通过，禁用。)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Short state;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.UPDATE_DATE (更新时间)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Date updateDate;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.DEL_FLAG (删除标记(0表示正常，1表示假删除))
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Short delFlag;

    /**
     * ZYC.CMS_ARTICLE_SOURCE.ART_DATA_ID (文章详情表编号)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Long artDataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
    	if(null == createBy ||"".equals(createBy)){
    		createBy = "官网";
    	}
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
    	if(null == createDate){
    		createDate = new Date();
    	}
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
    	if(null == updateBy || "".equals(updateBy)){
    		updateBy = "官网";
    	}
        this.updateBy = updateBy;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Short getNewsPaging() {
        return newsPaging;
    }

    public void setNewsPaging(Short newsPaging) {
        this.newsPaging = newsPaging;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Short getToFrom() {
        return toFrom;
    }

    public void setToFrom(Short toFrom) {
    	if(null == toFrom){
    		toFrom = 2;
    	}
        this.toFrom = toFrom;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
    	if(null == updateDate){
    		updateDate = new Date();
    	}
        this.updateDate = updateDate;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    public Long getArtDataId() {
        return artDataId;
    }

    public void setArtDataId(Long artDataId) {
        this.artDataId = artDataId;
    }
}