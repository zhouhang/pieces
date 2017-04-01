package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.UserFollowRecord;
import com.pieces.dao.vo.UserFollowRecordVo;

import java.util.List;

public interface UserFollowRecordService extends ICommonService<UserFollowRecord>{

    public PageInfo<UserFollowRecordVo> findByParams(UserFollowRecordVo userFollowRecordVo,Integer pageNum,Integer pageSize);

    public List<UserFollowRecordVo> findByUserId(Integer userId);
}
