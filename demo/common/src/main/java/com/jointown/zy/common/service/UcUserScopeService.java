package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.UcUserBreed;
import com.jointown.zy.common.model.UcUserScope;

public interface UcUserScopeService {
	/**
	 * @Description: 获取经营信息
	 * @Author: ff
	 * @Date: 2015年10月19日
	 * @return
	 * @throws Exception
	 */
	public UcUserScope selectUcUserScopeById(Long userId) throws Exception;
	
	/**
	 * @Description: 获取品种
	 * @Author: ff
	 * @Date: 2015年10月20日
	 * @return
	 * @throws Exception
	 */
	public List<UcUserBreed> getBreedsById(Long userId) throws Exception;
}
