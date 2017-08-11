package com.xiatianlong.model.response;

import com.xiatianlong.model.NoteModel;

import java.util.List;

/**
 * 前端笔记列表返回对象
 * Created by xiatianlong on 2017/6/03.
 */
public class IndexNoteResult extends AsynchronousResult {

    /**
     * 最后笔记的创建时间
     */
    private String lastNoteCreateTime;

    /**
     * 返回数据
     */
    private List<NoteModel> noteList;


    /**
     * 获取 最后笔记的创建时间
     */
    public String getLastNoteCreateTime() {
        return this.lastNoteCreateTime;
    }

    /**
     * 设置 最后笔记的创建时间
     */
    public void setLastNoteCreateTime(String lastNoteCreateTime) {
        this.lastNoteCreateTime = lastNoteCreateTime;
    }

    /**
     * 获取 返回数据
     */
    public List<NoteModel> getNoteList() {
        return this.noteList;
    }

    /**
     * 设置 返回数据
     */
    public void setNoteList(List<NoteModel> noteList) {
        this.noteList = noteList;
    }
}
