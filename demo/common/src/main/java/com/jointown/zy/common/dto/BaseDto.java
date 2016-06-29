package com.jointown.zy.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.util.ReflectionUtil;
import com.jointown.zy.common.validate.ValidateAnnotationHandler;


/**
 * @date 2014-01-07 12:15:56
 */
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 降序 */
	public static final String DIR_DESC = "DESC";
	/** 升序 */
	public static final String DIR_ASC = "ASC";

	/** 每页记录数 */
	private Integer pageSize;
	
	/** 当前页数 */
	private Integer pageNo;

	/** 起始行 */
	private Long startNo;

	/** 排序的字段 */
	private String sort;

	/** 顺序或者逆序 */
	private String dir = "DESC";
	
	protected List<ErrorMessage> errors;
	
	
	/**
	 * 客户化验证逻辑
	 * @return
	 */
	public List<ErrorMessage> validate(){
		try {
			this.errors = ValidateAnnotationHandler.handle(this, this.getClass());
		} catch (ErrorException e) {
			e.printStackTrace();
		}
		return this.errors;
	}
	
	/**
	 * 根据字段名验证字段是否为空
	 * @param fieldNames
	 * @return
	 */
	public Map<String,Boolean> validateEmpty(String...fieldNames){
		Map<String,Boolean> flag = new HashMap<String,Boolean>();
		if(fieldNames==null||fieldNames.length==0){
			fieldNames = ReflectionUtil.getList(Arrays.asList(this.getClass().getDeclaredFields()), String.class, "name").toArray(new String[0]);
		}
		for(String fieldName:fieldNames){
			Object fieldValue = ReflectionUtil.get(this, fieldName);
			if(fieldValue==null){
				flag.put(fieldName, false);
			}else{
				if(fieldValue instanceof String){
					if(StringUtils.isEmpty((String)fieldValue)){
						flag.put(fieldName, false);
					}
				}
			}
		}
		return flag;
	}
	
	
	protected void addError(ErrorMessage error){
		if(errors==null){
			errors = new ArrayList<ErrorMessage>();
		}
		errors.add(error);
	}
	


	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getStartNo() {
		return startNo;
	}

	public void setStartNo(Long startNo) {
		this.startNo = startNo;
	}
	
	public List<ErrorMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
	};

	public Integer getPageNo() {
		if (this.pageNo == null) {
            return new Integer(1);
        }
        return this.pageNo;
	}

	public void setPageNo(Integer pageNo) {
		if ((pageNo == null) || (pageNo.intValue() <= 0))
            this.pageNo = new Integer(1);
        else{
        	this.pageNo = pageNo;
        }
	}
	
}