package com.ms.boss.controller;


import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;
import com.ms.service.CategoryService;
import com.ms.tools.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("category/")
public class CategoryController {

   //分两个 品种 和类别(根茎类 ...)
    // 品种的 CRUD---variety
    // 类别CRUD--category
    // 根据类别名 模糊查询 类别
    // 主要参考上工好药 (命名不要有歧义)

    @Autowired
    CategoryService categoryService;

    /**
     * 按类别名查询列表
     * @param pageNum
     * @param pageSize
     * @return
     */

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listCategory(CategoryVo categoryVo, Integer pageNum,
                               Integer pageSize,ModelMap model
                       ) {
        PageInfo<CategoryVo> categoryList = categoryService.findByParams(categoryVo,pageNum,pageSize);
        model.put("categoryList",categoryList);
        return "commodity_list";
    }

    /**
     * 保存品种（类别）需要加验证
     * @param category
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveCategory(Category category){
        Date now=new Date();
        category.setCreateTime(now);
        category.setUpdateTime(now);
        Integer id= categoryService.create(category);
        return null;
    }

    /**
     * 获取所有的一级分类
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String findAllCategory(CategoryVo categoryVo,ModelMap model){
        List<CategoryVo> categoryList= categoryService.findAllCategory(categoryVo);
        return "";
    }

    /**
     * 删除品种(分类)
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCategory(@PathVariable("id") Integer id){
        Category category=categoryService.findById(id);
        if(category!=null&&category.getLevel()==1){
            return Result.success().msg("不许删除顶级分类");
        }
        categoryService.deleteById(id);
        return Result.success().data(id).msg("删除分类成功");
    }


    /**
     * 修改品种(分类）
     * @param category
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateCategory(Category category){
        Date now=new Date();
        category.setUpdateTime(now);
        categoryService.update(category);
        return Result.success().msg("修改分类成功");
    }

    /**
     * 获取品种（分类）信息
     * @param id
     * @return
     */

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public String getCategory(@PathVariable("id") Integer id,ModelMap model){
        Category category=categoryService.findById(id);
        return "";
    }







}
