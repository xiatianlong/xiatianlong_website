package com.xiatianlong.common.enums;

/**
 * DQ导航菜单
 * Created by xiatianlong on 2017/7/30.
 */
public enum DQNavbarKey {
    /**
     * 主页
     */
    HOME("050001"),

    /**
     * 文章
     */
    MESSAGE("050002"),

    /**
     * 笔记
     */
    PHOTOS("050003");

    private String code;

    private DQNavbarKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(String typeCode) {
        return this.code.equals(typeCode);
    }
}
