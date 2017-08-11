package com.xiatianlong.controller.index;

import com.xiatianlong.common.Common;
import com.xiatianlong.common.enums.DQNavbarKey;
import com.xiatianlong.common.enums.NavbarKey;
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
 * DQ
 * Created by xiatianlong on 2017/6/2.
 */
@Controller
@RequestMapping("/dq")
public class DQController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 首页
     * @param reqUrl 请求url
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String reqUrl, Model model){
        model.addAttribute("navbarKey", NavbarKey.DQ.getCode());

        model.addAttribute("reqUrl", reqUrl);
        return "view/dq/login";
    }

    /**
     * 登录处理
     * @param userName  用户名
     * @param password  密码
     * @param valiCode  验证码
     * @param session   session
     * @return  return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
        // DQ 和 管理员可以访问
        if (!RoleType.DQ.getCode().equals(user.getRoleCode()) && !RoleType.ADMIN.getCode().equals(user.getRoleCode())){
            result.setMessage(getMessage("user.role.error"));
            result.setResult(Common.FAIL);
            return result;
        }
        if (!UserStatus.NORMAL.getCode().equals(user.getStatus())){
            result.setMessage(getMessage("user.status.error", new String[]{DictionaryCache.getName(user.getStatus())}));
            result.setResult(Common.FAIL);
            return result;
        }
        session.setAttribute("SESSION_USER_DQ", user);
        result.setResult(Common.SUCCESS);
        return result;
    }

    /**
     * 首页
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("dq_navbarKey", DQNavbarKey.HOME.getCode());

        return "view/dq/index";
    }

    /**
     * 留言板
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(Model model){
        model.addAttribute("dq_navbarKey", DQNavbarKey.MESSAGE.getCode());

        return "view/dq/messageList";
    }

    /**
     * 照片墙
     * @param model model
     * @return  首页
     */
    @RequestMapping(value = "/photos", method = RequestMethod.GET)
    public String photos(Model model){
        model.addAttribute("dq_navbarKey", DQNavbarKey.PHOTOS.getCode());

        return "view/dq/photoList";
    }
}
