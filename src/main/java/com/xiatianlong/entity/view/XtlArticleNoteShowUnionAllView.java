package com.xiatianlong.entity.view;

import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.entity.id.XtlArticleNoteShowUnionAllViewId;

import javax.persistence.*;
import java.util.Date;

/**
 * Article and Note Union All View
 * Created by xiatianlong on 2017/6/07.
 */
@Entity(name = "xtl_v_article_note_show_union_all")
@IdClass(XtlArticleNoteShowUnionAllViewId.class)
public class XtlArticleNoteShowUnionAllView {

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
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String introduction;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户对象
     */
    private XtlUserEntity user;

    /**
     * 浏览次数
     */
    private int browsTimes;

    /**
     * 是否推荐
     */
    private boolean recommend;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 获取 类型：article;note
     */
    @Id
    @Column(name = "type")
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
    @Id
    @Column(name = "id")
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
    @Column(name = "title")
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
    @Column(name = "image")
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
     * 获取 内容
     */
    @Column(name = "content")
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
    @Column(name = "introduction")
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
    @Column(name = "user_id")
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
     * 获取 用户对象
     */
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public XtlUserEntity getUser() {
        return this.user;
    }

    /**
     * 设置 用户对象
     */
    public void setUser(XtlUserEntity user) {
        this.user = user;
    }

    /**
     * 获取 浏览次数
     */
    @Column(name = "browse_times")
    public int getBrowsTimes() {
        return this.browsTimes;
    }

    /**
     * 设置 浏览次数
     */
    public void setBrowsTimes(int browsTimes) {
        this.browsTimes = browsTimes;
    }

    /**
     * 获取 是否推荐
     */
    @Column(name = "is_recommend")
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
    @Column(name = "create_time")
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
