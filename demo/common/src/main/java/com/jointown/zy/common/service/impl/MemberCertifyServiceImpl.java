package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CertifyImgDao;
import com.jointown.zy.common.dao.CertifyLogDao;
import com.jointown.zy.common.dao.CompanyCertifyDao;
import com.jointown.zy.common.dao.UcUserCertifyDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.PersonCertifyDto;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.model.UcUserCertify;
import com.jointown.zy.common.model.UcUserCertifyLog;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.IMemberCertifyService;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 */
@Service
public class MemberCertifyServiceImpl implements IMemberCertifyService {

	private static final Logger log = LoggerFactory.getLogger(MemberCertifyServiceImpl.class);
	
	@Autowired
	private UcUserCertifyDao ucUserCertifyDao;
	@Autowired
	private CompanyCertifyDao companyCertifyDao;
	@Autowired 
	private CertifyImgDao certifyImgDao;
	@Autowired
	private CertifyLogDao certifyLogDao;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private UcUserDao ucUserDao;
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Override
	public List<UcUserCertify> getCertifyUcUsersByCondition(Page<UcUserCertify> page) {
		List<UcUserCertify> ucUserCertifies = ucUserCertifyDao.searchCertifyUcUsersByCondition(page);
		return ucUserCertifies;
	}

	@Override
	public UcPersonCertify getCertifyUcUserInfoByCertifyId(String certifyId) {
		return ucUserCertifyDao.getCertifyUcUserInfoByCertifyId(certifyId);
	}

	@Override
	public UcPersonCertify getCertifyUcUserInfoByUserId(String userId) {
		return ucUserCertifyDao.getCertifyUcUserInfoByUserId(userId);
	}
	
	public UcPersonCertify getCertifyUcUserInfoByUserId1(String userId){
		return ucUserCertifyDao.getCertifyUcUserInfoByUserId1(userId);
	}

	@Override
	public int updatePersonCertify(PersonCertifyDto pcdto,BossUserVo bossUserVo,String imgDir) {
		UcPersonCertify ucPersonCertify = new UcPersonCertify();
		ucPersonCertify.setCertifyId(Integer.parseInt(pcdto.getCertifyId()));
		ucPersonCertify.setUserId(Integer.parseInt(pcdto.getUserId()));
		ucPersonCertify.setName(pcdto.getName());
		ucPersonCertify.setIdCard(pcdto.getIdCard());
		ucPersonCertify.setStatus(Integer.parseInt(pcdto.getIsPass()));
		ucPersonCertify.setUpdateTime(new Date());
		ucPersonCertify.setApproveTime(new Date());
		ucPersonCertify.setApproverId(bossUserVo.getId());
		ucPersonCertify.setRejectMemo(Integer.parseInt(pcdto.getCheckRemark()));
		int passFlag = ucUserCertifyDao.updatePersonCertify(ucPersonCertify);
		if (passFlag ==1 ) {
			UcUser ucUser = ucUserDao.findMemberByUserId(pcdto.getUserId());
			int certifyStatus = ucPersonCertify.getStatus();
			log.info("Person certify is：" + ((certifyStatus==1)?"pass":"unpass"));
			if (certifyStatus==1) {
				ucUserCertifyDao.updateUcUserCertifyStatusByUserId("1", pcdto.getUserId());//通过审核时，修改会员表认证状态
				log.info("person certify pass send Msg !");
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getPersonCertifyPassMsg()));
			}else if (certifyStatus==2) {
				log.info("person certify unpass send Msg !");
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getPersonCertifyUnPassMsg()));
			}
		}
		
		//如果需上传图片,插入图片信息
		if (pcdto.getPicPath() != null && !"".equals(pcdto.getPicPath())) {
			List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(pcdto.getCertifyId()),0,1,2);
			if (null != imgList && imgList.size() != 0) {
				certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(pcdto.getCertifyId()),0,1,2);//把已上传的图片软删除
			}
			insertImg(Integer.parseInt(pcdto.getCertifyId()), imgDir + pcdto.getPicName(), pcdto.getPicType(), new Date());
			insertImg(Integer.parseInt(pcdto.getCertifyId()), imgDir + pcdto.getPicNameSmall(), pcdto.getPicSmallType(), new Date());
			insertImg(Integer.parseInt(pcdto.getCertifyId()), imgDir + pcdto.getPicNameBig(), pcdto.getPicBigType(), new Date());
		}		
		if(pcdto.getPicPath1() != null && !"".equals(pcdto.getPicPath1())){
			List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(pcdto.getCertifyId()),3,4,5);
			if (null != imgList && imgList.size() != 0) {
				certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(pcdto.getCertifyId()),3,4,5);//把已上传的图片软删除
			}
			insertImg(Integer.parseInt(pcdto.getCertifyId()), imgDir + pcdto.getPicName1(), pcdto.getPicType1(), new Date());
			insertImg(Integer.parseInt(pcdto.getCertifyId()), imgDir + pcdto.getPicNameSmall1(), pcdto.getPicSmallType1(), new Date());
			insertImg(Integer.parseInt(pcdto.getCertifyId()), imgDir + pcdto.getPicNameBig1(), pcdto.getPicBigType1(), new Date());
		}	
		//日志记录
		UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
		ucUserCertifyLog.setCertifyId(Integer.parseInt(pcdto.getCertifyId()));
		ucUserCertifyLog.setCreateDate(new Date());
		ucUserCertifyLog.setType(0);//个人认证
		ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(bossUserVo.getId())));
		ucUserCertifyLog.setOpraterName(bossUserVo.getUserCode());
		ucUserCertifyLog.setMemo("个人实名认证审核:"
				+ (pcdto.getIsPass().equals("1") ? ("通过审核") : ("不通过审核"))
				+ ";操作结果：" + (passFlag == 1 ? ("操作成功") : ("操作失败")));
		certifyLogDao.addCertifyLog(ucUserCertifyLog);
		
		return passFlag;
	}


	@Override
	public int updatePersonCertifyInfoPassAfter(PersonCertifyDto pcDto,BossUserVo bossUserVo,String imgDir) {
		UcPersonCertify ucPersonCertify = new UcPersonCertify();
		ucPersonCertify.setUserId(Integer.parseInt(pcDto.getUserId()));
		ucPersonCertify.setCertifyId(Integer.parseInt(pcDto.getCertifyId()));
		ucPersonCertify.setName(pcDto.getName());
		ucPersonCertify.setIdCard(pcDto.getIdCard());
		ucPersonCertify.setUpdateTime(new Date());
		int updateFlag = ucUserCertifyDao.updatePersonCertifyInfoPassAfter(ucPersonCertify);
		if (updateFlag ==1 ) {
			log.info("person cerify info is save success !");
		}
		
		//如果需上传图片,插入图片信息
		if (null != pcDto.getPicPath() && !"".equals(pcDto.getPicPath()) ) {
			List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(pcDto.getCertifyId()),0,1,2);
			if (null != imgList && imgList.size() != 0) {
				certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(pcDto.getCertifyId()),0,1,2);//把已上传的图片软删除
			}
			insertImg(Integer.parseInt(pcDto.getCertifyId()), imgDir + pcDto.getPicName(), pcDto.getPicType(), new Date());
			insertImg(Integer.parseInt(pcDto.getCertifyId()), imgDir + pcDto.getPicNameSmall(), pcDto.getPicSmallType(), new Date());
			insertImg(Integer.parseInt(pcDto.getCertifyId()), imgDir + pcDto.getPicNameBig(), pcDto.getPicBigType(), new Date());
		}		
		if(pcDto.getPicPath1() != null && !"".equals(pcDto.getPicPath1())){
			List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(pcDto.getCertifyId()),3,4,5);
			if (null != imgList && imgList.size() != 0) {
				certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(pcDto.getCertifyId()),3,4,5);//把已上传的图片软删除
			}
			insertImg(Integer.parseInt(pcDto.getCertifyId()), imgDir + pcDto.getPicName1(), pcDto.getPicType1(), new Date());
			insertImg(Integer.parseInt(pcDto.getCertifyId()), imgDir + pcDto.getPicNameSmall1(), pcDto.getPicSmallType1(), new Date());
			insertImg(Integer.parseInt(pcDto.getCertifyId()), imgDir + pcDto.getPicNameBig1(), pcDto.getPicBigType1(), new Date());
		}	
		//日志记录
		UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
		ucUserCertifyLog.setCertifyId(Integer.parseInt(pcDto.getCertifyId()));
		ucUserCertifyLog.setCreateDate(new Date());
		ucUserCertifyLog.setType(0);//个人认证
		ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(bossUserVo.getId())));
		ucUserCertifyLog.setOpraterName(bossUserVo.getUserCode());
		ucUserCertifyLog.setMemo("个人认证通过后保存资料");
		certifyLogDao.addCertifyLog(ucUserCertifyLog);		
		
		return updateFlag;
	}


	@Override
	public int addPersonCertifyInfo(PersonCertifyDto pcCerifyDto,String imgDir) {
		UcPersonCertify ucPersonCertify = new UcPersonCertify();
		
		UcUserVo ucUserVo = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		
		Integer certifyId = null;
		if ("0".equals(pcCerifyDto.getIsPass())) {//待审核时添加数据
			certifyId = companyCertifyDao.findSeqNext();
		}else if ("2".equals(pcCerifyDto.getIsPass())) {//已驳回时重新提交数据
			certifyId = Integer.parseInt(pcCerifyDto.getCertifyId());
		}
		ucPersonCertify.setCertifyId(certifyId);
		ucPersonCertify.setUserId(Integer.parseInt(String.valueOf(ucUserVo.getUserId())));
		ucPersonCertify.setName(pcCerifyDto.getName());
		ucPersonCertify.setIdCard(pcCerifyDto.getIdCard());
		ucPersonCertify.setStatus(Integer.parseInt(pcCerifyDto.getIsPass()));//0待审核 2已驳回
 		ucPersonCertify.setSubmitTime(new Date());
		ucPersonCertify.setCreateTime(new Date());
		ucPersonCertify.setUpdateTime(new Date());
		
		int resultFlag = 0;
		if ("0".equals(pcCerifyDto.getIsPass())) {//待审核,第一次提交
			UcPersonCertify p_certify = ucUserCertifyDao.getCertifyUcUserInfoByUserId(String.valueOf(ucUserVo.getUserId()));
			if(p_certify!=null){
				return 3;
			}
			int addFlag = ucUserCertifyDao.addPersonCertifyInfo(ucPersonCertify);
			if (addFlag == 1) {
				log.info("add person certify Info is success !");
			}
			//插入图片信息
			if (null != pcCerifyDto.getPicPath() && !"".equals(pcCerifyDto.getPicPath())) {
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName(), pcCerifyDto.getPicType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall(), pcCerifyDto.getPicSmallType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig(), pcCerifyDto.getPicBigType(), new Date());
			}
			if(null != pcCerifyDto.getPicPath1() && !"".equals(pcCerifyDto.getPicPath1())){
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName1(), pcCerifyDto.getPicType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall1(), pcCerifyDto.getPicSmallType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig1(), pcCerifyDto.getPicBigType1(), new Date());
			}	
			
			resultFlag = addFlag;
		}else if ("2".equals(pcCerifyDto.getIsPass())) {//已驳回重新提交
			UcPersonCertify p_certify = ucUserCertifyDao.getCertifyUcUserInfoByUserId(String.valueOf(ucUserVo.getUserId()));
			if(p_certify!=null){
				return 3;
			}
			int updateFlag = ucUserCertifyDao.updatePersonCertifyInfo(ucPersonCertify);
			if (updateFlag == 1) {
				log.info("readd person certify Info is success !");
			}
			//插入图片信息
			if (null != pcCerifyDto.getPicPath() && !"".equals(pcCerifyDto.getPicPath())) {
				List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(certifyId,0,1,2);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(certifyId,0,1,2);//把已上传的图片软删除
				}
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName(), pcCerifyDto.getPicType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall(), pcCerifyDto.getPicSmallType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig(), pcCerifyDto.getPicBigType(), new Date());
			}
			if(null != pcCerifyDto.getPicPath1() && !"".equals(pcCerifyDto.getPicPath1())){
				List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(certifyId,3,4,5);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(certifyId,3,4,5);//把已上传的图片软删除
				}
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName1(), pcCerifyDto.getPicType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall1(), pcCerifyDto.getPicSmallType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig1(), pcCerifyDto.getPicBigType1(), new Date());
			}	
			//certifyImgDao.updateAllCertifyImg(certifyId);//把已上传的图片软删除
			resultFlag = updateFlag;
		}
		
			
		
		//日志记录
		UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
		ucUserCertifyLog.setCertifyId(certifyId);
		ucUserCertifyLog.setCreateDate(new Date());
		ucUserCertifyLog.setType(0);//个人认证
		ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(ucUserVo.getUserId())));
		ucUserCertifyLog.setOpraterName(ucUserVo.getUserName());
		if ("0".equals(pcCerifyDto.getIsPass())) {
			ucUserCertifyLog.setMemo("个人会员提交实名认证审核资料");
		}else if ("2".equals(pcCerifyDto.getIsPass())) {
			ucUserCertifyLog.setMemo("个人会员重新提交实名认证审核资料");
		}
		certifyLogDao.addCertifyLog(ucUserCertifyLog);			
		return resultFlag;
	}

	/**
	 * 保存图片
	 * @param certifyId
	 * @param picPath
	 * @param picType
	 * @param createTime
	 */
	public void insertImg(Integer certifyId,String picPath,Integer picType,Date createTime) {
		CertifyImg certifyImg = new CertifyImg();
		certifyImg.setCertifyId(certifyId);
		certifyImg.setPath(picPath);
		certifyImg.setType(picType);
		certifyImg.setCreateTime(createTime);
		certifyImg.setUpdateTime(createTime);
		certifyImgDao.addCertifyImg(certifyImg);
	}

}
