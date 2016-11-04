package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.ArticleDao;
import com.ms.dao.model.Article;
import com.ms.dao.vo.ArticleVo;
import com.ms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl  extends AbsCommonService<Article> implements ArticleService{

	@Autowired
	private ArticleDao articleDao;

	@Override
	public PageInfo<ArticleVo> findByParams(ArticleVo articleVo,Integer pageNum,Integer pageSize) {
		if (pageNum == null || pageSize == null){
			pageNum = 1;
			pageSize = 10;
		}
    	PageHelper.startPage(pageNum, pageSize);
    	List<ArticleVo>  list = articleDao.findByParams(articleVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public void changeStatus(Integer id, Integer status) {
		Article article  = new Article();
		article.setId(id);
		article.setStatus(status);
		update(article);
	}

	@Override
	@Transactional
	public void save(Article article) {
		if (article.getStatus() == null) {
			article.setStatus(1);
		}
		article.setUpdateTime(new Date());
		if (article.getId() != null) {
			update(article);
		} else {
			article.setCreateTime(new Date());
			create(article);
		}
	}

	@Override
	public ICommonDao<Article> getDao() {
		return articleDao;
	}

}
