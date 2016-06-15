package com.jointown.zy.common.dto;

import java.util.Date;
/**
 * @description 查询收藏列表的时候，的查询条件
 * @author seven
 *
 */
public class QueryCollectDto {
	private String lowPrice;//最低挂牌价格
	private String highPrice;//最高挂牌价格
//	private Date datetimepicker1;//挂牌开始时间
//	private Date datetimepicker2;//挂牌结束时间
	private String datetimepicker1;//挂牌开始时间
	private String datetimepicker2;//挂牌结束时间
	private String order;//排序
	private String origin;//产地
	private String breed; //品种ID
	public String getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}
	public String getHighPrice() {
		return highPrice;
	}
	/*public void setHighPrice(String highPrice) {
		this.highPrice = highPrice;
	}
	public Date getDatetimepicker1() {
		return datetimepicker1;
	}
	public void setDatetimepicker1(Date datetimepicker1) {
		this.datetimepicker1 = datetimepicker1;
	}
	public Date getDatetimepicker2() {
		return datetimepicker2;
	}
	public void setDatetimepicker2(Date datetimepicker2) {
		this.datetimepicker2 = datetimepicker2;
	}*/
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDatetimepicker1() {
		return datetimepicker1;
	}
	public void setDatetimepicker1(String datetimepicker1) {
		this.datetimepicker1 = datetimepicker1;
	}
	public String getDatetimepicker2() {
		return datetimepicker2;
	}
	public void setDatetimepicker2(String datetimepicker2) {
		this.datetimepicker2 = datetimepicker2;
	}
	public void setHighPrice(String highPrice) {
		this.highPrice = highPrice;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	
}
