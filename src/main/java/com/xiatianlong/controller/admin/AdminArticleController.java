package com.xiatianlong.controller.admin;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.ArticleStatus;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.entity.XtlArticleEntity;
import com.xiatianlong.model.form.ArticleForm;
import com.xiatianlong.model.form.ArticleQueryPageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.service.ArticleService;
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
 * Article Controller
 * Created by xiatianlong on 2017/4/16.
 */
@Controller
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseController{

    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表页
     * @param form form
     * @param model model
     * @return  文章列表页
     */
    @RequestMapping(value = "/manage", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(ArticleQueryPageForm form, Model model){

        PageList pageList = articleService.getArticlePageList(form);
        List<XtlArticleEntity> articleEntityList = pageList.getResultList();
        model.addAttribute("list", articleService.getArticleModelList(articleEntityList));
        //数据总数
        model.addAttribute("dataCnt", pageList.getCount());
        // 当前页数
        model.addAttribute("pageNo", form.getPageNo());
        //每页显示数量
        model.addAttribute("pageSize", form.getPageSize());
        // 关键字
        model.addAttribute("keyword", form.getKeyWord());
        return "/admin/article/manage";
    }

    /**
     * 初始化新建文章画面
     * @return  string
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle(){

        return "/admin/article/newOrEdit";
    }

    /**
     * 初始化编辑文章画面
     * @return  string
     */
    @RequestMapping(value = "/edit/{articleId}", method = RequestMethod.GET)
    public String editArticle(@PathVariable("articleId") Integer articleId, Model model){

        XtlArticleEntity articleEntity = articleService.getArticle(articleId);
        if(articleEntity == null || ArticleStatus.DELETE.getCode().equals(articleEntity.getStatus())){
            model.addAttribute("message", getMessage("article.null"));
            return "404";
        }
        model.addAttribute("article", articleService.getArticleModel(articleEntity));
        return "/admin/article/newOrEdit";
    }

    /**
     * 文章画面预览 TODO
     * @return  文章预览
     */
    @RequestMapping(value = "/preview/{articleId}", method = RequestMethod.GET)
    public String preview(@PathVariable("articleId") Integer articleId, Model model){
        XtlArticleEntity articleEntity = articleService.getArticle(articleId);
        if(articleEntity == null){
            model.addAttribute("message", getMessage("article.null"));
            return "404";
        }
        model.addAttribute("article", articleService.getArticleModel(articleEntity));
        return "/admin/article/preview";
    }


    /**
     * 创建文章提交
     * @param form  表单
     * @param request   请求
     * @return  result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult createArticle(ArticleForm form, HttpServletRequest request){
        AsynchronousResult result = new AsynchronousResult();

        if(StringUtils.isEmpty(form.getTitle())){
            result.setMessage(getMessage("article.title.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getTitle().length() > 100){
            result.setMessage(getMessage("article.title.too.long", new Object[]{100}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getIntroduction())){
            result.setMessage(getMessage("article.introduction.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getContent())){
            result.setMessage(getMessage("article.content.null"));
            result.setResult(Common.FAIL);
            return result;
        }

        articleService.createArticle(form, request);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 编辑文章提交
     * @param form  表单
     * @return  result
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult updateArticle(ArticleForm form){
        AsynchronousResult result = new AsynchronousResult();

        if (form.getId() == null){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlArticleEntity articleEntity = articleService.getArticle(form.getId());
        if (articleEntity == null){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getTitle())){
            result.setMessage(getMessage("article.title.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getTitle().length() > 100){
            result.setMessage(getMessage("article.title.too.long", new Object[]{100}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getIntroduction())){
            result.setMessage(getMessage("article.introduction.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getContent())){
            result.setMessage(getMessage("article.content.null"));
            result.setResult(Common.FAIL);
            return result;
        }

        articleService.updateArticle(form, articleEntity);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 文章上线
     * @param articleId 文章ID
     * @return  result
     */
    @RequestMapping(value = "/online/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult online(@PathVariable("articleId") Integer articleId){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(articleId)){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlArticleEntity articleEntity = articleService.getArticle(articleId);
        if (articleEntity == null || ArticleStatus.DELETE.getCode().equals(articleEntity.getStatus())){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return articleService.online(articleEntity,result);
    }

    /**
     * 文章下线
     * @param articleId 文章ID
     * @return  result
     */
    @RequestMapping(value = "/offline/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult offline(@PathVariable("articleId") Integer articleId){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(articleId)){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlArticleEntity articleEntity = articleService.getArticle(articleId);
        if (articleEntity == null || ArticleStatus.DELETE.getCode().equals(articleEntity.getStatus())){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return articleService.offline(articleEntity,result);
    }

    /**
     * 文章是否推荐
     * @param articleId 文章ID
     * @param isRecommend 是否推荐
     * @return  result
     */
    @RequestMapping(value = "/recommend/{articleId}/{isRecommend}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult recommend(@PathVariable("articleId") Integer articleId, @PathVariable("isRecommend") boolean isRecommend){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(articleId)){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlArticleEntity articleEntity = articleService.getArticle(articleId);
        if (articleEntity == null || ArticleStatus.DELETE.getCode().equals(articleEntity.getStatus())){
            result.setMessage(getMessage("note.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return articleService.articleRecommend(articleEntity,result, isRecommend);
    }

    /**
     * 文章删除
     * @param articleId 文章ID
     * @return  result
     */
    @RequestMapping(value = "/delete/{articleId}", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult delete(@PathVariable("articleId") Integer articleId){
        AsynchronousResult result = new AsynchronousResult();
        if(StringUtils.isEmpty(articleId)){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlArticleEntity articleEntity = articleService.getArticle(articleId);
        if (articleEntity == null || ArticleStatus.DELETE.getCode().equals(articleEntity.getStatus())){
            result.setMessage(getMessage("article.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        return articleService.delete(articleEntity,result);
    }



}
