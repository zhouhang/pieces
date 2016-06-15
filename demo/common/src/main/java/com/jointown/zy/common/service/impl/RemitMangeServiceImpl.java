package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.jointown.zy.common.dao.RemitAccountDao;
import com.jointown.zy.common.dto.RemitDto;
import com.jointown.zy.common.enums.RefundRemitStatusEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.pay.ucs.service.UcsService;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.RemitMangeService;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.RestHttpUtil;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.RemitVo;
/**
 * @ClassName: RemitMangeServiceImpl
 * @Description: 交易订单分润划账，交易订单退款划账
 * @Author: ldp
 * @Date: 2015年7月2日
 * @Version: 1.0
 */
@Service
public class RemitMangeServiceImpl implements RemitMangeService {
	
	@Autowired
	private RemitAccountDao remitDao;
	@Autowired
	private UcsService ucsService;

	@Override
	public List<RemitVo> searchRemitFlow(Page<RemitVo> page){
		return remitDao.getPage(page);
	}

	@Override
	public List<RemitFlow> getRemitFlowList(RemitDto remitDto) {
		return remitDao.getRemitFlowList(remitDto);
	}

	@Override
	public RemitFlow getRemitFlowInfo(String flowId) {
		return remitDao.getRemitFlowById(flowId);
	}

	public String processRemit(RemitFlow remitFlow,int type) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		BossUserVo  bossUserVo = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		long userId = Long.valueOf(bossUserVo.getId().toString());
		String result = "";
		//设置 退款状态
		if(RefundRemitStatusEnum.REMIT_SUCCESS.getStatus() == type){
			remitFlow.setStatus(RefundRemitStatusEnum.REMIT_SUCCESS.getStatus());
		}else if(RefundRemitStatusEnum.REMIT_REFUSE.getStatus() == type){
			remitFlow.setStatus(RefundRemitStatusEnum.REMIT_REFUSE.getStatus());
		}
		remitFlow.setOpraterId(userId);
		remitFlow.setOpraterTime(new Date());
		remitFlow.setUpdateTime(new Date());
		remitFlow.setSourcesys(0);
		//将remitFlow对象转换为json字符串
		String jsonStr = GsonFactory.toJson(remitFlow,"yyyy-MM-dd HH:mm:ss");
		String reqUrl = SpringUtil.getConfigProperties("jointown.pay.batchRemitAccount");
		try {
			result = RestHttpUtil.RestPost(reqUrl, jsonStr);
		} catch (Exception e) {
			map.put("code", "0");
			map.put("msg", "请求超时");
			result = map.toString();
			return result;
		}
		if(result == null){
			map.put("code", "0");
			map.put("msg", "返回结果为空");
			result = map.toString();
			return result;
		}
		return result;
	}

}
