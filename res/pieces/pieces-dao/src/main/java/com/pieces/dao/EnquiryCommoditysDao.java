package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.EnquiryCommoditys;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@AutoMapper
public interface EnquiryCommoditysDao extends ICommonDao<EnquiryCommoditys>{

    public List<EnquiryCommoditys> findByBillId(@Param(value = "billId") Integer billId,@Param(value = "pageSize") Integer pageSize);

    public List<EnquiryCommoditys> findByBillId(@Param(value = "userId") Integer userId, @Param(value = "billId") Integer billId,@Param(value = "pageSize") Integer pageSize);

    public void batchCreate(List<EnquiryCommoditys> enquiryCommoditysList);

    /**
     * 批量更新报价信息
     * @param list
     * @return
     */
    public Integer quotedUpdate(List<EnquiryCommoditys> list);


    /**
     * 根据用户ID 查询用户最近询价的商品
     * @param userId
     * @return
     */
    public List<EnquiryCommoditys> findCommoditysByUser(String userId);

    public void deleteByBillId(Integer billId);

	public List<EnquiryCommoditys> findByIds(String ids);
	
}
