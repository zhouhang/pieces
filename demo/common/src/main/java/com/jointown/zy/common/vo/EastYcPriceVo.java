/**
 * @author guoyb
 * 2015年3月9日 下午12:27:34
 */
package com.jointown.zy.common.vo;

import java.sql.Date;

/**
 * 药材价格
 * 
 * @author guoyb
 * 2015年3月9日 下午12:27:34
 */
public class EastYcPriceVo {
	
	//id
	private Integer jgid;
	
	//品种id
	private Integer pzid;
	
	//价格
	private Float pri;
	
	//时间
	private Date dtm;

	public Integer getJgid() {
		return jgid;
	}

	public void setJgid(Integer jgid) {
		this.jgid = jgid;
	}

	public Integer getPzid() {
		return pzid;
	}

	public void setPzid(Integer pzid) {
		this.pzid = pzid;
	}

	public Float getPri() {
		return pri;
	}

	public void setPri(Float pri) {
		this.pri = pri;
	}

	public Date getDtm() {
		return dtm;
	}

	public void setDtm(Date dtm) {
		this.dtm = dtm;
	}
}
