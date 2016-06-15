package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.EastGongqiu;
import com.jointown.zy.common.vo.EastGongqiuVo;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: EastGongqiuDao
 * @Description: 东方中药材网供求信息Dao
 * @Author: wangjunhu
 * @Date: 2015年6月12日
 * @Version: 1.0
 */
public interface EastGongqiuDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    int deleteByPrimaryKey(BigDecimal gqid);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    int insert(EastGongqiu record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    int insertSelective(EastGongqiu record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    EastGongqiu selectByPrimaryKey(BigDecimal gqid);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    int updateByPrimaryKeySelective(EastGongqiu record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-06-12 09:32:18
     */
    int updateByPrimaryKey(EastGongqiu record);
    
    /**
     * 后台求购信息管理：查询单条求购信息
     *
     * @param gqid
     * @return
     *
     * @author aizhengdong
     *
     * @data 2015年6月19日
     */
    EastGongqiuVo selectEastGongqiuById(Long gqid);
}