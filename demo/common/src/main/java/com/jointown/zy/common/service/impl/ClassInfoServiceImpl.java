package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CategorysDao;
import com.jointown.zy.common.dao.ClassInfoDao;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.ClassInfo;
import com.jointown.zy.common.service.ClassInfoService;

@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {

	private static Logger logger = LoggerFactory.getLogger(ClassInfoServiceImpl.class);
	@Autowired
	private ClassInfoDao classInfoDao;
	@Autowired
	private CategorysDao categorysDao;

	@Override
	public Long addClassInfo(String className,String operateStrage,Long userId) {
		int num = 0;
		List<ClassInfo> classInfoList = getClassByName(className,null);
	 if(!(classInfoList != null && classInfoList.size()>0)){
		ClassInfo classInfo = new ClassInfo();
			classInfo.setClassName(className);
			classInfo.setDescription(className);
			classInfo.setCreater(userId);
			classInfo.setCreateTime(new Date());
			classInfo.setStatus(Short.valueOf("1"));
			classInfo.setOperateStrate(operateStrage);
			num = classInfoDao.insert(classInfo);
			if(num>0){
				 return classInfo.getClassId();
			 }
	  }else{
		  logger.info("已存在此名字的分类信息，因此不再做插入操作");
	  }
	 
		return null;	
	}

	@Override
	public int updateClassInfo(String updateClassName, Long classId, Long userId,String classStrage) {
		List<ClassInfo> classInfoList = getClassByName(updateClassName,classId);
	 if(!(classInfoList != null && classInfoList.size()>0)){
		ClassInfo classInfo = new ClassInfo();
			classInfo.setClassId(classId);
			classInfo.setClassName(updateClassName);
			classInfo.setDescription(updateClassName);
			classInfo.setOperateStrate(classStrage);
			classInfo.setUpdater(userId);
			classInfo.setUpdateTime(new Date());
			return classInfoDao.updateByPrimaryKeySelective(classInfo);
		}
	 return 0;
	}

	@Override
	public int deleteClassInfo(Long classId, Long userId) {
	  int num = 0;
	  List<Categorys> categoryList = getCategoryByClass(classId);
	 if(!(categoryList != null && categoryList.size()>0)){
		ClassInfo classInfo = new ClassInfo();
			classInfo.setClassId(classId);
			classInfo.setStatus(Short.valueOf("0"));
			classInfo.setUpdater(userId);
			classInfo.setUpdateTime(new Date());
			num = classInfoDao.updateByPrimaryKeySelective(classInfo);
	 }else{
		 logger.info("此分类下存在类目信息，因此不能删除！");
	 }
	 return num;
	}

	@Override
	public List<ClassInfo> getEffectClass() {
		ClassInfo classInfo = new ClassInfo();
			classInfo.setStatus(Short.valueOf("1")); //有效
			classInfo.setSqlSort(" CREATE_TIME ");
			return classInfoDao.selectList(classInfo);
	}

	@Override
	public int updateOperateStrate(Long classId, String strategy, Long userId) {
		ClassInfo classInfo = new ClassInfo();
			classInfo.setClassId(classId);
			classInfo.setOperateStrate(strategy);
			classInfo.setUpdater(userId);
			classInfo.setUpdateTime(new Date());
			return classInfoDao.updateByPrimaryKeySelective(classInfo);
	}

	@Override
	public List<ClassInfo> getClassByName(String className,Long classId) {
		ClassInfo classInfo = new ClassInfo();
			classInfo.setClassName(className);
			classInfo.setStatus(Short.valueOf("1")); //有效的
			if(classId != null)
				classInfo.setSqlStr(" and CLASS_ID!="+classId);
		  return classInfoDao.selectList(classInfo);
	}

	@Override
	public List<Categorys> getCategoryByClass(Long classId) {
		Categorys category = new Categorys();
			category.setClassId(classId);
			category.setStatus(Short.valueOf("1"));
		return  categorysDao.selectList(category);
		
	}

	@Override
	public ClassInfo selectByPrimaryKey(Long classId) {
		return classInfoDao.selectByPrimaryKey(classId);
	} 
	
}
