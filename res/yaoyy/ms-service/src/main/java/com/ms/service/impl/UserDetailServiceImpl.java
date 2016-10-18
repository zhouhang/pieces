package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.UserDetailDao;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.UserDetailVo;
import com.ms.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserDetailServiceImpl  extends AbsCommonService<UserDetail> implements UserDetailService{

	@Autowired
	private UserDetailDao userDetailDao;


	@Override
	public PageInfo<UserDetailVo> findByParams(UserDetailVo userDetailVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserDetailVo>  list = userDetailDao.findByParams(userDetailVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<UserDetail> getDao() {
		return userDetailDao;
	}

}