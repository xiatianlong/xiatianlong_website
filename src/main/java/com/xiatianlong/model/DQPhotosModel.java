package com.xiatianlong.model;

/**
 * DQ 相册model
 * Created by xiatianlong on 2017/8/18.
 */
public class DQPhotosModel {

    /**
     * 图片url
     */
    private String photoUrl;

    /**
     * 图片缩略图url
     */
    private String photoThumbnailUrl;

    /**
     * 图片备注
     */
    private String photoMemo;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String userName;

    /**
     * 是否删除
     */
    private boolean delete;


    /**
     * 获取 图片url
     */
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    /**
     * 设置 图片url
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取 图片缩略图url
     */
    public String getPhotoThumbnailUrl() {
        return this.photoThumbnailUrl;
    }

    /**
     * 设置 图片缩略图url
     */
    public void setPhotoThumbnailUrl(String photoThumbnailUrl) {
        this.photoThumbnailUrl = photoThumbnailUrl;
    }

    /**
     * 获取 图片备注
     */
    public String getPhotoMemo() {
        return this.photoMemo;
    }

    /**
     * 设置 图片备注
     */
    public void setPhotoMemo(String photoMemo) {
        this.photoMemo = photoMemo;
    }

    /**
     * 获取 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 创建人
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置 创建人
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取 是否删除
     */
    public boolean isDelete() {
        return this.delete;
    }

    /**
     * 设置 是否删除
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
