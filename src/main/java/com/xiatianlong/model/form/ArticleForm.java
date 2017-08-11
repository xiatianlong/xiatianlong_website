package com.xiatianlong.model.form;

/**
 * 文章请求表单对象
 * Created by xiatianlong on 2017/5/27.
 */
public class ArticleForm {

    /**
     * 文章id
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
     * 摘要
     */
    private String introduction;

    /**
     * 封面图片
     */
    private String xtlFile;

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
     * 获取 封面图片
     */
    public String getXtlFile() {
        return this.xtlFile;
    }

    /**
     * 设置 封面图片
     */
    public void setXtlFile(String xtlFile) {
        this.xtlFile = xtlFile;
    }

    /**
     * 获取 文章id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
