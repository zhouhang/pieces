package com.jointown.zy.common.dto;

/**
 * 微信活动查询Dto
 * @author wangjunhu
 * 2015-3-2
 */
public class WxActivitySearchDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 类型 */
	private Integer type;
	/** 开始时间 */
	private String startWxActivityDate;
	/** 结束时间 */
	private String endWxActivityDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStartWxActivityDate() {
		return startWxActivityDate;
	}
	public void setStartWxActivityDate(String startWxActivityDate) {
		this.startWxActivityDate = startWxActivityDate;
	}
	public String getEndWxActivityDate() {
		return endWxActivityDate;
	}
	public void setEndWxActivityDate(String endWxActivityDate) {
		this.endWxActivityDate = endWxActivityDate;
	}
}
