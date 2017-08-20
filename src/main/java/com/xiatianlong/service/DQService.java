package com.xiatianlong.service;

import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.model.DQPhotosModel;
import com.xiatianlong.model.form.DQPhotosForm;
import com.xiatianlong.model.response.AsynchronousResult;

import java.util.List;

/**
 * dq Service
 * Created by xiatianlong on 2017/8/18.
 */
public interface DQService extends BaseService {


    /**
     * 获取相册
     * @param isGetDelete  是否获取删除的
     * @param userId    用户id(查询全部传NULL)
     * @param keyWord   关键字（照片描述）
     * @return  照片集
     */
    List<DQPhotosModel> getPhotos(boolean isGetDelete, Integer userId, String keyWord);

    /**
     * 保存上传的照片
     * @param form  请求的表单
     * @param currentUser   当前登录用户
     * @return  结果
     */
    AsynchronousResult savePhotos(DQPhotosForm form, XtlUserEntity currentUser);

}
