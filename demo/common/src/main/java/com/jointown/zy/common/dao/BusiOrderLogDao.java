package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderLog;

public interface BusiOrderLogDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 添加订单日志
     * @Author: 赵航
     * @Date: 2015年4月17日
     * @param orderInfo 订单信息
     * @param remark 日志备注信息
     * @param operatorId 操作者ID
     * @param optype 操作类型
     * @return
     */
    int insertBusiOrderLog(BusiOrder  orderInfo, String remark, Long operatorId, String optype);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    int insertSelective(BusiOrderLog record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    BusiOrderLog selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    int updateByPrimaryKeySelective(BusiOrderLog record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    int updateByPrimaryKeyWithBLOBs(BusiOrderLog record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-13 14:20:48
     */
    int updateByPrimaryKey(BusiOrderLog record);
    
    /**
     * @Description: 根据订单Id查询订单的日志列表
     * @Author: 赵航
     * @Date: 2015年8月18日
     * @param orderId 订单Id
     * @param sortFlg 排序Flag，true（按日志创建时间升序），false（按日志创建时间降序）
     * @return 订单日志列表
     */
    List<BusiOrderLog> selectOrderLogList(String orderId, boolean sortFlg);
}