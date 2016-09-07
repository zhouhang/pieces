package com.pieces.dao.vo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import com.pieces.dao.model.Commodity;
import org.apache.commons.lang.StringUtils;


public class CommodityVo extends Commodity implements Serializable {

	private static final long serialVersionUID = 1L;


	private String categoryName;

	// 商品二级类别ID的集合 1,2,3
	private String categoryIds;

	//与商品相关联的二级品种ID
	private Integer breedId;
	
	private String breedIds;
	
	private String breedName;
	
	private String eqName;

	private String eqName;

	//设置商品列表页面该code是否选中
	private boolean checked;

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
	
	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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
}