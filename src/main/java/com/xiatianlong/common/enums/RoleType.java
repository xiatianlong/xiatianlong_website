package com.xiatianlong.common.enums;

/**
 * 角色类型
 * Created by xiatianlong on 2017/1/15.
 */
public enum RoleType {

    /**
     * 管理员
     */
    ADMIN("010001"),

    /**
     * 用户
     */
    USER("010002"),

    /**
     * DQ用户
     */
    DQ("010003");



    private String code;

    private RoleType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(String typeCode) {
        return this.code.equals(typeCode);
    }

}
