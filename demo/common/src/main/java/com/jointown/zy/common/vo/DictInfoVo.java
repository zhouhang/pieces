package com.jointown.zy.common.vo;

/**
 * 品种计量单位VO
 * @author wangjunhu
 *	2014-12-25
 */
public class DictInfoVo {
	
	private Integer id;
	private String dictCode;
	private String dictType;
	private String dictValue;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
}
