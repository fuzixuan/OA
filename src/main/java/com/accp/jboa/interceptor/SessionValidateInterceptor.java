package com.accp.jboa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: SessionValidateInterceptor
 * @Description: Session拦截器
 * @author: wy
 * @date: 2018年6月24日 下午1:29:40
 * @version: V1.0
 */
public class SessionValidateInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("拦截器GO！");
		if(request.getSession().getAttribute("EMP")==null) {
			response.sendRedirect("/jboa/sessionerror.jsp");
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
