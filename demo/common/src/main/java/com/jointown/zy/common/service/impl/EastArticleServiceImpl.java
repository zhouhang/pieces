/**
 * @author guoyb
 * 2015年3月3日 上午9:47:17
 */
package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.dao.EastArticleDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.EastArticleService;
import com.jointown.zy.common.util.WxUtils;
import com.jointown.zy.common.vo.EastArticleVo;
import com.jointown.zy.common.vo.WxArticleVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * @author guoyb 2015年3月3日 上午9:47:17
 */
@Service
public class EastArticleServiceImpl implements EastArticleService {

	@Autowired
	EastArticleDao articleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jointown.zy.common.service.EastArticleService#findEastArticles(com
	 * .jointown.zy.common.vo.WxReqBaseMessageVo, java.lang.Integer)
	 */
	@Override
	public String findEastArticles(WxReqBaseMessageVo reqMessage, Integer lmid) {
		List<EastArticleVo> articles = articleDao.selectEastArticleByLMId(lmid);
		return WxUtils.createNewsMessage(packWxArticle(articles, lmid),
				reqMessage);
	}

	/**
	 * 把行业行文/市场详情的数据封装为微信接口格式的数据
	 * 
	 * @param activitys
	 *            活动的数据
	 * @return 微信多图文格式的数据
	 */
	private List<WxArticleVo> packWxArticle(List<EastArticleVo> eastArticles,
			int lmid) {
		List<WxArticleVo> articles = new ArrayList<WxArticleVo>();
		for (int i = 0; i < eastArticles.size(); i++) {
			EastArticleVo eastArticle = eastArticles.get(i);
			WxArticleVo article = new WxArticleVo();
			article.setTitle(eastArticle.getTitle());
			article.setUrl(WxConstant.ARTICLE_URL + eastArticle.getAcid());
			if (i == 0) {
				if (lmid == WxConstant.HY_NEWS_ARTICLE_TYPE) {
					article.setPicUrl(WxConstant.HY_NEWS_PIC_URL);
				} else if (lmid == WxConstant.SCDT_ARTICLE_TYPE) {
					article.setPicUrl(WxConstant.SCDT_PIC_URL);
				}
			}
			articles.add(article);
		}
		return articles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jointown.zy.common.service.EastArticleService#findOneEastArticle(
	 * java.lang.Integer)
	 */
	@Override
	public EastArticleVo findOneEastArticle(Integer acid) {
		return this.articleDao.selectEastArticleByACid(acid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jointown.zy.common.service.EastArticleService#findEastArticleByPage
	 * (com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<EastArticleVo> findEastArticleByPage(
			Page<Map<String, Object>> page) {
		return this.articleDao.selectEastArticlePagedByLMId(page);
	}

	@Override
	public List<EastArticleVo> findEastArticleNewsByCondition(
			Page<EastArticleVo> page) {
		return this.articleDao.selectEastArticleNewsByCondition(page);
	}

	@Override
	public void updateEastArticleTipByACid(Integer acid) throws Exception {
		articleDao.updateEastArticleTipByACid(acid);
	}
}
