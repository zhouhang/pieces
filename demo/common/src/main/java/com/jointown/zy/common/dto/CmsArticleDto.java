package com.jointown.zy.common.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 文章来源表（爬虫、后台数据采集元数据表，仅供审核所需，不涉及前台展示）
 * @author Mr.songwei 
 * @date 2014年11月25日下午15:15:28
 */
public class CmsArticleDto extends BaseDto {

	private static final long serialVersionUID = -3038136092259317328L;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCopyfrom() {
		return copyfrom;
	}
	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//表单服务端验证，防止入库异常。
	@Override
	public List<ErrorMessage> validate() {
		this.title = StringUtils.trim(title);
		this.keywords = StringUtils.trim(keywords);
		this.description = StringUtils.trim(description);
		this.content = StringUtils.trim(content);
		
		if(StringUtils.isEmpty(title)){
			addError(new ErrorMessage(ErrorRepository.CMS_ART_TITLE_ISNULL));
		}
		if(StringUtils.isEmpty(keywords)){
			addError(new ErrorMessage(ErrorRepository.CMS_ART_KEYWORDS_ISNULL));
		}
		if(StringUtils.isEmpty(description)){
			addError(new ErrorMessage(ErrorRepository.CMS_ART_DESCRIPTION_ISNULL));
		}
		if(StringUtils.isEmpty(content)){
			addError(new ErrorMessage(ErrorRepository.CMS_ART_CONTENT_ISNULL));
		}
		return errors;
	}
	
	// 标题
	private  String title;
	// 关键字
	private  String keywords;
	// 栏目编号
	private  Long category_id;
	// 摘要，文章正文简要描述
	private  String description;
	//来源
	private String copyfrom;
	//内容
	private String content;
	//创建时间 
	private  Timestamp create_date;
	//图片
	private  String file_id;
	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Timestamp create_date) {
		if(create_date==null){
			String time = TimeUtil.getYMDHMS(new Date());
			create_date = TimeUtil.getTimeStamp(time);
		}
		this.create_date = create_date;
	}
}
