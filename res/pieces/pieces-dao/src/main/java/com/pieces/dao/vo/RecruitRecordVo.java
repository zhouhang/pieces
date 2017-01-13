package com.pieces.dao.vo;

import com.pieces.dao.model.RecruitRecord;

public class RecruitRecordVo extends RecruitRecord{

    /**
     * 跟踪姓名
     */
    private String followName;

    public String getFollowName() {
        return followName;
    }

    public void setFollowName(String followName) {
        this.followName = followName;
    }
}