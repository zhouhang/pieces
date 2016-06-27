package com.pieces.tools.exception;

/**
 * Created by wangbin on 2016/6/27.
 */
public class FileUploadException extends RuntimeException{

    public FileUploadException(){
        super();
    }

    public FileUploadException(String message){
        super(message);
    }
}
