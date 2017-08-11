package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * User Entity
 * Created by xiatianlong on 2017/1/15.
 */
@Entity(name = "xtl_user")
public class XtlUserEntity extends XtlBaseEntity {

    /**
     * User name
     */
    private String userName;

    /**
     * Password
     */
    private String password;

    /**
     * Email
     */
    private String email;

    /**
     * Phone
     */
    private String phone;

    /**
     * qq
     */
    private String qq;

    /**
     * role code
     */
    private String roleCode;

    /**
     * Status
     */
    private String status;

    /**
     * head img
     */
    private String img;

    /**
     * Last login time
     */
    private Date lastLoginTime;

    /**
     * 获取 User name
     */
    @Column(name = "username", nullable = false, length = 30, unique = true)
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置 User name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取 password
     */
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置 password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 email
     */
    @Column(name = "email", nullable = false, length = 50, unique = true)
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置 email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取 phone
     */
    @Column(name = "phone", nullable = true, length = 15)
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置 phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取 qq
     */
    @Column(name = "qq", nullable = true, length = 15)
    public String getQq() {
        return this.qq;
    }

    /**
     * 设置 qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取 role code
     */
    @Column(name = "role_code", nullable = false, length = 10)
    public String getRoleCode() {
        return this.roleCode;
    }

    /**
     * 设置 role code
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }


    /**
     * 获取 Status
     */
    @Column(name = "status", nullable = false, length = 10)
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置 Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取 Last login time
     */
    @Column(name = "last_login_time", nullable = true)
    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    /**
     * 设置 Last login time
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取 head img
     */
    @Column(name = "img", nullable = true)
    public String getImg() {
        return this.img;
    }

    /**
     * 设置 head img
     */
    public void setImg(String img) {
        this.img = img;
    }
}
