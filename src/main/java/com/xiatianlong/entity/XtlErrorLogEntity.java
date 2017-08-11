package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Error Log Entity
 * Created by xiatianlong on 2017/06/21.
 */
@Entity(name = "xtl_error_log")
public class XtlErrorLogEntity extends XtlBaseEntity {

    /**
     * ip
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * Email
     */
    private String info;


    /**
     * 获取 ip
     */
    @Column(name = "ip", nullable = true, length = 50)
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置 ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取 url
     */
    @Column(name = "url", nullable = true, length = 200)
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取 Email
     */
    @Column(name = "info", nullable = true)
    public String getInfo() {
        return this.info;
    }

    /**
     * 设置 Email
     */
    public void setInfo(String info) {
        this.info = info;
    }
}
