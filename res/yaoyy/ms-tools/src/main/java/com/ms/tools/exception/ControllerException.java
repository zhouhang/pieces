package com.ms.tools.exception;

import com.ms.tools.entity.ResultStatus;

/**
 * Author: koabs
 * 11/7/16.
 */
public class ControllerException extends RuntimeException {

    private Integer status = ResultStatus.Error;

    public ControllerException(String message) {
        super(message);
    }
}
