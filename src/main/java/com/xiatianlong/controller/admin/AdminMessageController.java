package com.xiatianlong.controller.admin;

import com.xiatianlong.common.Common;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.entity.XtlMessageEntity;
import com.xiatianlong.model.form.MessageQueryPageForm;
import com.xiatianlong.model.response.MessageResult;
import com.xiatianlong.service.MessageService;
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
 * Message Controller
 * Created by xiatianlong on 2017/5/31.
 */
@Controller
@RequestMapping("/admin/message")
public class AdminMessageController extends BaseController{

    @Autowired
    private MessageService messageService;


    /**
     * 笔记列表页
     * @param model model
     * @return  笔记列表页
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(MessageQueryPageForm form, Model model){

        PageList pageList =  messageService.getMessagePageList(form);
        List<XtlMessageEntity> messageEntityList = pageList.getResultList();

        model.addAttribute("list", messageService.getMessageModelList(messageEntityList));
        //数据总数
        model.addAttribute("dataCnt", pageList.getCount());
        // 当前页数
        model.addAttribute("pageNo", form.getPageNo());
        //每页显示数量
        model.addAttribute("pageSize", form.getPageSize());
        // 关键字
        model.addAttribute("keyword", form.getKeyWord());
        return "/admin/message/list";
    }

    @RequestMapping(value = "/view/{messageId}", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult view(@PathVariable("messageId") Integer messageId){
        MessageResult result = new MessageResult();

        XtlMessageEntity messageEntity = messageService.getMessage(messageId);
        if (messageEntity == null){
            result.setMessage(getMessage("message.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        // 设置为已读
        messageService.setMessageView(messageEntity);

        result.setData(messageService.getMessageModel(messageEntity));
        result.setResult(Common.SUCCESS);
        return result;
    }

}
