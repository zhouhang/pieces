/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.rabbitmq;

import java.io.Serializable;
import java.util.List;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.solr.SolrListingVo;

/**
 * @ClassName: SolrMessage
 * @Description: 为solr提供元数据依据的消息类
 * @Author: 赵航
 * @Date: 2015年4月18日
 * @Version: 1.0
 */
public class SolrMessage implements Serializable{

	private static final long serialVersionUID = 7429431110587679035L;
	
	/** solr操作类型 */
	private SolrOperationTypeEnum operationType;
	
	/** solr内容类型 */
	private SolrContentTypeEnum contentType;
	
	/** solr内容 */
	private List<SolrListingVo> solrDataList;
	
	/**
	 * 构造函数
	 */
	public SolrMessage(){}

	/**
	 * 带参数构造函数
	 */
	public SolrMessage(SolrOperationTypeEnum operationType,
			SolrContentTypeEnum contentType, List<SolrListingVo> solrDataList) {
		super();
		this.operationType = operationType;
		this.contentType = contentType;
		this.solrDataList = solrDataList;
	}

	/**
	 * 取得solr操作类型
	 * @return solr操作类型
	 */
	public SolrOperationTypeEnum getOperationType() {
	    return operationType;
	}

	/**
	 * 设定solr操作类型
	 * @param operationType solr操作类型
	 */
	public void setOperationType(SolrOperationTypeEnum operationType) {
	    this.operationType = operationType;
	}

	/**
	 * 取得solr内容类型
	 * @return solr内容类型
	 */
	public SolrContentTypeEnum getContentType() {
	    return contentType;
	}

	/**
	 * 设定solr内容类型
	 * @param contentType solr内容类型
	 */
	public void setContentType(SolrContentTypeEnum contentType) {
	    this.contentType = contentType;
	}

	/**
	 * 取得solr内容
	 * @return solr内容
	 */
	public List<SolrListingVo> getSolrDataList() {
	    return solrDataList;
	}

	/**
	 * 设定solr内容
	 * @param content solr内容
	 */
	public void setSolrDataList(List<SolrListingVo> solrDataList) {
	    this.solrDataList = solrDataList;
	}

//	@Override
//	public String toString() {
//		String operationTypeStr = "";
//		String contentTypeStr = "";
//		String content = "";
//		if(operationType != null){
//			operationTypeStr = operationType.getName();
//		}
//		if(contentType != null){
//			contentTypeStr = contentType.getName();
//		}
//		if(contentList != null && contentList.size() > 0){
//			StringBuffer sbf = new StringBuffer();
//			for(String item : contentList){
//				sbf.append(item + ",");
//			}
//			content = sbf.toString();
//		}
//		content = content.length() > 0 ? content.substring(0, content.length() - 1) : "";
//		return operationTypeStr + "|" + contentTypeStr + "|" + content;
//	}
}
