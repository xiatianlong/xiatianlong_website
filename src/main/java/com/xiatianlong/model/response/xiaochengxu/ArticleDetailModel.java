package com.xiatianlong.model.response.xiaochengxu;

/**
 * 详情页封装
 * Created by xiatianlong on 2017/9/20.
 */
public class ArticleDetailModel {

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String userName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 内容
     */
    private String content;


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
     * 获取 作者
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置 作者
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * 获取 内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}
