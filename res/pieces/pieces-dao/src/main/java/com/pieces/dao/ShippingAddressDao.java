package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.vo.ShippingAddressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@AutoMapper
public interface ShippingAddressDao extends ICommonDao<ShippingAddress>{

    public List<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo);

    public void deleteByUserIdAndId(@Param("userId")Integer userId, @Param("id") Integer addressId);
}
