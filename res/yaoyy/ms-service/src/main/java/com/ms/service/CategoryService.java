package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;


import java.util.List;

public interface CategoryService extends ICommonService<Category>{

    public PageInfo<CategoryVo> findByParams(CategoryVo categoryVo,Integer pageNum,Integer pageSize);

    List<CategoryVo> findAllCategory(CategoryVo categoryVo);

    List<CategoryVo> searchCategory(CategoryVo categoryVo);

    public void save(CategoryVo categoryVo);

    public Category findById(Integer id);

    public CategoryVo getVoById(Integer id);

    public PageInfo<CategoryVo> findVoByPage(int pageSize, int pageNum);

    /**
     * 前台查询品种方法
     * 1.只显示有商品的品种
     * 2.添加一个商品id
     * @param categoryVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<CategoryVo> findByParamsBiz(CategoryVo categoryVo,Integer pageNum,Integer pageSize);

}
