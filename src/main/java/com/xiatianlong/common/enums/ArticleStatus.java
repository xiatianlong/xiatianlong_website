package com.xiatianlong.common.enums;

/**
 * 文章状态
 * Created by xiatianlong on 2017/2/11.
 */
public enum ArticleStatus {

    /**
     * 上线
     */
    SHOW("020001"),

    /**
     * 下线
     */
    HIDE("020002"),

    /**
     * 删除
     */
    DELETE("020003");

    private String code;

    private ArticleStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(String typeCode) {
        return this.code.equals(typeCode);
    }


}
