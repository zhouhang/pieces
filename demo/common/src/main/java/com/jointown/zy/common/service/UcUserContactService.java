package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.UcUserContactDto;
import com.jointown.zy.common.model.UcUserContact;

public interface UcUserContactService {
	/**
	 * @Description: 根据userid获取联系人信息
	 * @Author: ff
	 * @Date: 2015年10月20日
	 * @return
	 * @throws Exception
	 */
	public List<UcUserContact> selectUcUserContactsByUserId(Long userId) throws Exception;
	
	/**
	 * @Description: 新增联系人信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String addContacter(UcUserContactDto dto) throws Exception;

	public String addContacterByUserId(UcUserContactDto dto) throws Exception;
	
	/**
	 * @Description: 逻辑删除会员联系人信息
	 * @Author: ldp
	 * @Date: 2015年10月22日
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String delContacter(UcUserContactDto dto) throws Exception;
}
