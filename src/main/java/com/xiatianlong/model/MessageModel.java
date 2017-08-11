package com.xiatianlong.model;

/**
 * Message Model
 * Created by xiatianlong on 2017/6/4.
 */
public class MessageModel {

    /**
     * id
     */
    private int id;

    /**
     * 留言ip
     */
    private String ip;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 联系方式
     */
    private String contactInfomation;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 是否已读
     */
    private boolean read;

    /**
     * create time
     */
    private String createTime;

    /**
     * modify time
     */
    private String modifyTime;


    /**
     * 获取 id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 留言ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置 留言ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取 ip地址
     */
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * 设置 ip地址
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
     * 获取 联系方式
     */
    public String getContactInfomation() {
        return this.contactInfomation;
    }

    /**
     * 设置 联系方式
     */
    public void setContactInfomation(String contactInfomation) {
        this.contactInfomation = contactInfomation;
    }

    /**
     * 获取 留言内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 留言内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 是否已读
     */
    public boolean isRead() {
        return this.read;
    }

    /**
     * 设置 是否已读
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * 获取 create time
     */
    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 create time
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 modify time
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 设置 modify time
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
