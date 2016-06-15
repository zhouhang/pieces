package com.jointown.zy.common.vo;

/**
 * 品种VO
 * @author wangjunhu
 *	2014-12-24
 */
public class BreedVo {
	
	private Long breedId;
	private String breedCode;
	private String breedName;
	private String breedCountUnit;
	private String breedStandardLevel;

	public Long getBreedId() {
		return breedId;
	}

	public void setBreedId(Long breedId) {
		this.breedId = breedId;
	}

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
}
