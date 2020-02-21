package com.accp.jboa.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: CharsetEncodingFilter
 * @Description: 字符过滤器
 * @author: wy
 * @date: 2018年6月24日 下午1:28:58
 * @version: V1.0
 */
public class CharsetEncodingFilter implements Filter {

	private String charset = "UTF-8";

	/**
	 * Default constructor.
	 */
	public CharsetEncodingFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("过滤器GO！");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setContentType("text/html;charset=" + charset);
		response.setCharacterEncoding(charset);
		String methodType = request.getMethod();
		if ("post".equalsIgnoreCase(methodType)) {
			request.setCharacterEncoding(charset);
			chain.doFilter(request, response);
		} else if ("get".equalsIgnoreCase(methodType)) {
			// 匿名子类完成request包装
			HttpServletRequestWrapper hrw = new HttpServletRequestWrapper(request) {
				@Override
				public String getParameter(String name) {
					String srcValue = super.getParameter(name);
					if (srcValue != null) {
						try {
							srcValue = new String(srcValue.getBytes("ISO-8859-1"), charset);
						} catch (UnsupportedEncodingException e) {
							srcValue = null;
						}
					}
					return srcValue;
				}

				@Override
				public String[] getParameterValues(String name) {
					String[] srcValue = super.getParameterValues(name);
					if (srcValue != null) {
						for (int i = 0; i < srcValue.length; i++) {
							try {
								srcValue[i] = new String(srcValue[i].getBytes("ISO-8859-1"), charset);
							} catch (UnsupportedEncodingException e) {
								srcValue[i] = null;
							}
						}
					}
					return srcValue;
				}

			};
			chain.doFilter(hrw, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		charset = fConfig.getInitParameter("encoding");
	}

}
