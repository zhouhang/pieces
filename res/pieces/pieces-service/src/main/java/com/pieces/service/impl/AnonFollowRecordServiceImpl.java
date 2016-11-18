package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AnonFollowRecordDao;
import com.pieces.dao.model.AnonFollowRecord;
import com.pieces.dao.vo.AnonFollowRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AnonFollowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnonFollowRecordServiceImpl  extends AbsCommonService<AnonFollowRecord> implements AnonFollowRecordService{

	@Autowired
	private AnonFollowRecordDao anonFollowRecordDao;


	@Override
	public PageInfo<AnonFollowRecordVo> findByParams(AnonFollowRecordVo anonFollowRecordVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AnonFollowRecordVo>  list = anonFollowRecordDao.findByParams(anonFollowRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<AnonFollowRecordVo> findByAnonId(Integer id) {
		AnonFollowRecordVo vo = new AnonFollowRecordVo();
		vo.setAnonEnquiryId(id);
		List<AnonFollowRecordVo>  list = anonFollowRecordDao.findByParams(vo);
		return list;
	}

	@Override
	public ICommonDao<AnonFollowRecord> getDao() {
		return anonFollowRecordDao;
	}

}
