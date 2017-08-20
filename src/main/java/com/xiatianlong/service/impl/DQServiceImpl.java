package com.xiatianlong.service.impl;

import com.xiatianlong.common.Common;
import com.xiatianlong.entity.XtlDQPhotosEntity;
import com.xiatianlong.entity.XtlUserEntity;
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
     * @param isGetDelete  是否获取删除的
     * @param userId    用户id(查询全部传NULL)
     * @param keyWord   关键字（照片描述）
     * @return  照片集
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DQPhotosModel> getPhotos(boolean isGetDelete, Integer userId, String keyWord) {
        Criteria criteria = getSession().createCriteria(XtlDQPhotosEntity.class);
        if (!isGetDelete){
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

        return getPhotosModels(photosEntities);
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
     * 获取照片相关数据（封装）
     * @param photosEntities    照片对象集合
     * @return  封装集合
     */
    private List<DQPhotosModel> getPhotosModels(List<XtlDQPhotosEntity> photosEntities){
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

                models.add(model);
            }
            return models;
        }
        return null;
    }
}
