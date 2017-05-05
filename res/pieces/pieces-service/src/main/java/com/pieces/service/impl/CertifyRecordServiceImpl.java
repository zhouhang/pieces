package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.CertifyRecordDao;
import com.pieces.dao.UserCertificationDao;
import com.pieces.dao.UserQualificationDao;
import com.pieces.dao.enums.CertifyRecordStatusEnum;
import com.pieces.dao.enums.CertifyStatusEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.CertifyDataVo;
import com.pieces.dao.vo.CertifyRecordVo;
import com.pieces.dao.vo.UserCertificationVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.*;
import com.pieces.service.enums.NotifyTemplateEnum;
import com.pieces.service.enums.PathEnum;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.listener.NotifyEvent;
import com.pieces.service.listener.UserUpdateEvent;
import com.pieces.tools.utils.SpringUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
	private UserCertificationService userCertificationService;

	@Autowired
	private UserQualificationDao userQualificationDao;

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private QualificationPicsService qualificationPicsService;

	@Autowired
	private WxMpService wxService;


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
			userQualificationVo.setCreateTime(now);
			userQualificationVo.setUpdateTime(now);
			userQualificationDao.create(userQualificationVo);
			for(QualificationPics qualificationPics:userQualificationVo.getPictures()){
				qualificationPics.setQid(userQualificationVo.getId());
				if (!Strings.isNullOrEmpty(qualificationPics.getPictureUrl()) && qualificationPics.getPictureUrl().contains("/")) {
					qualificationPics.setPictureUrl(FileUtil.getAbsolutePath(qualificationPics.getPictureUrl()));
				} else {
					try {
						qualificationPics.setPictureUrl(FileUtil.saveFileFromWechat(wxService.getMaterialService().mediaDownload(qualificationPics.getPictureUrl()),qualificationPics.getPictureUrl(), PathEnum.CERTIFY.getValue()));
					} catch (WxErrorException e) {
						e.printStackTrace();
					}
				}

				qualificationPicsService.create(qualificationPics);
			}
		}
	}



	@Override
	@Transactional
	public void passCertify(CertifyRecordVo certifyRecordVo) {
		certifyRecordDao.update(certifyRecordVo);
		User user=userService.findById(certifyRecordVo.getUserId());
		user.setCertifyStatus(CertifyStatusEnum.CERTIFY_SUCESS.getValue());
		UserCertificationVo userCertificationVo=new UserCertificationVo();
		userCertificationVo.setRecordId(certifyRecordVo.getId());
		UserCertificationVo userCertificationVo1=userCertificationService.findAll(userCertificationVo);
		if(userCertificationVo1!=null){
			user.setCompanyFullName(userCertificationVo1.getCompany());
		}
		user.setCertifyTime(new Date());
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

		/**
		 * 更新user更新时间
		 */
		UserUpdateEvent userUpdateEvent=new UserUpdateEvent(certifyRecordVo.getUserId());

		applicationContext.publishEvent(userUpdateEvent);

	}

	@Override
	public CertifyRecordVo getLatest(Integer userId) {
		return certifyRecordDao.getLatest(userId);
	}

	@Override
	@Transactional
	public void saveCertify(UserCertificationVo certificationVo, List<UserQualificationVo> userQualificationVos) {
		Date now=new Date();
		certificationVo.setUpdateTime(now);
		if(certificationVo.getId()==null){
			certificationVo.setCreateTime(now);
			userCertificationDao.create(certificationVo);
		}
		else{
			userCertificationDao.update(certificationVo);
		}

		for(UserQualificationVo userQualificationVo:userQualificationVos){
			//userQualificationVo.setPictureUrl(FileUtil.getAbsolutePath(userQualificationVo.getPictureUrl()));
			userQualificationVo.setCreateTime(now);
			userQualificationVo.setUpdateTime(now);
			if(userQualificationVo.getId()==null){
				certificationVo.setCreateTime(now);
				userQualificationDao.create(userQualificationVo);
				for(QualificationPics qualificationPics:userQualificationVo.getPictures()){
					qualificationPics.setQid(userQualificationVo.getId());
					qualificationPics.setPictureUrl(FileUtil.getAbsolutePath(qualificationPics.getPictureUrl()));
					qualificationPicsService.create(qualificationPics);
				}
			}
			else{
				userQualificationDao.update(userQualificationVo);
				/**
				 * 删除以前的
				 */
				qualificationPicsService.deleteByQid(userQualificationVo.getId());
				for(QualificationPics qualificationPics:userQualificationVo.getPictures()){
					qualificationPics.setQid(userQualificationVo.getId());
					qualificationPics.setPictureUrl(FileUtil.getAbsolutePath(qualificationPics.getPictureUrl()));
					qualificationPicsService.create(qualificationPics);
				}
			}
		}
		User user=userService.findById(certificationVo.getUserId());
		user.setCertifyStatus(CertifyStatusEnum.CERTIFY_SUCESS.getValue());
		user.setCompanyFullName(certificationVo.getCompany());
		user.setCertifyTime(new Date());
		userService.update(user);
		/**
		 * 更新user更新时间
		 */
		UserUpdateEvent userUpdateEvent=new UserUpdateEvent(certificationVo.getUserId());

		applicationContext.publishEvent(userUpdateEvent);
	}

	@Override
	public Integer getNotHandleCount() {
		return certifyRecordDao.getNotHandleCount();
	}

	@Override
	public List<Integer> getNotHandleIds() {
		return certifyRecordDao.getNotHandleIds();
	}


	@Override
	public ICommonDao<CertifyRecord> getDao() {
		return certifyRecordDao;
	}

	@Override
	public void saveCertify(CertifyDataVo certifyDataVo, User user) {
		UserCertificationVo certificationVo=new UserCertificationVo();
		certificationVo.setType(certifyDataVo.getType());
		CertifyRecord certifyRecord=new CertifyRecord();
		certifyRecord.setUserId(user.getId());
		certifyRecord.setUserName(user.getUserName());
		certifyRecord.setCreateTime(new Date());
		certifyRecord.setStatus(CertifyRecordStatusEnum.NOT_HANDLE.getValue());
		saveRecord(certifyRecord,certificationVo,certifyDataVo.getUserQualificationVos());
		// 通知管理员有新的资质审核请求提交
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SpringUtil.getApplicationContext().
				publishEvent(new NotifyEvent(NotifyTemplateEnum.certify.getTitle(String.valueOf(certificationVo.getId())),
						NotifyTemplateEnum.certify.getContent("待认证用户",time.format(new Date())),NotifyTemplateEnum.certify.getType(),certificationVo.getRecordId()));

	}
}
