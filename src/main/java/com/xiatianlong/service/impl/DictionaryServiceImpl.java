package com.xiatianlong.service.impl;

import com.xiatianlong.entity.XtlDictionaryEntity;
import com.xiatianlong.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dictionary service implement
 * Created by xiatianlong on 2017/1/16.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DictionaryServiceImpl extends BaseServiceImpl implements DictionaryService {

    @Override
    @SuppressWarnings("unchecked")
    public List<XtlDictionaryEntity> findAll() {

        return getSession().createCriteria(XtlDictionaryEntity.class).list();
    }
}
