package com.jointown.zy.common.dto;

/**
 * 仓单管理查询Dto
 * @author wangjunhu
 * 2014-12-22
 */
public class BusiWhlistSearchDto extends BaseDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wareHouseId;  //仓库ID
	private String wareHouseName; //仓库名称
	private String startWlrkDate; //查询开始时间
	private String endWlrkDate;   //查询结束时间
	private String breedName;     //品种名称
	private String account;       //用户
	private Integer wlState;       //是否质押
	/**
	 * add by songwei 2015.6.16 11:45 后台系统仓管管理增加仓单ID搜索条件
	 */
	private String wlId;         //仓单id
	
	/**
	 * @return the wlId
	 */
	public String getWlId() {
		return wlId;
	}
	/**
	 * @param wlId the wlId to set
	 */
	public void setWlId(String wlId) {
		this.wlId = wlId;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}
	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}
	public String getWareHouseName() {
		return wareHouseName;
	}
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
	public String getStartWlrkDate() {
		return startWlrkDate;
	}
	public void setStartWlrkDate(String startWlrkDate) {
		this.startWlrkDate = startWlrkDate;
	}
	public String getEndWlrkDate() {
		return endWlrkDate;
	}
	public void setEndWlrkDate(String endWlrkDate) {
		this.endWlrkDate = endWlrkDate;
	}
	public String getBreedName() {
		return breedName;
	}
	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getWlState() {
		return wlState;
	}
	public void setWlState(Integer wlState) {
		this.wlState = wlState;
	}
}
