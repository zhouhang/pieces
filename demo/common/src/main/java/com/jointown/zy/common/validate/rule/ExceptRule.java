package com.jointown.zy.common.validate.rule;

public interface ExceptRule{
	
	public static final String NAME = "except";
	
	public boolean except(Object exceptValue, Object targetValue);
}
