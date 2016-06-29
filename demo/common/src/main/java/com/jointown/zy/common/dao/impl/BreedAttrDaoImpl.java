package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.BreedAttrDao;
import com.jointown.zy.common.model.BreedAttr;
/**
 * 
 * @author seven
 *
 */
@Repository
public class BreedAttrDaoImpl extends BaseDaoImpl implements BreedAttrDao {
/**
 * 通过主键删除品种属性表的属性
 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		int result =this.getSqlSession().delete("com.jointown.zy.common.dao.BreedAttrMapper.deleteByPrimaryKey", id);
		return result;
	}

	@Override
	public int insert(BreedAttr record) {
		int result =this.getSqlSession().insert("com.jointown.zy.common.dao.BreedAttrMapper.insert", record);
		return result;
	}

	@Override
	public int insertSelective(BreedAttr record) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.BreedAttrMapper.insertSelective", record);
	}
	/**
	 * @return BreedAttr
	 */
	@Override
	public BreedAttr selectByPrimaryKey(Long id) {
		return   this.getSqlSession().selectOne("com.jointown.zy.common.dao.BreedAttrMapper.selectByPrimaryKey", id);
		 
	}

	@Override
	public int updateByPrimaryKeySelective(BreedAttr record) {
		
		return this.getSqlSession().update("com.jointown.zy.common.dao.BreedAttrMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(BreedAttr record) {
		
		return this.getSqlSession().update("com.jointown.zy.common.dao.BreedAttrMapper.updateByPrimaryKey", record);
	}
	/**
	 * @param List<BreedAttr>
	 * @return int 插入的BreedAttr的个数
	 * @description 传入list<BreedAttr>插入后返回插入的个数
	 */
	@Override
	public int insert(List<BreedAttr> breedAttrs) {
		int result=0;
		if(breedAttrs!=null&&breedAttrs.size()>0){
			for (BreedAttr breedAttr:breedAttrs){
				int flag= this.insert(breedAttr);
				if(flag>0){
					result++;
				}
			}
		}
		return result;
	}

	@Override
	public List<BreedAttr> queryBreedAttr(BreedAttr breedAttr) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.BreedAttrMapper.selectByBreedAttr", breedAttr);
	}

	@Override
	public int deleteBreedAttrByBreedId(long breedId) {
		return this.getSqlSession().delete("com.jointown.zy.common.dao.BreedAttrMapper.deleteBybreedId", breedId);
	}

	
}
