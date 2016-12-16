package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.QualificationPicsDao;
import com.pieces.dao.UserQualificationDao;
import com.pieces.dao.model.QualificationPics;
import com.pieces.dao.model.UserQualification;
import com.pieces.dao.vo.QualificationPicsVo;
import com.pieces.dao.vo.UserQualificationVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.UserQualificationService;
import com.pieces.tools.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserQualificationServiceImpl  extends AbsCommonService<UserQualification> implements UserQualificationService{

	@Autowired
	private UserQualificationDao userQualificationDao;

	@Autowired
	private QualificationPicsDao qualificationPicsDao;


	@Override
	public PageInfo<UserQualificationVo> findByParams(UserQualificationVo userQualificationVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<UserQualificationVo>  list = userQualificationDao.findByParams(userQualificationVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<UserQualificationVo> findAll(UserQualificationVo userQualificationVo) {
		List<UserQualificationVo>  list = userQualificationDao.findByParams(userQualificationVo);
		for(UserQualificationVo userQualificationVo1:list){
			QualificationPicsVo param=new QualificationPicsVo();
			param.setQid(userQualificationVo1.getId());
			List<QualificationPicsVo> qualificationPicses=qualificationPicsDao.findByParams(param);
			for(QualificationPicsVo qualificationPicsVo:qualificationPicses){
				qualificationPicsVo.setPictureUrl(FileUtil.getUrl(qualificationPicsVo.getPictureUrl()));
			}
			userQualificationVo1.setPictures(qualificationPicses);
		}
		return list;
	}

	@Override
	public UserQualificationVo findByCondition(UserQualificationVo userQualificationVo) {
		List<UserQualificationVo>  list = userQualificationDao.findByParams(userQualificationVo);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public ICommonDao<UserQualification> getDao() {
		return userQualificationDao;
	}

}
