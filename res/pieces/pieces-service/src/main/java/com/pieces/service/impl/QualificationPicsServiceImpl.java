package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.QualificationPicsDao;
import com.pieces.dao.model.QualificationPics;
import com.pieces.dao.vo.QualificationPicsVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.QualificationPicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QualificationPicsServiceImpl  extends AbsCommonService<QualificationPics> implements QualificationPicsService{

	@Autowired
	private QualificationPicsDao qualificationPicsDao;


	@Override
	public PageInfo<QualificationPicsVo> findByParams(QualificationPicsVo qualificationPicsVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<QualificationPicsVo>  list = qualificationPicsDao.findByParams(qualificationPicsVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public void deleteByQid(Integer qid) {
		qualificationPicsDao.deleteByQid(qid);
	}


	@Override
	public ICommonDao<QualificationPics> getDao() {
		return qualificationPicsDao;
	}

}
