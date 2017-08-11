package com.xiatianlong.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Note Entity
 * Created by xiatianlong on 2017/5/31.
 */
@Entity(name = "xtl_note_tag")
public class XtlNoteTagEntity extends XtlBaseEntity {

    /**
     * 标签名称
     */
    private String content;

    /**
     * 笔记id
     */
    private Integer noteId;

    /**
     * 笔记对象
     */
    private XtlNoteTagEntity note;


    /**
     * 获取 标签名称
     */
    @Column(name = "content", nullable = false, length = 20)
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 标签名称
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 笔记id
     */
    @Column(name = "noteId", nullable = false, length = 11)
    public Integer getNoteId() {
        return this.noteId;
    }

    /**
     * 设置 笔记id
     */
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    /**
     * 获取 笔记对象
     */
    @ManyToOne
    @JoinColumn(name = "noteId", referencedColumnName = "id", updatable = false, insertable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    public XtlNoteTagEntity getNote() {
        return this.note;
    }

    /**
     * 设置 笔记对象
     */
    public void setNote(XtlNoteTagEntity note) {
        this.note = note;
    }
}
