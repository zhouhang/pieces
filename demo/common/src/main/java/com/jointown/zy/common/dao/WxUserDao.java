package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.UcUser;


public interface WxUserDao {

	UcUser findByCondition(String userName);

}
