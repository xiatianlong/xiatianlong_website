package com.xiatianlong.controller.index;

import com.xiatianlong.common.enums.NavbarKey;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.service.IndexService;
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
        // 最新列表
        model.addAttribute("newList", indexService.getArticleAndNoteByNew());
        // 最热列表
        model.addAttribute("hotList", indexService.getArticleAndNoteByHot());

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
