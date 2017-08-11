package com.xiatianlong.model;

import java.util.List;

/**
 * 文章详情模型(时间轴)
 * Created by xiatianlong on 2017/5/28.
 */
public class ArticleTimeMonthModel {

    /**
     * 时间戳
     */
    private String month;

    /**
     * 文章对象集合
     */
    private List<ArticleTimeDayModel> dayList;


    /**
     * 获取 时间戳
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * 设置 时间戳
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * 获取 文章对象集合
     */
    public List<ArticleTimeDayModel> getDayList() {
        return this.dayList;
    }

    /**
     * 设置 文章对象集合
     */
    public void setDayList(List<ArticleTimeDayModel> dayList) {
        this.dayList = dayList;
    }
}
