package com.xiatianlong.service;

import com.xiatianlong.entity.XtlArticleEntity;
import com.xiatianlong.model.ArticleModel;
import com.xiatianlong.model.ArticleTimeMonthModel;
import com.xiatianlong.model.form.ArticleForm;
import com.xiatianlong.model.form.ArticleQueryPageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.model.response.xiaochengxu.ArticleResultModel;
import com.xiatianlong.utils.PageList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Article Service
 * Created by xiatianlong on 2017/5/21.
 */
public interface ArticleService extends BaseService {

    /**
     * 创建文章
     * @param form  文章表单
     * @param request   请求
     */
    void createArticle(ArticleForm form, HttpServletRequest request);

    /**
     * 更新文章
     * @param form  文章表单
     * @param articleEntity   文章对象
     */
    void updateArticle(ArticleForm form, XtlArticleEntity articleEntity);


    /**
     * 获取文章分页列表（admin）
     * @param form  form
     * @return  list
     */
    PageList getArticlePageList(ArticleQueryPageForm form);

    /**
     * 获取文章分页列表（admin）
     *
     * @param form form
     * @return list
     */
     PageList getArticlePageListByIndex(ArticleQueryPageForm form);

    /**
     * 根据文章id获取文章
     * @param articleId 文章id
     * @return  文章
     */
    XtlArticleEntity getArticle(int articleId);

    /**
     * 文章上线
     * @param articleEntity 文章
     * @param result    结果
     * @return  结果
     */
    AsynchronousResult online(XtlArticleEntity articleEntity, AsynchronousResult result);

    /**
     * 文章下线
     * @param articleEntity 文章
     * @param result    结果
     * @return  结果
     */
    AsynchronousResult offline(XtlArticleEntity articleEntity, AsynchronousResult result);

    /**
     * 文章是否推荐处理
     *
     * @param articleEntity 文章
     * @param result        结果
     * @param isRecommend  是否上线
     * @return 结果
     */
    AsynchronousResult articleRecommend(XtlArticleEntity articleEntity, AsynchronousResult result, boolean isRecommend);

    /**
     * 文章删除
     * @param articleEntity 文章
     * @param result    结果
     * @return  结果
     */
    AsynchronousResult delete(XtlArticleEntity articleEntity, AsynchronousResult result);

    /**
     *  封装文章model
     * @param articleEntity 文章对象
     * @return  文章model
     */
    ArticleModel getArticleModel(XtlArticleEntity articleEntity);

    /**
     *  封装文章model
     * @param articleEntityList 文章对象集合
     * @return  文章model
     */
    List<ArticleModel> getArticleModelList(List<XtlArticleEntity> articleEntityList);

    /**
     * 获取前端文章列表
     * @param date 日期
     * @return  文章列表
     */
    List<XtlArticleEntity> getArticleListByIndex(String date);

    /**
     * 封装时间轴数据
     * @param articleEntityList 文章列表
     * @return  时间轴
     */
    List<ArticleTimeMonthModel> articleTimeModelList(List<XtlArticleEntity> articleEntityList);

    /**
     * 更新文章浏览次数
     * @param articleEntity 文章对象
     */
    void addArticleBrowseTimes(XtlArticleEntity articleEntity);

    /**
     * 获取上线的文章总数
     * @return  数量
     */
    int getArticleCntByOnline();

    /**
     * 获取下线的文章总数
     * @return  数量
     */
    int getArticleCntByOffline();


    /**
     * 获取文章列表
     * @param id    文章id
     * @return  文章对象
     */
    List<ArticleResultModel> getArticleListByXcx(Integer id);
}
