package com.pieces.dao;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.RecruitRecord;
import com.pieces.dao.vo.RecruitRecordVo;

import java.util.List;
@AutoMapper
public interface RecruitRecordDao extends ICommonDao<RecruitRecord>{

    public List<RecruitRecordVo> findByParams(RecruitRecordVo recruitRecordVo);

}
