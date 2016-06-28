package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.InfoWarehousModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.InfoWarehousVo;

/**
 * @ClassName:InfoWarehousDao
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:11:56
 * @version V1.0
 * @Description:入仓信息管理Dao
 */
public interface InfoWarehousDao {

	public List<InfoWarehousVo> getByPageList(Page<InfoWarehousVo> page);

	public List<Breed> selectTypeNames(String param);

	public int insertInfoWarehous(InfoWarehousModel infoWarehous);

	public InfoWarehousModel selectByWarehousId(InfoWarehousModel infoWarehous);
	
	public int updateInfoWarehous(InfoWarehousModel infoWarehous);
}