package com.xiatianlong.service;

import com.xiatianlong.entity.XtlDictionaryEntity;

import java.util.List;

/**
 * Dictionary Service
 * Created by xiatianlong on 2017/1/16.
 */
public interface DictionaryService extends BaseService{

    /**
     * find all Dictionary
     * @return  Dictionary list
     */
    List<XtlDictionaryEntity> findAll();

}
