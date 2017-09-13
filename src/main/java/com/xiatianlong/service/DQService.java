package com.xiatianlong.service;

import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.model.DQMessageModel;
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
     * @param userId    用户id(查询全部传NULL)
     * @param keyWord   关键字（照片描述）
     * @param currentUser 当前用户
     * @return  照片集
     */
    List<DQPhotosModel> getPhotos(Integer userId, String keyWord, XtlUserEntity currentUser);

    /**
     * 保存上传的照片
     * @param form  请求的表单
     * @param currentUser   当前登录用户
     * @return  结果
     */
    AsynchronousResult savePhotos(DQPhotosForm form, XtlUserEntity currentUser);

    /**
     * 获取留言
     * @param userId    用户id（查询全部传null）
     * @param id    留言id（异步加载用，否则传null）
     * @param  currentUser 当前用户
     * @return  留言板
     */
    List<DQMessageModel> getMessages(Integer userId, Integer id, XtlUserEntity currentUser);


    /**
     * 保存留言
     * @param message   留言内容
     * @param currentUser   当前用户
     * @return  结果
     */
    AsynchronousResult saveMessage(String message, XtlUserEntity currentUser);

    /**
     * 删除留言
     * @param messageId messageID
     * @return  返回
     */
    AsynchronousResult removeMessage(Integer messageId);

}
