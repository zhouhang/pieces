package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.vo.MemberHomeVo;

public interface IMemberHomeService {

	/**
	 * 获取会员首页信息
	 * @param userName
	 * @return
	 * @useBy：微信项目：guoyb：WxUserMaterialController
	 */
	public MemberHomeVo getMemberHomeInfo(String userName);
	
	//动态获取会员中心首页的数字（如销售已成交笔数，待付保证金笔数，待付货款笔数;采购已成交笔数，待付保证金笔数，待付货款笔数）
	public List<Integer> getNums(Long userId);
}
