package com.xiatianlong.model.response.xiaochengxu;

import com.xiatianlong.common.Common;

import java.io.Serializable;
import java.util.List;

/**
 * 小程序文章返回结果
 * Created by xiatianlong on 2017/8/22.
 */
public class ArticleResult implements Serializable {
    private static final long serialVersionUID = -5100185835589307972L;

    /**
     * 返回类型
     */
    private String result = Common.FAIL;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 数据结果集
     */
    private List<ArticleResultModel> dataList;


    /**
     * 获取 返回类型
     */
    public String getResult() {
        return this.result;
    }

    /**
     * 设置 返回类型
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取 返回消息
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * 设置 返回消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取 数据结果集
     */
    public List<ArticleResultModel> getDataList() {
        return this.dataList;
    }

    /**
     * 设置 数据结果集
     */
    public void setDataList(List<ArticleResultModel> dataList) {
        this.dataList = dataList;
    }
}
