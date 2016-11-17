package com.pieces.dao.vo;

import com.pieces.dao.model.AnonFollowRecord;

public class AnonFollowRecordVo extends AnonFollowRecord{

    private String followerName;

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }
}