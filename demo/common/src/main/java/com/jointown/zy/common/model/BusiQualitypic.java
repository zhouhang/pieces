package com.jointown.zy.common.model;

import java.io.Serializable;

public class BusiQualitypic implements Serializable {
    /**
     * ZYC.BUSI_QUALITYPIC.QCID (质检图片主键ID)
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    private Long qcid;

    /**
     * ZYC.BUSI_QUALITYPIC.WLID (仓单ID)
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    private String wlid;

    /**
     * ZYC.BUSI_QUALITYPIC.ISFILE (是否是附件)
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    private Short isfile;

    /**
     * ZYC.BUSI_QUALITYPIC.PATH (相对路径路径)
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    private String path;

    /**
     * ZYC.BUSI_QUALITYPIC.PICINDEX (图片排序(散货1，细节2，包装3，堆垛4))
     * @ibatorgenerated 2014-12-25 19:15:47
     */
    private Short picindex;

    public Long getQcid() {
        return qcid;
    }

    public void setQcid(Long qcid) {
        this.qcid = qcid;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public Short getIsfile() {
        return isfile;
    }

    public void setIsfile(Short isfile) {
        this.isfile = isfile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Short getPicindex() {
        return picindex;
    }

    public void setPicindex(Short picindex) {
        this.picindex = picindex;
    }
}