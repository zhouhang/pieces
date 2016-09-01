package com.pieces.dao.exception;

/**
 * Created by wangbin on 2016/9/1.
 */
public class SmsOverException extends RuntimeException{

    public SmsOverException(){
        super();
    }

    public SmsOverException(String message){
        super(message);
    }

}
