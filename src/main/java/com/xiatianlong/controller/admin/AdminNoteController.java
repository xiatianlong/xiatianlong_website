package com.xiatianlong.controller.admin;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.NoteStatus;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.entity.XtlNoteEntity;
import com.xiatianlong.model.form.NoteForm;
import com.xiatianlong.model.form.NoteQueryPageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.service.NoteService;
import com.xiatianlong.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Note Controller
 * Created by xiatianlong on 2017/5/31.
 */
@Controller
@RequestMapping("/admin/note")
public class AdminNoteController extends BaseController{

    @Autowired
    private NoteService noteService;


    /**
     * 笔记列表页
     * @param model model
     * @return  笔记列表页
     */
    @RequestMapping(value = "/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(NoteQueryPageForm form, Model model){

        PageList pageList = noteService.getNotePageList(form);
        List<XtlNoteEntity> noteEntityList = pageList.getResultList();
        model.addAttribute("list", noteService.getNoteModelList(noteEntityList));
        //数据总数
        model.addAttribute("dataCnt", pageList.getCount());
        // 当前页数
        model.addAttribute("pageNo", form.getPageNo());
        //每页显示数量
        model.addAttribute("pageSize", form.getPageSize());
        // 关键字
        model.addAttribute("keyword", form.getKeyWord());
        return "/admin/note/manage";
    }

    /**
     * 初始化新建笔记画面
     * @return  string
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle(){

        return "/admin/note/newOrEdit";
    }

    /**
     * 创建笔记提交
     * @param form  表单
     * @param request   请求
     * @return  result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult createNote(NoteForm form, HttpServletRequest request){
        AsynchronousResult result = new AsynchronousResult();

        if(StringUtils.isEmpty(form.getTitle())){
            result.setMessage(getMessage("note.title.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getTitle().length() > 100){
            result.setMessage(getMessage("note.title.too.long", new Object[]{100}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getTags().length == 0 || form.getTags().length > 5){
            result.setMessage(getMessage("note.tag.max.count", new Object[]{5}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getContent())){
            result.setMessage(getMessage("note.content.null"));
            result.setResult(Common.FAIL);
            return result;
        }

        noteService.createNote(form, request);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 初始化编辑笔记画面
     * @return  string
     */
    @RequestMapping(value = "/edit/{noteId}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("noteId") Integer noteId, Model model){

        XtlNoteEntity noteEntity = noteService.getNote(noteId);
        if(noteEntity == null || NoteStatus.DELETE.getCode().equals(noteEntity.getStatus())){
            model.addAttribute("message", getMessage("note.null"));
            return "404";
        }
        model.addAttribute("note", noteService.getNoteModel(noteEntity));
        return "/admin/note/newOrEdit";
    }


    /**
     * 编辑笔记提交
     * @param form  表单
     * @return  result
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult updateNote(NoteForm form){
        AsynchronousResult result = new AsynchronousResult();

        if (form.getId() == null){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlNoteEntity noteEntity = noteService.getNote(form.getId());
        if (noteEntity == null){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getTitle())){
            result.setMessage(getMessage("note.title.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getTitle().length() > 100){
            result.setMessage(getMessage("note.title.too.long", new Object[]{100}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getTags().length == 0 || form.getTags().length > 5){
            result.setMessage(getMessage("note.tag.max.count", new Object[]{5}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getContent())){
            result.setMessage(getMessage("note.content.null"));
            result.setResult(Common.FAIL);
            return result;
        }

        noteService.updateNote(form, noteEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }


    /**
     * 笔记上线
     * @param noteId 笔记ID
     * @return  result
     */
    @RequestMapping(value = "/online/{noteId}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult online(@PathVariable("noteId") Integer noteId){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(noteId)){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlNoteEntity noteEntity = noteService.getNote(noteId);
        if (noteEntity == null || NoteStatus.DELETE.getCode().equals(noteEntity.getStatus())){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return noteService.online(noteEntity,result);
    }

    /**
     * 笔记下线
     * @param noteId 笔记ID
     * @return  result
     */
    @RequestMapping(value = "/offline/{noteId}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult offline(@PathVariable("noteId") Integer noteId){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(noteId)){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlNoteEntity noteEntity = noteService.getNote(noteId);
        if (noteEntity == null || NoteStatus.DELETE.getCode().equals(noteEntity.getStatus())){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return noteService.offline(noteEntity,result);
    }

    /**
     * 笔记是否推荐
     * @param noteId 笔记ID
     * @param isRecommend 是否推荐
     * @return  result
     */
    @RequestMapping(value = "/recommend/{noteId}/{isRecommend}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult recommend(@PathVariable("noteId") Integer noteId, @PathVariable("isRecommend") boolean isRecommend){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(noteId)){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlNoteEntity noteEntity = noteService.getNote(noteId);
        if (noteEntity == null || NoteStatus.DELETE.getCode().equals(noteEntity.getStatus())){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return noteService.noteRecommend(noteEntity,result, isRecommend);
    }

    /**
     * 笔记删除
     * @param noteId 笔记ID
     * @return  result
     */
    @RequestMapping(value = "/delete/{noteId}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult delete(@PathVariable("noteId") Integer noteId){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(noteId)){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlNoteEntity noteEntity = noteService.getNote(noteId);
        if (noteEntity == null || NoteStatus.DELETE.getCode().equals(noteEntity.getStatus())){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return noteService.delete(noteEntity,result);
    }

}
