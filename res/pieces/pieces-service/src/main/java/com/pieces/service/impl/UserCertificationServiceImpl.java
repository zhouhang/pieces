package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserCertificationDao;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserCertificationService;
import com.pieces.tools.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserCertificationServiceImpl  extends AbsCommonService<UserCertification> implements UserCertificationService{

	@Autowired
	private UserCertificationDao userCertificationDao;


	@Override
	public PageInfo<UserCertificationVo> findByParams(UserCertificationVo userCertificationVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserCertificationVo>  list = userCertificationDao.findByParams(userCertificationVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public UserCertificationVo findAll(UserCertificationVo userCertificationVo) {
		List<UserCertificationVo>  list=userCertificationDao.findByParams(userCertificationVo);
	    if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public ICommonDao<UserCertification> getDao() {
		return userCertificationDao;
	}

}
