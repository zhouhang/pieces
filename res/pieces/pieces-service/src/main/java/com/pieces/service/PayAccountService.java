package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.vo.PayAccountVo;

public interface PayAccountService extends ICommonService<PayAccount>{

    public PageInfo<PayAccountVo> findByParams(PayAccountVo payAccountVo, Integer pageNum, Integer pageSize);
}
