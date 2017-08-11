package com.xiatianlong.controller.admin;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.RoleType;
import com.xiatianlong.common.enums.UserStatus;
import com.xiatianlong.controller.BaseController;
import com.xiatianlong.dictionary.DictionaryCache;
import com.xiatianlong.entity.XtlUserEntity;
import com.xiatianlong.model.response.AsynchronousResult;
import com.xiatianlong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Login Controller
 * Created by xiatianlong on 2017/5/7.
 */
@Controller
@RequestMapping("/admin/login")
public class AdminLoginController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 登录页
     * @param reqUrl    请求的url
     * @param model model
     * @return  登录画面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(String reqUrl, Model model){
        model.addAttribute("reqUrl", reqUrl);
        return "/admin/login";
    }

    /**
     * 登录处理
     * @return  登录
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public AsynchronousResult doLogin(String userName, String password, String valiCode, HttpSession session){
        AsynchronousResult result = new AsynchronousResult();
        if (StringUtils.isEmpty(userName)){
            result.setMessage(getMessage("login.username.email.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if (StringUtils.isEmpty(password)){
            result.setMessage(getMessage("login.password.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if (StringUtils.isEmpty(valiCode)){
            result.setMessage(getMessage("login.checkCode.null"));
            result.setResult(Common.FAIL);
            return result;
        }
        if (!session.getAttribute("sessionImgCode").equals(valiCode.toUpperCase())){
            result.setMessage(getMessage("login.checkCode.error"));
            result.setResult(Common.FAIL);
            return result;
        }
        XtlUserEntity user = userService.login(userName, password);
        if (user == null){
            result.setMessage(getMessage("login.username.or.password.fail"));
            result.setResult(Common.FAIL);
            return result;
        }
        if (!RoleType.ADMIN.getCode().equals(user.getRoleCode())){
            result.setMessage(getMessage("user.role.error"));
            result.setResult(Common.FAIL);
            return result;
        }
        if (!UserStatus.NORMAL.getCode().equals(user.getStatus())){
            result.setMessage(getMessage("user.status.error", new String[]{DictionaryCache.getName(user.getStatus())}));
            result.setResult(Common.FAIL);
            return result;
        }
        session.setAttribute("SESSION_USER_ADMIN", user);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 注销 退出
     * @return  登录页
     */
    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String SignOut(HttpSession session){
        session.removeAttribute("SESSION_USER_ADMIN");
        return "redirect:/admin/login";
    }


}
