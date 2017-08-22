package com.xiatianlong.model.response;

import com.xiatianlong.model.DQMessageModel;

import java.util.List;

/**
 * DQ留言消息返回
 * Created by xiatianlong on 2017/8/21.
 */
public class DQMessageResult extends AsynchronousResult {

    /**
     * 留言集合
     */
    private List<DQMessageModel> messageModelList;


    /**
     * 获取 留言集合
     */
    public List<DQMessageModel> getMessageModelList() {
        return this.messageModelList;
    }

    /**
     * 设置 留言集合
     */
    public void setMessageModelList(List<DQMessageModel> messageModelList) {
        this.messageModelList = messageModelList;
    }
}
