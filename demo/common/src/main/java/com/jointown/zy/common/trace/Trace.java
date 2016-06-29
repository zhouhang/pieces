/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.trace;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SysLogUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * @ClassName: Trace
 * @Description: 踪迹类
 * @Author: robin.liu
 * @Date: 2015年8月19日
 * @Version: 1.0
 */
public class Trace {
	private String id;
	private String who;
	private Date when;
	private TraceWhereEnum where;
	private TraceWhatEnum what;
	private Map<String, Object> data;
	
	/**
	 * 
	 */
	public Trace() {
	}
	
	public Trace(String who,String...id) {
		this.id = ((id==null||id.length==0)?buildId():id[0]);
		this.who = who;
		this.data = new HashMap<String, Object>();
	}
	
	public String getId() {
		return id;
	}
	public Trace setId(String id) {
		this.id = id;
		return this;
	}
	public String getWho() {
		return who;
	}
	public Trace setWho(String who) {
		this.who = who;
		return this;
	}
	public Date getWhen() {
		return when;
	}
	public Trace setWhen(Date when) {
		this.when = when;
		return this;
	}
	public TraceWhereEnum getWhere() {
		return where;
	}
	public Trace setWhere(TraceWhereEnum where) {
		this.where = where;
		return this;
	}
	public TraceWhatEnum getWhat() {
		return what;
	}
	public Trace setWhat(TraceWhatEnum what) {
		this.what = what;
		return this;
	}
	public Map<String, Object>  getData() {
		return data;
	}
	public Trace addData(String key,String value) {
		this.data.put(key, value);
		return this;
	}
	public Trace addDatas(Map<String,Object> data) {
		this.data.putAll(data);
		return this;
	}
	public Trace addDatas(Trace base) {
		this.data.putAll(base.getData());
		return this;
	}
	public Trace removeData(String key) {
		this.data.remove(key);
		return this;
	}
	public Trace setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}
	
	
	public String buildId(){
		return UUID.randomUUID().toString();
	}
	
	@Override
	public String toString(){
		return new StringBuilder().append("[id:").append(id)
				.append(",who:").append(who==null?"":who)
				.append(",when:").append(when==null?"":TimeUtil.getYMDHMS(when))
				.append(",where:").append(where==null?"":where.getCode())
				.append(",what:").append(where==null?"":what.getCode())
				.append(",data:").append(data==null?"":getJsonData(data))
				.append("]")
				.toString();
	}
	
	/**
	 * 
	 * @Description: 根据map转换成json类型数据
	 * @Author: robin.liu
	 * @Date: 2015年8月19日
	 * @param data
	 * @return
	 */
	public String getJsonData(Map<String, Object> data){
		return GsonFactory.toJson(data);
	}

	
	/**
	 * 
	 * @Description: 构造进入详情页踪迹
	 * @Author: robin.liu
	 * @Date: 2015年8月20日
	 * @return
	 */
	public Trace setForEnterListingDetail(String listingId){
		return setWhere(TraceWhereEnum.LISTING_DETAIL)
		.setWhat(TraceWhatEnum.ENTER)
		.setWhen(new Date())
		.addData("listingId", listingId);
	}
	
	/**
	 * 
	 * @Description: 构造离开详情页踪迹
	 * @Author: robin.liu
	 * @Date: 2015年8月20日
	 * @return
	 */
	public Trace setForExitListingDetail(Trace dto){
		return setWhere(TraceWhereEnum.LISTING_DETAIL)
		.setWhat(TraceWhatEnum.ENTER)
		.setWhen(new Date())
		.addDatas(dto);
	}
	
	/**
	 * 
	 * @Description: 构造搜索关键字踪迹
	 * @Author: robin.liu
	 * @Date: 2015年8月20日
	 * @return
	 */
	public Trace setForSearchKeyword(String keyword){
		return setWhere(TraceWhereEnum.LISTING_DETAIL)
		.setWhat(TraceWhatEnum.ENTER)
		.setWhen(new Date())
		.addData("keyword", keyword);
	}
	
	/**
	 * 
	 * @Description: 在business系统中syslog自身
	 * @Author: robin.liu
	 * @Date: 2015年8月25日
	 * @return
	 */
	public Trace syslogForBusiness(){
		SysLogUtil.logForBusiness(toString());
		return this;
	}
	
	
	
}
