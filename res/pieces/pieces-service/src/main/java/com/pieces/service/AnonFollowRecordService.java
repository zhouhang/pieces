package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.AnonFollowRecord;
import com.pieces.dao.vo.AnonFollowRecordVo;

public interface AnonFollowRecordService extends ICommonService<AnonFollowRecord>{

    public PageInfo<AnonFollowRecordVo> findByParams(AnonFollowRecordVo anonFollowRecordVo,Integer pageNum,Integer pageSize);
}
