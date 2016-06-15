package com.jointown.zy.common.vo;

import java.util.List;

/**
 * 微信公众平台开发--回复消息之图文消息
 *  
 * @author aizhengdong
 *
 * @data 2015年2月10日
 */
public class WxRespNewsMessageVo extends WxRespBaseMessageVo{
	/** 图文消息个数，限制为10条以内 */
	private int ArticleCount;

	/** 多条图文消息信息，默认第一个item为大图  */
    private List<WxArticleVo> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<WxArticleVo> getArticles() {
		return Articles;
	}

	public void setArticles(List<WxArticleVo> articles) {
		Articles = articles;
	}
    
    
}
