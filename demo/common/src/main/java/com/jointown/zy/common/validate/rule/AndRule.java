package com.jointown.zy.common.validate.rule;

import java.lang.annotation.Annotation;

/**
 * annotation的and属性处理规则
 * @author LiuPiao
 *
 */
public interface AndRule {
	
	public static final String NAME = "and";
	
	
	/**
	 * 方法定义 annotation的and属性验证
	 * @param keys and属性值
	 * @param targetValue 验证的属性值
	 * @param anno 触发的annotation
	 * @return String[] 返回成功的key数组,如果返回的集合和keys长度一致则全通过,否则就不通过
	 */
	public String[] and(String[] keys,Object targetValue,Annotation anno);//(此处可以进化成反射自动匹配,自动调用方法,不过要考虑性能影响)
}
