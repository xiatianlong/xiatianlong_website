package com.xiatianlong.controller.admin;

import com.xiatianlong.controller.BaseController;
import com.xiatianlong.service.ArticleService;
import com.xiatianlong.service.MessageService;
import com.xiatianlong.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Properties;

/**
 * 首页
 * Created by xiatianlong on 2017/3/2.
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private MessageService messageService;

    /**
     * 首页
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){

        Properties props=System.getProperties(); //获得系统属性集
        model.addAttribute("osName", props.getProperty("os.name"));//操作系统名称
        model.addAttribute("osArch", props.getProperty("os.arch"));//操作系统构架
        model.addAttribute("javaVersion", props.getProperty("java.version"));// jdk版本
        model.addAttribute("currentDir", props.getProperty("user.dir"));// 当前工作目录

        model.addAttribute("onlineArticleCnt", articleService.getArticleCntByOnline());
        model.addAttribute("offlineArticleCnt", articleService.getArticleCntByOffline());
        model.addAttribute("onlineNoteCnt", noteService.getNoteCntByOnline());
        model.addAttribute("offlineNoteCnt",  noteService.getNoteCntByOffline());
        model.addAttribute("messageAllCnt", messageService.getMessageCount(false));
        model.addAttribute("unReadMessageCnt", messageService.getMessageCount(true));

        return "/admin/index";
    }
}
