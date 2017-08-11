package com.xiatianlong.entity.id;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class XtlArticleNoteShowUnionAllViewId implements Serializable {


    /**
     * 类型：article;note
     */
    private String type;

    /**
     * id
     */
    private int id;


    /**
     * 获取 类型：article;note
     */
    @Column(name = "type")
    public String getType() {
        return this.type;
    }

    /**
     * 设置 类型：article;note
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取 id
     */
    @Column(name = "id")
    public int getId() {
        return this.id;
    }

    /**
     * 设置 id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XtlArticleNoteShowUnionAllViewId that = (XtlArticleNoteShowUnionAllViewId) o;
        return id == that.id &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }
}
