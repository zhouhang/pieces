package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CertifyRecordDao;
import com.pieces.dao.UserCertificationDao;
import com.pieces.dao.UserQualificationDao;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CertifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import com.pieces.tools.utils.FileUtil;

@Service
public class CertifyRecordServiceImpl  extends AbsCommonService<CertifyRecord> implements CertifyRecordService{

	@Autowired
	private CertifyRecordDao certifyRecordDao;

	@Autowired
	private UserCertificationDao userCertificationDao;

	@Autowired
	private UserQualificationDao userQualificationDao;


	@Override
	public PageInfo<CertifyRecordVo> findByParams(CertifyRecordVo certifyRecordVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
    	List<CertifyRecordVo>  list = certifyRecordDao.findByParams(certifyRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public void saveRecord(CertifyRecord certifyRecord, UserCertificationVo certificationVo, List<UserQualificationVo> userQualificationVos) {
		certifyRecordDao.create(certifyRecord);
		Date now=new Date();
		certificationVo.setRecordId(certifyRecord.getId());
		certificationVo.setCreateTime(now);
		certificationVo.setUpdateTime(now);
		userCertificationDao.create(certificationVo);
		for(UserQualificationVo userQualificationVo:userQualificationVos){
			userQualificationVo.setRecordId(certifyRecord.getId());
			userQualificationVo.setPictureUrl(FileUtil.getAbsolutePath(userQualificationVo.getPictureUrl()));
			userQualificationVo.setCreateTime(now);
			userQualificationVo.setUpdateTime(now);
			userQualificationDao.create(userQualificationVo);
		}
	}


	@Override
	public ICommonDao<CertifyRecord> getDao() {
		return certifyRecordDao;
	}

}
