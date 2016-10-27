package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;

import java.util.List;
@AutoMapper
public interface CategoryDao extends ICommonDao<Category>{

    public List<CategoryVo> findByParams(CategoryVo categoryVo);
    public List<CategoryVo> findAllCategory(CategoryVo categoryVo);
    public CategoryVo getVoById(Integer id);
    public List<CategoryVo> findVoByPage();


}
