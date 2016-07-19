package com.pieces.service.enums;

/**
 * Author: koabs
 * 7/19/16.
 */
public enum StatusEnum {

    delete(-1),disable(0),enable(1);

    private StatusEnum(Integer value) {
        this.value = value;
    }

    private Integer value;
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
}
