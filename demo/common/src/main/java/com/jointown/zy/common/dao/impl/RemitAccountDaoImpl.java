package com.jointown.zy.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.RemitAccountDao;
import com.jointown.zy.common.dto.RemitDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.RefuseRemitFlow;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.model.RemitFlowLog;
import com.jointown.zy.common.model.RemitResult;
import com.jointown.zy.common.vo.RemitVo;

@Repository
public class RemitAccountDaoImpl extends BaseDaoImpl implements RemitAccountDao{

	@Override
	public int addRemitFlow(RemitFlow remitFlow) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.RemitAccountDao.addRemitFlow", remitFlow);
	}

	@Override
	public int addRemitFlowLog(RemitFlowLog remitFlowLog) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.RemitAccountDao.addRemitFlowLog", remitFlowLog);
	}

	@Override
	public int addRemitResult(RemitResult remitResult) {
		return this.getSqlSession().insert("com.jointown.zy.common.dao.RemitAccountDao.addRemitResult", remitResult);
	}

	@Override
	public int updateRemitFlow(RemitFlow remitFlow) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.RemitAccountDao.updateRemitFlow", remitFlow);
	}

	@Override
	public RemitFlow getRemitFlowById(String flowId) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.RemitAccountDao.getRemitFlowById", flowId);
	}

	@Override
	public List<RemitResult> selectRemitResult(Integer sysSource, Integer num)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sysSource", sysSource);
		params.put("num", num);
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.RemitAccountDao.selectRemitResult", params);
	}

	@Override
	public int updateRemitResult(Integer remitResultId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("remitResultId", remitResultId);
		params.put("doneTime", new Date());
		return this.getSqlSession().update("com.jointown.zy.common.dao.RemitAccountDao.updateRemitResult", params);
	}
	

	@Override
	public List<RemitVo> getPage(Page<RemitVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.RemitAccountDao.getRemitFlowPage", page);
	}

	@Override
	public List<RemitFlow> getRemitFlowList(RemitDto remitDto) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.RemitAccountDao.getRemitFlowList", remitDto);
	}
	
	public int processRemit(RemitFlow remitFlow){
		return this.getSqlSession().update("com.jointown.zy.common.dao.RemitAccountDao.processRemit", remitFlow);
	}
	
	@Override
	public List<RefuseRemitFlow> getRefuseRemitFlowsByOrderId(RemitFlow remitFlow){
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.RemitAccountDao.getRefuseRemitFlowsByOrderId", remitFlow);
	}
}
