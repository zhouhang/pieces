package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.UcUserScopeDto;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.UcUserScope;

/**
 * @ClassName: UcUserDealInService
 * @Description: 会员经营service
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public interface UcUserDealInService {

	/**
	 * @Description: 经营信息操作
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @param ucUserScopeDto
	 * @return
	 * @throws Exception
	 */
	String addUserDealInfo(UcUserScopeDto ucUserScopeDto)throws Exception;
	
	/**
	 * @Description: 模糊查询品种信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-19
	 * @return breedId  breedName
	 */
	public List<Breed> getBreeds(String param);

	String addAnyUserDealInfo(UcUserScopeDto ucUserScopeDto) throws Exception;
	
	/**
	 * @Description: 根据用户ID获取经营信息
	 * @Author: ldp
	 * @Date: 2015年10月22日
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	UcUserScope getUcUserScope(Long userId) throws Exception;
}
