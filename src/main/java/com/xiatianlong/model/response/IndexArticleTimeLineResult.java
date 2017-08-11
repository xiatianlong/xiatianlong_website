package com.xiatianlong.model.response;

import com.xiatianlong.model.ArticleTimeMonthModel;

import java.util.List;

/**
 * 前端画面时间轴文章列表返回对象
 * Created by xiatianlong on 2017/5/29.
 */
public class IndexArticleTimeLineResult extends AsynchronousResult {

    /**
     * 最后文章的月份日期
     */
    private String lastDate;

    /**
     * 返回数据
     */
    private List<ArticleTimeMonthModel> dataList;


    /**
     * 获取 最后文章的月份日期
     */
    public String getLastDate() {
        return this.lastDate;
    }

    /**
     * 设置 最后文章的月份日期
     */
    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    /**
     * 获取 返回数据
     */
    public List<ArticleTimeMonthModel> getDataList() {
        return this.dataList;
    }

    /**
     * 设置 返回数据
     */
    public void setDataList(List<ArticleTimeMonthModel> dataList) {
        this.dataList = dataList;
    }
}
