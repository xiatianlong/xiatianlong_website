package com.xiatianlong.service;

import com.xiatianlong.model.IndexArticleNoteModel;

import java.util.List;

/**
 * 首页服务类
 * Created by xiatianlong on 2017/5/21.
 */
public interface IndexService extends BaseService {

    /**
     * 获取文章和笔记(首页大列表)
     * @return  model集合
     */
    List<IndexArticleNoteModel> getArticleAndNote();

    /**
     * 获取文章和笔记(首页最新)
     * @return  model集合
     */
    List<IndexArticleNoteModel> getArticleAndNoteByNew();

    /**
     * 获取文章和笔记(首页最热)
     * @return  model集合
     */
    List<IndexArticleNoteModel> getArticleAndNoteByHot();


}
