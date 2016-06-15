/**
 * @author guoyb
 * 2015年3月9日 下午12:24:54
 */
package com.jointown.zy.common.vo;

/**
 * 药材品种信息
 * 
 * @author guoyb
 * 2015年3月9日 下午12:24:54
 */
public class EastPinZhongVo {
	
	//品种id
	private Integer pzid;
	
	//药材id
	private Integer ycid;
	
	//规格
	private String guige;
	
	//产地
	private String chandi;

	public Integer getPzid() {
		return pzid;
	}

	public void setPzid(Integer pzid) {
		this.pzid = pzid;
	}

	public Integer getYcid() {
		return ycid;
	}

	public void setYcid(Integer ycid) {
		this.ycid = ycid;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getChandi() {
		return chandi;
	}

	public void setChandi(String chandi) {
		this.chandi = chandi;
	}
}
