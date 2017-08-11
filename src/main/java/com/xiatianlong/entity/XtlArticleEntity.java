package com.xiatianlong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Article Entity
 * Created by xiatianlong on 2017/2/6.
 */
@Entity(name = "xtl_article")
public class XtlArticleEntity extends XtlBaseEntity {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String introduction;

    /**
     * 封面图片
     */
    private String image;

    /**
     * 状态
     */
    private String status;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 用户信息
     */
    private XtlUserEntity user;

    /**
     *  浏览次数
     */
    private int browseTimes;

    /**
     * 是否推荐
     */
    private boolean recommend;


    /**
     * 获取 标题
     */
    @Column(name = "title", nullable = false, length = 100)
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
     * 获取 内容
     */
    @Column(name = "content", nullable = false)
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
     * 获取 摘要
     */
    @Column(name = "introduction", nullable = false)
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
     * 获取 封面图片
     */
    @Column(name = "image", nullable = true, length = 200)
    public String getImage() {
        return this.image;
    }

    /**
     * 设置 封面图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取 状态
     */
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取 用户ID
     */
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取 用户信息
     * #(fetch = FetchType.LAZY)  懒加载 未测试，默认 fetch = EAGER
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public XtlUserEntity getUser() {
        return this.user;
    }

    /**
     * 设置 用户信息
     */
    public void setUser(XtlUserEntity user) {
        this.user = user;
    }


    /**
     * 获取  浏览次数
     */
    @Column(name = "browse_times", nullable = false, length = 10)
    public int getBrowseTimes() {
        return this.browseTimes;
    }

    /**
     * 设置  浏览次数
     */
    public void setBrowseTimes(int browseTimes) {
        this.browseTimes = browseTimes;
    }

    /**
     * 获取 是否推荐
     */
    @Column(name = "is_recommend", nullable = false)
    public boolean isRecommend() {
        return this.recommend;
    }

    /**
     * 设置 是否推荐
     */
    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
