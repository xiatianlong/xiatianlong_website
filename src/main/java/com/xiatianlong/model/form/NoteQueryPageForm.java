package com.xiatianlong.model.form;

/**
 * 分页查询文章表单
 * Created by xiatianlong on 2017/5/27.
 */
public class NoteQueryPageForm {

    /**
     * 要访问的页数
     */
    private int pageNo = 1;

    /**
     * 每页显示的记录数
     */
    private int pageSize = 5;

    /**
     * 关键字
     */
    private String keyWord;

    /**
     * 获取 要访问的页数
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * 设置 要访问的页数
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取 每页显示的记录数
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置 每页显示的记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取 关键字
     */
    public String getKeyWord() {
        return this.keyWord;
    }

    /**
     * 设置 关键字
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
