package com.pieces.dao.vo;

import java.util.Date;

import com.pieces.dao.model.LogisticalCommodity;

public class LogisticalCommodityVo extends LogisticalCommodity{
	//商品名称
	private String name;
	//切制规格 片型
	private String spec;
	//等级
	private String level;
	//产地
	private String originOf;
	//数量
	private Integer oAmount;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOriginOf() {
		return originOf;
	}
	public void setOriginOf(String originOf) {
		this.originOf = originOf;
	}
	public Integer getoAmount() {
		return oAmount;
	}
	public void setoAmount(Integer oAmount) {
		this.oAmount = oAmount;
	}
}