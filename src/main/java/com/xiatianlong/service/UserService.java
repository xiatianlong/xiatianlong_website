package com.xiatianlong.service;

import com.xiatianlong.entity.XtlUserEntity;

/**
 * User Service
 * Created by xiatianlong on 2017/5/21.
 */
public interface UserService extends BaseService {

    /**
     * 登录
     * @param userName  用户名/邮箱
     * @param password  密码
     * @return  用户
     */
    XtlUserEntity login(String userName, String password);

}
