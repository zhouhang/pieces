package com.pieces.service.enums;

/**
 * Author: koabs
 * 11/16/16.
 */
public enum AnonEnquiryEnum {

    TODO(1),COMPLETE(2);

    private Integer value;

    AnonEnquiryEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
