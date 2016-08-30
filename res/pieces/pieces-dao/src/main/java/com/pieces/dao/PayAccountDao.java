package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.vo.PayAccountVo;

import java.util.List;
@AutoMapper
public interface PayAccountDao extends ICommonDao<PayAccount>{

    public List<PayAccountVo> findByParams(PayAccountVo payAccountVo);

}
