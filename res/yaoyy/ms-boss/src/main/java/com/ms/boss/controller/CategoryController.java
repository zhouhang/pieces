package com.ms.boss.controller;


import com.github.pagehelper.PageInfo;
import com.ms.dao.enums.CodeEnum;
import com.ms.dao.model.Category;
import com.ms.dao.model.Code;
import com.ms.dao.vo.CategoryVo;
import com.ms.dao.vo.CodeVo;
import com.ms.service.CategoryService;
import com.ms.dao.enums.CategoryEnum;
import com.ms.service.CodeService;
import com.ms.tools.entity.Result;
import com.ms.tools.utils.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: koabs
 * 10/12/16.
 */
@Controller
@RequestMapping("/category/")
public class CategoryController {

   //分两个 品种 和类别(根茎类 ...)
    // 品种的 CRUD---category
    // 类别CRUD--variety
    // 根据类别名 模糊查询 类别
    // 主要参考上工好药 (命名不要有歧义)

    @Autowired
    CategoryService categoryService;

    @Autowired
    CodeService codeService;

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
        //不显示一级父类
        categoryVo.setLevel(CategoryEnum.LEVEL_BREED.getValue());
        PageInfo<CategoryVo> categoryPage = categoryService.findByParams(categoryVo,pageNum,pageSize);
        //取出所有的一级父类
        CategoryVo variety=new CategoryVo();
        variety.setLevel(CategoryEnum.LEVEL_CATEGORY.getValue());
        List<CategoryVo> varieties= categoryService.findAllCategory(variety);
        CodeVo codeVo=new CodeVo();
        //取出所有单位
        codeVo.setName(CodeEnum.CODE_UNIT.getValue());
        List<CodeVo> codeVos=codeService.findAllByParams(codeVo);

        model.put("varieties",varieties);
        model.put("categoryPage",categoryPage);
        model.put("categoryVo",categoryVo);
        model.put("codeVos",codeVos);
        model.put("categoryParams", Reflection.serialize(categoryVo));
        return "category_list";
    }

    /**
     * 保存品种（类别）需要加验证
     * @param category
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveCategory(CategoryVo category){
        categoryService.save(category);
        return Result.success("成功创建商品");
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
    public Result updateCategory(CategoryVo category){
        categoryService.save(category);
        return Result.success("修改分类成功");
    }

    /**
     * 获取品种（分类）信息
     * @param id
     * @return
     */

    @RequestMapping(value = "/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result getCategory(@PathVariable("id") Integer id){
        Category category=categoryService.findById(id);
        return  Result.success().data(category);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ResponseBody
    public Result searchCategory(CategoryVo categoryVo){
        categoryVo.setLevel(CategoryEnum.LEVEL_BREED.getValue());
        List<CategoryVo> categoryVoList=categoryService.searchCategory(categoryVo);
        return  Result.success().data(categoryVoList);
    }







}
