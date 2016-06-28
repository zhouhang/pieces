/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.vo;


/**
 * @ClassName: RangeVo
 * @Description: 范围对象
 * @Author: robin.liu
 * @Date: 2015年8月25日
 * @Version: 1.0
 */
public class RangeVo<T> {
	private final T start;
	private final T end;
	
	public RangeVo(T start,T end) {
		this.start = start;
		this.end = end;
	}
	
	public Object getStart() {
		return start;
	}
	public Object getEnd() {
		return end;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+getStart()+","+getEnd()+"]";
	}
	
	
}
