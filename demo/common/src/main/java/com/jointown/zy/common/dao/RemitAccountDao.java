package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.dto.RemitDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.RefuseRemitFlow;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.model.RemitFlowLog;
import com.jointown.zy.common.model.RemitResult;
import com.jointown.zy.common.vo.RemitVo;

public interface RemitAccountDao {
	
	public int addRemitFlow(RemitFlow remitFlow);
	public int addRemitFlowLog(RemitFlowLog remitFlowLog);
	public int addRemitResult(RemitResult remitResult);
	public int updateRemitFlow(RemitFlow remitFlow);
	public RemitFlow getRemitFlowById(String flowId);
	
	public List<RemitVo> getPage(Page<RemitVo> page);
	
	public List<RemitFlow> getRemitFlowList(RemitDto remitDto); 
	
	public int processRemit(RemitFlow remitFlow);
	
	/**
	 * @Description: 根据系统标识和num,查询划账流水结果记录
	 * @Author: ldp
	 * @Date: 2015年7月3日
	 * @param sysSource
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public List<RemitResult> selectRemitResult(Integer sysSource,Integer num) throws Exception;
	
	/**
	 * @Description: 根据划账流水结果表ID，修改划账流水结果记录状态
	 * @Author: ldp
	 * @Date: 2015年7月3日
	 * @param remitResultId
	 * @return
	 * @throws Exception
	 */
	public int updateRemitResult(Integer remitResultId) throws Exception;
	
	/**--added by biran 
	 * 20150706
	 * 订单查看拒绝划账流水使用
	 * param RemitFlow
	 **/
	public List<RefuseRemitFlow> getRefuseRemitFlowsByOrderId(RemitFlow remitFlow) throws Exception; 
	
}
