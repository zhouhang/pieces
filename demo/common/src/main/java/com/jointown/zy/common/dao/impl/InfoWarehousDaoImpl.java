package com.jointown.zy.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.InfoWarehousDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.InfoWarehousModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.InfoWarehousVo;

/**
 * @ClassName:InfoWarehousDaoImpl
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:11:12
 * @version V1.0
 * @Description:入仓信息管理DaoImpl
 */
@Repository
public class InfoWarehousDaoImpl extends BaseDaoImpl implements InfoWarehousDao {
	
	public List<InfoWarehousVo> getByPageList(Page<InfoWarehousVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.persistence.InfoWarehousMapper.getQueryPageList",page);
	}
	
	public List<Breed> selectTypeNames(String param){
		return this.getSqlSession().selectList("com.jointown.zy.common.persistence.InfoWarehousMapper.selectTypeName", param);
	}
	
	public int insertInfoWarehous(InfoWarehousModel infoWarehous){
		return this.getSqlSession().insert("com.jointown.zy.common.persistence.InfoWarehousMapper.insertInfoWarehous", infoWarehous);
	}
	
	public InfoWarehousModel selectByWarehousId(InfoWarehousModel infoWarehous){
		return this.getSqlSession().selectOne("com.jointown.zy.common.persistence.InfoWarehousMapper.selectByWarehousId", infoWarehous);
	}
	
	public int updateInfoWarehous(InfoWarehousModel infoWarehous){
		return this.getSqlSession().update("com.jointown.zy.common.persistence.InfoWarehousMapper.updateByWarehousId", infoWarehous);
	}

}