package com.xiatianlong.service.impl;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.NoteStatus;
import com.xiatianlong.dictionary.DictionaryCache;
import com.xiatianlong.entity.XtlNoteEntity;
import com.xiatianlong.entity.XtlNoteTagEntity;
import com.xiatianlong.entity.view.XtlTagGroupView;
import com.xiatianlong.model.NoteModel;
import com.xiatianlong.model.form.IndexNoteQueryForm;
import com.xiatianlong.model.form.NoteForm;
import com.xiatianlong.model.form.NoteQueryPageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.service.NoteService;
import com.xiatianlong.utils.DateUtil;
import com.xiatianlong.utils.PageList;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
 * Note Service implements
 * Created by xiatianlong on 2017/6/01.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class NoteServiceImpl extends BaseServiceImpl implements NoteService {

    /**
     * 获取笔记列表
     * @return  笔记列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<NoteModel> getNoteList() {
        Criteria criteria = getSession().createCriteria(XtlNoteEntity.class);
        criteria.add(Restrictions.eq("status", NoteStatus.SHOW.getCode()));
        criteria.setMaxResults(Common.INDEX_NOTE_PAGE_SIZE);
        criteria.addOrder(Order.desc("createTime"));
        List<XtlNoteEntity> noteList = criteria.list();
        if (noteList != null && !noteList.isEmpty()){
            for (XtlNoteEntity note : noteList){
                Hibernate.initialize(note.getTags());
            }
            return getNoteModelList(noteList);
        }
        return null;
    }

    /**
     * 前端条件查询笔记列表
     * @param queryForm 查询条件表单
     * @param isScroll 是否滑动加载
     * @return  笔记列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<NoteModel> getNoteListBySearch(IndexNoteQueryForm queryForm, boolean isScroll){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM xtl_note note JOIN xtl_note_tag tag ON note.id = tag.noteId");
        sql.append(" WHERE note.status = '").append(NoteStatus.SHOW.getCode()).append("'");
        if (isScroll){
            if (queryForm.getTags() != null && queryForm.getTags().length > 0){
                sql.append("AND tag.content in (").append( getTagsBySql(queryForm.getTags())).append(")");
            }
            if (!StringUtils.isEmpty(queryForm.getKeyword())){
                sql.append("AND (note.title LIKE '%").append(queryForm.getKeyword())
                        .append("%' OR tag.content LIKE '%").append(queryForm.getKeyword())
                        .append("%')");
            }
            if (!StringUtils.isEmpty(queryForm.getLastNoteCreateTime())){
                sql.append(" and date_format(note.create_time, '%Y-%m-%d %H:%i:%s') < '").append(queryForm.getLastNoteCreateTime()).append("'");
            }
        }else{
            if (queryForm.getTags() != null && queryForm.getTags().length > 0){
                sql.append("AND tag.content in (").append( getTagsBySql(queryForm.getTags())).append(")");
            }
            if (!StringUtils.isEmpty(queryForm.getKeyword())){
                sql.append("AND (note.title LIKE '%").append(queryForm.getKeyword())
                        .append("%' OR tag.content LIKE '%").append(queryForm.getKeyword())
                        .append("%')");
            }
        }

        sql.append(" GROUP BY note.id");
        sql.append(" ORDER BY note.create_time DESC");
        sql.append(" LIMIT ").append(Common.INDEX_NOTE_PAGE_SIZE);

        List<XtlNoteEntity> noteEntities = getSession().createSQLQuery(sql.toString()).addEntity(XtlNoteEntity.class).list();
        if (noteEntities !=null && !noteEntities.isEmpty()){
            return getNoteModelList(noteEntities);
        }
        return null;
    }

    /**
     * 封装sql需要的标签参数
     * @param tags  标签数组
     * @return  参数
     */
    private String getTagsBySql(String[] tags){
        StringBuilder strTag = new StringBuilder();
        for(int i = 0; i < tags.length; i++){
            if (i==0){
                strTag.append("'").append(tags[i]).append("'");
            }else{
                strTag.append(",").append("'").append(tags[i]).append("'");
            }
        }
        return strTag.toString();
    }


    /**
     * 获取笔记分页列表（admin）
     *
     * @param form form
     * @return list
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageList getNotePageList(NoteQueryPageForm form) {

        //Step 1: 根据查询条件获取数据总数
        Criteria countCriteria = getSession().createCriteria(XtlNoteEntity.class);
        Criteria criteria = getSession().createCriteria(XtlNoteEntity.class);

        countCriteria.add(Restrictions.ne("status", NoteStatus.DELETE.getCode()));
        criteria.add(Restrictions.ne("status", NoteStatus.DELETE.getCode()));

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
        List<XtlNoteEntity> list = criteria.list();
        if(list != null && !list.isEmpty()){
            for (XtlNoteEntity note : list){
                Hibernate.initialize(note.getTags());
            }
        }
        return new PageList(list, null, form.getPageSize(), form.getPageSize(), count);
    }

    /**
     *  封装笔记model
     * @param noteEntity 笔记对象
     * @return  Notemodel
     */
    @Override
    public NoteModel getNoteModel(XtlNoteEntity noteEntity) {
        if (noteEntity != null){
            NoteModel model = new NoteModel();
            model.setId(noteEntity.getId());
            model.setTitle(noteEntity.getTitle());
            model.setContent(noteEntity.getContent());
            model.setStatus(DictionaryCache.getName(noteEntity.getStatus()));
            model.setStatusCode(noteEntity.getStatus());
            model.setUserId(noteEntity.getUserId());
            model.setUserName(noteEntity.getUser().getUserName());
            model.setEmail(noteEntity.getUser().getEmail());
            model.setBrowseTimes(noteEntity.getBrowseTimes());
            model.setRecommend(noteEntity.isRecommend());
            model.setCreateTime(DateUtil.getFormatString(noteEntity.getCreateTime(), DateUtil.defaultDatePattern));
            model.setUpdateTime(DateUtil.getFormatString(noteEntity.getModifyTime(), DateUtil.defaultDatePattern));
            List<XtlNoteTagEntity> tagEntities = noteEntity.getTags();
            if(tagEntities != null && !tagEntities.isEmpty()){
                List<String> tags = new ArrayList<>();
                StringBuilder fmtTags = new StringBuilder();
                for(int i = 0; i < tagEntities.size(); i++){
                    tags.add(tagEntities.get(i).getContent());
                    if(i == 0){
                        fmtTags.append(tagEntities.get(i).getContent());
                    }else{
                        fmtTags.append(",").append(tagEntities.get(i).getContent());
                    }
                }
                model.setTags(tags);
                model.setFmtTags(fmtTags.toString());
            }
            return model;
        }
        return null;
    }

    /**
    * 封装笔记model
    *
    * @param noteEntityList 笔记对象集合
    * @return 笔记model
    */
    @Override
    public List<NoteModel> getNoteModelList(List<XtlNoteEntity> noteEntityList) {

        if (noteEntityList != null && !noteEntityList.isEmpty()) {
            List<NoteModel> list = new ArrayList<>();
            for (XtlNoteEntity noteEntity : noteEntityList) {
                list.add(getNoteModel(noteEntity));
            }
            return list;
        }
        return null;
    }

    /**
     * 创建笔记
     * @param form  笔记表单
     * @param request   请求
     */
    @Transactional
    @Override
    public void createNote(NoteForm form, HttpServletRequest request) {

        // 保存笔记
        XtlNoteEntity xtlNoteEntity = new XtlNoteEntity();
        xtlNoteEntity.setStatus(NoteStatus.HIDE.getCode());
        xtlNoteEntity.setTitle(form.getTitle());
        xtlNoteEntity.setContent(form.getContent());
        xtlNoteEntity.setUserId(getAdmin(request).getId());
        xtlNoteEntity.setModifyTime(new Date());
        getSession().persist(xtlNoteEntity);

        // 保存标签
        saveTags(form.getTags(), xtlNoteEntity.getId());
    }

    /**
     * 根据笔记id获取笔记
     * @param noteId 笔记id
     * @return  笔记
     */
    @Override
    public XtlNoteEntity getNote(int noteId) {

        XtlNoteEntity note =  (XtlNoteEntity) getSession().get(XtlNoteEntity.class, noteId);
        Hibernate.initialize(note.getTags());
        return note;
    }

    /**
     * 更新笔记
     * @param form  笔记表单
     * @param noteEntity   笔记对象
     */
    @Transactional
    @Override
    public void updateNote(NoteForm form, XtlNoteEntity noteEntity) {
        // 更新笔记
        noteEntity.setStatus(NoteStatus.HIDE.getCode());
        noteEntity.setTitle(form.getTitle());
        noteEntity.setContent(form.getContent());
        noteEntity.setModifyTime(new Date());

        getSession().saveOrUpdate(noteEntity);

        // 更新标签
        List<XtlNoteTagEntity> noteTagEntities = noteEntity.getTags();
        if(noteTagEntities != null && !noteTagEntities.isEmpty()){
            for(XtlNoteTagEntity noteTagEntity : noteTagEntities){
                getSession().delete(noteTagEntity);
            }
        }
        saveTags(form.getTags(), noteEntity.getId());
    }

    /**
     * 标签保存
     * @param tags  标签数组
     * @param noteId    笔记id
     */
    private void saveTags(String[] tags, int noteId){
        for (String tag : tags) {
            XtlNoteTagEntity xtlNoteTagEntity = new XtlNoteTagEntity();
            xtlNoteTagEntity.setContent(tag);
            xtlNoteTagEntity.setNoteId(noteId);
            xtlNoteTagEntity.setModifyTime(new Date());
            getSession().persist(xtlNoteTagEntity);
        }
    }

    /**
     * 笔记上线
     *
     * @param noteEntity 笔记
     * @param result        结果
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult online(XtlNoteEntity noteEntity, AsynchronousResult result) {

        if (NoteStatus.SHOW.getCode().equals(noteEntity.getStatus())) {
            result.setMessage(getMessage("note.onlined"));
            result.setResult(Common.FAIL);
            return result;
        }
        noteEntity.setStatus(NoteStatus.SHOW.getCode());
        noteEntity.setModifyTime(new Date());
        getSession().update(noteEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 笔记下线
     *
     * @param noteEntity 笔记
     * @param result        结果
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult offline(XtlNoteEntity noteEntity, AsynchronousResult result) {

        if (NoteStatus.HIDE.getCode().equals(noteEntity.getStatus())) {
            result.setMessage(getMessage("note.offlined"));
            result.setResult(Common.FAIL);
            return result;
        }
        noteEntity.setStatus(NoteStatus.HIDE.getCode());
        noteEntity.setModifyTime(new Date());
        getSession().update(noteEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 笔记是否推荐处理
     *
     * @param noteEntity 笔记
     * @param result        结果
     * @param isRecommend  是否上线
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult noteRecommend(XtlNoteEntity noteEntity, AsynchronousResult result, boolean isRecommend) {

        if (isRecommend == noteEntity.isRecommend()) {
            if(isRecommend){
                result.setMessage(getMessage("note.isRecommend.true"));
            }else{
                result.setMessage(getMessage("note.isRecommend.false"));
            }
            result.setResult(Common.FAIL);
            return result;
        }
        noteEntity.setRecommend(isRecommend);
        noteEntity.setModifyTime(new Date());
        getSession().update(noteEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 笔记删除
     *
     * @param noteEntity 笔记
     * @param result        结果
     * @return 结果
     */
    @Transactional
    @Override
    public AsynchronousResult delete(XtlNoteEntity noteEntity, AsynchronousResult result) {

        noteEntity.setStatus(NoteStatus.DELETE.getCode());
        noteEntity.setModifyTime(new Date());
        getSession().update(noteEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 获取笔记标签视图数据
     * @return  标签集合
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<XtlTagGroupView> getTagView() {
        Criteria criteria = getSession().createCriteria(XtlTagGroupView.class);
        criteria.setMaxResults(Common.INDEX_NOTE_TAG_COUNT);
        return criteria.list();
    }

    /**
     * 获取上线的笔记总数
     * @return  数量
     */
    @Override
    public int getNoteCntByOnline() {
        Criteria countCriteria = getSession().createCriteria(XtlNoteEntity.class);
        countCriteria.add(Restrictions.eq("status", NoteStatus.SHOW.getCode()));
        countCriteria.setProjection(Projections.rowCount());
        return Integer.valueOf(countCriteria.uniqueResult().toString());
    }

    /**
     * 获取下线的笔记总数
     * @return  数量
     */
    @Override
    public int getNoteCntByOffline() {
        Criteria countCriteria = getSession().createCriteria(XtlNoteEntity.class);
        countCriteria.add(Restrictions.eq("status", NoteStatus.HIDE.getCode()));
        countCriteria.setProjection(Projections.rowCount());
        return Integer.valueOf(countCriteria.uniqueResult().toString());
    }

    /**
     * 更新笔记浏览次数
     * @param noteEntity 笔记对象
     */
    @Transactional
    @Override
    public void addNoteBrowseTimes(XtlNoteEntity noteEntity) {
        // 单纯的加次数，不修改更新时间等其他信息
        if(noteEntity != null){
            noteEntity.setBrowseTimes(noteEntity.getBrowseTimes()+1);
            getSession().saveOrUpdate(noteEntity);
        }

    }
    
}
