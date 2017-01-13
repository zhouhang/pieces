package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.RecruitAgentDao;
import com.pieces.dao.RecruitRecordDao;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.model.RecruitRecord;
import com.pieces.dao.vo.RecruitRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RecruitAgentService;
import com.pieces.service.RecruitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RecruitRecordServiceImpl  extends AbsCommonService<RecruitRecord> implements RecruitRecordService{

	@Autowired
	private RecruitRecordDao recruitRecordDao;

	@Autowired
	private RecruitAgentDao recruitAgentDao;


	@Override
	public PageInfo<RecruitRecordVo> findByParams(RecruitRecordVo recruitRecordVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<RecruitRecordVo>  list = recruitRecordDao.findByParams(recruitRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<RecruitRecordVo> findByRecruitId(Integer recruitAgentId) {
		return recruitRecordDao.findByRecruitId(recruitAgentId);
	}

	@Override
	@Transactional
	public void save(RecruitRecordVo recruitRecordVo) {
          recruitRecordVo.setCreateTime(new Date());
		  recruitRecordDao.create(recruitRecordVo);
		  /**
		  * 修改最后跟进人信息
		  */
		 RecruitAgent recruitAgent=recruitAgentDao.findById(recruitRecordVo.getRecruitAgentId());
		  recruitAgent.setStatus(1);
		  recruitAgent.setLastFollowId(recruitRecordVo.getFollowId());
		  recruitAgent.setLastFollowTime(new Date());
		  recruitAgentDao.update(recruitAgent);
	}


	@Override
	public ICommonDao<RecruitRecord> getDao() {
		return recruitRecordDao;
	}

}
