package com.ms.service.shiro;


public interface Cache<K, V> extends org.apache.shiro.cache.Cache<K, V> {
	

	public String getName();
	

}
