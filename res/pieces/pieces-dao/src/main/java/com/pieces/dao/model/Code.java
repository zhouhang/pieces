package com.pieces.dao.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 常用枚举值的表
 * 例如:商品规格,产地,等级,首页广告类型
 */
public class Code  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	//自定义的Code值
	private String code;
	
	//显示值
	private String name;
	
	private boolean checked;
	
	//逗号隔开的id值
	private String idStr;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	
	
}