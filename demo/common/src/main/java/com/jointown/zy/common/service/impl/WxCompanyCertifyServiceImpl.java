package com.jointown.zy.common.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.UcUserCertifyLog;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxCompanyCertifyService;
import com.jointown.zy.common.vo.UcUserVo;


@Service
public class WxCompanyCertifyServiceImpl implements WxCompanyCertifyService {
	
	private static final Logger log = LoggerFactory.getLogger(WxCompanyCertifyServiceImpl.class);
	
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
			UcUserVo ucUserVo = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
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
}
