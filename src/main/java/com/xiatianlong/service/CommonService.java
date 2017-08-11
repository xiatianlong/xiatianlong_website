package com.xiatianlong.service;

/**
 * Common Service
 * Created by xiatianlong on 2017/6/04.
 */
public interface CommonService extends BaseService {

    /**
     * 添加错误日志
     */
    void addErrorLog(String ip, String url, String info);

}
