package com.pieces.dao.vo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.pieces.dao.model.Commodity;
import com.pieces.tools.utils.FileUtil;
import com.pieces.tools.utils.GsonUtil;
import org.apache.commons.lang.StringUtils;


public class CommodityVo extends Commodity {

	private static final long serialVersionUID = 1L;


	private String categoryName;

	// 商品二级类别ID的集合 1,2,3
	private String categoryIds;

	//与商品相关联的二级品种ID
	private Integer breedId;
	
	private String breedIds;
	
	private String breedName;
	

	private String eqName;

	// 商品不同规格缩略图90*90
	private String pictureUrl90;

	// 商品不同规格缩略图180*180
	private String pictureUrl180;

	// 用户查询时当前商品的最近成交价格
	private String orderPrice;

	private Map<String,String> attributeView;

	public Integer getBreedId() {
		return breedId;
	}

	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	
	public String getBreedIds() {
		return breedIds;
	}

	public void setBreedIds(String breedIds) {
		this.breedIds = breedIds;
	}
	

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Map<String, String> getAttributeView() {
		if(StringUtils.isBlank(getAttribute())){
			return null;
		}
		return GsonUtil.jsonToEntity(getAttribute(),LinkedHashMap.class);
	}

	public void setAttributeView(Map<String, String> attributeView) {
		this.attributeView = attributeView;
	}

	@Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        try {
            for(Field field : fields){
                if(field.get(this)!=null
						&& (!field.getName().equals("serialVersionUID")
						&&  !field.getName().equals("checked"))){
                    sb.append("&").append(field.getName()).append("=").append(field.get(this).toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String str = sb.toString();
        if(StringUtils.isNotBlank(str)){
        	str = str.substring(1,str.length());
        }
        return str;
    }

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getPictureUrl90() {
		if (!Strings.isNullOrEmpty(getPictureUrl())) {
			pictureUrl90 = FileUtil.getFilePathNoExt(getPictureUrl()) + "@90" + FileUtil.getFileExt(getPictureUrl());
		}
		return pictureUrl90;
	}

	public void setPictureUrl90(String pictureUrl90) {
		this.pictureUrl90 = pictureUrl90;
	}

	public String getPictureUrl180() {
		if (!Strings.isNullOrEmpty(getPictureUrl())) {
			pictureUrl180 = FileUtil.getFilePathNoExt(getPictureUrl()) + "@180" + FileUtil.getFileExt(getPictureUrl());
		}
		return pictureUrl180;
	}

	public void setPictureUrl180(String pictureUrl180) {
		this.pictureUrl180 = pictureUrl180;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice==null?"-":orderPrice;
	}
}