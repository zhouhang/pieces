package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.UserDao;
import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;
import com.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl  extends AbsCommonService<User> implements UserService{

	@Autowired
	private UserDao userDao;


	@Override
	public PageInfo<UserVo> findByParams(UserVo userVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserVo>  list = userDao.findByParams(userVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public UserVo findByPhone(UserVo userVo) {
		List<UserVo>  list = userDao.findByParams(userVo);
		if (list.size()!=0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public ICommonDao<User> getDao() {
		return userDao;
	}

}
