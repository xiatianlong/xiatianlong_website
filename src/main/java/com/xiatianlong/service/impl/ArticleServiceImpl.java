package com.xiatianlong.service.impl;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.ArticleStatus;
import com.xiatianlong.dictionary.DictionaryCache;
import com.xiatianlong.entity.XtlArticleEntity;
import com.xiatianlong.model.ArticleModel;
import com.xiatianlong.model.ArticleTimeDayModel;
import com.xiatianlong.model.ArticleTimeMonthModel;
import com.xiatianlong.model.form.ArticleForm;
import com.xiatianlong.model.form.ArticleQueryPageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.model.response.xiaochengxu.ArticleDetailModel;
import com.xiatianlong.model.response.xiaochengxu.ArticleResultModel;
import com.xiatianlong.service.ArticleService;
import com.xiatianlong.utils.DateUtil;
import com.xiatianlong.utils.PageList;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Article Service implements
 * Created by xiatianlong on 2017/5/21.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleService {

    /**
     * 创建文章
     *
     * @param form    文章表单
     * @param request 请求
     */
    @Transactional
    @Override
    public void createArticle(ArticleForm form, HttpServletRequest request) {

        XtlArticleEntity articleEntity = new XtlArticleEntity();

        articleEntity.setTitle(form.getTitle());
        articleEntity.setImage(form.getXtlFile());
        articleEntity.setIntroduction(form.getIntroduction());
        articleEntity.setContent(form.getContent());
        articleEntity.setStatus(ArticleStatus.HIDE.getCode());
        articleEntity.setUserId(getAdmin(request).getId());
        articleEntity.setCreateTime(new Date());
        articleEntity.setModifyTime(new Date());
        getSession().persist(articleEntity);
    }

    /**
     * 更新文章
     *
     * @param form          文章表单
     * @param articleEntity 文章对象
     */
    @Transactional
    @Override
    public void updateArticle(ArticleForm form, XtlArticleEntity articleEntity) {
        articleEntity.setStatus(ArticleStatus.HIDE.getCode());
        articleEntity.setIntroduction(form.getIntroduction());
        articleEntity.setContent(form.getContent());
        articleEntity.setTitle(form.getTitle());
        articleEntity.setImage(form.getXtlFile());
        articleEntity.setModifyTime(new Date());
        getSession().saveOrUpdate(articleEntity);
    }

    /**
     * 获取文章分页列表（admin）
     *
     * @param form form
     * @return list
     */
    @Override
    public PageList getArticlePageList(ArticleQueryPageForm form) {

        //Step 1: 根据查询条件获取数据总数
        Criteria countCriteria = getSession().createCriteria(XtlArticleEntity.class);
        Criteria criteria = getSession().createCriteria(XtlArticleEntity.class);

        countCriteria.add(Restrictions.ne("status", ArticleStatus.DELETE.getCode()));
        criteria.add(Restrictions.ne("status", ArticleStatus.DELETE.getCode()));

        if (!StringUtils.isEmpty(form.getKeyWord())){
            countCriteria.add(Restrictions.like("title", form.getKeyWord(), MatchMode.ANYWHERE));
            criteria.add(Restrictions.like("title",form.getKeyWord(), MatchMode.ANYWHERE));
        }

        countCriteria.setProjection(Projections.rowCount());
        int count = Integer.valueOf(countCriteria.uniqueResult().toString());
        //Step 2: 获取查询结果
        criteria.setFirstResult((form.getPageNo() - 1) * form.getPageSize());
        criteria.setMaxResults(form.getPageSize());
        // 排序（1. 按是否上线降序 2. 按评测日期降序）
        criteria.addOrder(Order.desc("status")).addOrder(Order.desc("createTime"));
        List list = criteria.list();
        return new PageList(list, null, form.getPageSize(), form.getPageSize(), count);
    }

    /**
     * 获取文章分页列表（admin）
     *
     * @param form form
     * @return list
     */
    @Override
    public PageList getArticlePageListByIndex(ArticleQueryPageForm form) {

        //Step 1: 根据查询条件获取数据总数
        Criteria countCriteria = getSession().createCriteria(XtlArticleEntity.class);
        Criteria criteria = getSession().createCriteria(XtlArticleEntity.class);

        countCriteria.add(Restrictions.eq("status", ArticleStatus.SHOW.getCode()));
        criteria.add(Restrictions.eq("status", ArticleStatus.SHOW.getCode()));

        if(!StringUtils.isEmpty(form.getKeyWord())){
            countCriteria.createAlias("user", "user");
            criteria.createAlias("user", "user");

            countCriteria.add(Restrictions.or(Restrictions.like("title", form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.or(Restrictions.like("introduction", form.getKeyWord(), MatchMode.ANYWHERE)),
                    Restrictions.or(Restrictions.like("user.userName", form.getKeyWord(), MatchMode.ANYWHERE))));

            criteria.add(Restrictions.or(Restrictions.like("title", form.getKeyWord(), MatchMode.ANYWHERE),
                    Restrictions.or(Restrictions.like("introduction", form.getKeyWord(), MatchMode.ANYWHERE)),
                    Restrictions.or(Restrictions.like("user.userName", form.getKeyWord(), MatchMode.ANYWHERE))));
        }

        countCriteria.setProjection(Projections.rowCount());
        int count = Integer.valueOf(countCriteria.uniqueResult().toString());
        //Step 2: 获取查询结果
        criteria.setFirstResult((form.getPageNo() - 1) * form.getPageSize());
        criteria.setMaxResults(form.getPageSize());
        // 排序（1. 按是否下载降序 2. 按评测日期降序）
        criteria.addOrder(Order.desc("createTime"));
        List list = criteria.list();
        return new PageList(list, null, form.getPageSize(), form.getPageSize(), count);
    }

    /**
     * 根据文章id获取文章
     *
     * @param articleId 文章id
     * @return 文章
     */
    @Override
    public XtlArticleEntity getArticle(int articleId) {
        return (XtlArticleEntity) getSession().get(XtlArticleEntity.class, articleId);
    }

    /**
     * 文章上线
     *
     * @param articleEntity 文章
     * @param result        结果
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult online(XtlArticleEntity articleEntity, AsynchronousResult result) {

        if (ArticleStatus.SHOW.getCode().equals(articleEntity.getStatus())) {
            result.setMessage(getMessage("article.onlined"));
            result.setResult(Common.FAIL);
            return result;
        }
        articleEntity.setStatus(ArticleStatus.SHOW.getCode());
        articleEntity.setModifyTime(new Date());
        getSession().update(articleEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 文章下线
     *
     * @param articleEntity 文章
     * @param result        结果
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult offline(XtlArticleEntity articleEntity, AsynchronousResult result) {

        if (ArticleStatus.HIDE.getCode().equals(articleEntity.getStatus())) {
            result.setMessage(getMessage("article.offlined"));
            result.setResult(Common.FAIL);
            return result;
        }
        articleEntity.setStatus(ArticleStatus.HIDE.getCode());
        articleEntity.setModifyTime(new Date());
        getSession().update(articleEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 文章是否推荐处理
     *
     * @param articleEntity 文章
     * @param result        结果
     * @param isRecommend  是否上线
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult articleRecommend(XtlArticleEntity articleEntity, AsynchronousResult result, boolean isRecommend) {

        if (isRecommend == articleEntity.isRecommend()) {
            if(isRecommend){
                result.setMessage(getMessage("article.isRecommend.true"));
            }else{
                result.setMessage(getMessage("article.isRecommend.false"));
            }
            result.setResult(Common.FAIL);
            return result;
        }
        articleEntity.setRecommend(isRecommend);
        articleEntity.setModifyTime(new Date());
        getSession().update(articleEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 文章删除
     *
     * @param articleEntity 文章
     * @param result        结果
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult delete(XtlArticleEntity articleEntity, AsynchronousResult result) {

        articleEntity.setStatus(ArticleStatus.DELETE.getCode());
        articleEntity.setModifyTime(new Date());
        getSession().update(articleEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 封装文章model
     *
     * @param articleEntity 文章对象
     * @return 文章model
     */
    @Override
    public ArticleModel getArticleModel(XtlArticleEntity articleEntity) {

        if (articleEntity != null) {
            ArticleModel model = new ArticleModel();
            model.setId(articleEntity.getId());
            model.setBrowseTimes(articleEntity.getBrowseTimes());
            model.setTitle(articleEntity.getTitle());
            if (!StringUtils.isEmpty(articleEntity.getImage())) {
                model.setImage(articleEntity.getImage() + Common.IMG_STYLE_1);
            }
            model.setIntroduction(articleEntity.getIntroduction());
            model.setContent(articleEntity.getContent());
            model.setStatus(DictionaryCache.getName(articleEntity.getStatus()));
            model.setStatusCode(articleEntity.getStatus());
            model.setRecommend(articleEntity.isRecommend());
            model.setUserId(articleEntity.getUserId());
            model.setUserName(articleEntity.getUser().getUserName());
            model.setEmail(articleEntity.getUser().getEmail());
            model.setCreateTime(DateUtil.getFormatString(articleEntity.getCreateTime(), DateUtil.defaultDatePattern));
            model.setUpdateTime(DateUtil.getFormatString(articleEntity.getModifyTime(), DateUtil.defaultDatePattern));
            return model;
        }
        return null;
    }

    /**
     * 封装文章model
     *
     * @param articleEntityList 文章对象集合
     * @return 文章model
     */
    @Override
    public List<ArticleModel> getArticleModelList(List<XtlArticleEntity> articleEntityList) {

        if (articleEntityList != null && !articleEntityList.isEmpty()) {
            List<ArticleModel> list = new ArrayList<>();
            for (XtlArticleEntity articleEntity : articleEntityList) {
                list.add(getArticleModel(articleEntity));
            }
            return list;
        }
        return null;
    }


    /**
     * 获取前端文章列表
     *
     * @return 文章列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<XtlArticleEntity> getArticleListByIndex(String date) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM xtl_article article ");
        sql.append(" WHERE article.status = '020001' ");
        if (!StringUtils.isEmpty(date)){
            sql.append(" AND date_format(article.create_time, '%Y-%m-%d') < '").append(date).append("'");
        }else{
            date = DateUtil.currentMonthFirstDay();
        }
        sql.append(" AND date_format(article.create_time, '%Y-%m-%d') >= DATE_SUB('").append(date).append("', INTERVAL 3 MONTH ) ");

        sql.append(" ORDER BY article.create_time desc");

        return getSession().createSQLQuery(sql.toString()).addEntity(XtlArticleEntity.class).list();
    }

    /**
     * 封装时间轴数据
     *
     * @param articleEntityList 文章列表
     * @return 时间轴
     */
    @Override
    public List<ArticleTimeMonthModel> articleTimeModelList(List<XtlArticleEntity> articleEntityList) {

        if (articleEntityList != null && !articleEntityList.isEmpty()) {
            List<ArticleTimeMonthModel> monthList = new ArrayList<>();
            List<String> tempMonth = new ArrayList<>();
            for (XtlArticleEntity article : articleEntityList) {
                String month = DateUtil.getFormatString(article.getCreateTime(), DateUtil.TIME_FORMATTER_YYMM);
                ArticleTimeMonthModel monthModel = new ArticleTimeMonthModel();
                // 进行月分组
                if (!tempMonth.contains(month)) {
                    List<ArticleTimeDayModel> dayList = new ArrayList<>();
                    List<String> tempDay = new ArrayList<>();
                    for (XtlArticleEntity article2 : articleEntityList) {
                        String month2 = DateUtil.getFormatString(article2.getCreateTime(), DateUtil.TIME_FORMATTER_YYMM);
                        if (month2.equals(month)) {
                            String day = DateUtil.getFormatString(article2.getCreateTime(), DateUtil.TIME_FORMATTER_YYMMDD);
                            // 画面显示的月日
                            String dayShow = DateUtil.getFormatString(article2.getCreateTime(), DateUtil.TIME_FORMATTER_MMDD);
                            ArticleTimeDayModel dayModel = new ArticleTimeDayModel();
                            // 进行日分组
                            if (!tempDay.contains(day)) {
                                List<ArticleModel> articleList = new ArrayList<>();
                                for (XtlArticleEntity article3 : articleEntityList) {
                                    String day2 = DateUtil.getFormatString(article3.getCreateTime(), DateUtil.TIME_FORMATTER_YYMMDD);
                                    if (day.equals(day2)) {
                                        articleList.add(getArticleModel(article3));
                                    }
                                }
                                // 设置数据给dayModel
                                dayModel.setDay(dayShow);
                                dayModel.setDataList(articleList);
                                // 添加dayModelList数据
                                dayList.add(dayModel);
                                // 已存在日标识
                                tempDay.add(day);
                            }
                        }
                    }
                    // 设置数据给monthModel
                    monthModel.setMonth(month);
                    monthModel.setDayList(dayList);
                    // 添加monthModelList数据
                    monthList.add(monthModel);

                    // 已存在月份标识
                    tempMonth.add(month);
                }
            }
            return monthList;
        }

        return null;
    }

    /**
     * 更新文章浏览次数
     * @param articleEntity 文章对象
     */
    @Transactional
    @Override
    public void addArticleBrowseTimes(XtlArticleEntity articleEntity) {
        // 单纯的加次数，不修改更新时间等其他信息
        if(articleEntity != null){
            articleEntity.setBrowseTimes(articleEntity.getBrowseTimes()+1);
            getSession().saveOrUpdate(articleEntity);
        }

    }

    /**
     * 获取上线的文章总数
     * @return  数量
     */
    @Override
    public int getArticleCntByOnline() {
        Criteria countCriteria = getSession().createCriteria(XtlArticleEntity.class);
        countCriteria.add(Restrictions.eq("status", ArticleStatus.SHOW.getCode()));
        countCriteria.setProjection(Projections.rowCount());
        return Integer.valueOf(countCriteria.uniqueResult().toString());
    }

    /**
     * 获取下线的文章总数
     * @return  数量
     */
    @Override
    public int getArticleCntByOffline() {
        Criteria countCriteria = getSession().createCriteria(XtlArticleEntity.class);
        countCriteria.add(Restrictions.eq("status", ArticleStatus.HIDE.getCode()));
        countCriteria.setProjection(Projections.rowCount());
        return Integer.valueOf(countCriteria.uniqueResult().toString());
    }

    /**
     * 获取文章列表
     * @param id    文章id
     * @param keyword   关键字
     * @return  文章对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ArticleResultModel> getArticleListByXcx(Integer id, String keyword) {

        Criteria criteria = getSession().createCriteria(XtlArticleEntity.class);
        if (id != null){
            criteria.add(Restrictions.lt("id", id));
        }
        if (keyword != null){
            criteria.add(Restrictions.like("title", keyword, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.eq("status", ArticleStatus.SHOW.getCode()));
        criteria.setMaxResults(7);
        criteria.addOrder(Order.desc("id"));
        List<XtlArticleEntity> articleEntities = criteria.list();

        if (articleEntities != null && !articleEntities.isEmpty()){
            List<ArticleResultModel> results = new ArrayList<>();
            for (XtlArticleEntity articleEntity : articleEntities){
                ArticleResultModel result = new ArticleResultModel();
                result.setId(articleEntity.getId());
                result.setIntroduction(articleEntity.getIntroduction());
                result.setImage(StringUtils.isEmpty(articleEntity.getImage())?"":articleEntity.getImage());
                result.setTitle(articleEntity.getTitle());
                result.setBrowseTimes(articleEntity.getBrowseTimes());
                result.setCreateTime(DateUtil.getFormatString(articleEntity.getCreateTime(), DateUtil.defaultDatePattern));
                results.add(result);
            }
            return results;
        }

        return null;
    }


    /**
     * 获取文章详情
     * @param id    文章id
     * @return 文章
     */
    @Override
    public ArticleDetailModel getArticleDetailByXcx(Integer id) {
        XtlArticleEntity articleEntity  = getArticle(id);
        if (articleEntity != null && articleEntity.getStatus().equals(ArticleStatus.SHOW.getCode())){
            ArticleDetailModel model = new ArticleDetailModel();
            model.setContent(articleEntity.getContent());
            model.setTitle(articleEntity.getTitle());
            model.setUserName(articleEntity.getUser().getUserName());
            model.setCreateTime(DateUtil.getFormatString(articleEntity.getCreateTime(), DateUtil.defaultDatePattern));
            return model;
        }
        return null;
    }

}
