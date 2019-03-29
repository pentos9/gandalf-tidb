package com.spacex.gandalf.enums;

public enum DeleteStatusEnum {
    NOT_DELETED(0), DELETED(1);

    private int code;

    DeleteStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
