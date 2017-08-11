package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Message Entity
 * Created by xiatianlong on 2017/6/2.
 */
@Entity(name = "xtl_message")
public class XtlMessageEntity extends XtlBaseEntity {

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
     * 获取 留言ip
     */
    @Column(name = "ip", nullable = true, length = 50)
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
    @Column(name = "ip_address", nullable = true, length = 50)
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
    @Column(name = "user_name", nullable = false, length = 50)
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
    @Column(name = "contact_information", nullable = true, length = 50)
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
    @Column(name = "content", nullable = false)
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
    @Column(name = "is_read", nullable = false)
    public boolean isRead() {
        return this.read;
    }

    /**
     * 设置 是否已读
     */
    public void setRead(boolean read) {
        this.read = read;
    }
}
