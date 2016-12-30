package com.pieces.boss.controller.exception;

import com.pieces.tools.exception.PiecesBaseException;

/**
 * Created by wangbin on 2016/12/28.
 */
public class FormRepeatException extends PiecesBaseException {

    public FormRepeatException(){
        super();
    }

    public FormRepeatException(String message){
        super(message);
    }

    public FormRepeatException(Throwable cause) {
        super(cause);
    }

    public FormRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
