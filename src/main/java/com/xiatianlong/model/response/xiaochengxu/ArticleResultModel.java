package com.xiatianlong.model.response.xiaochengxu;

import java.io.Serializable;

/**
 * 小程序文章返回对象
 * Created by xiatianlong on 2017/8/22.
 */
public class ArticleResultModel implements Serializable {
    private static final long serialVersionUID = -5100185835589307972L;

    /**
     * 文章物理id
     */
    private int id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章图片
     */
    private String image;

    /**
     * 文章摘要
     */
    private String introduction;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 浏览次数
     */
    private int browseTimes;


    /**
     * 获取 文章物理id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 文章物理id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 文章标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取 文章图片
     */
    public String getImage() {
        return this.image;
    }

    /**
     * 设置 文章图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取 文章摘要
     */
    public String getIntroduction() {
        return this.introduction;
    }

    /**
     * 设置 文章摘要
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
