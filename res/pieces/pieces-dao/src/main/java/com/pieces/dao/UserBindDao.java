package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.UserBind;
import com.pieces.dao.vo.UserBindVo;

import java.util.List;
@AutoMapper
public interface UserBindDao extends ICommonDao<UserBind>{

    public List<UserBindVo> findByParams(UserBindVo userBindVo);

}
