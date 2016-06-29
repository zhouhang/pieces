package com.jointown.zy.common.validate.rule;

public interface MaxRule{
	
	public static final String NAME = "max";
	
	public boolean max(Object max, Object targetValue);
}
