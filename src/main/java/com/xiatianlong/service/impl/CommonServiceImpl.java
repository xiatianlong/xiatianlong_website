package com.xiatianlong.service.impl;

import com.xiatianlong.entity.XtlErrorLogEntity;
import com.xiatianlong.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Common Service implements
 * Created by xiatianlong on 2017/5/21.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CommonServiceImpl extends BaseServiceImpl implements CommonService {


    /**
     * 添加错误日志
     */
    @Transactional
    @Override
    public void addErrorLog(String ip, String url, String info) {
        XtlErrorLogEntity errorLogEntity = new XtlErrorLogEntity();
        errorLogEntity.setIp(ip);
        errorLogEntity.setUrl(url);
        errorLogEntity.setInfo(info);
        errorLogEntity.setModifyTime(new Date());
        getSession().persist(errorLogEntity);
    }
}
