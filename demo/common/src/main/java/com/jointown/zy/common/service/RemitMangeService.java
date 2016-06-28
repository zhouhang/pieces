package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.RemitDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.vo.RemitVo;

/**
 * @ClassName: RemitMangeService
 * @Description: 交易订单分润划账，交易订单退款划账
 * @Author: ldp
 * @Date: 2015年6月30日
 * @Version: 1.0
 */
public interface RemitMangeService {

	/**
	 * @Description: 划账流水查询
	 * @Author: ldp
	 * @Date: 2015年7月1日
	 * @return
	 * @throws Exception
	 */
	public List<RemitVo> searchRemitFlow(Page<RemitVo> page);
	
	public List<RemitFlow> getRemitFlowList(RemitDto remitDto);
	
	public RemitFlow getRemitFlowInfo(String flowId);
	
	public String processRemit(RemitFlow remitFlow,int type) throws Exception;
}
