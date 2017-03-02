package com.pieces.boss.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pieces.boss.commons.LogConstant;
import com.pieces.dao.model.Commodity;
import com.pieces.service.enums.CategoryEnum;
import com.pieces.tools.annotation.SecurityToken;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.Reflection;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.BreedVo;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.service.CategoryService;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.GsonUtil;
import com.pieces.tools.utils.WebUtil;
import javax.validation.Valid;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommodityService commodityService;
	
	/**
	 * 分类列表
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "category:index")
	@RequestMapping(value = "/category/list" ,method = RequestMethod.GET)
	@BizLog(type = LogConstant.category, desc = "商品分类列表")
	public String getCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  Integer pageNum,
							  Integer pageSize,
							  String name,
							  ModelMap model){
		
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		CategoryVo t = new CategoryVo();
		t.setName(name);
		t.setStatus(CategoryEnum.STATUS_VALID.getValue());
		PageInfo<Category> categoryPage = categoryService.findClassify(t, pageNum, pageSize);
		model.put("categoryPage", categoryPage);
		model.put("categoryParams", Reflection.serialize(t));
		model.put("category", t);
		return "category";
	}
	
	/**
	 * 分类列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/list" ,method = RequestMethod.POST)
	@BizLog(type = LogConstant.category, desc = "获取所有的商品分类")
	public void getAllCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model){
		
		CategoryVo t = new CategoryVo();
		t.setStatus(CategoryEnum.STATUS_VALID.getValue());
		List<Category> categorys = categoryService.findClassify(t);
        String result = GsonUtil.toJsonInclude(categorys, "id", "name");
        WebUtil.printJson(response, result);
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions(value = "category:add")
	@RequestMapping(value = "/category/add")
	@BizLog(type = LogConstant.category, desc = "添加商品分类页面")
	@SecurityToken(generateToken = true)
	public String addCategory(HttpServletRequest request,
							  HttpServletResponse response){
		return "category_add";
	}
	
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "category:edit")
	@RequestMapping(value = "/category/edit/{id}")
	@BizLog(type = LogConstant.category, desc = "修改商品分类页面")
	@SecurityToken(generateToken = true)
	public String editCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  @PathVariable("id") Integer id,
							  ModelMap model){
		Category category = categoryService.findById(id);
		model.put("category", category);
		return "category_edit";
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "category:delete")
	@RequestMapping(value = "/category/delete/{id}")
	@BizLog(type = LogConstant.category, desc = "删除商品分类")
	public void deleteCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  @PathVariable("id") Integer id,
							  ModelMap model){
		List<CategoryVo> vo = categoryService.findBreedByParentId(id);
		if(ValidUtils.listNotBlank(vo)){
			Result result = new Result(false).info("该分类已有关联品种，请先取消关联后再删除。");
	        WebUtil.printJson(response, result);
	        return;
		}
		categoryService.deleteById(id);
		Result result = new Result(true).info("删除分类成功。");
        WebUtil.printJson(response, result);
	}
	
	/**
	 * 保存分类，id为空新增，id不为空修改
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = {"category:add","category:edit"},logical = Logical.OR)
	@RequestMapping(value = "/category/save")
	@BizLog(type = LogConstant.category, desc = "保存商品分类")
	@SecurityToken(generateToken = true,validateToken=true)
	public void saveCategory(HttpServletRequest request,
							  HttpServletResponse response,
							  Integer id,
							  String name,
							  ModelMap model){
		Result result = new Result(true);
		if(StringUtils.isNotBlank(name) ) {
			if (id == null) {
				categoryService.addClassify(name);
				result.info("新增分类成功。");
			} else {
				categoryService.updateClassify(name, id);
				result.info("修改分类成功。");
			}
		}
        WebUtil.printJson(response, result);
	}
	
	/**
	 * 品种列表
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "breed:index")
	@RequestMapping(value = "/breed/list")
	@BizLog(type = LogConstant.category, desc = "商品品种列表页面")
	public String getBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  Integer pageNum,
							  Integer pageSize,
							  CategoryVo vo,
							  ModelMap model){
		
		pageNum = pageNum==null?1:pageNum;
		pageSize = pageSize==null?10:pageSize;
		vo.setStatus(CategoryEnum.STATUS_VALID.getValue());
		PageInfo<CategoryVo> categoryPage = categoryService.findBreed(vo, pageNum, pageSize);
		model.put("categoryPage", categoryPage);
		model.put("categoryParams", Reflection.serialize(vo));
		model.put("category", vo);
		return "breed";
		
	}
	
	/**
	 * 添加品种
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "breed:add")
	@RequestMapping(value = "/breed/add")
	@BizLog(type = LogConstant.category, desc = "添加商品品种页面")
	@SecurityToken(generateToken = true)
	public String addBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model){
		return "breed_add";
	}
	
	/**
	 * 修改品种
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "breed:edit")
	@RequestMapping(value = "/breed/edit/{id}")
	@BizLog(type = LogConstant.category, desc = "修改商品品种页面")
	@SecurityToken(generateToken = true)
	public String editBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  @PathVariable("id") Integer id,
							  ModelMap model){
		BreedVo breed = categoryService.getBreedById(id);
		model.put("breed", breed);
		return "breed_edit";
	}
	
	/**
	 * 保存品种，id为空新增，id不为空修改
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = {"breed:add","breed:edit"} ,logical = Logical.OR)
	@RequestMapping(value = "/breed/save")
	@BizLog(type = LogConstant.category, desc = "保存品种")
	@SecurityToken(generateToken = true,validateToken=true)
	public void saveBreed(HttpServletRequest request,
							  HttpServletResponse response,
						      @Valid BreedVo bvo,
							  ModelMap model){
		Result result = new Result(true);
		bvo.setAliases(bvo.getAliases().replace("，", ","));
		if(bvo.getId() != null){
			categoryService.updateBreed(bvo);
			result.info("修改品种成功。");
		}else{
			categoryService.addBreed(bvo);
			result.info("新增品种成功。");
		}

        WebUtil.printJson(response, result);
	}
	
	/**
	 * 删除品种
	 * @param request
	 * @param response
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = "breed:delete")
	@RequestMapping(value = "/breed/delete/{id}")
	@BizLog(type = LogConstant.category, desc = "删除品种")
	public void deleteBreed(HttpServletRequest request,
							  HttpServletResponse response,
							  @PathVariable("id") Integer id,
							  ModelMap model){
		List<CommodityVo> vos = commodityService.findCommodityByBreedId(id);
		if(ValidUtils.listNotBlank(vos)){
			Result result = new Result(false).info("该品种下已有商品，请先将所有商品移除后再删除。");
	        WebUtil.printJson(response, result);
	        return;
		}
		categoryService.deleteById(id);
		Result result = new Result(true).info("删除品种成功。");
        WebUtil.printJson(response, result);
	}
	
    private void setCodeCheck(List<Code> source,String target){
    	for(Code code : source){
        	if(StringUtils.isNotBlank(target) && target.contains(code.getId().toString())){
        		code.setChecked(true);
        	}else{
        		code.setChecked(false);
        	}
        }
    }


	/**
	 * 根据品种名查询
	 * @param name
	 * @return
     */
	@RequestMapping(value = "/breed/search", method = RequestMethod.GET)
	@ResponseBody
	@BizLog(type = LogConstant.category, desc = "根据名称查询品种列表")
	public Result searchBreed(String name){
		return new Result(true).data(categoryService.findBreedByName(name));
	}

	/**
	 * 根据品种ID和和code类型来查询对应的Code list.
	 * @param beedId
	 * @param typeId
     * @return
     */
	@RequestMapping(value = "/code/query")
	@ResponseBody
	public Result findCode(Integer beedId,String typeId) {
		return new Result(true).data(categoryService.findCode(beedId, typeId));
	}


	@RequestMapping(value = "/category/gen/pinyin")
	@ResponseBody
	public Result genCategory2Pinyin(){
		categoryService.allCategory2Pinyin();
		return new Result(true).info("拼音生成完成!");
	}


	/**
	 * //根据品种名查
	 * @param breeId
	 * @return
     */
	@RequestMapping(value = "/breed/commodity", method = RequestMethod.GET)
	public String commodityByBreed(Integer breeId, ModelMap model){
		// 根据品种ID 查询品种下的所有商品
		model.put("list",commodityService.queryCommodityByBreedId(breeId));
		BreedVo breed = categoryService.getBreedById(breeId);
		model.put("breed",breed);
		return "breed_sort";
	}

	/**
	 * 保存商品的排序信息
	 * @param commodities
	 * @return
     */
	@RequestMapping(value = "/breed/commodity", method = RequestMethod.POST)
	@ResponseBody
	public Result commodityByBreedSave(@RequestBody List<Commodity> commodities){
		commodityService.batchUpdate(commodities);
		return new Result(true).info("保存排序成功");
	}

}
