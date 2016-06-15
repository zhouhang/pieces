package com.jointown.zy.common.vo;

import java.math.BigDecimal;
import java.util.List;

public class PriceIndexVo {
	
	/**时间数组*/
	private List<String> dateStr;
	/**指数(图表数据)*/
	private List<BigDecimal> data;
	
	/**今日指数（综合指数）*/
	private String todayIndex;
	/**近一周指数（综合指数）*/
	private String weekIndex;
	/**近一月指数（综合指数）*/
	private String monthIndex;
	/**近半年指数（综合指数）*/
	private String halfYearIndex;
	/**近一年指数（综合指数）*/
	private String yearIndex;
	
	
	public List<String> getDateStr() {
		return dateStr;
	}
	public void setDateStr(List<String> dateStr) {
		this.dateStr = dateStr;
	}
	public List<BigDecimal> getData() {
		return data;
	}
	public void setData(List<BigDecimal> data) {
		this.data = data;
	}
	public String getTodayIndex() {
		return todayIndex;
	}
	public void setTodayIndex(String todayIndex) {
		this.todayIndex = todayIndex;
	}
	public String getWeekIndex() {
		return weekIndex;
	}
	public void setWeekIndex(String weekIndex) {
		this.weekIndex = weekIndex;
	}
	public String getMonthIndex() {
		return monthIndex;
	}
	public void setMonthIndex(String monthIndex) {
		this.monthIndex = monthIndex;
	}
	public String getHalfYearIndex() {
		return halfYearIndex;
	}
	public void setHalfYearIndex(String halfYearIndex) {
		this.halfYearIndex = halfYearIndex;
	}
	public String getYearIndex() {
		return yearIndex;
	}
	public void setYearIndex(String yearIndex) {
		this.yearIndex = yearIndex;
	}
	
	
}
