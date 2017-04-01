package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.UserFollowRecordDao;
import com.pieces.dao.model.UserFollowRecord;
import com.pieces.dao.vo.UserFollowRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserFollowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserFollowRecordServiceImpl  extends AbsCommonService<UserFollowRecord> implements UserFollowRecordService{

	@Autowired
	private UserFollowRecordDao userFollowRecordDao;


	@Override
	public PageInfo<UserFollowRecordVo> findByParams(UserFollowRecordVo userFollowRecordVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserFollowRecordVo>  list = userFollowRecordDao.findByParams(userFollowRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<UserFollowRecordVo> findByUserId(Integer userId) {
		UserFollowRecordVo userFollowRecordVo=new UserFollowRecordVo();
		userFollowRecordVo.setUserId(userId);
		List<UserFollowRecordVo>  list = userFollowRecordDao.findByParams(userFollowRecordVo);
		return list;
	}


	@Override
	public ICommonDao<UserFollowRecord> getDao() {
		return userFollowRecordDao;
	}

}
