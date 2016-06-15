package com.jointown.zy.common.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.jointown.zy.common.dto.CompanyCertifyDto;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.model.UcUserCertifyLog;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.CompanyCertifyService;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.UcUserVo;


@Service
public class CompanyCertifyServiceImpl implements CompanyCertifyService {
	
	private static final Logger log = LoggerFactory.getLogger(CompanyCertifyServiceImpl.class);
	
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
	private UcUserCertifyDao ucUserCertifyDao;
	@Autowired
	private MessageConfigManage messageConfigManage;
	

	@Override
	public int addCompanyCertify(CompanyCertifyDto companyCertifyDto,String imgDir) {
		int resultFlag =0;
		try {
			//插入企业信息
			CompanyCertify companyCertify = new CompanyCertify();
			Integer certifyId =companyCertifyDao.findSeqNext();
			companyCertify.setUserId(Integer.parseInt(companyCertifyDto.getUserId().toString()));
			companyCertify.setCompanyName(companyCertifyDto.getCompanyName());
			companyCertify.setPresidentName(companyCertifyDto.getPresidentName());
			companyCertify.setLicenceCode(companyCertifyDto.getLicenceCode());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//小写的mm表示的是分钟  
			Date licenceStartdate= sdf.parse(companyCertifyDto.getLicenceStartdate());
			Date licenceEnddate=sdf.parse(companyCertifyDto.getLicenceEnddate());  
			companyCertify.setLicenceStartdate(licenceStartdate);
			companyCertify.setLicenceEnddate(licenceEnddate);
			companyCertify.setOrgCode(companyCertifyDto.getOrgCode());
			Date createTime = new Date();
			companyCertify.setCreateTime(createTime);
			companyCertify.setUpdateTime(createTime);
			companyCertify.setSubmitTime(createTime);
			companyCertify.setStatus(0);
			if(companyCertifyDto.getCertifyId()==null){
				companyCertify.setCertifyId(certifyId);
				CompanyCertify c_certify = companyCertifyDao.findCompanyCertifyByUserId(Integer.parseInt(companyCertifyDto.getUserId().toString()));
				if(c_certify!=null){
					return 3;
				}
				resultFlag = companyCertifyDao.addCompanyCertify(companyCertify);
				//插入图片信息
				insertImg(certifyId, imgDir+companyCertifyDto.getPicName(), companyCertifyDto.getPicType(), createTime);
				insertImg(certifyId, imgDir+companyCertifyDto.getPicNameSmall(), companyCertifyDto.getPicSmallType(), createTime);
				insertImg(certifyId, imgDir+companyCertifyDto.getPicNameBig(), companyCertifyDto.getPicBigType(), createTime);
				if(!companyCertifyDto.getPicPath1().equals("")){
					insertImg(certifyId, imgDir+companyCertifyDto.getPicName1(), companyCertifyDto.getPicType1(), createTime);
					insertImg(certifyId, imgDir+companyCertifyDto.getPicNameSmall1(), companyCertifyDto.getPicSmallType1(), createTime);
					insertImg(certifyId, imgDir+companyCertifyDto.getPicNameBig1(), companyCertifyDto.getPicBigType1(), createTime);
				}
			}else{
				Integer ori_certifyId=Integer.parseInt(companyCertifyDto.getCertifyId());
				CompanyCertify c_certify = companyCertifyDao.findCompanyCertifyByUserId(Integer.parseInt(companyCertifyDto.getUserId().toString()));
				if(c_certify!=null){
					return 3;
				}
				certifyImgDao.updateAllCertifyImg(ori_certifyId);
				companyCertify.setCertifyId(ori_certifyId);
				resultFlag = companyCertifyDao.updateCompanyCertify(companyCertify);
				insertImg(ori_certifyId, imgDir+companyCertifyDto.getPicName(), companyCertifyDto.getPicType(), createTime);
				insertImg(ori_certifyId, imgDir+companyCertifyDto.getPicNameSmall(), companyCertifyDto.getPicSmallType(), createTime);
				insertImg(ori_certifyId, imgDir+companyCertifyDto.getPicNameBig(), companyCertifyDto.getPicBigType(), createTime);
				if(!companyCertifyDto.getPicPath1().equals("")){
					insertImg(ori_certifyId, imgDir+companyCertifyDto.getPicName1(), companyCertifyDto.getPicType1(), createTime);
					insertImg(ori_certifyId, imgDir+companyCertifyDto.getPicNameSmall1(), companyCertifyDto.getPicSmallType1(), createTime);
					insertImg(ori_certifyId, imgDir+companyCertifyDto.getPicNameBig1(), companyCertifyDto.getPicBigType1(), createTime);
				}
			}
			UcUserVo ucUserVo = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			//日志记录
			UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
			if(companyCertifyDto.getCertifyId()==null){
				ucUserCertifyLog.setCertifyId(certifyId);
			}else{
				ucUserCertifyLog.setCertifyId(Integer.parseInt(companyCertifyDto.getCertifyId()));
			}
			ucUserCertifyLog.setCreateDate(new Date());
			ucUserCertifyLog.setType(1);//企业认证
			ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(ucUserVo.getUserId())));
			ucUserCertifyLog.setOpraterName(ucUserVo.getUserName());
			if (companyCertifyDto.getCertifyId() == null) {
				ucUserCertifyLog.setMemo("企业会员提交实名认证审核资料");
			}else {
				ucUserCertifyLog.setMemo("企业会员重新提交实名认证审核资料");
			}
			certifyLogDao.addCertifyLog(ucUserCertifyLog);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return resultFlag;
	}
	public void insertImg(Integer certifyId,String picPath,Integer picType,Date createTime) {
		CertifyImg certifyImg = new CertifyImg();
		certifyImg.setPath(picPath);
		certifyImg.setType(picType);
		certifyImg.setUpdateTime(createTime);
		certifyImg.setCertifyId(certifyId);
		certifyImg.setCreateTime(createTime);
		certifyImgDao.addCertifyImg(certifyImg);
	}

	@Override
	public CompanyCertify findCompanyCertifyByUserId(Integer userId) {
		return companyCertifyDao.findCompanyCertifyByUserId(userId);
	}
	
	@Override
	public CompanyCertify findCompanyCertifyByUserId1(Integer userId) {
		return companyCertifyDao.findCompanyCertifyByUserId1(userId);
	}
	
	@Override
	public CompanyCertify  findReallyCompanyCertifyByUserId(Integer userId){
		return companyCertifyDao.findReallyCompanyCertifyByUserId(userId);
	}

	@Override
	public CompanyCertify findCompanyCertifyByCondition(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int updateCompanyCertify(CompanyCertifyDto comCertDto,
			BossUserVo bossUserVo,String imgDir) {
		CompanyCertify companyCertify = new CompanyCertify();
		int comPassFlag = 0;
		try {
			companyCertify.setCertifyId(Integer.parseInt(comCertDto.getCertifyId()));
			companyCertify.setUserId(Integer.parseInt(String.valueOf(comCertDto.getUserId())));
			companyCertify.setCompanyName(comCertDto.getCompanyName());
			companyCertify.setPresidentName(comCertDto.getPresidentName());
			companyCertify.setOrgCode(comCertDto.getOrgCode());
			companyCertify.setLicenceCode(comCertDto.getLicenceCode());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			companyCertify.setLicenceStartdate(sdf.parse(comCertDto.getLicenceStartdate()));
			companyCertify.setLicenceEnddate(sdf.parse(comCertDto.getLicenceEnddate()));
			companyCertify.setStatus(comCertDto.getStatus());
			companyCertify.setUpdateTime(new Date());
			companyCertify.setProperty(comCertDto.getProperty());//会员属性，个体工商户、公司
			companyCertify.setRegisteredCapital(comCertDto.getRegisteredCapital());//注册资金
			if(comCertDto.getLicenceDate()!=null && !"".equals(comCertDto.getLicenceDate())) companyCertify.setLicenceDate(sdf.parse(comCertDto.getLicenceDate()));//营业执照成立日期
			companyCertify.setApproveTime(new Date());
			companyCertify.setApproverid(bossUserVo.getId());
			companyCertify.setRejectmemo(Integer.parseInt(comCertDto.getCheckRemark()));
			
			comPassFlag = companyCertifyDao.updateCompanyCertify(companyCertify);
			if (comPassFlag == 1) {
				log.info("企业会员审核操作成功!");
				UcUser ucUser = ucUserDao.findMemberByUserId(String.valueOf(comCertDto.getUserId()));
				int certifyStatus = companyCertify.getStatus();
				log.info("company certify is：" + ((certifyStatus==1)?"pass":"unpass"));
				if (certifyStatus==1) {
					ucUserCertifyDao.updateUcUserCertifyStatusByUserId("2", String.valueOf(comCertDto.getUserId()));//企业认证通过修改会员企业认证状态
					log.info("company certify pass send Msg !");
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getCompanyCertifyPassMsg()));
				}else if (certifyStatus==2) {
					log.info("company certify unpass send Msg !");
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getCompanyCertifyUnPassMsg()));
				}
			}
			
			//如何需上传图片,插入图片信息
			if (comCertDto.getPicPath() != null && !"".equals(comCertDto.getPicPath())) {
				List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(comCertDto.getCertifyId()),6,7,8);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(comCertDto.getCertifyId()),6,7,8);//把已上传的图片软删除
				}
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicName(), comCertDto.getPicType(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameSmall(), comCertDto.getPicSmallType(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameBig(), comCertDto.getPicBigType(), new Date());
			}
			if(comCertDto.getPicPath1() != null && !"".equals(comCertDto.getPicPath1())){
				List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(comCertDto.getCertifyId()),9,10,11);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(comCertDto.getCertifyId()),9,10,11);//把已上传的图片软删除
				}
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicName1(), comCertDto.getPicType1(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameSmall1(), comCertDto.getPicSmallType1(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameBig1(), comCertDto.getPicBigType1(), new Date());
			}	
			
			//添加日志
			UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
			ucUserCertifyLog.setCertifyId(Integer.parseInt(comCertDto.getCertifyId()));
			ucUserCertifyLog.setCreateDate(new Date());
			ucUserCertifyLog.setType(1);//个人认证
			ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(bossUserVo.getId())));
			ucUserCertifyLog.setOpraterName(bossUserVo.getUserCode());
			ucUserCertifyLog.setMemo("企业会员实名认证审核:"
					+ (String.valueOf(comCertDto.getStatus()).equals("1") ? ("通过审核") : ("不通过审核"))
					+ ";操作结果：" + (comPassFlag == 1 ? ("操作成功") : ("操作失败")));
			certifyLogDao.addCertifyLog(ucUserCertifyLog);	
			
		} catch (Exception e) {
			log.error("error is:", e);
		}
		return comPassFlag;
	}

	@Override
	public int updateCompanyCertifyInfoAfterPass(CompanyCertifyDto comCertDto,
			BossUserVo bossUserVo,String imgDir) {
		CompanyCertify companyCertify = new CompanyCertify();
		int saveComFlag = 0;
		try {
			companyCertify.setCertifyId(Integer.parseInt(comCertDto.getCertifyId()));
			companyCertify.setUserId(Integer.parseInt(String.valueOf(comCertDto.getUserId())));
			companyCertify.setCompanyName(comCertDto.getCompanyName());
			companyCertify.setPresidentName(comCertDto.getPresidentName());
			companyCertify.setOrgCode(comCertDto.getOrgCode());
			companyCertify.setLicenceCode(comCertDto.getLicenceCode());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			companyCertify.setLicenceStartdate(sdf.parse(comCertDto.getLicenceStartdate()));
			companyCertify.setLicenceEnddate(sdf.parse(comCertDto.getLicenceEnddate()));
			companyCertify.setUpdateTime(new Date());
			companyCertify.setProperty(comCertDto.getProperty());//会员属性，个体工商户、公司
			companyCertify.setRegisteredCapital(comCertDto.getRegisteredCapital());//注册资金
			if(comCertDto.getLicenceDate()!=null && !"".equals(comCertDto.getLicenceDate()))companyCertify.setLicenceDate(sdf.parse(comCertDto.getLicenceDate()));//营业执照成立日期
			saveComFlag = companyCertifyDao.updateCompanyCertifyInfoAfterPass(companyCertify);
			if (saveComFlag == 1) {
				log.info("企业认证通过后的保存操作成功!");
			}
			
			//如果需上传图片,插入图片信息
			if (comCertDto.getPicPath() != null && !"".equals(comCertDto.getPicPath())) {
				List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(comCertDto.getCertifyId()),6,7,8);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(comCertDto.getCertifyId()),6,7,8);//把已上传的图片软删除
				}
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicName(), comCertDto.getPicType(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameSmall(), comCertDto.getPicSmallType(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameBig(), comCertDto.getPicBigType(), new Date());
			}
			if(comCertDto.getPicPath1() != null && !"".equals(comCertDto.getPicPath1())){
				List<CertifyImg> imgList = certifyImgDao.findAllTypeImgByCertifyId(Integer.parseInt(comCertDto.getCertifyId()),9,10,11);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(Integer.parseInt(comCertDto.getCertifyId()),9,10,11);//把已上传的图片软删除
				}
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicName1(), comCertDto.getPicType1(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameSmall1(), comCertDto.getPicSmallType1(), new Date());
				insertImg(Integer.parseInt(comCertDto.getCertifyId()), imgDir + comCertDto.getPicNameBig1(), comCertDto.getPicBigType1(), new Date());
			}	
			
			//添加日志
			UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
			ucUserCertifyLog.setCertifyId(Integer.parseInt(comCertDto.getCertifyId()));
			ucUserCertifyLog.setCreateDate(new Date());
			ucUserCertifyLog.setType(1);//个人认证
			ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(bossUserVo.getId())));
			ucUserCertifyLog.setOpraterName(bossUserVo.getUserCode());
			ucUserCertifyLog.setMemo("企业认证通过后保存资料");
			certifyLogDao.addCertifyLog(ucUserCertifyLog);
		} catch (Exception e) {
			log.error("error is:", e);
		}
		return saveComFlag;
	}
	
	@Override
	public CompanyCertify findCompanyCertifyByCertifyId(Integer certifyId) {
		return companyCertifyDao.findCompanyCertifyByCertifyId(certifyId);
	}
	
}
