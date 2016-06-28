package com.jointown.zy.common.validate.handler;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.validate.Binder;
import com.jointown.zy.common.validate.annotation.StringValidate;
import com.jointown.zy.common.validate.rule.BaseRule;
import com.jointown.zy.common.validate.rule.ExceptRule;
import com.jointown.zy.common.validate.rule.MaxRule;
import com.jointown.zy.common.validate.rule.MinRule;

public class StringHandler extends BaseAnnotationHandler implements BaseRule,MinRule,MaxRule,ExceptRule{
	
	@Override
	protected List<ErrorMessage> doHandleAnnotation(Map<HandlerEnum,Binder> context,List<ErrorMessage> errors) throws ErrorException {
		StringValidate annotation = (StringValidate)getBinder(context).getAnnotation();
		//遍历所有实现的annotation属性规则接口方法(可以反射实现自动遍历,对性能有影响暂不实现)
		Binder binder = getBinder(context);
		//代理实现
		HandlerOutsource.doHandleMin(this, binder, annotation.min());
		HandlerOutsource.doHandleMax(this, binder, annotation.max());
		HandlerOutsource.doHandleExcept(this, binder, annotation.except());
		return errors;
	}
	

	@Override
	public boolean min(Object min, Object targetValue) {
		if((Integer)min==0){//默认值
			return true;
		}
		if(StringUtils.trim(targetValue.toString()).length()>=(Integer)min){
			return true;
		}
		return false;
	}

	@Override
	public boolean max(Object max, Object targetValue) {
		if((Integer)max==0){//默认值
			return true;
		}
		if(StringUtils.trim(targetValue.toString()).length()<=(Integer)max){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean except(Object exceptValue, Object targetValue) {
		if(StringUtils.isEmpty((String)exceptValue)){//默认值
			return true;
		}
		if(!StringUtils.trim(targetValue.toString()).equals((String)exceptValue)){
			return true;
		}
		return false;
	}

	
	@Override
	public String[] and(String[] keys,Object targetValue,Annotation anno) {
		String[] successKeys = null;
		List<String> list = new LinkedList<String>();
		String value = (String)targetValue;
		StringValidate annotation = (StringValidate)anno;
		for(String key:keys){
			//(此处可以进化成反射自动匹配,自动调用方法,考虑到性能暂不进化)
			if(key.equalsIgnoreCase(MinRule.NAME)){
				if(!min(annotation.min(), value)){
					return successKeys;
				}else{
					list.add(MinRule.NAME);
				}
			}else if(key.equalsIgnoreCase(MaxRule.NAME)){
				if(!max(annotation.max(), value)){
					return successKeys;
				}else{
					list.add(MaxRule.NAME);
				}
			}else if(key.equalsIgnoreCase(ExceptRule.NAME)){
				if(!except(annotation.except(), value)){
					return successKeys;
				}else{
					list.add(ExceptRule.NAME);
				}
			}
		}
		successKeys = list.toArray(new String[list.size()]);
		return successKeys;
	}
	
	@Override
	public ErrorRepository error(Annotation annotation){
		return ((StringValidate)annotation).error();
	}
	
	private Binder getBinder(Map<HandlerEnum,Binder> context){
		return context.get(HandlerEnum.COMMON);
	}

}
