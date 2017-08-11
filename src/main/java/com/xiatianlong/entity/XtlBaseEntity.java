package com.xiatianlong.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Base Endtity
 * Created by xiatianlong on 2017/1/15.
 */
@MappedSuperclass
public class XtlBaseEntity implements Serializable {

    /**
     * id
     */
    private int id;

    /**
     * create time
     */
    private Date createTime = new Date();

    /**
     * modify time
     */
    private Date modifyTime;


    /**
     * 获取 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 10)
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
     * 获取 create time
     */
    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 modify time
     */
    @Column(name = "modify_time", nullable = false)
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 设置 modify time
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
