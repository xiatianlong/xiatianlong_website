package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * DQ Message Entity
 * Created by xiatianlong on 2017/7/9.
 */
@Entity(name = "xtl_dq_message")
public class XtlDQMessageEntity extends XtlBaseEntity {

    /**
     * 留言内容
     */
    private String text;

    /**
     * 是否删除
     */
    private boolean delete;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户信息
     */
    private XtlUserEntity user;


    /**
     * 获取 留言内容
     */
    @Column(name = "text", nullable = false)
    public String getText() {
        return this.text;
    }

    /**
     * 设置 留言内容
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * 获取 是否删除
     */
    @Column(name = "is_delete", nullable = false)
    public boolean isDelete() {
        return this.delete;
    }

    /**
     * 设置 是否删除
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * 获取 用户ID
     */
    @Column(name = "user_id", length = 11)
    public int getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取 用户信息
     */
    /**
     * 获取 用户信息
     * #(fetch = FetchType.LAZY)  懒加载 未测试，默认 fetch = EAGER
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public XtlUserEntity getUser() {
        return this.user;
    }

    /**
     * 设置 用户信息
     */
    public void setUser(XtlUserEntity user) {
        this.user = user;
    }
}
