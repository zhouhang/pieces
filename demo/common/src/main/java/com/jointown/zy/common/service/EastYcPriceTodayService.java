package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.EastYcPriceTodayVo;
import com.jointown.zy.common.vo.WxReqBaseMessageVo;

/**
 * 微信公众平台开发--今日价格Service
 * 
 * @author aizhengdong
 *
 * @data 2015年3月1日
 */
public interface EastYcPriceTodayService {

	/**
	 * 查询药材的今日价格
	 * 
	 * @param reqMessage
	 *            接收的消息
	 * @param ycName
	 *            药材名称或别名
	 * @return
	 */
	String findPriceByYcName(WxReqBaseMessageVo reqMessage, String ycName);
	
	/**
	 * 查询药材的今日价格
	 * 
	 * @author guoyb
	 * @param reqMessage
	 *            接收的消息
	 * @param ycName
	 *            药材名称或别名
	 * @return
	 */
	List<EastYcPriceTodayVo> selectPriceBy(Map<String, Object> map);
}
