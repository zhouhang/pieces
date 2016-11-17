package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CertifyRecordDao;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CertifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CertifyRecordServiceImpl  extends AbsCommonService<CertifyRecord> implements CertifyRecordService{

	@Autowired
	private CertifyRecordDao certifyRecordDao;


	@Override
	public PageInfo<CertifyRecordVo> findByParams(CertifyRecordVo certifyRecordVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CertifyRecordVo>  list = certifyRecordDao.findByParams(certifyRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<CertifyRecord> getDao() {
		return certifyRecordDao;
	}

}
