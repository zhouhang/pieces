package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiGoodsOrderVo;
import com.jointown.zy.common.vo.BusiOrderVo;

/**
 * 
 * 描述： 订单相关操作的DAO接口
 * 
 * 日期： 2014年12月31日
 * 
 * 作者： 赵航
 *
 * 版本： V1.0
 */
public interface BusiOrderDao {
	/**
	 * 分页查询我的购买记录
	 */
	List<BusiOrderVo> selectOrderListByPage(Page<BusiOrderVo> page);
	
	/**
	 * 插入购买订单
	 */
	int insertOrder(BusiOrder busiOrder);
	
	/**
	 * 取消购买订单
	 */
	int cancleOrderById(String orderid);
	
	/**
	 * 删除关闭的订单
	 */
	int deleteColosedOrderById(String orderid);
	
	/**
	 * 分页查询购买申请
	 */
	List<BusiOrderVo> selectOrderListByCondition(Page<BusiOrderVo> page);
	
	/**
	 * add by Mr.song 
	 * 查询我的购买记录列表中所有洽谈中的记录
	 * 查询全部符合条件订单 update by wangjunhu
	 */
	List<BusiOrder> selectMyOrdering(HashMap<String,Object> map);
	
	/**
	 * add by Mr.song 2015.3.20
	 * 修改购买记录信息
	 */
	int updateByIdSelective(BusiOrder record);
	
	/**
	 * 更新交易状态
	 */
	int updateOrderState(BusiOrder record);
	
	/**
	 * 更新交易总价
	 */
	int updateDiscountPrice(BusiOrder record);
	
	/**
	 * 分页查询挂牌商品的购买记录
	 */
	List<BusiGoodsOrderVo> selectGoodsOrderList(Page<BusiGoodsOrderVo> page);
	
	/**
	 * 根据主键查询订单信息
	 */
	BusiOrder selectBusiOrderById(String orderid);
	
	/**
	 * 根据品种名称查询订单
	 *
	 * @param map
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年3月31日
	 */
	List<BusiOrderVo> selectOrderListByBreedName(Map<String, Object> map);

	/**
	 * @Description: 订单申诉后改变订单状态
	 * @Author: guoyb
	 * @Date: 2015年4月20日
	 * @param busiOrder
	 * @return
	 */
	int updateOrderAppealState(BusiOrder busiOrder);
	
	/**
	 * 
	 * @Description: 查询全部符合条件的订单
	 * @Author: wangjunhu
	 * @Date: 2015年4月22日
	 * @param record
	 * @return
	 */
	List<BusiOrder> selectBusiOrdersByAttributes(BusiOrder record);
	
	/**
	 * 
	 * @Description: 查询全部已下单状态的72小时过期的订单
	 * @Author: wangjunhu
	 * @Date: 2015年7月1日
	 * @param afterDays
	 * @return
	 */
	List<BusiOrder> selectOverTimeOrdersByPlaced(Integer afterDays);
	
	/**
	 * @Description: WMS冻结成功后，更新订单属性
	 * @Author: 赵航
	 * @Date: 2015年4月30日
	 * @param record
	 * @return
	 */
	int updateOrderByWmsFreezeSuccess(BusiOrder record);
	
	/**
	 * 获取认证名称
	 * @Author:zhouji
	 * @Date: 2015年8月4号
	 * @param userId
	 * @return
	 */
	String selectCertifyName(Integer userId);
	
	/**
	 * 
	 * @Description: 查询即将到期的未付尾款的账期订单id、账期起止时间
	 *               以实现发提醒
	 * @Author: fanyuna
	 * @Date: 2015年9月8日
	 * @param  map里三个key，一个是beforeDays 提前天数，为-1则到期发(因为是过期的第二天发提醒，SQL里是减，减减等于加)
	 *         一个是optype 提醒类型，即将到期提醒或到期提醒
	 *         一个是hms 提醒时间（时分秒）
	 * @return
	 */
	List<Map<String,Object>> selectSoonTermOrder(Map<String,Object> map);
	
	/**
	 * 根据用户ID查询所有订单
	 * @author ldp
	 * @Date 2015年09月21号
	 * @param userId 卖家ID
	 * @return 已下单订单数
	 */
	int selectOrdersCountByUserId(Long userId);
}
