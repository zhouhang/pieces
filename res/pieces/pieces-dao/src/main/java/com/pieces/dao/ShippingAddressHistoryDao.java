package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.dao.vo.ShippingAddressHistoryVo;

import java.util.List;
@AutoMapper
public interface ShippingAddressHistoryDao extends ICommonDao<ShippingAddressHistory>{

    public List<ShippingAddressHistoryVo> findByParams(ShippingAddressHistoryVo shippingAddressHistoryVo);

}
