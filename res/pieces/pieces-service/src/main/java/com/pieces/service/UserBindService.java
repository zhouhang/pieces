package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.UserBind;
import com.pieces.dao.vo.UserBindVo;

public interface UserBindService extends ICommonService<UserBind>{

    public PageInfo<UserBindVo> findByParams(UserBindVo userBindVo,Integer pageNum,Integer pageSize);

    public Integer deleteByTerminalId(Integer terminalId);

    public void saveBind(UserBind userBind);
}
