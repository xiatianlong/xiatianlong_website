package com.xiatianlong.service;

import com.xiatianlong.entity.XtlMessageEntity;
import com.xiatianlong.model.MessageModel;
import com.xiatianlong.model.form.MessageForm;
import com.xiatianlong.model.form.MessageQueryPageForm;
import com.xiatianlong.utils.PageList;

import java.util.List;

/**
 * Note Service
 * Created by xiatianlong on 2017/6/04.
 */
public interface MessageService extends BaseService {

    /**
     * 添加留言
     * @param form  留言表单
     */
    void addMessage(MessageForm form);

    /**
     * 获取笔记分页列表（admin）
     * @param form 查询表单
     * @return  list
     */
    PageList getMessagePageList(MessageQueryPageForm form);

    /**
     *  封装留言model
     * @param messageEntity 留言对象
     * @return  留言model
     */
    MessageModel getMessageModel(XtlMessageEntity messageEntity);

    /**
     *  封装留言model
     * @param messageEntityList 留言对象集合
     * @return  留言model
     */
    List<MessageModel> getMessageModelList(List<XtlMessageEntity> messageEntityList);

    /**
     * 获取message
     * @param messageId 留言id
     * @return  留言对象
     */
    XtlMessageEntity getMessage(int messageId);

    /**
     * 设置为已读
     * @param messageEntity 留言对象
     */
    void setMessageView(XtlMessageEntity messageEntity);

    /**
     * 获取留言的数量
     * @param isUnRead    是否只查未读的
     * @return  数量
     */
    int getMessageCount(boolean isUnRead);
}
