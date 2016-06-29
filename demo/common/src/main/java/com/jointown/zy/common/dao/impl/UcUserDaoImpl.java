package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.model.UcUser;

@Repository
public class UcUserDaoImpl extends BaseDaoImpl implements UcUserDao{

	@Override
	public List<UcUser> getUserByUsernamePassword(Map<String, Object> map) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<UcUser> list = sqlSession.selectList("com.jointown.zy.common.dao.UcUserDao.getUserByUsernamePassword", map);
		return list;
	}

	@Override
	public List<UcUser> getUserByUsername(Map<String, Object> map) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		List<UcUser> list = sqlSession.selectList("com.jointown.zy.common.dao.UcUserDao.getUserByUsername", map);
		return list;
	}
	
	@Override
	public int addUcUser(UcUser ucuser) {
		return getSqlSession().insert("com.jointown.zy.common.dao.UcUserDao.addUcUser",ucuser);
	}

	@Override
	public UcUser findUcUserByUserName(String userName,boolean...ignoreStatus) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map <Object,Object> queryMap = new HashMap<Object, Object>();
		queryMap.put("userName", userName);
		if(ignoreStatus.length>0&&ignoreStatus[0]){//忽略状态
			queryMap.put("ignoreStatus", "true");
		}
		List<UcUser> list = sqlSession.selectList("com.jointown.zy.common.dao.UcUserDao.findUcUserByUserName",queryMap);
		return CollectionUtils.isNotEmpty(list)?list.get(0):null;
	}
	
	/**
	 * 根据用户ID获取前台用户的基本信息，不用判断是否删除
	 */
	public UcUser getUcUserById(Integer id){
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectOne("com.jointown.zy.common.dao.UcUserDao.getUcUserById",id);
	}

	@Override
	public int updateUcUserPassword(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateUcUserPassword",ucuser);
	}

	@Override
	public int updateUcUserEmail(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateUcUserEmail",ucuser);
	}

	@Override
	public int updateUcUserInfo(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateUcUserInfo",ucuser);
	}
	
	public int updatePhoneAndEmail(UcUser ucuser){
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updatePhoneAndEmail", ucuser);
	}
	
	@Override
	public int updateUcUserLoginInfo(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateUcUserLoginInfo",ucuser);
	}

	@Override
	public int deleteUcUser(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.deleteUcUser",ucuser);
	}

	@Override
	public UcUser findUcUserById(Integer id) {
		UcUser ucuser = getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.findUcUserById",id);
		return ucuser;
	}

	@Override
	public List<UcUser> findMemberByCondition(Map ucUserParams) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.UcUserDao.findMemberByCondition", ucUserParams);
	}

	@Override
	public UcUser findMemberByUserId(String userId) {
		return (UcUser) getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.findUcUserById", userId);
	}

	@Override
	public int isLock(UcUser ucUser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.lockUcUser", ucUser);
	}

	@Override
	public int secretReset(UcUser ucUser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.secretReset", ucUser);
	}

	@Override
	public UcUser findMemberByCompanyName(String companyName) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.findMemberByCompanyName", companyName);
	}

	@Override
	public int updateUcUserMobile(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateUcUserMobile",ucuser);
	}

	@Override
	public int updateUcUserCompany(UcUser ucuser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateUcUserCompany",ucuser);		
	}
	
	@Override
	public List<UcUser> findMemberMobile(String mobile) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.UcUserDao.findMemberMobile", mobile);
	}

	@Override
	public UcUser findUcUserByMobile(String mobile) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.findUcUserByMobile",mobile);
	}

	@Override
	public List<UcUser> reMemberMobIsExist(String userId, String mobile) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("mobile", mobile);
		return getSqlSession().selectList("com.jointown.zy.common.dao.UcUserDao.findUcUserByMobAndId", params);
	}

	@Override
	public UcUser findMemberByAllUserName(String userName) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.findMemberByAllUserName", userName);
	}

	@Override
	public int updateMemberByUserName(UcUser ucUser) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserDao.updateMemberByUserName", ucUser);
	}

	@Override
	public List<UcUser> findMemberByMobileAndUsername(
			Map<Object, Object> queryMap) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.UcUserDao.findMemberByMobileAndUsername", queryMap);
	}

	
	@Override
	public UcUser getUcUserById(String userId) {
		UcUser ucuser = getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.getUcUserById",userId);
		return ucuser;
	}

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.UcUserDao#getNums(java.util.HashMap)
	 * 动态获取会员中心首页的数字（如销售已成交笔数，待付保证金笔数，待付货款笔数;采购已成交笔数，待付保证金笔数，待付货款笔数）
	 */
	@Override
	public List<Integer> getNums(HashMap map) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.UcUserDao.getNums", map);
	}
	
	
	@Override
	public void updateMemberSalesMan(UcUser ucuser) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("com.jointown.zy.common.dao.UcUserDao.updateMemberSalesMan",ucuser);
	}

	@Override
	public String getCertifyNameByUserId(Long userId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId+"");
		params.put("name", "");
		getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.getCertifyNameByUserId", params);
		return params.get("name");
	}

	@Override
	public List<UcUser> findUcUserByEmail(String email)throws Exception {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.UcUserDao.findUcUserByEmail", email);
	}

	@Override
	public UcUser getUcUserByListingId(String listingId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserDao.getUcUserByListingId", listingId);
	}

	/**
	 * 微信添加用户记录
	 * @param ucser
	 * @author lichenxiao
	 * @see UcUserService
	 * @param 
	 * @time 2015年7月15日
	 */
	@Override
	public int wxAddUcUser(UcUser ucuser) {
		return getSqlSession().insert("com.jointown.zy.common.dao.UcUserDao.wxAddUcUser",ucuser);
	}
}

