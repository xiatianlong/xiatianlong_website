package com.xiatianlong.common.enums;

/**
 * 笔记状态
 * Created by xiatianlong on 2017/06/01.
 */
public enum NoteStatus {

    /**
     * 上线
     */
    SHOW("021001"),

    /**
     * 下线
     */
    HIDE("021002"),

    /**
     * 删除
     */
    DELETE("021003");

    private String code;

    private NoteStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(String typeCode) {
        return this.code.equals(typeCode);
    }


}
