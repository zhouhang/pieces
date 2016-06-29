package com.jointown.zy.common.service;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.MessageSend;


public interface WxUserService {

	UcUser findByCondition(String userName);
	
	/**
 	  * @Description: 根据挂牌ID查询UcUser信息
    * @Author: 宋威
    * @Date: 2015年7月17日
    * @param String listingId 挂牌id
    * @return UcUser
 	 */
 	public UcUser getUcUserByListingId(String listingId);
 	
 	
 	/**
 	 * @Description: 短信验证码
 	 * @Author: guoyb
 	 * @Date: 2015年7月24日
 	 * @param mobileNo 
 	 * @param mobileCode 
 	 * @return
 	 */
 	public String getMobileCode(String mobileNo,String mobileCode);

}
