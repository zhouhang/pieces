package com.jointown.zy.common.service;

import java.text.ParseException;

import com.jointown.zy.common.dto.CompanyCertifyDto;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.vo.BossUserVo;

public interface WxCompanyCertifyService {
	/**
	 * 提交企业认证
	 * @author zhouji
	 * @param companyCertify
	 * @throws ParseException 
	 */
	public int addCompanyCertify(CompanyCertifyDto companyCertifyDto,String imgDir) throws Exception;
}
