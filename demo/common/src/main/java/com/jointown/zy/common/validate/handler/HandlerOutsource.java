package com.jointown.zy.common.validate.handler;

import com.jointown.zy.common.validate.Binder;
import com.jointown.zy.common.validate.rule.ExceptRule;
import com.jointown.zy.common.validate.rule.MaxRule;
import com.jointown.zy.common.validate.rule.MinRule;

/**
 * handler外包处理类
 * @author LiuPiao
 *
 */
public class HandlerOutsource {
	
	public static void doHandleMin(BaseAnnotationHandler handler,Binder binder,Object annotationValue){
		MinRule rule = (MinRule)handler;
		//没执行过才执行,避免and影响
		if(binder.getResult()==null || !binder.getResult().containsKey(MinRule.NAME)){
			if(binder.getValue()!=null && rule.min(annotationValue, binder.getValue())){
				binder.addResult(MinRule.NAME, true);
			}else{
				binder.addResult(MinRule.NAME, false);
			}
		}
	}
	
	public static void doHandleMax(BaseAnnotationHandler handler,Binder binder,Object annotationValue){
		MaxRule rule = (MaxRule)handler;
		//没执行过才执行,避免and影响
		if(binder.getResult()==null || !binder.getResult().containsKey(MaxRule.NAME)){
			if(binder.getValue()!=null && rule.max(annotationValue, binder.getValue())){
				binder.addResult(MaxRule.NAME, true);
			}else{
				binder.addResult(MaxRule.NAME, false);
			}
		}
	}
	
	public static void doHandleExcept(BaseAnnotationHandler handler,Binder binder,Object annotationValue){
		ExceptRule rule = (ExceptRule)handler;
		//没执行过才执行,避免and影响
		if(binder.getResult()==null || !binder.getResult().containsKey(ExceptRule.NAME)){
			if(binder.getValue()!=null && rule.except(annotationValue, binder.getValue())){
				binder.addResult(ExceptRule.NAME, true);
			}else{
				binder.addResult(ExceptRule.NAME, false);
			}
		}
	}
	
}
