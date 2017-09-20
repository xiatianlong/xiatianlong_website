package com.xiatianlong.model.response.xiaochengxu;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序笔记返回对象
 * Created by xiatianlong on 2017/8/22.
 */
public class NoteResultModel implements Serializable {


    private static final long serialVersionUID = 1452329974391391327L;
    /**
     * 笔记物理id
     */
    private int id;

    /**
     * 笔记标题
     */
    private String title;

    /**
     * 笔记标签
     */
    private List<String> tags;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 浏览次数
     */
    private int browseTimes;

    /**
     * 获取 笔记物理id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 笔记物理id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 笔记标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 笔记标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 笔记标签
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * 设置 笔记标签
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
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
     * 获取 浏览次数
     */
    public int getBrowseTimes() {
        return this.browseTimes;
    }

    /**
     * 设置 浏览次数
     */
    public void setBrowseTimes(int browseTimes) {
        this.browseTimes = browseTimes;
    }
}
