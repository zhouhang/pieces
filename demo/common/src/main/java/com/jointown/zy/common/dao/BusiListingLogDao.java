package com.jointown.zy.common.dao;

import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiListingLog;
import com.jointown.zy.common.model.BusiWhlist;

public interface BusiListingLogDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    int deleteByPrimaryKey(Long id);
    
    /**
     * 
     * @Description: 保存挂牌日志
     * @Author: 刘漂
     * @Date: 2015年4月18日
     * @param listingInfo
     * @param remark
     * @param operatorId
     * @param optype
     * @return
     */
    public int insertBusiListingLog(BusiListing  listingInfo, String remark, Long operatorId, String optype, boolean...recodeSnapshot);
    
    /**
     * 
     * @Description: 保存挂牌日志
     * @Author: fanyuna
     * @Date: 2015年8月27日
     * @param listingInfo 挂牌信息
     * @param logType  日志类型
     * @param userId 操作用户
     * @param data  日志备注里的参数
     * @return
     */
    public int insertBusiListingLog(BusiListing  listingInfo,BusinessLogEnum logType,Long userId,Object...data);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    int insertListing(BusiListingLog record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    int insertSelective(BusiListingLog record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    BusiListingLog selectByPrimaryKey(Long id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    int updateByPrimaryKeySelective(BusiListingLog record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    int updateByPrimaryKeyWithBLOBs(BusiListingLog record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-03-13 14:20:46
     */
    int updateByPrimaryKey(BusiListingLog record);
}