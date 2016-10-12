package com.ms.dao;

import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;

import java.util.List;
public interface CategoryDao extends ICommonDao<Category>{

    public List<CategoryVo> findByParams(CategoryVo categoryVo);

}
