package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;

public interface UserService extends ICommonService<User>{

    public PageInfo<UserVo> findByParams(UserVo userVo,Integer pageNum,Integer pageSize);

    public UserVo findByPhone(UserVo userVo);
}
