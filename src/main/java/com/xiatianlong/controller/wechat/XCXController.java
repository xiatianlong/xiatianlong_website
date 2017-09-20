package com.xiatianlong.controller.wechat;

import com.xiatianlong.common.Common;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.model.response.xiaochengxu.*;
import com.xiatianlong.service.ArticleService;
import com.xiatianlong.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 小程序相关接口Controller
 * Created by xiatianlong on 2017/8/22.
 */
@Controller
@RequestMapping("/xiaochengxu")
public class XCXController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private NoteService noteService;
    /**
     * 文章初始化
     * @return  文章初始化
     */
    @RequestMapping(value = "/article/init", method = RequestMethod.GET)
    @ResponseBody
    public ArticleResult articleInit(){
        ArticleResult result = new ArticleResult();
        List<ArticleResultModel> resultList = articleService.getArticleListByXcx(null, null);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

    /**
     * 文章加载更多
     * @return  加载更多
     */
    @RequestMapping(value = "/article/{lastId}/more", method = RequestMethod.POST)
    @ResponseBody
    public ArticleResult articleGetMore(@PathVariable("lastId") Integer lastId, String keyword){
        ArticleResult result = new ArticleResult();
        if (lastId == null){
            result.setMessage(getMessage("request.error"));
            return result;
        }
        List<ArticleResultModel> resultList = articleService.getArticleListByXcx(lastId, keyword);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

    /**
     * 文章搜索
     * @return  加载更多
     */
    @RequestMapping(value = "/article/search", method = RequestMethod.POST)
    @ResponseBody
    public ArticleResult articleGetMore(String keyword){
        ArticleResult result = new ArticleResult();
        List<ArticleResultModel> resultList = articleService.getArticleListByXcx(null, keyword);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

    /**
     * 文章详情
     * @return  文章
     */
    @RequestMapping(value = "/article/{articleId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public ArticleResult articleDetail(@PathVariable("articleId") Integer articleId){
        ArticleResult result = new ArticleResult();
        ArticleDetailModel detail = articleService.getArticleDetailByXcx(articleId);
        if(detail != null){
            result.setResult(Common.SUCCESS);
            result.setData(detail);
        }else{
            result.setMessage(getMessage("request.obj.null"));
        }
        return result;
    }

    /**
     * 笔记初始化
     * @return  笔记初始化
     */
    @RequestMapping(value = "/note/init", method = RequestMethod.GET)
    @ResponseBody
    public NoteResult noteInit(){
        NoteResult result = new NoteResult();
        List<NoteResultModel> resultList = noteService.getNoteListByXcx(null, null);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

    /**
     * 笔记加载更多
     * @return  笔记加载更多
     */
    @RequestMapping(value = "/note/{lastId}/more", method = RequestMethod.POST)
    @ResponseBody
    public NoteResult noteGetMore(@PathVariable("lastId") Integer lastId, String keyword){
        NoteResult result = new NoteResult();
        if (lastId == null){
            result.setMessage(getMessage("request.error"));
            return result;
        }
        List<NoteResultModel> resultList = noteService.getNoteListByXcx(lastId, keyword);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

    /**
     * 笔记检索
     * @return  笔记加载更多
     */
    @RequestMapping(value = "/note/search", method = RequestMethod.POST)
    @ResponseBody
    public NoteResult noteGetMore(String keyword){
        NoteResult result = new NoteResult();
        List<NoteResultModel> resultList = noteService.getNoteListByXcx(null, keyword);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

    /**
     * 笔记详情
     * @return  笔记
     */
    @RequestMapping(value = "/note/{noteId}/detail", method = RequestMethod.GET)
    @ResponseBody
    public NoteResult noteDetail(@PathVariable("noteId") Integer noteId){
        NoteResult result = new NoteResult();
        NoteDetailModel detail = noteService.getNoteDetailByXcx(noteId);
        if(detail != null){
            result.setResult(Common.SUCCESS);
            result.setData(detail);
        }else{
            result.setMessage(getMessage("request.obj.null"));
        }
        return result;
    }

}
