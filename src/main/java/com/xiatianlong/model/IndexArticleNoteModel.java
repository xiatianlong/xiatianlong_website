package com.xiatianlong.model;

import java.util.List;

/**
 * 首页文章笔记model
 * Created by xiatianlong on 2017/6/8.
 */
public class IndexArticleNoteModel {

    /**
     * 类型：article;note
     */
    private String type;

    /**
     * id
     */
    private int id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String image;

    /**
     * 摘要
     */
    private String introduction;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 浏览次数
     */
    private int browswTimes;

    /**
     * 是否推荐
     */
    private boolean recommend;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 笔记标签集合
     */
    private List<String> tags;


    /**
     * 获取 类型：article;note
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置 类型：article;note
     */
    public void setType(String type) {
        this.type = type;
    }

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
     * 获取 标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 封面图
     */
    public String getImage() {
        return this.image;
    }

    /**
     * 设置 封面图
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取 摘要
     */
    public String getIntroduction() {
        return this.introduction;
    }

    /**
     * 设置 摘要
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取 用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * 获取 邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取 浏览次数
     */
    public int getBrowswTimes() {
        return this.browswTimes;
    }

    /**
     * 设置 浏览次数
     */
    public void setBrowswTimes(int browswTimes) {
        this.browswTimes = browswTimes;
    }

    /**
     * 获取 是否推荐
     */
    public boolean isRecommend() {
        return this.recommend;
    }

    /**
     * 设置 是否推荐
     */
    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
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
     * 获取 笔记标签集合
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * 设置 笔记标签集合
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
