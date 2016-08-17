package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.vo.ShippingAddressVo;

import java.util.List;

public interface ShippingAddressService extends ICommonService<ShippingAddress>{

    public PageInfo<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo, Integer pageNum, Integer pageSize);

    public List<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo);

    public List<ShippingAddressVo> findByUser(Integer userId);

    public void delete(int userId,int id);
}
