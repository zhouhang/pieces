/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderCancelInfoVo;
import com.jointown.zy.common.vo.BossOrderCancelListVo;
import com.jointown.zy.common.vo.BusiAppealVo;

/**
 * @ClassName: BusiAppealDao
 * @Description: 订单申诉DAO
 * @Author: 赵航
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
public interface BusiAppealDao {
	
	/**
	 * @Description: 后台-交易取消审核列表查询
	 * @Author: 赵航
	 * @Date: 2015年4月9日
	 * @param page 分页查询条件
	 * @return 交易取消申诉列表结果
	 * @throws Exception
	 */
	List<BossOrderCancelListVo> selectOrderCancelListByPage(Page<BossOrderCancelListVo> page) throws Exception;
	
	/**
	 * @Description: 后台-交易取消审核-根据订单编号查询取消进度详情
	 * @Author: 赵航
	 * @Date: 2015年4月9日
	 * @param orderId 订单编号
	 * @return 取消进度详情
	 * @throws Exception
	 */
	BossOrderCancelInfoVo selectOrderCancelInfoById(String orderId) throws Exception;
	
	/**
	 * @Description: 后台-交易取消审核-驳回申请
	 * @Author: 赵航
	 * @Date: 2015年4月9日
	 * @param appeal 更新条件及内容
	 * @return 记录数
	 * @throws Exception
	 */
	int updateRejectReason(BusiAppeal appeal) throws Exception;
	
	/**
	 * @Description: 根据订单编号，更新申述状态
	 * @Author: 赵航
	 * @Date: 2015年4月9日
	 * @param orderId 订单编号
	 * @param stateKey 状态KEY
	 * @return 记录数
	 * @throws Exception
	 */
	int updateExamineStateById(String orderId, String stateKey) throws Exception;
	
    /**
     * 
     * @Description: 查询订单申诉详情
     * @Author: wangjunhu
     * @Date: 2015年4月9日
     * @param orderId
     * @return
     */
    BusiAppealVo selectBusiAppealVoByOrderId(Map<String,Object> map);

	/**
	 * @Description: 插入申述数据
	 * @Author: guoyb
	 * @Date: 2015年4月17日
	 * @param busiAppeal
	 * @return
	 */
	int insertBusiAppeal(BusiAppeal busiAppeal);
	
	/**
	 * @Description: 根据订单ID查询订单申述表记录
	 * @Author: 赵航
	 * @Date: 2015年7月8日
	 * @param orderId 订单ID
	 * @return 订单申述表记录
	 */
	BusiAppeal selectBusiAppealByOrderId(String orderId);
}
