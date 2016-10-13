package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.CategoryDao;
import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;
import com.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl  extends AbsCommonService<Category> implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;


	@Override
	public PageInfo<CategoryVo> findByParams(CategoryVo categoryVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CategoryVo>  list = categoryDao.findByParams(categoryVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<Category> getDao() {
		return categoryDao;
	}

}
