package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiWarehouseApply;
import com.jointown.zy.common.vo.AreaVo;

public interface BusiWarehouseApplyDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-05-19 21:28:18
     */
    int deleteByPrimaryKey(Long warehouseId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-05-19 21:28:18
     */
    int insert(BusiWarehouseApply record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-05-19 21:28:18
     */
    int insertSelective(BusiWarehouseApply record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-05-19 21:28:18
     */
    BusiWarehouseApply selectByPrimaryKey(Long warehouseId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-05-19 21:28:18
     */
    int updateByPrimaryKeySelective(BusiWarehouseApply record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-05-19 21:28:18
     */
    int updateByPrimaryKey(BusiWarehouseApply record);
    
    /**
     * 
     * @Description: 查询地区，条件查询
     * @Author: wangjunhu
     * @Date: 2015年5月20日
     * @param areaVo
     * @return
     */
    List<AreaVo> selectAreasByCondition(AreaVo record);
    
    /**
     * 获取品种信息
     * @param breedName
     * @return
     */
    Breed getBreedInfo(String breedName);
}