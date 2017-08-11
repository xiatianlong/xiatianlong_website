package com.xiatianlong.model.form;

/**
 * 文章请求表单对象
 * Created by xiatianlong on 2017/6/01.
 */
public class NoteForm {

    /**
     * 笔记id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签聚合
     */
    private String[] tags;


    /**
     * 获取 笔记id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 笔记id
     */
    public void setId(Integer id) {
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

    /**
     * 获取 标签聚合
     */
    public String[] getTags() {
        return this.tags;
    }

    /**
     * 设置 标签聚合
     */
    public void setTags(String[] tags) {
        this.tags = tags;
    }
}

