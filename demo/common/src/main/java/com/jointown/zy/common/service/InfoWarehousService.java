package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.InfoWarehousModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.InfoWarehousVo;

/**
 * 
 * @ClassName:InfoWarehousService
 * @author:Calvin.Wangh
 * @date:2015-6-10上午10:13:35
 * @version V1.0
 * @Description:入仓信息管理Service
 */
public interface InfoWarehousService {
	
	public List<InfoWarehousVo> getPageList(Page<InfoWarehousVo> page);
	
	public List<Breed> getTypeNames(String param);
	
	public int addInfoWarehous(InfoWarehousModel infoWarehous) throws Exception ;
	
	public InfoWarehousModel getInfoWarehous(InfoWarehousModel infoWarehous) throws Exception;
	
	public int updateInfoWarehous(InfoWarehousModel infoWarehous) throws Exception;

}