package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.ShippingAddress;
import com.pieces.dao.vo.ShippingAddressVo;

public interface ShippingAddressService extends ICommonService<ShippingAddress>{

    public PageInfo<ShippingAddressVo> findByParams(ShippingAddressVo shippingAddressVo, Integer pageNum, Integer pageSize);
}
