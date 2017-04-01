package com.pieces.dao.vo;

import com.pieces.dao.model.UserFollowRecord;

public class UserFollowRecordVo extends UserFollowRecord{

    private String followerName;

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

}