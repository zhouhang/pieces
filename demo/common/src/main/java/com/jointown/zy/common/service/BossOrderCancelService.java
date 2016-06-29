/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.BossOrderCancelListDto;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossOrderCancelInfoVo;
import com.jointown.zy.common.vo.BossOrderCancelListVo;

/**
 * @ClassName: BossOrderCancelService
 * @Description: 后台交易取消审核相关操作Service
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public interface BossOrderCancelService {

	/**
	 * @Description: 后台交易取消审核列表查询
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param query 查询条件
	 * @return 交易取消审核列表结果
	 */
	Page<BossOrderCancelListVo> selectOrderCancelList(BossOrderCancelListDto query) throws Exception;
	
	/**
	 * @Description: 后台交易取消审核列表-查看订单申请退款详情
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param orderId 订单编号
	 * @return 订单申请退款详情
	 */
	BossOrderCancelInfoVo selectOrderCancelInfoById(String orderId) throws Exception;
	
	/**
	 * @Description: 后台交易取消审核列表-审核通过
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param orderId 订单编号
	 * @return success:操作成功；failure：操作失败
	 */
	String appealFinishById(String orderId) throws Exception;
	
	/**
	 * @Description: 后台交易取消审核列表-驳回申请
	 * @Author: 赵航
	 * @Date: 2015年4月8日
	 * @param orderId 订单编号
	 * @param rejectedComment 驳回原因
	 * @return success:操作成功；failure：操作失败
	 */
	String updateAppealRejectedById(String orderId, String rejectedComment) throws Exception;
	
	/**
	 * @Description: 将过期订单取消
	 * @Author: 赵航
	 * @Date: 2015年4月17日
	 * @param order 订单
	 * @return success:操作成功；failure：操作失败
	 * @throws Exception
	 */
	String orderCancelByTask(BusiOrder order) throws Exception;
	
	/**
	 * @Description: 扫描过期订单
	 * @Author: 赵航
	 * @Date: 2015年4月17日
	 * @param count 最大检索条数（注：当为null时，检索所有符合条件的记录）
	 * @return
	 * @throws Exception
	 */
	List<BusiOrder> selectOverTimeOrderList(Integer count) throws Exception;
	
}
