package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.DictInfoDao;
import com.jointown.zy.common.model.DictInfo;
/**
 * 操作字典表
 * @author chengchang
 * @param <T>
 */
@Repository
public class DictInfoDaoImpl extends BaseDaoImpl implements DictInfoDao  {
	/**
	 * @author chengchang
	 * @param DICT_TYPE
	 * @description 输入字典表中的DICT_TYPE,查询出相同的DICT_TYPE封装成list返回在页面上
	 * @return
	 */
	public  List <DictInfo> getDictList(String dictType){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.DictInfoMapper.selectByDictType", dictType);
	}
	
	public List<DictInfo> selectDictByCode(String dictCode){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.DictInfoMapper.selectDictByCode", dictCode);
	}

	@Override
	public Long getMaxId() {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.DictInfoMapper.selectMaxId");
	}

	@Override
	public int insert(DictInfo dictInfo) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.DictInfoMapper.insert",dictInfo);
	} 

	@Override
	public DictInfo getDictByValue(String dictValue) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.DictInfoMapper.selectByDictValue", dictValue);
	}
}
