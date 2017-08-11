package com.xiatianlong.controller.index;

import com.xiatianlong.common.enums.NavbarKey;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.service.ArticleService;
import com.xiatianlong.service.IndexService;
import com.xiatianlong.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * Created by xiatianlong on 2017/3/2.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private ArticleService articleService;

    /**
     * 首页
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("navbarKey", NavbarKey.HOME.getCode());

        // 列表
        model.addAttribute("list", indexService.getArticleAndNote());
        // 最新笔记列表
        model.addAttribute("newNoteList", noteService.getNewNoteListByIndex());
        // 最热笔记列表
        model.addAttribute("hotNoteList", noteService.getHotNoteListByIndex());
        // 最新文章列表
        model.addAttribute("newArticleList", articleService.getNewArticleListByIndex());
        // 最热文章列表
        model.addAttribute("hotArticleList", articleService.getHotArticleListByIndex());

        return "/view/index";
    }

    /**
     * 首页
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String index2(Model model){

        return "/view/test";
    }
}
