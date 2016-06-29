package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.UcUserContactDao;
import com.jointown.zy.common.model.UcUserContact;

/**
 * @ClassName: UcUserContactDaoImpl
 * @Description: 会员联系人信息DaoImpl
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
@Repository
public class UcUserContactDaoImpl extends BaseDaoImpl implements UcUserContactDao {

	@Override
	public int addUcUserContact(UcUserContact ucUserContact) throws Exception {
		
		return 0;
	}
	
	/**
	 * @Description: 获取会员联系人信息
	 * @Author: ff
	 * @Date: 2015年10月20日
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<UcUserContact> getUcUserContactsByUserId(Long userId) throws Exception{
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.UcUserContactDao.selectUcUserContactsByUserId", userId);
	}
	
	/**
	 * @Description: 新增联系人信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @return
	 */
	public int addContacter(UcUserContact contacter) throws Exception {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.UcUserContactDao.addUcUserContact",contacter);
	}
	/**
	 * @Description: 逻辑删除联系人信息
	 * @Author: ff
	 * @Date: 2015-10-22
	 * @return
	 */
	public int delUcUserContactByUserId(UcUserContact contacter) throws Exception{
		return this.getSqlSession().update("com.jointown.zy.common.dao.UcUserContactDao.delUcUserContactByUserId", contacter);
	}
	
	@Override
	public int updateUcUserContact(UcUserContact ucUserContact)
			throws Exception {
		Map<String, Object> params = new Hashtable<String, Object>();
		params.put("userId", ucUserContact.getUserId());
		params.put("status", 0);//0:删除状态
		params.put("updateTime", new Date());
		return this.getSqlSession().update("com.jointown.zy.common.dao.UcUserContactDao.updateUcUserContact", params);
	}

}
