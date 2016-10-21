package com.ms.dao;

import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.UserDetailVo;
import com.ms.dao.config.AutoMapper;

import java.util.List;
@AutoMapper
public interface UserDetailDao extends ICommonDao<UserDetail>{

    public List<UserDetailVo> findByParams(UserDetailVo userDetailVo);

    public UserDetailVo findByUserId(int userId);

}
