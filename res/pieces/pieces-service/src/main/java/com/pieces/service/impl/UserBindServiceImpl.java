package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserBindDao;
import com.pieces.dao.model.UserBind;
import com.pieces.dao.vo.UserBindVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserBindServiceImpl  extends AbsCommonService<UserBind> implements UserBindService{

	@Autowired
	private UserBindDao userBindDao;




	@Override
	public PageInfo<UserBindVo> findByParams(UserBindVo userBindVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserBindVo>  list = userBindDao.findByParams(userBindVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public Integer deleteByTerminalId(Integer terminalId) {
		return null;
	}

	@Override
	@Transactional
	public void saveBind(UserBind userBind) {
		userBindDao.deleteByTerminalId(userBind.getTerminalId());
		userBindDao.create(userBind);
	}


	@Override
	public ICommonDao<UserBind> getDao() {
		return userBindDao;
	}

}
