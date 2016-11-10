package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.UserDetail;
import com.ms.dao.vo.UserDetailVo;

public interface UserDetailService extends ICommonService<UserDetail>{

    public PageInfo<UserDetailVo> findByParams(UserDetailVo userDetailVo,Integer pageNum,Integer pageSize);

    public UserDetailVo findByUserId(Integer userId);

    public void save(UserDetail userDetail);

}
