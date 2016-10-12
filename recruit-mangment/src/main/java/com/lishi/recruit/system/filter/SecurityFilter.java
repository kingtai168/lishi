package com.lishi.recruit.system.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lishi.recruit.service.user.model.UserDTO;
import com.lishi.recruit.system.Constants;


public class SecurityFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterCon = null;
	
	public void init(FilterConfig config) throws ServletException {
		filterCon = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		UserDTO userInfo = (UserDTO) httpRequest.getSession().getAttribute(Constants.USERINFO);
		String str=httpRequest.getRequestURL().toString();
		String contextPath=httpRequest.getContextPath();
		httpRequest.getSession().setAttribute("path", contextPath);
		if(userInfo==null){
			if(str.indexOf("/login.jsp")==-1){
				httpResponse.sendRedirect(contextPath+"/view/login.jsp");
			}else{
				filterChain .doFilter(request, response);
			}
		}else{
			filterChain .doFilter(request, response);		
		}
	}

	public void destroy() {
		filterCon = null;
	}
}
