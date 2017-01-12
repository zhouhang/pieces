package com.pieces.dao.vo;

import com.pieces.dao.model.RecruitAgent;

import java.util.Date;

public class RecruitAgentVo extends RecruitAgent{

    /**
     * 最后跟进人信息
     */
    private String lastFollowName;

    private Date publishTimeStart;

    private Date publishTimeEnd;

    private Date lastFollowTimeStart;

    private Date lastFollowTimeEnd;

    /**
     * 区域信息
     */
    private String area;


    public String getLastFollowName() {
        return lastFollowName;
    }

    public void setLastFollowName(String lastFollowName) {
        this.lastFollowName = lastFollowName;
    }

    public Date getPublishTimeStart() {
        return publishTimeStart;
    }

    public void setPublishTimeStart(Date publishTimeStart) {
        this.publishTimeStart = publishTimeStart;
    }

    public Date getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(Date publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd;
    }

    public Date getLastFollowTimeStart() {
        return lastFollowTimeStart;
    }

    public void setLastFollowTimeStart(Date lastFollowTimeStart) {
        this.lastFollowTimeStart = lastFollowTimeStart;
    }

    public Date getLastFollowTimeEnd() {
        return lastFollowTimeEnd;
    }

    public void setLastFollowTimeEnd(Date lastFollowTimeEnd) {
        this.lastFollowTimeEnd = lastFollowTimeEnd;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}