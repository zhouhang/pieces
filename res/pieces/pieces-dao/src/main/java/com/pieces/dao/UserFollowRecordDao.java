package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.UserFollowRecord;
import com.pieces.dao.vo.UserFollowRecordVo;

import java.util.List;
@AutoMapper
public interface UserFollowRecordDao extends ICommonDao<UserFollowRecord>{

    public List<UserFollowRecordVo> findByParams(UserFollowRecordVo userFollowRecordVo);

}
