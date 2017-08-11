package com.xiatianlong.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Note Entity
 * Created by xiatianlong on 2017/5/31.
 */
@Entity(name = "xtl_note")
public class XtlNoteEntity extends XtlBaseEntity {

    /**
     * 笔记标题
     */
    private String title;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 状态
     */
    private String status;

    /**
     * 标签集合
     */
    private List<XtlNoteTagEntity> tags;

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
     * 获取 笔记标题
     */
    @Column(name = "title", nullable = false, length = 100)
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
     * 获取 笔记内容
     */
    @Column(name = "content", nullable = false)
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 笔记内容
     */
    public void setContent(String content) {
        this.content = content;
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
     * 获取 标签集合
     */

    @OneToMany(mappedBy = "note")
    public List<XtlNoteTagEntity> getTags() {
        return this.tags;
    }

    /**
     * 设置 标签集合
     */
    public void setTags(List<XtlNoteTagEntity> tags) {
        this.tags = tags;
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
