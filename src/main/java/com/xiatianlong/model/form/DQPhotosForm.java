package com.xiatianlong.model.form;

/**
 * DQ 照片请求form
 * Created by xiatianlong on 2017/8/19.
 */
public class DQPhotosForm {

    /**
     * 照片URL
     */
    private String[] photoUrl;

    /**
     * 照片名称
     */
    private String[] photoName;


    /**
     * 获取 照片URL
     */
    public String[] getPhotoUrl() {
        return this.photoUrl;
    }

    /**
     * 设置 照片URL
     */
    public void setPhotoUrl(String[] photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取 照片名称
     */
    public String[] getPhotoName() {
        return this.photoName;
    }

    /**
     * 设置 照片名称
     */
    public void setPhotoName(String[] photoName) {
        this.photoName = photoName;
    }
}
