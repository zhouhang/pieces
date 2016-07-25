package com.pieces.biz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.elasticsearch.document.CommodityDoc;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CategoryService;
import com.pieces.service.CommoditySearchService;
import com.pieces.service.CommodityService;
import com.pieces.service.enums.CodeEnum;
import com.pieces.service.utils.ValidUtils;
import com.pieces.tools.utils.WebUtil;

/**
 * Author: ff
 * 7/19/16.
 * 商品信息
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController{

    @Autowired
    private CommoditySearchService commoditySearchService;


    @Autowired
    CommodityService commodityService;
    
	@Autowired
	private CategoryService categoryService;

//    @Autowired
//    private DefaultUploadFile defaultUploadFile;
    @RequestMapping(value = "/index")
    public String index(Integer pageSize, Integer pageNum, CommodityVO commodityVO , ModelMap model){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        
        //列表页“已经筛选”处显示
        List<String> screens = new ArrayList<String>();
        
        //处理中文字符串，加单引号
        commodityVO.setExecutiveStandardNameStr(formatString(commodityVO.getExecutiveStandardNameStr()));
        commodityVO.setFactoryStr(formatString(commodityVO.getFactoryStr()));
        
        //查询分页数据
        PageInfo<CommodityVO> pageInfo = commodityService.query(commodityVO,pageNum, pageSize);
        
        //产地，规格和等级参数字符串
        String specs = "";
        String origins = "";
        String level = "";
        //品种id字符串
        String breedIds = "";
        //查询品种
        if(commodityVO.getBreedId() != null){
        	//获取类别
            Category category = categoryService.findById(commodityVO.getBreedId());
            commodityVO.setBreedName(category.getName());
            Category parent = categoryService.findById(category.getPartenId());     
            model.put("parent", parent);
            specs = category.getSpecs();
            origins = category.getOrigins();
            level = category.getLevels();
            breedIds = category.getId().toString();
        }
        
        //查询分类
        if(commodityVO.getBreedId() == null && commodityVO.getCategoryId() != null){
        	//获取类别
            Category category = categoryService.findById(commodityVO.getCategoryId());
            commodityVO.setCategoryName(category.getName());
            List<CategoryVo> breedList = categoryService.findBreedByPartenId(commodityVO.getCategoryId());
            model.put("parent", category);
            if(ValidUtils.listNotBlank(breedList)){
                for(CategoryVo vo : breedList){
                	if(StringUtils.isNotBlank(vo.getSpecs())){
                		specs = specs + vo.getSpecs() + ",";
                	}
                	if(StringUtils.isNotBlank(vo.getLevels())){
                		level = level + vo.getLevels() + ",";
                	}
                	if(StringUtils.isNotBlank(vo.getSpecs())){
                		origins = origins + vo.getOrigins() + ",";
                	}
                	if(StringUtils.isNotBlank(vo.getSpecs())){
                		breedIds = breedIds + vo.getId() + ",";
                	}
                }
                specs = specs.substring(0 , specs.length() - 1);
                level = level.substring(0 , level.length() - 1);
                origins = origins.substring(0 , origins.length() - 1);
                breedIds = breedIds.substring(0 , breedIds.length() - 1);
            }
        }
        
        //查询所有
        if(commodityVO.getBreedId() == null && commodityVO.getCategoryId() == null){
        	//获取类别
            List<CategoryVo> breedList = categoryService.findBreedNoPage(new CategoryVo());
            if(ValidUtils.listNotBlank(breedList)){
	            for(CategoryVo vo : breedList){
	            	if(StringUtils.isNotBlank(vo.getSpecs())){
	            		specs = specs + vo.getSpecs() + ",";
	            	}
	            	if(StringUtils.isNotBlank(vo.getLevels())){
	            		level = level + vo.getLevels() + ",";
	            	}
	            	if(StringUtils.isNotBlank(vo.getSpecs())){
	            		origins = origins + vo.getOrigins() + ",";
	            	}
	            }
	            specs = specs.substring(0 , specs.length() - 1);
	            level = level.substring(0 , level.length() - 1);
	            origins = origins.substring(0 , origins.length() - 1);
	        }
        }
        
        //获取品种属性
        List<Code> specifications;
        List<Code> place;
        List<Code> levels;
        if(StringUtils.isNotBlank(specs)){
        	specifications = categoryService.findCodeByString(specs);
        }else{
        	specifications = categoryService.findCode(CodeEnum.Type.ORIGIN.name());
        }
        if(StringUtils.isNotBlank(origins)){
        	place = categoryService.findCodeByString(origins);
        }else{
        	place = categoryService.findCode(CodeEnum.Type.ORIGIN.name());
        }
        if(StringUtils.isNotBlank(specs)){
        	levels = categoryService.findCodeByString(level);
        }else{
        	levels = categoryService.findCode(CodeEnum.Type.LEVEL.name());
        }
        
        //设置code是否选中
        setCodeCheck(specifications,commodityVO.getSpecNameStr(),screens);
        setCodeCheck(place,commodityVO.getOriginOfNameStr(),screens);
        setCodeCheck(levels,commodityVO.getLevelNameStr(),screens);
        
        model.put("specifications", specifications);
        model.put("place", place);
        model.put("levels", levels);
        
        //获取执行标准
        List<CommodityVO> standards = commodityService.findStandardByBreedId(breedIds);
        //设置执行标准是否选中
        for(CommodityVO vo : standards){
        	if(StringUtils.isNotBlank(commodityVO.getExecutiveStandardNameStr()) && commodityVO.getExecutiveStandardNameStr().contains(vo.getExecutiveStandard())){
        		vo.setChecked(true);
        		screens.add(vo.getExecutiveStandard());
        	}else{
        		vo.setChecked(false);
        	}
        }
        //获取生产厂家
        List<CommodityVO> factorys = commodityService.findFactoryByBreedId(breedIds);
        //设置生产厂家是否选中
        for(CommodityVO vo : factorys){
        	if(StringUtils.isNotBlank(commodityVO.getFactoryStr()) && commodityVO.getFactoryStr().contains(vo.getFactory())){
        		vo.setChecked(true);
        		screens.add(vo.getFactory());
        	}else{
        		vo.setChecked(false);
        	}
        }
        model.put("standards", standards);
        model.put("factorys", factorys);
        model.put("screens", screens);
        
        
        model.put("pageNum", pageNum);
        model.put("pageSize", pageSize);
        model.put("pageInfo", pageInfo);
        model.put("commodity", commodityVO);
        model.put("commodityParam", commodityVO.toString());
        return "product_list";
    }
    
    //处理中文字符串，加单引号
    private String formatString(String str){
    	if(StringUtils.isBlank(str)){
    		return str;
    	}
    	
    	if(str.indexOf(",")>0){
    		String[] strs = str.split(",");
        	String newStr = "";
        	for(int i=0 ; i<strs.length ; i++){
        		newStr = newStr + "'" + strs[i] + "'" + ",";
        	}
        	newStr = newStr.substring(0,newStr.length()-1);
        	return newStr;
        }else{
        	return "'" + str + "'";
        }
    	
    }
    
    private void setCodeCheck(List<Code> source,String target,List<String> screens){
    	if(ValidUtils.listNotBlank(source)){
        	for(Code code : source){
            	if(StringUtils.isNotBlank(target) && target.contains(code.getId().toString())){
            		code.setChecked(true);
            		screens.add(code.getName());
            	}else{
            		code.setChecked(false);
            	}
            }
    	}
    }


    /**
     * 搜索并跳转到搜索结果页面
     * @param request
     * @param response
     * @param pageNum
     * @param pageSize
     * @param model
     * @param keyword
     * @return
     */
    @RequestMapping(value = "search")
    public String proResult(HttpServletRequest request,
                            HttpServletResponse response,
                            Integer pageNum,
                            Integer pageSize,
                            ModelMap model,
                            String keyword){
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        Page<CommodityDoc> commodityDocPage= commoditySearchService.findByNameOrCategoryName(pageNum,pageSize,keyword);
        model.put("commodityDocPage",commodityDocPage);
        model.put("keyword",keyword);
        return "product_search_result";
    }


    /**
     * 自动补全搜索关键字
     * @param request
     * @param response
     * @param keyword
     */
    @RequestMapping(value = "search/auto")
    public void autoComplete(HttpServletRequest request,
                             HttpServletResponse response,
                             String keyword){
        if(StringUtils.isBlank(keyword) ){
            return;
        }
        List<Map<String,String>> result = commoditySearchService.findByName(keyword);
        WebUtil.print(response,result);
    }

    @RequestMapping(value = "/{id}")
    public String detail(@PathVariable("id")Integer id, ModelMap model) {
        CommodityVO commodity =  commodityService.findVoById(id);
        if (commodity == null) {
            //TODO: 商品不存在
            return  "redirect:error/404";
        }
        Category category = categoryService.findById(commodity.getCategoryId());
        Category category1 = categoryService.findById(category.getPartenId());

        List<CommodityVO> featured = commodityService.featured(null, category.getId(),category1.getId());
        model.put("category", category1.getName());
        model.put("categoryId", category1.getId());
        model.put("commodity", commodity);
        model.put("featured", featured);

        return "product_detail";
    }

}
