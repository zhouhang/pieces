package com.jointown.zy.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jointown.zy.common.dto.WxOpinionDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxOpinion;
import com.jointown.zy.common.vo.WxOpinionVo;

/**
 * 意见反馈
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
public interface WxOpinionService {
	
	/**
	 * 添加记录
	 *
	 * @param wxOpinion
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	int addWxOpinion(WxOpinionDto wxOpinionDto);
	
	/**
	 * 更新记录
	 *
	 * @author aizhengdong
	 * @data 2015年8月26日
	 */
	int updateWxOpinion(WxOpinion wxOpinion);
	
	/**
	 * 删除记录
	 *
	 * @author aizhengdong
	 * @date 2015年8月27日
	 */
	int deleteWxOpinion(Long opId);
	
	/**
	 * 根据ID获取记录
	 *
	 * @param opId
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	WxOpinion findWxOpinionById(Long opId);

	/**
	 * 获取满足条件的记录
	 *
	 * @param page
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	List<WxOpinionVo> findWxOpinionByCondition(Page<WxOpinionVo> page);
	
	/**
	 * 获取手机验证码
	 *
	 * @param request
	 * @param mobile
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月16日
	 */
	String getMobileCode(HttpServletRequest request, String mobile);
}
