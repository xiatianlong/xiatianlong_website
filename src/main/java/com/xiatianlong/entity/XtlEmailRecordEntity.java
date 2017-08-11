package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * XtlEmailRecordEntity
 * Created by xiatianlong on 2017/3/22.
 */
@Entity(name = "xtl_email_record")
public class XtlEmailRecordEntity extends XtlBaseEntity {

    /**
     * 发送对象
     */
    private String toEmail;

    /**
     * 代码
     */
    private String code;

    /**
     * 是否成功 默认1
     */
    private boolean success;


    /**
     * 获取 发送对象
     */
    @Column(name = "to_email", nullable = false, length = 100)
    public String getToEmail() {
        return this.toEmail;
    }

    /**
     * 设置 发送对象
     */
    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    /**
     * 获取 代码
     */
    @Column(name = "code", nullable = false, length = 100)
    public String getCode() {
        return this.code;
    }

    /**
     * 设置 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取 是否成功 默认1
     */
    @Column(name = "is_success", nullable = false)
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * 设置 是否成功 默认1
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
