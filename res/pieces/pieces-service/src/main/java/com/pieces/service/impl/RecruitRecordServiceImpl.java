package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.RecruitRecordDao;
import com.pieces.dao.model.RecruitRecord;
import com.pieces.dao.vo.RecruitRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.RecruitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecruitRecordServiceImpl  extends AbsCommonService<RecruitRecord> implements RecruitRecordService{

	@Autowired
	private RecruitRecordDao recruitRecordDao;


	@Override
	public PageInfo<RecruitRecordVo> findByParams(RecruitRecordVo recruitRecordVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<RecruitRecordVo>  list = recruitRecordDao.findByParams(recruitRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<RecruitRecord> getDao() {
		return recruitRecordDao;
	}

}
