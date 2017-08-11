package com.xiatianlong.common.enums;

/**
 * 用户状态
 * Created by xiatianlong on 2017/3/22.
 */
public enum UserStatus {

    /**
     * 未激活
     */
    NOT_ACTIVE("040001"),

    /**
     * 正常
     */
    NORMAL("040002"),

    /**
     * 封禁
     */
    BLOCKED("040003");

    private String code;

    private UserStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(String typeCode) {
        return this.code.equals(typeCode);
    }

}
