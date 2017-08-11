package com.xiatianlong.model.response;

import com.xiatianlong.common.Common;

import java.io.Serializable;

/**
 * 异步返回结果超类
 * Created by xiatianlong on 2017/1/18.
 */
public class AsynchronousResult implements Serializable{

    /**
     * 返回类型
     */
    private String result = Common.FAIL;

    /**
     * 返回消息
     */
    private String message;


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
}
