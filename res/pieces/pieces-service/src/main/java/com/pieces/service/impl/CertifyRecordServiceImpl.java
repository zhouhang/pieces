package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CertifyRecordDao;
import com.pieces.dao.UserCertificationDao;
import com.pieces.dao.UserQualificationDao;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.CertifyRecord;
import com.pieces.dao.model.User;
import com.pieces.dao.model.UserCertification;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.CertifyRecordService;
import com.pieces.service.UserService;
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

	@Autowired
	private UserService userService;


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
	@Transactional
	public void passCertify(CertifyRecordVo certifyRecordVo) {
		certifyRecordDao.update(certifyRecordVo);
		User user=userService.findById(certifyRecordVo.getUserId());
		user.setCertifyStatus(CertifyStatusEnum.CERTIFY_SUCESS.getValue());
		userService.update(user);
		UserQualificationVo userQualification=new UserQualificationVo();
		userQualification.setRecordId(certifyRecordVo.getId());
		userQualification.setUserId(certifyRecordVo.getUserId());
		userQualification.setUpdateTime(new Date());
		UserCertificationVo userCertification=new UserCertificationVo();
		userCertification.setRecordId(certifyRecordVo.getId());
		userCertification.setUserId(certifyRecordVo.getUserId());
		userCertification.setUpdateTime(new Date());
		userCertificationDao.updateByRecordId(userCertification);
		userQualificationDao.updateByRecordId(userQualification);

	}


	@Override
	public ICommonDao<CertifyRecord> getDao() {
		return certifyRecordDao;
	}

}
