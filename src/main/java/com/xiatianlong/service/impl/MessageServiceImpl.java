package com.xiatianlong.service.impl;

import com.xiatianlong.entity.XtlMessageEntity;
import com.xiatianlong.model.MessageModel;
import com.xiatianlong.model.form.MessageForm;
import com.xiatianlong.model.form.MessageQueryPageForm;
import com.xiatianlong.service.MessageService;
import com.xiatianlong.utils.DateUtil;
import com.xiatianlong.utils.PageList;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Message Service implements
 * Created by xiatianlong on 2017/6/01.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

    /**
     * 添加留言
     * @param form  留言表单
     */
    @Transactional
    @Override
    public void addMessage(MessageForm form) {
        XtlMessageEntity messageEntity = new XtlMessageEntity();
        messageEntity.setIp(form.getIp());
        messageEntity.setIpAddress(form.getIpAddress());
        messageEntity.setUserName(form.getUserName());
        messageEntity.setContactInfomation(form.getContactInfomation());
        messageEntity.setContent(form.getContent());
        messageEntity.setModifyTime(new Date());
        messageEntity.setRead(false);
        getSession().persist(messageEntity);
    }

    /**
     * 获取笔记分页列表（admin）
     * @param form 查询表单
     * @return  list
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageList getMessagePageList(MessageQueryPageForm form) {
        //Step 1: 根据查询条件获取数据总数
        Criteria countCriteria = getSession().createCriteria(XtlMessageEntity.class);
        Criteria criteria = getSession().createCriteria(XtlMessageEntity.class);

        if (!StringUtils.isEmpty(form.getKeyWord())){
            countCriteria.add(Restrictions.or(Restrictions.like("ipAddress",form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.like("userName",form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.like("content",form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.like("contactInfomation",form.getKeyWord(), MatchMode.ANYWHERE)));

            criteria.add(Restrictions.or(Restrictions.like("ipAddress",form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.like("userName",form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.like("content",form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.like("contactInfomation",form.getKeyWord(), MatchMode.ANYWHERE)));
        }


        countCriteria.setProjection(Projections.rowCount());
        int count = Integer.valueOf(countCriteria.uniqueResult().toString());
        //Step 2: 获取查询结果
        criteria.setFirstResult((form.getPageNo() - 1) * form.getPageSize());
        criteria.setMaxResults(form.getPageSize());
        // 排序（1. 按是否上线降序 2. 按评测日期降序）
        criteria.addOrder(Order.asc("read")).addOrder(Order.desc("createTime"));
        List<XtlMessageEntity> list = criteria.list();
        return new PageList(list, null, form.getPageSize(), form.getPageSize(), count);
    }

    /**
     *  封装留言model
     * @param messageEntity 留言对象
     * @return  留言model
     */
    @Override
    public MessageModel getMessageModel(XtlMessageEntity messageEntity) {

        if(messageEntity != null){
            MessageModel model = new MessageModel();
            model.setId(messageEntity.getId());
            model.setRead(messageEntity.isRead());
            model.setIp(messageEntity.getIp());
            model.setIpAddress(messageEntity.getIpAddress());
            model.setContent(messageEntity.getContent());
            model.setContactInfomation(messageEntity.getContactInfomation());
            model.setUserName(messageEntity.getUserName());
            model.setCreateTime(DateUtil.getFormatString(messageEntity.getCreateTime(), DateUtil.defaultDatePattern));
            model.setModifyTime(DateUtil.getFormatString(messageEntity.getModifyTime(), DateUtil.defaultDatePattern));
            return model;
        }

        return null;
    }

    /**
     *  封装留言model
     * @param messageEntityList 留言对象集合
     * @return  留言model
     */
    @Override
    public List<MessageModel> getMessageModelList(List<XtlMessageEntity> messageEntityList) {
        if (messageEntityList != null && !messageEntityList.isEmpty()) {
            List<MessageModel> list = new ArrayList<>();
            for (XtlMessageEntity messageEntity : messageEntityList) {
                list.add(getMessageModel(messageEntity));
            }
            return list;
        }
        return null;
    }

    /**
     * 获取message
     * @param messageId 留言id
     * @return  留言对象
     */
    @Override
    public XtlMessageEntity getMessage(int messageId) {
        return (XtlMessageEntity) getSession().get(XtlMessageEntity.class, messageId);
    }

    /**
     * 设置为已读
     * @param messageEntity 留言对象
     */
    @Transactional
    @Override
    public void setMessageView(XtlMessageEntity messageEntity) {
        if (messageEntity != null){
            messageEntity.setRead(true);
            getSession().saveOrUpdate(messageEntity);
        }
    }

    /**
     * 获取留言的数量
     * @param isUnRead    是否只查未读的
     * @return  数量
     */
    @Override
    public int getMessageCount(boolean isUnRead) {
        Criteria countCriteria = getSession().createCriteria(XtlMessageEntity.class);
        if (isUnRead){
            countCriteria.add(Restrictions.eq("read", false));
        }
        countCriteria.setProjection(Projections.rowCount());
        return Integer.valueOf(countCriteria.uniqueResult().toString());
    }


}
