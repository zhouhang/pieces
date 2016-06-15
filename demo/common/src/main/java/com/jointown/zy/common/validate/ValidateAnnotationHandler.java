package com.jointown.zy.common.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.util.ReflectionUtil;
import com.jointown.zy.common.validate.annotation.Error;
import com.jointown.zy.common.validate.annotation.NumberValidate;
import com.jointown.zy.common.validate.annotation.StringValidate;
import com.jointown.zy.common.validate.annotation.TimeValidate;
import com.jointown.zy.common.validate.handler.BaseAnnotationHandler;
import com.jointown.zy.common.validate.handler.BaseAnnotationHandler.HandlerEnum;
import com.jointown.zy.common.validate.handler.ErrorHandler;
import com.jointown.zy.common.validate.handler.NumberHandler;
import com.jointown.zy.common.validate.handler.StringHandler;
import com.jointown.zy.common.validate.handler.TimeHandler;

public class ValidateAnnotationHandler {
	
	private static Map<Class<? extends Annotation>, BaseAnnotationHandler> strategyMap = new HashMap<Class<? extends Annotation>, BaseAnnotationHandler>();
	
	/**
	 * 初始化annatation处理策略
	 */
	static{
		//(此处可以进化成扫描包自动读取)
		strategyMap.put(Error.class, new ErrorHandler());
		strategyMap.put(StringValidate.class, new StringHandler());
		strategyMap.put(NumberValidate.class, new NumberHandler());
		strategyMap.put(TimeValidate.class, new TimeHandler());
	}
	
	/**
	 * 处理验证annotation入口方法
	 * @param t
	 * @param cls
	 * @return
	 */
	public static <T> List<ErrorMessage> handle(T target,Class<? extends T> cls) throws ErrorException{
		List<ErrorMessage> result = null;
		for(Field field:cls.getDeclaredFields()){
			field.setAccessible(true);
			//组装处理器链条
			Map<HandlerEnum, Binder> context = new HashMap<HandlerEnum, Binder>();
			for(Annotation annotation:field.getAnnotations()){
				//设置Context和Value
				Object value = ReflectionUtil.get(target, field.getName());
				Binder binder = new Binder(annotation, strategyMap.get(annotation.annotationType()), value);
				if(annotation instanceof Error){
					context.put(HandlerEnum.ERROR, binder);
				}else{//最好还要过滤其它类型的annotation
					context.put(HandlerEnum.COMMON, binder);
				}
			}
			//过滤不需要验证的情况
			if(MapUtils.isEmpty(context) || context.get(HandlerEnum.COMMON)==null){
				continue;
			}
			//处理annotation(此处可以进化成处理器链式操作,考虑到性能暂不进化)
			if(context.get(HandlerEnum.COMMON)!=null){
				result = context.get(HandlerEnum.COMMON).getHandler().handleAnnotation(context,result);//处理common
			}
			if(context.get(HandlerEnum.ERROR)!=null){
				result = context.get(HandlerEnum.ERROR).getHandler().handleAnnotation(context,result);//处理error
			}
			
		}
		return result;
	}
	
	

}
