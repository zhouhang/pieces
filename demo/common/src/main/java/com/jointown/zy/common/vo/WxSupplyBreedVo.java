package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: WxSupplyBreedVo
 * @Description: 微信发布供应信息品种信息Vo
 * @Author: wangjunhu
 * @Date: 2015年5月8日
 * @Version: 1.0
 */
public class WxSupplyBreedVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Long breedId;
	private String breedName;
	private List<String> breedStandardLevels;
	private String qtyUnit;
	private List<String> breedPlaces;
	
	public Long getBreedId() {
		return breedId;
	}
	public void setBreedId(Long breedId) {
		this.breedId = breedId;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	public List<String> getBreedStandardLevels() {
		return breedStandardLevels;
	}
	public void setBreedStandardLevels(String breedStandardLevel) {
		if(breedStandardLevel!=null&&!breedStandardLevel.isEmpty()){
			this.breedStandardLevels = new ArrayList<String>();
			String breedStandardLevelz[] = breedStandardLevel.split(",");
			for (String string : breedStandardLevelz) {
				this.breedStandardLevels.add(string);
			}
		}
	}
	public String getQtyUnit() {
		return qtyUnit;
	}
	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}
	public List<String> getBreedPlaces() {
		return breedPlaces;
	}
	public void setBreedPlaces(String breedPlace) {
		if(breedPlace!=null&&!breedPlace.isEmpty()){
			this.breedPlaces = new ArrayList<String>();
			String breedPlacez[] = breedPlace.split(",");
			for (String string : breedPlacez) {
				this.breedPlaces.add(string);
			}
		}
	}
}
