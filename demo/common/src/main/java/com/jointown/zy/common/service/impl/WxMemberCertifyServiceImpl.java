/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
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
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUserCertifyLog;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxMemberCertifyService;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: WxMemberCertifyServiceImpl
 * @Description: TODO
 * @Author: guoyb
 * @Date: 2015年8月3日
 * @Version: 1.0
 */
@Service
public class WxMemberCertifyServiceImpl implements WxMemberCertifyService {

	private static final Logger log = LoggerFactory
			.getLogger(WxMemberCertifyServiceImpl.class);

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

	@Override
	public int addPersonCertifyInfo(PersonCertifyDto pcCerifyDto, String imgDir) {
		UcPersonCertify ucPersonCertify = new UcPersonCertify();

		UcUserVo ucUserVo = (UcUserVo) SecurityUtils.getSubject().getSession()
				.getAttribute(RedisEnum.SESSION_USER_WX.getValue());

		Integer certifyId = null;
		if ("0".equals(pcCerifyDto.getIsPass())) {// 待审核时添加数据
			certifyId = companyCertifyDao.findSeqNext();
		} else if ("2".equals(pcCerifyDto.getIsPass())) {// 已驳回时重新提交数据
			certifyId = Integer.parseInt(pcCerifyDto.getCertifyId());
		}
		ucPersonCertify.setCertifyId(certifyId);
		ucPersonCertify.setUserId(Integer.parseInt(String.valueOf(ucUserVo
				.getUserId())));
		ucPersonCertify.setName(pcCerifyDto.getName());
		ucPersonCertify.setIdCard(pcCerifyDto.getIdCard());
		ucPersonCertify.setStatus(Integer.parseInt(pcCerifyDto.getIsPass()));// 0待审核
																				// 2已驳回
		ucPersonCertify.setSubmitTime(new Date());
		ucPersonCertify.setCreateTime(new Date());
		ucPersonCertify.setUpdateTime(new Date());

		int resultFlag = 0;
		if ("0".equals(pcCerifyDto.getIsPass())) {// 待审核,第一次提交
			UcPersonCertify p_certify = ucUserCertifyDao
					.getCertifyUcUserInfoByUserId(String.valueOf(ucUserVo
							.getUserId()));
			if (p_certify != null) {
				return 3;
			}
			int addFlag = ucUserCertifyDao
					.addPersonCertifyInfo(ucPersonCertify);
			if (addFlag == 1) {
				log.info("add person certify Info is success !");
			}
			// 插入图片信息
			if (null != pcCerifyDto.getPicPath()
					&& !"".equals(pcCerifyDto.getPicPath())) {
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName(),
						pcCerifyDto.getPicType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall(),
						pcCerifyDto.getPicSmallType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig(),
						pcCerifyDto.getPicBigType(), new Date());
			}
			if (null != pcCerifyDto.getPicPath1()
					&& !"".equals(pcCerifyDto.getPicPath1())) {
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName1(),
						pcCerifyDto.getPicType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall1(),
						pcCerifyDto.getPicSmallType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig1(),
						pcCerifyDto.getPicBigType1(), new Date());
			}

			resultFlag = addFlag;
		} else if ("2".equals(pcCerifyDto.getIsPass())) {// 已驳回重新提交
			UcPersonCertify p_certify = ucUserCertifyDao
					.getCertifyUcUserInfoByUserId(String.valueOf(ucUserVo
							.getUserId()));
			if (p_certify != null) {
				return 3;
			}
			int updateFlag = ucUserCertifyDao
					.updatePersonCertifyInfo(ucPersonCertify);
			if (updateFlag == 1) {
				log.info("readd person certify Info is success !");
			}
			// 插入图片信息
			if (null != pcCerifyDto.getPicPath()
					&& !"".equals(pcCerifyDto.getPicPath())) {
				List<CertifyImg> imgList = certifyImgDao
						.findAllTypeImgByCertifyId(certifyId, 0, 1, 2);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(certifyId, 0, 1, 2);// 把已上传的图片软删除
				}
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName(),
						pcCerifyDto.getPicType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall(),
						pcCerifyDto.getPicSmallType(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig(),
						pcCerifyDto.getPicBigType(), new Date());
			}
			if (null != pcCerifyDto.getPicPath1()
					&& !"".equals(pcCerifyDto.getPicPath1())) {
				List<CertifyImg> imgList = certifyImgDao
						.findAllTypeImgByCertifyId(certifyId, 3, 4, 5);
				if (null != imgList && imgList.size() != 0) {
					certifyImgDao.updateAllTypeCertifyImg(certifyId, 3, 4, 5);// 把已上传的图片软删除
				}
				insertImg(certifyId, imgDir + pcCerifyDto.getPicName1(),
						pcCerifyDto.getPicType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameSmall1(),
						pcCerifyDto.getPicSmallType1(), new Date());
				insertImg(certifyId, imgDir + pcCerifyDto.getPicNameBig1(),
						pcCerifyDto.getPicBigType1(), new Date());
			}
			// certifyImgDao.updateAllCertifyImg(certifyId);//把已上传的图片软删除
			resultFlag = updateFlag;
		}

		// 日志记录
		UcUserCertifyLog ucUserCertifyLog = new UcUserCertifyLog();
		ucUserCertifyLog.setCertifyId(certifyId);
		ucUserCertifyLog.setCreateDate(new Date());
		ucUserCertifyLog.setType(0);// 个人认证
		ucUserCertifyLog.setOpraterId(Integer.parseInt(String.valueOf(ucUserVo
				.getUserId())));
		ucUserCertifyLog.setOpraterName(ucUserVo.getUserName());
		if ("0".equals(pcCerifyDto.getIsPass())) {
			ucUserCertifyLog.setMemo("个人会员提交实名认证审核资料");
		} else if ("2".equals(pcCerifyDto.getIsPass())) {
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
