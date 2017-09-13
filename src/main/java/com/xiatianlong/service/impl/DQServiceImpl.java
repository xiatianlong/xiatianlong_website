package com.xiatianlong.service.impl;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.RoleType;
import com.xiatianlong.entity.XtlDQMessageEntity;
import com.xiatianlong.entity.XtlDQPhotosEntity;
import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.model.DQMessageModel;
import com.xiatianlong.model.DQPhotosModel;
import com.xiatianlong.model.form.DQPhotosForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.service.DQService;
import com.xiatianlong.utils.DateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DQ Service implements
 * Created by xiatianlong on 2017/8/18.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DQServiceImpl extends BaseServiceImpl implements DQService {


    /**
     * 获取相册
     * @param userId    用户id(查询全部传NULL)
     * @param keyWord   关键字（照片描述）
     * @param currentUser 当前用户
     * @return  照片集
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DQPhotosModel> getPhotos(Integer userId, String keyWord, XtlUserEntity currentUser) {
        Criteria criteria = getSession().createCriteria(XtlDQPhotosEntity.class);
        if (currentUser == null || !RoleType.ADMIN.getCode().equals(currentUser.getRoleCode())){
            criteria.add(Restrictions.eq("delete", false));
        }
        if (userId != null){
            criteria.add(Restrictions.eq("userId", userId));
        }
        if (!StringUtils.isEmpty(keyWord)){
            criteria.add(Restrictions.like("photoName", keyWord, MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.desc("createTime"));
        List<XtlDQPhotosEntity> photosEntities = criteria.list();

        return getPhotosModels(photosEntities, currentUser);
    }

    /**
     * 保存上传的照片
     * @param form  请求的表单
     * @param currentUser   当前登录用户
     * @return  结果
     */
    @Transactional
    @Override
    public AsynchronousResult savePhotos(DQPhotosForm form, XtlUserEntity currentUser) {
        AsynchronousResult result = new AsynchronousResult();

        if (currentUser == null){
            result.setMessage(getMessage("login.timeout"));
            return result;
        }

        if (form.getPhotoUrl().length <= 0){
            result.setMessage(getMessage("dq.photos.save.no.photos"));
            return result;
        }
        if (form.getPhotoUrl().length != form.getPhotoName().length){
            result.setMessage(getMessage("data.error"));
            return result;
        }
        for (int i = 0; i < form.getPhotoUrl().length; i++){
            XtlDQPhotosEntity photosEntity = new XtlDQPhotosEntity();
            photosEntity.setPhotoUrl(form.getPhotoUrl()[i]);
            photosEntity.setPhotoName(form.getPhotoName()[i]);
            photosEntity.setDelete(false);
            photosEntity.setUser(currentUser);
            photosEntity.setUserId(currentUser.getId());
            Date currentTime = new Date();
            photosEntity.setCreateTime(currentTime);
            photosEntity.setModifyTime(currentTime);
            getSession().persist(photosEntity);
        }
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 获取留言
     * @param userId    用户id（查询全部传null）
     * @param id    留言id（异步加载用，否则传null）
     * @param  currentUser 当前用户
     * @return  留言板
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DQMessageModel> getMessages(Integer userId, Integer id, XtlUserEntity currentUser) {
        Criteria criteria = getSession().createCriteria(XtlDQMessageEntity.class);
        if (currentUser == null || !RoleType.ADMIN.getCode().equals(currentUser.getRoleCode())){
            criteria.add(Restrictions.eq("delete", false));
        }
        if (userId != null){
            criteria.add(Restrictions.eq("userId", userId));
        }
        if (id != null){
            criteria.add(Restrictions.lt("id", id));
        }
        criteria.addOrder(Order.desc("id"));
        List<XtlDQMessageEntity> messageEntities = criteria.list();

        return getMessages(messageEntities, currentUser);
    }

    /**
     * 保存留言
     * @param message   留言内容
     * @param currentUser   当前用户
     * @return  结果
     */
    @Transactional
    @Override
    public AsynchronousResult saveMessage(String message, XtlUserEntity currentUser) {
        AsynchronousResult result = new AsynchronousResult();

        if (currentUser == null){
            result.setMessage(getMessage("login.timeout"));
            return result;
        }
        if (StringUtils.isEmpty(message)){
            result.setMessage(getMessage("dq.message.not.null"));
            return result;
        }
        XtlDQMessageEntity messageEntity = new XtlDQMessageEntity();
        messageEntity.setText(message);
        messageEntity.setDelete(false);
        messageEntity.setUser(currentUser);
        messageEntity.setUserId(currentUser.getId());
        Date currentTime = new Date();
        messageEntity.setCreateTime(currentTime);
        messageEntity.setModifyTime(currentTime);
        getSession().persist(messageEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 删除留言
     * @param messageId messageID
     * @return  返回
     */
    @Transactional
    @Override
    public AsynchronousResult removeMessage(Integer messageId) {
        AsynchronousResult result = new AsynchronousResult();
        if (messageId == null){
            result.setMessage(getMessage("message.id.null"));
            return  result;
        }
        XtlDQMessageEntity messageEntity = (XtlDQMessageEntity)getSession().get(XtlDQMessageEntity.class, messageId);
        if (messageEntity == null){
            result.setMessage(getMessage("message.null"));
            return  result;
        }
        messageEntity.setDelete(true);
        messageEntity.setModifyTime(new Date());
        getSession().update(messageEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 获取照片相关数据（封装）
     * @param photosEntities    照片对象集合
     * @param currentUser   当前用户
     * @return  封装集合
     */
    private List<DQPhotosModel> getPhotosModels(List<XtlDQPhotosEntity> photosEntities, XtlUserEntity currentUser){
        if (photosEntities != null && !photosEntities.isEmpty()){
            List<DQPhotosModel> models = new ArrayList<>();
            for (XtlDQPhotosEntity photosEntity : photosEntities){
                DQPhotosModel model = new DQPhotosModel();

                model.setUserName(photosEntity.getUser().getUserName());
                model.setPhotoUrl(photosEntity.getPhotoUrl());
                model.setPhotoThumbnailUrl(photosEntity.getPhotoUrl() + Common.IMG_STYLE_1);
                model.setCreateTime(DateUtil.getFormatString(photosEntity.getCreateTime(), DateUtil.defaultDatePattern));
                model.setPhotoMemo(photosEntity.getPhotoName());
                model.setDelete(photosEntity.isDelete());
                model.setId(photosEntity.getId());
                model.setShowDelete(photosEntity.getUserId()==currentUser.getId());

                models.add(model);
            }
            return models;
        }
        return null;
    }

    /**
     * 获取留言集合（封装）
     * @param messageEntities   留言对象集合
     * @param currentUser dangqianyonghu
     * @return  封装集合
     */
    private List<DQMessageModel> getMessages(List<XtlDQMessageEntity> messageEntities, XtlUserEntity currentUser){
        if (messageEntities != null && !messageEntities.isEmpty()){
            List<DQMessageModel> messageModels =  new ArrayList<>();
            for (XtlDQMessageEntity messageEntity : messageEntities){
                DQMessageModel messageModel = new DQMessageModel();
                messageModel.setContent(messageEntity.getText());
                messageModel.setUserName(messageEntity.getUser().getUserName());
                messageModel.setUserImg(messageEntity.getUser().getImg());
                messageModel.setCreateTime(DateUtil.getFormatString(messageEntity.getCreateTime(), DateUtil.defaultDatePattern));
                messageModel.setId(messageEntity.getId());
                messageModel.setShowDelete(messageEntity.getUserId()==currentUser.getId());
                messageModels.add(messageModel);
            }
            return messageModels;
        }
        return null;
    }
}
