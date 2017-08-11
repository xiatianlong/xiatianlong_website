package com.xiatianlong.controller.index;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.NavbarKey;
import com.xiatianlong.common.enums.NoteStatus;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.entity.XtlNoteEntity;
import com.xiatianlong.model.NoteModel;
import com.xiatianlong.model.form.IndexNoteQueryForm;
import com.xiatianlong.model.response.IndexNoteResult;
import com.xiatianlong.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Note Controller
 * Created by xiatianlong on 2017/4/16.
 */
@Controller
@RequestMapping("/note")
public class NoteController extends BaseController{

    @Autowired
    private NoteService noteService;

    /**
     * 笔记列表页
     * @param model model
     * @return  笔记列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("navbarKey", NavbarKey.NOTE.getCode());

        List<NoteModel> noteList = noteService.getNoteList();
        model.addAttribute("noteList", noteList);
        if (noteList != null && !noteList.isEmpty()){
            model.addAttribute("lastNoteCreateTime", noteList.get(noteList.size()-1).getCreateTime());
        }

        model.addAttribute("tags", noteService.getTagView());

        return "/view/note/list";
    }

    /**
     * 条件查询笔记列表/加载更多
     * @param queryForm 查询表单
     * @return  笔记列表
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public IndexNoteResult listPost(IndexNoteQueryForm queryForm){

        IndexNoteResult result = new IndexNoteResult();

        List<NoteModel> noteList = noteService.getNoteListBySearch(queryForm, true);
        if (noteList != null && !noteList.isEmpty()){
            result.setLastNoteCreateTime(noteList.get(noteList.size()-1).getCreateTime());
            result.setNoteList(noteList);
            result.setResult(Common.SUCCESS);
        }else{
            result.setMessage(getMessage("note.ajax.nodata"));
            result.setResult(Common.FAIL);
        }
        return result;
    }

    /**
     * 条件查询笔记列表/加载更多
     * @param queryForm 查询表单
     * @return  笔记列表
     */
    @RequestMapping(value = "tagSearch", method = RequestMethod.POST)
    @ResponseBody
    public IndexNoteResult tagSearch(IndexNoteQueryForm queryForm){

        IndexNoteResult result = new IndexNoteResult();
        List<NoteModel> noteList = noteService.getNoteListBySearch(queryForm, false);
        if (noteList != null && !noteList.isEmpty()){
            result.setLastNoteCreateTime(noteList.get(noteList.size()-1).getCreateTime());
            result.setNoteList(noteList);
            result.setResult(Common.SUCCESS);
        }else{
            result.setResult(Common.FAIL);
        }
        return result;
    }

    /**
     * 笔记详情页
     * @param model model
     * @return  笔记详情页
     */
    @RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
    public String index(@PathVariable("noteId") Integer noteId, Model model){
        model.addAttribute("navbarKey", NavbarKey.NOTE.getCode());

        XtlNoteEntity note = noteService.getNote(noteId);

        if (note == null || !NoteStatus.SHOW.getCode().equals(note.getStatus())){
            model.addAttribute("message", getMessage("note.null"));
            return "404";
        }

        // 更新文章浏览数量
        noteService.addNoteBrowseTimes(note);

        model.addAttribute("note", noteService.getNoteModel(note));

        return "/view/note/detail";
    }
}
