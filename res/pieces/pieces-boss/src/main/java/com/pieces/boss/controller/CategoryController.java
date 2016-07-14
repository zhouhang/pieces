package com.pieces.boss.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 分类列表
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param categoryName
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/list")
	public String getCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  Integer pageNum,
							  Integer pageSize,
							  String categoryName,
							  Integer status,
							  ModelMap model){
		
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		Category t = new Category();
		t.setName(categoryName);
		t.setStatus(status);
		PageInfo<Category> categoryPage = categoryService.findClassify(t, pageNum, pageSize);
		model.put("categoryPage", categoryPage);
		model.put("categoryParams", t.toString());
		return "";
		
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @param categoryName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/add")
	public String addCategory(HttpServletRequest request,
							  HttpServletResponse response){
		return "";
	}
	
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/edit/{id}")
	public String editCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  @PathVariable("id") Integer id,
							  ModelMap model){
		Category category = categoryService.findById(id);
		model.put("category", category);
		return "";
	}
	
	/**
	 * 保存分类，id为空新增，id不为空修改
	 * @param request
	 * @param response
	 * @param id
	 * @param categoryName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/save")
	public String saveCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  String id,
							  String categoryName,
							  ModelMap model){
		if(StringUtils.isNotBlank(categoryName)){
			if(StringUtils.isNotBlank(id)){
				categoryService.updateClassify(categoryName, Integer.parseInt(id));
			}else{
				categoryService.addClassify(categoryName);
			}
		}
		return "";
	}
	
	/**
	 * 品种列表
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param categoryName
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/breed/list")
	public String getBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  Integer pageNum,
							  Integer pageSize,
							  CategoryVo vo,
							  ModelMap model){
		
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		PageInfo<CategoryVo> categoryPage = categoryService.findBreed(vo, pageNum, pageSize);
		model.put("categoryPage", categoryPage);
		model.put("categoryParams", vo.toString());
		return "";
		
	}
	
	/**
	 * 添加品种
	 * @param request
	 * @param response
	 * @param categoryName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/breed/add")
	public String addBreed(HttpServletRequest request,
							  HttpServletResponse response){
		return "";
	}
	
	/**
	 * 修改品种
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/breed/edit/{id}")
	public String addBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  @PathVariable("id") Integer id,
							  ModelMap model){
		BreedVo breed = categoryService.getBreedById(id);
		model.put("breed", breed);
		return "";
	}
	
	/**
	 * 保存品种，id为空新增，id不为空修改
	 * @param request
	 * @param response
	 * @param id
	 * @param categoryName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/breed/save")
	public String saveBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  BreedVo bvo,
							  ModelMap model){
		if(StringUtils.isNotBlank(bvo.getId())){
			categoryService.updateBreed(bvo);
		}else{
			categoryService.addBreed(bvo);
		}
		return "";
	}
}
