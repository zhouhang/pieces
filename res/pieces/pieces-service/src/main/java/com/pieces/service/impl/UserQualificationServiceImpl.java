package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserQualificationDao;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserQualificationServiceImpl  extends AbsCommonService<UserQualification> implements UserQualificationService{

	@Autowired
	private UserQualificationDao userQualificationDao;


	@Override
	public PageInfo<UserQualificationVo> findByParams(UserQualificationVo userQualificationVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserQualificationVo>  list = userQualificationDao.findByParams(userQualificationVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<UserQualification> getDao() {
		return userQualificationDao;
	}

}
