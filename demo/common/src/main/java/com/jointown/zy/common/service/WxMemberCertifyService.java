/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service;

import com.jointown.zy.common.dto.PersonCertifyDto;

/**
 * @ClassName: WxMemberCertifyService
 * @Description: 微信认证相关
 * @Author: guoyb
 * @Date: 2015年8月3日
 * @Version: 1.0
 */
public interface WxMemberCertifyService {
	
	/**
	 * @Description: 提交个人会员认证审核资料
	 * @Author: guoyb
	 * @Date: 2015年8月3日
	 * @param pcCerifyDto 
	 * @param imgDir 
	 * @return
	 */
	public int addPersonCertifyInfo(PersonCertifyDto pcCerifyDto,String imgDir);

}
