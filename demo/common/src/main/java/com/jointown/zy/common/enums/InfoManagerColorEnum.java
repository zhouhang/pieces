package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName:InfoManagerColorEnum
 * @author:Calvin.Wangh
 * @date:2015-6-17下午2:19:01
 * @version V1.0
 * @Description:
 * 后台 平台首页管理  入仓/供应/求购 信息管理共用
 * 根据状态 匹配状态颜色
 */
public enum InfoManagerColorEnum {
	
	UNDISPOSE_COLOR(0, "blue"),//未处理
	VALID_COLOR(1, "green"),//有效 
	INVALID_COLOR(2, "red"),//无效
	USER_REVOCA_COLOR(3,"red");//用户撤销
	
	private int status;
	private String statusDesc;
	
	public static Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (InfoManagerColorEnum status : InfoManagerColorEnum.values()) {
			map.put(String.valueOf(status.getStatus()), status.getStatusDesc());
		}
		return map;
	}
	
	private InfoManagerColorEnum(){}
	private InfoManagerColorEnum(int status, String statusDesc) {
		this.status = status;
		this.statusDesc = statusDesc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
		
	

}
