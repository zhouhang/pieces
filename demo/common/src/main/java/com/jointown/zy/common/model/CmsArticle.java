package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表（供前端展示）
 * @author Mr.songwei 
 * @date 2014年11月7日下午12:16:00
 */
public class CmsArticle implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * ZYC.CMS_ARTICLE.ID (编号)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Long id;

    /**
     * ZYC.CMS_ARTICLE.CATEGORY_ID (栏目编号)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Long categoryId;

    /**
     * ZYC.CMS_ARTICLE.TITLE (标题)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String title;

    /**
     * ZYC.CMS_ARTICLE.LINK (文章链接)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String link;

    /**
     * ZYC.CMS_ARTICLE.COLOR (标题颜色)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String color;

    /**
     * ZYC.CMS_ARTICLE.IMAGE (文章图片)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String image;

    /**
     * ZYC.CMS_ARTICLE.KEYWORDS (关键字)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String keywords;

    /**
     * ZYC.CMS_ARTICLE.DESCRIPTION (描述、摘要)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String description;

    /**
     * ZYC.CMS_ARTICLE.WEIGHT (权重，越大越靠前)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Long weight;

    /**
     * ZYC.CMS_ARTICLE.WEIGHT_DATE (权重期限)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Date weightDate;

    /**
     * ZYC.CMS_ARTICLE.HITS (点击数)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Long hits;

    /**
     * ZYC.CMS_ARTICLE.POSID (推荐位，多选)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String posid;

    /**
     * ZYC.CMS_ARTICLE.CUSTOM_CONTENT_VIEW (自定义内容视图)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String customContentView;

    /**
     * ZYC.CMS_ARTICLE.CREATE_BY (创建者)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String createBy;

    /**
     * ZYC.CMS_ARTICLE.CREATE_DATE (创建时间)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Date createDate;

    /**
     * ZYC.CMS_ARTICLE.UPDATE_BY (更新者)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String updateBy;

    /**
     * ZYC.CMS_ARTICLE.UPDATE_DATE (更新时间)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Date updateDate;

    /**
     * ZYC.CMS_ARTICLE.REMARKS (备注信息)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String remarks;

    /**
     * ZYC.CMS_ARTICLE.DEL_FLAG (删除标记(0表示正常，1表示假删除))
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Short delFlag;

    /**
     * ZYC.CMS_ARTICLE.TO_FROM (采集出处，默认1。（1官方后台录入，2爬虫抓取，3用户自己录入。）)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Short toFrom;

    /**
     * ZYC.CMS_ARTICLE.ART_DATA_ID (文章详情表编号)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private Long artDataId;

    /**
     * ZYC.CMS_ARTICLE.VIEW_CONFIG (视图配置)
     * @ibatorgenerated 2014-11-26 09:46:38
     */
    private String viewConfig;

    private CmsArticleData cmsArticleDataArtDataId;

    private CmsCategory cmsCategoryCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Date getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
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

    public Short getToFrom() {
        return toFrom;
    }

    public void setToFrom(Short toFrom) {
        this.toFrom = toFrom;
    }

    public Long getArtDataId() {
        return artDataId;
    }

    public void setArtDataId(Long artDataId) {
        this.artDataId = artDataId;
    }

    public String getViewConfig() {
        return viewConfig;
    }

    public void setViewConfig(String viewConfig) {
        this.viewConfig = viewConfig;
    }

    public void setCmsArticleDataArtDataId(CmsArticleData cmsArticleDataArtDataId) {
        this.cmsArticleDataArtDataId=cmsArticleDataArtDataId;
    }

    public CmsArticleData getCmsArticleDataArtDataId() {
        return cmsArticleDataArtDataId;
    }

    public void setCmsCategoryCategoryId(CmsCategory cmsCategoryCategoryId) {
        this.cmsCategoryCategoryId=cmsCategoryCategoryId;
    }

    public CmsCategory getCmsCategoryCategoryId() {
        return cmsCategoryCategoryId;
    }
}