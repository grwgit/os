package com.johe.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//@WebFilter({"/*"})
public class Filter extends HttpFilter {

	private String encoding;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
		encoding = filterConfig.getInitParameter("encoding");
	}
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		String path = request.getServletPath();
		HttpSession session = request.getSession();
		System.err.println("path:"+ path);
		/*System.err.println("####"+request.getRequestURI());
		chain.doFilter(request, response);*/
		
		/*
		 *禁用缓存
		 */
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		response.addHeader("Cache-Control","pre-check=0, post-check=0");
		response.setDateHeader("Expires", 0);
		
//		System.err.println(Thread.currentThread().getName());
		/*
		 * 单点登录，未登录，资源不可访问，自动跳转到登录界面
		 */
		if(session.getAttribute("user")!=null&&session.getAttribute("passwd")!=null||path.equals("/login.do")||path.equals("/doLogin.do")||path.equals("/doCoptInfo")||path.equals("/os04/loginUi/completeInfo.jsp")||path.equals("/image/oa_bg2.jpg")) 		
			chain.doFilter(request, response);
		else {
			System.out.println("##### Redirect!");
			response.sendRedirect("/os04/login.do");
//			chain.doFilter(request, response);
		}
		
		/*Thread threadLocal = Thread.currentThread();
		System.err.println(threadLocal.get()+"###@@@");*/
//		Thread.currentThread().
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
