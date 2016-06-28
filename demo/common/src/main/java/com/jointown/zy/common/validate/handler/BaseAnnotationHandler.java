package com.jointown.zy.common.validate.handler;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.jointown.zy.common.exception.CommonErrorException;
import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.util.ReflectionUtil;
import com.jointown.zy.common.validate.Binder;
import com.jointown.zy.common.validate.rule.AndRule;
import com.jointown.zy.common.validate.rule.ErrorRule;

/**
 * 公共处理验证annotation类，封装了公共的方法和抽象
 * @author LiuPiao
 *
 */
public abstract class BaseAnnotationHandler {
	
	public enum HandlerEnum{
		COMMON,
		ERROR
	}
	
	/**
	 * annotation处理的入口方法
	 * @param errors
	 * @return
	 */
	public List<ErrorMessage> handleAnnotation(Map<HandlerEnum,Binder> context,List<ErrorMessage> errors) throws ErrorException{
		// 第1步处理 and逻辑
		boolean isContinue = true;
		if(this instanceof AndRule){//and 处理结果成功则验证余下逻辑,否则返回不验证
			isContinue = handleAnd(context,errors);
		}
		// 第2步看是否处理余下逻辑
		if(isContinue){
			//处理其他验证逻辑
			errors = doHandleAnnotation(context,errors);
		}
		// 第3步看是否存在error handler,如果不存在则用默认error属性
		//如果没有error后续处理,则在此返回默认错误
		if(getBinder(context, HandlerEnum.ERROR)==null){
			if(getBinder(context, HandlerEnum.COMMON).getResult().containsValue(false)){
				errors = handleDefaultError(context, errors);
			}
		}
		return errors;
	}
	
	/**
	 * 处理特殊属性and的入口方法
	 * @param errors
	 * @return
	 */
	protected boolean handleAnd(Map<HandlerEnum,Binder> context,List<ErrorMessage> errors)  throws ErrorException{
		Binder binder = getBinder(context,HandlerEnum.COMMON);
		Annotation anno = binder.getAnnotation();
		String[] key = (String[])ReflectionUtil.getAnnotationValue(AndRule.NAME, anno);
		if(ArrayUtils.isEmpty(key)){//不存在and属性则算通过
			return true;
		}
		String[] successKeys = ((AndRule)this).and(key,binder.getValue(),anno);
		if(ArrayUtils.isEmpty(successKeys) || successKeys.length != key.length){//存在失败情况
			//返回,不进行下一个验证
			binder.addResult(AndRule.NAME, false);
		}else{//如果全成功通过
			binder.addResult(AndRule.NAME, true);
			for(String sKey:successKeys){//绑定处理过的属性
				binder.addResult(sKey, true);
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * 具體處理annotation
	 * @param anno
	 * @param val
	 * @param errorMessages
	 * @return
	 */
	protected abstract List<ErrorMessage> doHandleAnnotation(Map<HandlerEnum,Binder> context,List<ErrorMessage> errors) throws ErrorException;
	
	
	protected List<ErrorMessage> addError(List<ErrorMessage> errors,ErrorRepository errRepo){
		if(errors==null){
			errors = new ArrayList<ErrorMessage>();
		}
		errors.add(new ErrorMessage(errRepo));
		return errors;
	}
	
	/**
	 * 返回默认的error
	 * @return
	 */
	protected ErrorRepository getDefaultError(Map<HandlerEnum,Binder> context){
		if(this instanceof ErrorRule){
			return ((ErrorRule)this).error(getBinder(context, HandlerEnum.COMMON).getAnnotation());
		}
		return null;
	}
	
	/**
	 * 处理默认error
	 * @param defaultError
	 * @param errors
	 * @throws ErrorException
	 */
	protected List<ErrorMessage> handleDefaultError(Map<HandlerEnum,Binder> context,List<ErrorMessage> errors) throws ErrorException{
		ErrorRepository defaultError = getDefaultError(context);
		if(defaultError==null){//验证annotation上没有默认的error属性
			throw new CommonErrorException(new ErrorMessage(ErrorRepository.NO_ERROR));
		}else{
			errors = addError(errors, defaultError);
		}
		return errors;
	}
	
	
	public Binder getBinder(Map<HandlerEnum,Binder> context,HandlerEnum en){
		return context.get(en);
	}

}
