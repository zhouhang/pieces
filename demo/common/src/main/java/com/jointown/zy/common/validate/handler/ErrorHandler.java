package com.jointown.zy.common.validate.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.validate.Binder;
import com.jointown.zy.common.validate.annotation.Error;


public class ErrorHandler extends BaseAnnotationHandler {

	@Override
	protected List<ErrorMessage> doHandleAnnotation(Map<HandlerEnum,Binder> context,List<ErrorMessage> errors) throws ErrorException{
		Error annotation = (Error)getBinder(context, HandlerEnum.ERROR).getAnnotation();
		//没有单独指定error annotation的属性
		if(ArrayUtils.isEmpty(annotation.key())){
			return handleDefaultError(context, errors);
		}
		//遍历error annotation定义的所有key进而提取errors属性
		int i=0;
		for(String key:annotation.key()){
			if((getResult(context)!=null) && (getResult(context).containsKey(key)) && (!getResult(context).get(key))){//如果验证结果包括此key且结果为失败，则加入对应异常并返回
				errors = addError(errors, annotation.errors()[i]);
				return errors;
			}
			i++;
		}
		return errors;
	}
	
	private Map<String,Boolean> getResult(Map<HandlerEnum,Binder> context){
		return context.get(HandlerEnum.COMMON).getResult();
	}
	
}
