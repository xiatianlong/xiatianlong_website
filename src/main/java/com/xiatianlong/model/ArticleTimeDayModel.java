package com.xiatianlong.model;

import java.util.List;

/**
 * 文章详情模型(时间轴)
 * Created by xiatianlong on 2017/5/28.
 */
public class ArticleTimeDayModel {

    /**
     * 时间戳
     */
    private String day;

    /**
     * 文章对象集合
     */
    private List<ArticleModel> dataList;


    /**
     * 获取 时间戳
     */
    public String getDay() {
        return this.day;
    }

    /**
     * 设置 时间戳
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * 获取 文章对象集合
     */
    public List<ArticleModel> getDataList() {
        return this.dataList;
    }

    /**
     * 设置 文章对象集合
     */
    public void setDataList(List<ArticleModel> dataList) {
        this.dataList = dataList;
    }
}
