package com.xiatianlong.interceptor;

import com.xiatianlong.common.enums.RoleType;
import com.xiatianlong.entity.XtlUserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * DQ登录验证
 * 
 * @author lolli
 *
 */
public class DQInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// do nothing

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// do nothing

	}

	/**
	 * 拦截DQ请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		
		String reqURL = req.getRequestURL().toString();
		System.out.println(">>>>>>> requestURL: " + reqURL);

		// 判断当前用户是否登录，且判断是否为管理员或DQ
		XtlUserEntity user = (XtlUserEntity) req.getSession().getAttribute("SESSION_USER_DQ");
		if (user != null &&
				(RoleType.ADMIN.getCode().equals(user.getRoleCode())
						|| RoleType.DQ.getCode().equals(user.getRoleCode())
				)
			) {
			return true;
		} else {
			// 重定向到dq登录页面
			res.sendRedirect(req.getContextPath() + "/dq/login?reqUrl=" + URLEncoder.encode(reqURL, "utf-8"));
			return false;
		}
	}

}
