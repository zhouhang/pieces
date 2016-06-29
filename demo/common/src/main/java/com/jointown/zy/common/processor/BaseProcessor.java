package com.jointown.zy.common.processor;

import java.util.List;
import java.util.Map;

/**
 * 抽象责任链逻辑处理类,具体子类可以扩展实现责任链模式处理
 * @author LiuPiao
 *
 */
public abstract class BaseProcessor {
	
	private Map<Object,Object> context;
	
	private Object value;
	
	private BaseProcessor next;
	
	public boolean hasNext(){
		return next==null?false:true;
	}
	
	/**
	 * 逻辑处理入口方法
	 * @param objects
	 * @return
	 */
	public List<Object> process(List<Object> objects ){
		List<Object> result = null;
		if(getContext()!=null){
			result = doProcess(result);
			if(hasNext()){
				result = next.process(result);
			}
		}
		return result;
	}
	
	/**
	 * 子类实现具体处理逻辑
	 * @param objects
	 * @return
	 */
	protected abstract List<Object> doProcess(List<Object> objects );
	
	/**
	 * 子类实现是否支持处理
	 * @param objects
	 * @return
	 */
	protected abstract boolean support(List<Object> objects );
	
	public BaseProcessor getNext() {
		return next;
	}

	public BaseProcessor setNext(BaseProcessor next) {
		this.next = next;
		return this;
	}

	public Map<Object, Object> getContext() {
		return context;
	}

	public BaseProcessor setContext(Map<Object, Object> context) {
		this.context = context;
		return this;
	}

	public Object getValue() {
		return value;
	}

	public BaseProcessor setValue(Object value) {
		this.value = value;
		return this;
	}
	

}
