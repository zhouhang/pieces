package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.AnonFollowRecord;
import com.pieces.dao.vo.AnonFollowRecordVo;

import java.util.List;
@AutoMapper
public interface AnonFollowRecordDao extends ICommonDao<AnonFollowRecord>{

    public List<AnonFollowRecordVo> findByParams(AnonFollowRecordVo anonFollowRecordVo);

}
