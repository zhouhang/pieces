package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.UcUserContact;

/**
 * @ClassName: UcUserContactDao
 * @Description: 会员联系人信息dao
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public interface UcUserContactDao {
	
	/**
	 * @Description: 添加会员联系人信息
	 * @Author: ldp
	 * @Date: 2015年10月18日
	 * @param ucUserContact
	 * @return
	 * @throws Exception
	 */
	int addUcUserContact(UcUserContact ucUserContact) throws Exception;
	
	/**
	 * @Description: 获取会员联系人信息
	 * @Author: ff
	 * @Date: 2015年10月20日
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<UcUserContact> getUcUserContactsByUserId(Long userId) throws Exception;
	
	/**
	 * @Description: 新增联系人信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @return
	 */
	public int addContacter(UcUserContact contacter) throws Exception;
	
	/**
	 * @Description: 逻辑删除联系人信息
	 * @Author: ff
	 * @Date: 2015-10-22
	 * @return
	 */
	public int delUcUserContactByUserId(UcUserContact contacter) throws Exception;
	
	/**
	 * @Description: 修改联系人状态
	 * @Author: ldp
	 * @Date: 2015年10月22日
	 * @param ucUserContact
	 * @return
	 * @throws Exception
	 */
	int updateUcUserContact(UcUserContact ucUserContact) throws Exception;

}
