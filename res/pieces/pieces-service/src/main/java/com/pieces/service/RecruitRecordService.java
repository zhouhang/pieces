package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.RecruitRecord;
import com.pieces.dao.vo.RecruitRecordVo;

import java.util.List;

public interface RecruitRecordService extends ICommonService<RecruitRecord>{

    public PageInfo<RecruitRecordVo> findByParams(RecruitRecordVo recruitRecordVo, Integer pageNum, Integer pageSize);

    public List<RecruitRecordVo> findByRecruitId(Integer recruitAgentId);
    public void save(RecruitRecordVo recruitRecordVo);
}
