package com.xiatianlong.model.form;

/**
 * 文章请求表单对象
 * Created by xiatianlong on 2017/6/01.
 */
public class IndexNoteQueryForm {

    /**
     * 笔记最后一条记录的创建时间
     */
    private String lastNoteCreateTime;

    /**
     *  关键字
     */
    private String keyword;

    /**
     * 标签聚合
     */
    private String[] tags;

    /**
     * 获取 笔记最后一条记录的创建时间
     */
    public String getLastNoteCreateTime() {
        return this.lastNoteCreateTime;
    }

    /**
     * 设置 笔记最后一条记录的创建时间
     */
    public void setLastNoteCreateTime(String lastNoteCreateTime) {
        this.lastNoteCreateTime = lastNoteCreateTime;
    }

    /**
     * 获取  关键字
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * 设置  关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

