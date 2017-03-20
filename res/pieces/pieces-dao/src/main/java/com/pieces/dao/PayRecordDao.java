package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@AutoMapper
public interface PayRecordDao extends ICommonDao<PayRecord>{

    public List<PayRecordVo> findByParams(PayRecordVo payRecordVo);

    public List<PayRecordVo> findByNormalRecord();

    public List<PayRecordVo> findByNormalRecord(PayRecordVo payRecordVo);

    public List<PayRecordVo> findByUserId(PayRecordVo payRecordVo);

    /**
     * 待处理支付记录数目
     * @return
     */
    public Integer getNotHandleCount();

    public List<Integer> getNotHandleIds();

    /**
     * 普通用户的订单记录找到支付记录
     * @param userId
     * @param orderId
     * @return
     */
    PayRecordVo findByOrderForUser(@Param("userId")Integer userId,@Param("orderId")Integer orderId);

    /**
     * 代理商用户的订单记录找到支付记录
     * @param agentId
     * @param orderId
     * @return
     */
    PayRecordVo findByOrderForAgent(@Param("agentId")Integer agentId,@Param("orderId")Integer orderId);
}
