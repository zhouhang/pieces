package com.pieces.dao.vo;

import com.pieces.dao.model.User;


/**
 * Created by burgl on 2016/7/6.
 */
public class UserVo  extends User{

    public String startDate;

    public String endDate;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }




}
