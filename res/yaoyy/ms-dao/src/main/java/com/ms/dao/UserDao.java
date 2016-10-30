package com.ms.dao;

import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;
import com.ms.dao.config.AutoMapper;

import java.util.List;
@AutoMapper
public interface UserDao extends ICommonDao<User>{

    public List<UserVo> findByParams(UserVo userVo);

    public UserVo findByPhone(String phone);

    public UserVo findByOpenId(String openId);


}
