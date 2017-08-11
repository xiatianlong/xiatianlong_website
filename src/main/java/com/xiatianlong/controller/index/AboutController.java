package com.xiatianlong.controller.index;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.NavbarKey;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.model.form.MessageForm;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 * Created by xiatianlong on 2017/6/2.
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {

    @Autowired
    private MessageService messageService;

    /**
     * 首页
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("navbarKey", NavbarKey.ABOUT.getCode());

        return "view/about/init";
    }

    /**
     * 首页
     * @return  首页
     */
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult index2(MessageForm form){
        AsynchronousResult result = new AsynchronousResult();

        if(StringUtils.isEmpty(form.getUserName())){
            result.setMessage(getMessage("message.name.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if(form.getUserName().length() > 20){
            result.setMessage(getMessage("message.name.length", new Object[]{20}));
            result.setResult(Common.FAIL);
            return result;
        }
        if(StringUtils.isEmpty(form.getContent())){
            result.setMessage(getMessage("message.content.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        messageService.addMessage(form);
        result.setResult(Common.SUCCESS);
        return result;
    }
}
