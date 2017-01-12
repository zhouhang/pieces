package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.RecruitAgentDao;
import com.pieces.dao.model.RecruitAgent;
import com.pieces.dao.vo.RecruitAgentVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RecruitAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecruitAgentServiceImpl  extends AbsCommonService<RecruitAgent> implements RecruitAgentService{

	@Autowired
	private RecruitAgentDao recruitAgentDao;


	@Override
	public PageInfo<RecruitAgentVo> findByParams(RecruitAgentVo recruitAgentVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<RecruitAgentVo>  list = recruitAgentDao.findByParams(recruitAgentVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<RecruitAgent> getDao() {
		return recruitAgentDao;
	}

}
