package com.jointown.zy.common.dto;

import com.google.gson.Gson;

public class WmsBreedDto {
	
	//品种编码
	private String breedCode;
	//品种名称
	private String breedName;
	//品种别名
	private String breedCName;
	//计量单位
	private String breedCountUnit;
	//规格等级
	private String breedStandardLevel;
	//产地
	private String breedPlace;
	//类目
	private String[] categoryNames;
	//扩展属性
	private String breedExtend;
	//创建时间
	private String createTime;
	//是否有效
	private Integer status;
	//创建人  用户名
	private String creater;
	public String getBreedCode() {
		return breedCode;
	}
	public void setBreedCode(String breedCode) {
		this.breedCode = breedCode;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	public String getBreedCName() {
		return breedCName;
	}
	public void setBreedCName(String breedCName) {
		this.breedCName = breedCName;
	}
	public String getBreedCountUnit() {
		return breedCountUnit;
	}
	public void setBreedCountUnit(String breedCountUnit) {
		this.breedCountUnit = breedCountUnit;
	}
	public String getBreedStandardLevel() {
		return breedStandardLevel;
	}
	public void setBreedStandardLevel(String breedStandardLevel) {
		this.breedStandardLevel = breedStandardLevel;
	}
	public String getBreedPlace() {
		return breedPlace;
	}
	public void setBreedPlace(String breedPlace) {
		this.breedPlace = breedPlace;
	}
	public String[] getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String[] categoryNames) {
		this.categoryNames = categoryNames;
	}
	public String getBreedExtend() {
		return breedExtend;
	}
	public void setBreedExtend(String breedExtend) {
		this.breedExtend = breedExtend;
	}

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	
	public static void main(String[] args) {
		WmsBreedDto dto = new WmsBreedDto();
			dto.setBreedCode("123");
			dto.setBreedCName("花,草");
			dto.setBreedName(null);
			Gson gson = new Gson();
			System.out.println(gson.toJson(dto));
//			String json = JsonHelper.objToJson(dto);
//			System.out.println(json);
			
	}
	
	
}
