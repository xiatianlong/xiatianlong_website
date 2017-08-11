package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * DQ Photos Entity
 * Created by xiatianlong on 2017/7/9.
 */
@Entity(name = "xtl_dq_photos")
public class XtlDQPhotosEntity extends XtlBaseEntity {

    /**
     * 照片名称
     */
    private String photoName;

    /**
     * 照片url
     */
    private String photoUrl;

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
     * 获取 照片名称
     */
    @Column(name = "photo_name", nullable = false, length = 50)
    public String getPhotoName() {
        return this.photoName;
    }

    /**
     * 设置 照片名称
     */
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    /**
     * 获取 照片url
     */
    @Column(name = "photo_url", nullable = false, length = 100)
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    /**
     * 设置 照片url
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
