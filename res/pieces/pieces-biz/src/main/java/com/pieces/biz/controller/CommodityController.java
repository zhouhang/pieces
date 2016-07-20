package com.pieces.biz.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.Category;
import com.pieces.dao.model.Code;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CategoryVo;
import com.pieces.dao.vo.CommodityVO;
import com.pieces.service.CategoryService;
import com.pieces.service.CommodityService;
import com.pieces.service.constant.bean.Result;
import com.pieces.service.enums.CommodityEnum;
import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.utils.ImageUtil;

/**
 * Author: ff
 * 7/19/16.
 * 商品信息
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController{

    @Autowired
    CommodityService commodityService;
    
	@Autowired
	private CategoryService categoryService;

//    @Autowired
//    private DefaultUploadFile defaultUploadFile;
    @RequestMapping(value = "/index")
    public String index(Integer pageSize, Integer pageNum, CommodityVO commodityVO , ModelMap model){
    	commodityVO.setCategoryId(45);
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        
        //列表页“已经筛选”处显示
        List<String> screens = new ArrayList<String>();
        
        //处理中文字符串，加单引号
        commodityVO.setExecutiveStandardNameStr(formatString(commodityVO.getExecutiveStandardNameStr()));
        commodityVO.setFactoryStr(formatString(commodityVO.getFactoryStr()));

        PageInfo<CommodityVO> pageInfo = commodityService.query(commodityVO,pageNum, pageSize);
        
        if(commodityVO.getCategoryId() != null){
        	//获取类别
            Category category = categoryService.findById(commodityVO.getCategoryId());
            commodityVO.setCategoryName(category.getName());
            category = categoryService.findById(category.getPartenId());     
            model.put("category", category);
            
            //获取品种属性
            List<Code> specifications = categoryService.findCode(commodityVO.getCategoryId(), CommodityEnum.COMMODITY_SPECIFICATIONS.getValue());
            List<Code> place = categoryService.findCode(commodityVO.getCategoryId(), CommodityEnum.COMMODITY_PLACE.getValue());
            //设置code是否选中
            setCodeCheck(specifications,commodityVO.getSpecNameStr(),screens);
            setCodeCheck(place,commodityVO.getOriginOfNameStr(),screens);
            
            model.put("specifications", specifications);
            model.put("place", place);
            
            //获取执行标准
            List<CommodityVO> standards = commodityService.findStandardByBreedId(commodityVO.getCategoryId());
            //设置执行标准是否选中
            for(CommodityVO vo : standards){
            	if(StringUtils.isNotBlank(commodityVO.getExecutiveStandardNameStr()) && commodityVO.getExecutiveStandardNameStr().contains(vo.getExecutiveStandardName())){
            		vo.setChecked(true);
            		screens.add(vo.getExecutiveStandardName());
            	}else{
            		vo.setChecked(false);
            	}
            }
            //获取生产厂家
            List<CommodityVO> factorys = commodityService.findFactoryByBreedId(commodityVO.getCategoryId());
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
        }
        
        model.put("pageNum", pageNum);
        model.put("pageSize", pageSize);
        model.put("pageInfo", pageInfo);
        model.put("commodity", commodityVO);
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
