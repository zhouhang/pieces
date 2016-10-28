package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.ArticleDao;
import com.ms.dao.model.Article;
import com.ms.dao.vo.ArticleVo;
import com.ms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImpl  extends AbsCommonService<Article> implements ArticleService{

	@Autowired
	private ArticleDao articleDao;


	@Override
	public PageInfo<ArticleVo> findByParams(ArticleVo articleVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<ArticleVo>  list = articleDao.findByParams(articleVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Article> getDao() {
		return articleDao;
	}

}
