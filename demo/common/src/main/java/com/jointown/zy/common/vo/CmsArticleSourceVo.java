package com.jointown.zy.common.vo;

import java.sql.Timestamp;


//@XmlRootElement
public class CmsArticleSourceVo {
	
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
    private Timestamp createDate;

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
     * ZYC.CMS_ARTICLE_SOURCE.CATEGORY_ID (栏目编号)
     * @ibatorgenerated 2014-12-04 10:54:49
     */
    private Long categoryId;

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
    private Timestamp updateDate;

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
    
    //栏目名称
    private String name;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
        this.createBy = createBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Short getToFrom() {
        return toFrom;
    }

    public void setToFrom(Short toFrom) {
        this.toFrom = toFrom;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
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
