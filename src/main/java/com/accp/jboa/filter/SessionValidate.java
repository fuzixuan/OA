package com.accp.jboa.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @ClassName: SessionValidate
 * @Description: Sesssion过滤器
 * @author: wy
 * @date: 2018年6月24日 下午1:29:13
 * @version: V1.0
 */
public class SessionValidate implements Filter {

    public SessionValidate() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		// 获得请求uri(/WebFilter/c/user?f=login)
		String uri = request.getRequestURI();
		if (uri.contains("/user") || uri.contains("/login.jsp")||uri.contains("/sessionerror.jsp")) {
			chain.doFilter(request, response);
		}else if(uri.contains(".jsp")){
			//验证session
			if (session.getAttribute("EMP") == null) {
				response.sendRedirect("/jboa/sessionerror.jsp");
			} else {
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
