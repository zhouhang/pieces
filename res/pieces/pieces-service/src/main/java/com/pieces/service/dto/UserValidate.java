package com.pieces.service.dto;

/**
 * Author: koabs
 * 2/23/17.
 */
public class UserValidate {

    // 200 成功 1 未提交资质审核 2 正在进行资质审核 3 资质审核未通过 4 代理商未绑定终端用户
    private Integer status;

    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserValidate(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
