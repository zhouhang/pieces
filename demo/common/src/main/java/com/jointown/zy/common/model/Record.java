package com.jointown.zy.common.model;

public class Record implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 封装指定执行的sql空间
	 */
	private String sqlStr;
	/**
	 * 封装sql排序
	 */
	private String sqlSort;
		
	
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public String getSqlSort() {
		return sqlSort;
	}
	public void setSqlSort(String sqlSort) {
		this.sqlSort = sqlSort;
	}
}

