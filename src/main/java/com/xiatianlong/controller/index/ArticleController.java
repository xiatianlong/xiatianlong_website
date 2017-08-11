package com.xiatianlong.controller.index;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.ArticleStatus;
import com.xiatianlong.common.enums.NavbarKey;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.entity.XtlArticleEntity;
import com.xiatianlong.model.ArticleTimeMonthModel;
import com.xiatianlong.model.form.ArticleQueryPageForm;
import com.xiatianlong.model.response.IndexArticleTimeLineResult;
import com.xiatianlong.service.ArticleService;
import com.xiatianlong.utils.DateUtil;
import com.xiatianlong.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Article Controller
 * Created by xiatianlong on 2017/4/16.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    /**
     * 文章列表页（时间轴、同步）
     * @param model model
     * @return  文章列表页
     */
    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
    public String index( Model model){
        model.addAttribute("navbarKey", NavbarKey.ARTICLE.getCode());

        // step1 ： 查询到列表
        List<XtlArticleEntity> articleEntityList = articleService.getArticleListByIndex(null);
        // step2 : 封装成时间轴数据
        List<ArticleTimeMonthModel> list = articleService.articleTimeModelList(articleEntityList);

        model.addAttribute("articleList", list);
//        if(articleEntityList != null && !articleEntityList.isEmpty()){
            model.addAttribute("lastDate", DateUtil.getDateMonthFirstDay(DateUtil.getFormatString(articleEntityList.get(articleEntityList.size()-1).getCreateTime(), DateUtil.dateformatter_YYMM)));
//        }
        return "/view/article/timeline";
    }

    /**
     * 文章列表页(时间轴、异步)
     * @param lastDate 列表最后的日期
     * @return  文章列表页
     */
    @RequestMapping(value = "/timeline", method = RequestMethod.POST)
    @ResponseBody
    public IndexArticleTimeLineResult indexAjax(String lastDate){

        IndexArticleTimeLineResult result = new IndexArticleTimeLineResult();

        // step1 ： 查询到列表
        List<XtlArticleEntity> articleEntityList = articleService.getArticleListByIndex(lastDate);
        if (articleEntityList!=null && !articleEntityList.isEmpty()){
            // step2 : 封装成时间轴数据
            List<ArticleTimeMonthModel> list = articleService.articleTimeModelList(articleEntityList);

            result.setLastDate(DateUtil.getDateMonthFirstDay(DateUtil.getFormatString(articleEntityList.get(articleEntityList.size()-1).getCreateTime(), DateUtil.dateformatter_yyyy_MM_dd)));
            result.setDataList(list);

            result.setResult(Common.SUCCESS);
        }else{
            result.setMessage(getMessage("article.timeline.nodata"));
            result.setResult(Common.FAIL);
        }
        return result;
    }

    /**
     * 文章页列表
     * @param form  请求表单
     * @param model model
     * @return  list
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(ArticleQueryPageForm form, Model model){
        model.addAttribute("navbarKey", NavbarKey.ARTICLE.getCode());

        PageList pageList = articleService.getArticlePageListByIndex(form);
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
        return "/view/article/list";
    }

    /**
     * 文章详情页
     * @param model model
     * @return  文章详情页
     */
    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public String index(@PathVariable("articleId") Integer articleId, Model model){
        model.addAttribute("navbarKey", NavbarKey.ARTICLE.getCode());

        XtlArticleEntity article = articleService.getArticle(articleId);

        if (article == null || !ArticleStatus.SHOW.getCode().equals(article.getStatus())){
            model.addAttribute("message", getMessage("article.null"));
            return "404";
        }

        // 更新文章浏览数量
        articleService.addArticleBrowseTimes(article);

        model.addAttribute("article", articleService.getArticleModel(article));

        return "/view/article/detail";
    }
}
