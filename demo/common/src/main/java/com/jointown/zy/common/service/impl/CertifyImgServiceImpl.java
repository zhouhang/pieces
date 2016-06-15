package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CertifyImgDao;
import com.jointown.zy.common.dao.CompanyCertifyDao;
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.service.CertifyImgService;
import com.jointown.zy.common.service.CompanyCertifyService;


@Service
public class CertifyImgServiceImpl implements CertifyImgService {
	
	@Autowired 
	private CertifyImgDao certifyImgDao;

	@Override
	public void addCertifyImg(CertifyImg certifyImg) {
		certifyImgDao.addCertifyImg(certifyImg);
	}

	@Override
	public void updateCertifyImg(CertifyImg certifyImg) {
		certifyImgDao.updateCertifyImg(certifyImg);
	}

	@Override
	public List<CertifyImg> findCertifyImgByCertifyId(Integer certifyId) {
		return certifyImgDao.findCertifyImgByCertifyId(certifyId);
	}

	@Override
	public CertifyImg findCertifyImgByCertifyIdAndType(Integer certifyId,Integer type) {
		return certifyImgDao.findCertifyImgByCertifyIdAndType(certifyId,type);
	}


}
