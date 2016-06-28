package com.jointown.zy.common.validate.rule;


public interface MinRule{
	
	public static final String NAME = "min";
	
	public boolean min(Object min, Object targetValue);
}
