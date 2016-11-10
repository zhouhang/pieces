package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.UserDao;
import com.ms.dao.UserDetailDao;
import com.ms.dao.model.User;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.UserDetailVo;
import com.ms.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserDetailServiceImpl  extends AbsCommonService<UserDetail> implements UserDetailService{

	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private UserDao userDao;


	@Override
	public PageInfo<UserDetailVo> findByParams(UserDetailVo userDetailVo,Integer pageNum,Integer pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<UserDetailVo>  list = userDetailDao.findByParams(userDetailVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public UserDetailVo findByUserId(Integer userId) {
		UserDetailVo userDetailVo=userDetailDao.findByUserId(userId);
		User user = null;
		if (userDetailVo != null) {
			user = userDao.findById(userDetailVo.getUserId());
			userDetailVo.setUserType(user.getType());
		}
		return userDetailVo;
	}

	@Override
	@Transactional
	public void save(UserDetail userDetail) {
		Date now=new Date();
		if(userDetail.getId()==null){
			userDetail.setCreateTime(now);
			userDetail.setUpdateTime(now);
			userDetailDao.create(userDetail);
		}
		else{
			userDetail.setUpdateTime(now);
			userDetailDao.update(userDetail);
		}
	}



	@Override
	public ICommonDao<UserDetail> getDao() {
		return userDetailDao;
	}

}
