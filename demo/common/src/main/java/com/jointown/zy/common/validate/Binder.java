package com.jointown.zy.common.validate;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.jointown.zy.common.validate.handler.BaseAnnotationHandler;

/**
 * 用于绑定annotation对象,处理对象和值对象。 
 * @author LiuPiao
 *
 */
public class Binder {
	
	private Annotation annotation;
	
	// annotation处理器
	private BaseAnnotationHandler handler;
	// 要验证的属性值
	private Object value;
	//每个binder的handler处理的结果集
	//key为annotation的方法名,value为处理结果
	private Map<String,Boolean> result;
	
	public Binder(Annotation annotation,BaseAnnotationHandler handler,Object value) {
		this.annotation = annotation;
		this.handler = handler;
		this.value = value;
	}

	public Annotation getAnnotation() {
		return annotation;
	}

	public Binder setAnnotation(Annotation annotation) {
		this.annotation = annotation;
		return this;
	}

	public BaseAnnotationHandler getHandler() {
		return handler;
	}

	public Binder setHandler(BaseAnnotationHandler handler) {
		this.handler = handler;
		return this;
	}

	public Object getValue() {
		return value;
	}

	public Binder setValue(Object value) {
		this.value = value;
		return this;
	}

	public Map<String, Boolean> getResult() {
		return result;
	}
	
	/**
	 * result是否存在此key
	 * @param key
	 * @return
	 */
	public boolean resultHas(String key){
		return getResult()==null?false:getResult().containsKey(key);
	}

	public Binder setResult(Map<String, Boolean> result) {
		this.result = result;
		return this;
	}
	
	public Binder addResult(String name,Boolean value) {
		if(result==null){
			result = new HashMap<String, Boolean>();
		}
		result.put(name,value);
		return this;
	}
	
	public Binder removeResult(String name) {
		if(result==null){
			return this;
		}
		result.remove(name);
		return this;
	}
	

}
