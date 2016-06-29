package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.InfoWarehousDao;
import com.jointown.zy.common.enums.InfoWarehousSourceEnum;
import com.jointown.zy.common.enums.InfoWarehousStateEnum;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.InfoWarehousModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.InfoWarehousService;
import com.jointown.zy.common.vo.InfoWarehousVo;

/**
 * 
 * @ClassName:InfoWarehousServiceImpl
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:12:48
 * @version V1.0
 * @Description:入仓信息管理ServiceImpl
 */
@Service
public class InfoWarehousServiceImpl implements InfoWarehousService {

    @Autowired
    private InfoWarehousDao infoWarehousDao;
    
    /**
     * 分页查询
     */
 	public List<InfoWarehousVo> getPageList(Page<InfoWarehousVo> page) {
		return infoWarehousDao.getByPageList(page);
	}
 	
 	/**
 	 * 根据品种名称 别名 查询
 	 */
 	public List<Breed> getTypeNames(String param){
 		return infoWarehousDao.selectTypeNames(param);
 	}
	
 	/**
 	 * 新增入仓信息
 	 */
 	public int addInfoWarehous(InfoWarehousModel infoWarehous) throws DataAccessException {
 		infoWarehous.setWarehouseAddress(infoWarehous.getWarehouseAddress());
 		infoWarehous.setWarehousePlace(infoWarehous.getWarehouseAddress());
 		infoWarehous.setApplyResource(InfoWarehousSourceEnum.BOSS.getStatusDesc());
 		infoWarehous.setStatus(InfoWarehousStateEnum.VALID.getStatus());
 		infoWarehous.setCreateTime(new Date());
 		infoWarehous.setUpdateTime(new Date());
 		infoWarehous.setHandleTime(new Date());
 		return infoWarehousDao.insertInfoWarehous(infoWarehous);
 	}
 	
 	/**
 	 * 主键查询入仓信息
 	 */
 	public InfoWarehousModel getInfoWarehous(InfoWarehousModel infoWarehous) throws Exception{
 		return infoWarehousDao.selectByWarehousId(infoWarehous);
 	}
 	
 	/**
 	 * 修改入仓信息
 	 */
 	public int updateInfoWarehous(InfoWarehousModel infoWarehous) throws Exception {
 		return infoWarehousDao.updateInfoWarehous(infoWarehous);
 	}

}