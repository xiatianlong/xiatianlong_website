package com.xiatianlong.common.enums;

/**
 * 导航菜单
 * Created by xiatianlong on 2017/3/20.
 */
public enum NavbarKey {
    /**
     * 主页
     */
    HOME("030001"),

    /**
     * 文章
     */
    ARTICLE("030002"),

    /**
     * 笔记
     */
    NOTE("030003"),

    /**
     * 杜琪
     */
    DQ("030004"),

    /**
     * 关于
     */
    ABOUT("030005");



    private String code;

    private NavbarKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(String typeCode) {
        return this.code.equals(typeCode);
    }
}
