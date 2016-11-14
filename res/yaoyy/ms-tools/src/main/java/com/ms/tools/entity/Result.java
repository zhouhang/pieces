package com.ms.tools.entity;


import java.io.Serializable;

/**
 * 返回结果封装
 */
public class Result implements Serializable {

    private int status;

    private String msg ="";


    private Object data = null;



    public Result(int status) {
        this.status = status;
    }

    public Result() {
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }


    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public static Result success() {
        Result result = new Result();
        result.setStatus(ResultStatus.Success);
        return result;
    }

    public static Result success(String info) {
        Result result = new Result();
        result.setMsg(info);
        result.setStatus(ResultStatus.Success);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(ResultStatus.Error);
        return result;
    }

    public static Result noLogin() {
        Result result = new Result();
        result.setStatus(ResultStatus.Not_Login);
        return result;
    }

    public static Result failVerification() {
        Result result = new Result();
        result.setStatus(ResultStatus.ERROR_VERIFICATION);
        return result;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
