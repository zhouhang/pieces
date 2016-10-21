package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends ICommonService<Category>{

    public PageInfo<CategoryVo> findByParams(CategoryVo categoryVo,Integer pageNum,Integer pageSize);

    List<CategoryVo> findAllCategory(CategoryVo categoryVo);

    List<CategoryVo> searchCategory(CategoryVo categoryVo);

}
