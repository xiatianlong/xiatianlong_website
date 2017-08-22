package com.xiatianlong.model;

/**
 * DQ 留言板model
 * Created by xiatianlong on 2017/8/20.
 */
public class DQMessageModel {

    /**
     * 留言id
     */
    private Integer id;
    /**
     * 内容
     */
    private String content;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像
     */
    private String userImg;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 显示删除
     */
    private boolean showDelete;


    /**
     * 获取 内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 用户名
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取 头像
     */
    public String getUserImg() {
        return this.userImg;
    }

    /**
     * 设置 头像
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
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
     * 获取 留言id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 留言id
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * 获取 显示删除
     */
    public boolean isShowDelete() {
        return this.showDelete;
    }

    /**
     * 设置 显示删除
     */
    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }
}
