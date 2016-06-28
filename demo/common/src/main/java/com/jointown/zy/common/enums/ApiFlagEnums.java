package com.jointown.zy.common.enums;

/**
 * wms 接口标识枚举
 * @author ldp
 * 时间：2015年3月11日
 * version 0.1
 */
public enum ApiFlagEnums {
	/**
	 * WMS 系统API 返回值
	 */
	WMS_API_RES_SUCCESS("101","成功"),
	WMS_API_RES_FAIL("102","失败"),
	WMS_API_RES_CHECK_SIGNATURE_ERROR("103","验签失败"),
	WMS_API_RES_OTHER_ERROR("104","其他异常"),
	WMS_API_RES_PARAMETER_ERROR("105","参数异常"),
	WMS_API_RES_NOT_EXIST("106","不存在"),

	UC_USER_ADD("wms.uc.user.add","前台用户添加"),
	UC_USER_UPDATE("wms.uc.user.update","前台用户修改"),
	BOSS_USER_ADD("wms.boss.user.add","后台用户添加"),
	BOSS_USER_UPDATE("wms.boss.user.update","后台用户修改"),
	BREED_ADD("wms.breed.add","品种添加"),
	BREED_UPDATE("wms.breed.update","品种修改"),
	WL_ADD("wms.wl.add","仓单新增"),
	WL_UPDATE("wms.wl.update","仓单修改"),
	WARE_HOUSE_ADD("wms.ware.house.add","仓库新增"),
	WARE_HOUSE_UPDATE("wms.ware.house.update","仓库修改"),
	WL_FREEZE("wms.wl.freeze","交易冻结"),
	WL_FREEZE_SUCCESS("wms.wl.freeze.success","交易冻结成功"),
	WL_APPLY_SPLIT("wms.wl.apply.split","仓单申请分割"),
	WL_SPLIT_SUCCESS("wms.split.success","仓单分割成功"),
	WL_UNFREEZE("wms.wl.unfreeze","仓单解冻"),
	WL_EX_THRESHOLD_QUERY("wms.wl.ex.threshold.query","仓单出库阈值查询"),
	
	SYNC_LOG_TYPE_WMS("1", "WMS"),
	SYNC_LOG_TYPE_SUPPLY("2", "供应链"),
	SYNC_LOG_SYNC_STATUS_SUCCESS("1", "成功"),
	SYNC_LOG_SYNC_STATUS_FAIL("2", "失败");
	
	
	private String flag;
	private String des;
	
	private ApiFlagEnums(String flag, String des) {
		this.flag = flag;
		this.des = des;
	}

	public String getFlag() {
		return flag;
	}

	public String getDes() {
		return des;
	}
	
	
}
