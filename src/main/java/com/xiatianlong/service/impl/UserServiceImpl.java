package com.xiatianlong.service.impl;

import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.service.UserService;
import com.xiatianlong.utils.MD5Util;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Service implements
 * Created by xiatianlong on 2017/5/21.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl  extends BaseServiceImpl implements UserService {

    /**
     * 登录
     * @param userName  用户名/邮箱
     * @param password  密码
     * @return  用户
     */
    public XtlUserEntity login(String userName, String password) {

        Criteria criteria = getSession().createCriteria(XtlUserEntity.class);
        criteria.add(Restrictions.or(Restrictions.eq("userName", userName), Restrictions.eq("email", userName)));
        criteria.add(Restrictions.eq("password", MD5Util.MD5Psw(password)));
        return (XtlUserEntity) criteria.uniqueResult();
    }
}
