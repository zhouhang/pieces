package com.pieces.tools.exception;

/**
 * Created by wangbin on 2016/12/28.
 */
public class PiecesBaseException extends RuntimeException{

    public PiecesBaseException(){
        super();
    }

    public PiecesBaseException(String message){
        super(message);
    }

    public PiecesBaseException(Throwable cause) {
        super(cause);
    }

    public PiecesBaseException(String message, Throwable cause) {
        super(message, cause);
    }


}
