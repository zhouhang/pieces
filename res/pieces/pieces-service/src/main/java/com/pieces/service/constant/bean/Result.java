package com.pieces.service.constant.bean;

/**
 * Created by wangbin on 2016/7/4.
 */
public class Result {

    private Integer status;
    private String msg ="";
    private Object data = new Object();

    public Result(Boolean status) {
        if(status){
            this.status = 0;
        }
    }

    public Result(Integer status) {
        this.status = status;
    }
    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }
    public Result data(Object data) {
        this.data = data;
        return this;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
