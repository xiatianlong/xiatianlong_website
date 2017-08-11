package com.xiatianlong.exception;

import com.xiatianlong.service.CommonService;
import com.xiatianlong.utils.EmailUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * ExceptionHandler
 * 全局异常处理，捕获异常发到邮箱
 * Created by xiatianlong on 2017/6/2.
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    @Autowired
    private CommonService commonService;

    /**
     * 日志
     */
    protected final Logger log = Logger.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView view = new ModelAndView();
        // 去错误500画面
        view.setViewName("500");

        // 打印异常信息
        log.error("exception begin ---------------  ");
        e.printStackTrace();
        // 发送异常到管理员邮箱

        // 获取整个错误栈
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        e.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // 存储数据库
        commonService.addErrorLog(request.getRemoteAddr(), request.getRequestURL().toString(), ret);
        // 异步发送错误邮件
        new Thread(new sendError(request.getRequestURL().toString(), request.getRemoteAddr(),ret)).start();
        return view;
    }

    /**
     * 发送邮件
     */
    private class sendError implements Runnable {
        private String url;
        private String ip;
        private String errorMessage;
        public sendError(String url, String ip, String errorMessage) {
            this.url = url;
            this.ip = ip;
            this.errorMessage = errorMessage;
        }
        @Override
        public void run() {
            EmailUtil.sendErrorEmail(url,ip,errorMessage);
        }
    }
}
