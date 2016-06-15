package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.WxSupplyPic;
import com.jointown.zy.common.vo.WxSupplyPicVo;

/**
 * 
 * @ClassName: WxSupplyPicDao
 * @Description: 供应信息图片Dao
 * @Author: wangjunhu
 * @Date: 2015年4月12日
 * @Version: 1.0
 */
public interface WxSupplyPicDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    int deleteByPrimaryKey(Long supplyPicId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    int insert(WxSupplyPic record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    int insertSelective(WxSupplyPic record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    WxSupplyPic selectByPrimaryKey(Long supplyPicId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    int updateByPrimaryKeySelective(WxSupplyPic record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    int updateByPrimaryKey(WxSupplyPic record);
    
    /**
     * 通过查询珍药材网第一张图片picpath值，得到supply_id的值
     * @param picOne
     * @return
     */
    WxSupplyPic selectSupplyIdBySupplyPic(String picOne);
    
    /**
     * 查询东网与珍药材的供应信息中的图片
     * @param supply_id
     * @return
     */
    List<WxSupplyPic> selectAllBySupplyPic(Long supply_id);
}