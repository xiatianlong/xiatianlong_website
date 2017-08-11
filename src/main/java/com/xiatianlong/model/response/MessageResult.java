package com.xiatianlong.model.response;

import com.xiatianlong.model.MessageModel;

/**
 * 前端笔记列表返回对象
 * Created by xiatianlong on 2017/6/03.
 */
public class MessageResult extends AsynchronousResult {

    /**
     * 返回数据
     */
    private MessageModel data;


    /**
     * 获取 返回数据
     */
    public MessageModel getData() {
        return this.data;
    }

    /**
     * 设置 返回数据
     */
    public void setData(MessageModel data) {
        this.data = data;
    }
}
