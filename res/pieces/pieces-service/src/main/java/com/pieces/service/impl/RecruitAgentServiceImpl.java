package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.AreaDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.RecruitAgentDao;
import com.pieces.dao.model.Area;
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

	@Autowired
	private AreaDao areaDao;


	@Override
	public PageInfo<RecruitAgentVo> findByParams(RecruitAgentVo recruitAgentVo,Integer pageNum,Integer pageSize) {
		pageNum=pageNum==null?1:pageNum;
		pageSize=pageSize==null?10:pageSize;
        PageHelper.startPage(pageNum, pageSize);
    	List<RecruitAgentVo>  list = recruitAgentDao.findByParams(recruitAgentVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public RecruitAgentVo findVoById(Integer id) {
		RecruitAgentVo recruitAgentVo= recruitAgentDao.findVoById(id);
		Area area =areaDao.findById(recruitAgentVo.getAreaId());
		Area parent =areaDao.findById(area.getParentid());
		recruitAgentVo.setArea(parent.getAreaname()+area.getAreaname());
		return recruitAgentVo;
	}

	@Override
	public Integer getNotHandleCount() {
		return recruitAgentDao.getNotHandleCount();
	}

	@Override
	public List<Integer> getNotHandleIds() {
		return recruitAgentDao.getNotHandleIds();
	}


	@Override
	public ICommonDao<RecruitAgent> getDao() {
		return recruitAgentDao;
	}

}
