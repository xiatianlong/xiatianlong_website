package com.xiatianlong.controller.wechat;

import com.xiatianlong.common.Common;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.model.response.xiaochengxu.ArticleResult;
import com.xiatianlong.model.response.xiaochengxu.ArticleResultModel;
import com.xiatianlong.service.ArticleService;
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



}
