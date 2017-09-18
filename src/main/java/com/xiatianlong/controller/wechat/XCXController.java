package com.xiatianlong.controller.wechat;

import com.xiatianlong.common.Common;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.model.response.xiaochengxu.ArticleResult;
import com.xiatianlong.model.response.xiaochengxu.ArticleResultModel;
import com.xiatianlong.model.response.xiaochengxu.NoteResult;
import com.xiatianlong.model.response.xiaochengxu.NoteResultModel;
import com.xiatianlong.service.ArticleService;
import com.xiatianlong.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        List<ArticleResultModel> resultList = articleService.getArticleListByXcx(null);
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
    @RequestMapping(value = "/article/more", method = RequestMethod.POST)
    @ResponseBody
    public ArticleResult articleGetMore(Integer id){
        ArticleResult result = new ArticleResult();
        if (id == null){
            result.setMessage(getMessage("request.error"));
            return result;
        }
        List<ArticleResultModel> resultList = articleService.getArticleListByXcx(id);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
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
        List<NoteResultModel> resultList = noteService.getNoteListByXcx(null);
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
    @RequestMapping(value = "/note/more", method = RequestMethod.POST)
    @ResponseBody
    public NoteResult noteGetMore(Integer id){
        NoteResult result = new NoteResult();
        if (id == null){
            result.setMessage(getMessage("request.error"));
            return result;
        }
        List<NoteResultModel> resultList = noteService.getNoteListByXcx(id);
        if(resultList != null && !resultList.isEmpty()){
            result.setResult(Common.SUCCESS);
            result.setDataList(resultList);
        }else{
            result.setMessage(getMessage("no.more.data"));
        }
        return result;
    }

}
