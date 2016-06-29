package com.jointown.zy.common.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 描述： 交易日志枚举<br/>
 * 
 * 日期： 2015年3月16日<br/>
 * 
 * 作者： Mr.songwei<br/>
 *
 * 版本： V1.0<br/>
 */
public enum WhlistLogEnum {
	/** 后台审核挂牌失败，归还仓单可挂牌数量 */
	WHLISTBOSSCANCEL("1", "挂牌后台审核失败，归还仓单可挂牌数量"),
	/** 前台操作挂牌，减去相应仓单可挂牌数量 */
	WHLISTING("2", "前台操作挂牌，减去相应仓单可挂牌数量"),
	/** 前台取消挂牌，归还仓单可挂牌数量 */
	WHLISTUSERCANCEL("3", "前台取消挂牌，归还仓单可挂牌数量"),
	/** 前台修改挂牌操作，减去相应仓单可挂牌数量 */
	WHLISTINGUPDATE("4", "前台修改挂牌操作，减去相应仓单可挂牌数量"),
	/** 程序异常*/
	WHILISTERROR("5", "程序异常，未知错误");
	
	private String code;
	private String codeName;
	private WhlistLogEnum(){}
	private WhlistLogEnum(String code, String codeName){
		this.code = code;
		this.codeName = codeName;
	}
	
	public static Map<String, String> toMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (WhlistLogEnum item : WhlistLogEnum.values()) {
			map.put(item.getCode(), item.getCodeName());
		}
		return map;
	}
	
	/**
	 * 取得code
	 * @return code
	 */
	public String getCode() {
	    return code;
	}
	/**
	 * 设定code
	 * @param code code
	 */
	public void setCode(String code) {
	    this.code = code;
	}
	/**
	 * 取得codeName
	 * @return codeName
	 */
	public String getCodeName() {
	    return codeName;
	}
	/**
	 * 设定codeName
	 * @param codeName codeName
	 */
	public void setCodeName(String codeName) {
	    this.codeName = codeName;
	}
}
