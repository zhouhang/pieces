package com.pieces.dao.vo;

import com.pieces.dao.model.Ad;

/**
 * Created by wangbin on 2016/8/3.
 */
public class AdVo extends Ad{

    private String typeName;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
