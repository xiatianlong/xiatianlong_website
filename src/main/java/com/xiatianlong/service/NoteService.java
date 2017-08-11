package com.xiatianlong.service;

import com.xiatianlong.entity.XtlNoteEntity;
import com.xiatianlong.entity.view.XtlTagGroupView;
import com.xiatianlong.model.NoteModel;
import com.xiatianlong.model.form.IndexNoteQueryForm;
import com.xiatianlong.model.form.NoteForm;
import com.xiatianlong.model.form.NoteQueryPageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.utils.PageList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Note Service
 * Created by xiatianlong on 2017/6/01.
 */
public interface NoteService extends BaseService {


    /**
     * 获取笔记列表
     * @return  笔记列表
     */
    List<NoteModel> getNoteList();

    /**
     * 前端条件查询笔记列表
     * @param queryForm 查询条件表单
     * @param isScroll 是否滑动加载
     * @return  笔记列表
     */
    List<NoteModel> getNoteListBySearch(IndexNoteQueryForm queryForm, boolean isScroll);

    /**
     * 前端首页最新笔记
     * @return  笔记列表
     */
    List<NoteModel> getNewNoteListByIndex();

    /**
     * 前端首页最热笔记
     * @return  笔记列表
     */
    List<NoteModel> getHotNoteListByIndex();

    /**
     * 获取笔记分页列表（admin）
     * @param form  form
     * @return  list
     */
    PageList getNotePageList(NoteQueryPageForm form);

    /**
     *  封装笔记model
     * @param noteEntity 笔记对象
     * @return  Notemodel
     */
    NoteModel getNoteModel(XtlNoteEntity noteEntity);

    /**
     *  封装笔记model
     * @param noteEntityList 笔记对象集合
     * @return  Notemodel
     */
    List<NoteModel> getNoteModelList(List<XtlNoteEntity> noteEntityList);

    /**
     * 创建笔记
     * @param form  笔记表单
     * @param request   请求
     */
    void createNote(NoteForm form, HttpServletRequest request);

    /**
     * 根据笔记id获取笔记
     * @param noteId 笔记id
     * @return  笔记
     */
    XtlNoteEntity getNote(int noteId);

    /**
     * 更新笔记
     * @param form  笔记表单
     * @param noteEntity   笔记对象
     */
    void updateNote(NoteForm form, XtlNoteEntity noteEntity);

    /**
     * 笔记上线
     * @param noteEntity 笔记
     * @param result    结果
     * @return  结果
     */
    AsynchronousResult online(XtlNoteEntity noteEntity, AsynchronousResult result);

    /**
     * 笔记下线
     * @param noteEntity 笔记
     * @param result    结果
     * @return  结果
     */
    AsynchronousResult offline(XtlNoteEntity noteEntity, AsynchronousResult result);

    /**
     * 笔记是否推荐处理
     *
     * @param noteEntity 笔记
     * @param result        结果
     * @param isRecommend  是否上线
     * @return 结果
     */
    AsynchronousResult noteRecommend(XtlNoteEntity noteEntity, AsynchronousResult result, boolean isRecommend);

    /**
     * 笔记删除
     * @param noteEntity 笔记
     * @param result    结果
     * @return  结果
     */
    AsynchronousResult delete(XtlNoteEntity noteEntity, AsynchronousResult result);

    /**
     * 获取笔记标签视图数据
     * @return  标签集合
     */
    List<XtlTagGroupView> getTagView();

    /**
     * 获取上线的笔记总数
     * @return  数量
     */
    int getNoteCntByOnline();

    /**
     * 获取下线的笔记总数
     * @return  数量
     */
    int getNoteCntByOffline();

    /**
     * 更新笔记浏览次数
     * @param noteEntity 笔记对象
     */
    void addNoteBrowseTimes(XtlNoteEntity noteEntity);

}
