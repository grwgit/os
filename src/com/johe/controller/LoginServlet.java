package com.johe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.johe.bean.Employee;
import com.johe.bean.User;
import com.johe.service.LoginService;
import com.johe.util.Utils;

@WebServlet({"/login.do","/doLogin.do","/doCoptInfo","/doLoginOut","/doLogOut"})
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		LoginService lservice = new LoginService();
		HttpSession session = req.getSession(true);
		Utils utils = new Utils();
		if(path.equals("/login.do")) {
			req.getRequestDispatcher("loginUi/login.jsp").forward(req, resp);
//			resp.sendRedirect("loginUi/login.jsp");
		}else if(path.equals("/doLogin.do")) {
			User user = new User();
			try {
				utils.fillBean(req, user);
			} catch (Exception e) {
				e.printStackTrace();
			} 
//			System.out.println(session.getAttribute("user"));
			req.getRequestDispatcher(lservice.checkUser(session,user)).forward(req,resp);
			/*
			 * 移除登录用户名密码
			 */
//			session.invalidate();
			session.removeAttribute("userNull");
			session.removeAttribute("userNone");
			session.removeAttribute("passwdError");
			session.removeAttribute("forbidden");
		}else if(path.equals("/doCoptInfo")) {
			/*
			 * 修改users和employee表
			 */
			Employee em = new Employee();
			User user = new User();
			try {
				/*System.err.println("$$$$$$"+req.getParameter("esex"));
				System.err.println(req.getParameter("eage"));
				*/
				utils.fillBean(req, em);
				utils.fillBean(req, user);
//				System.err.println(em.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			lservice.coptInfo(em,user);
			session.setAttribute("user", user.getUid());
			session.setAttribute("passwd", user.getPasswd());
			/*
			 * 跳转到登录界面
			 */
			req.getRequestDispatcher("loginUi/login.jsp").forward(req, resp);
		}else if(path.equals("/doLoginOut")) {
			session.setAttribute("type", "loginOut");
			req.getRequestDispatcher("loginUi/login.jsp").forward(req, resp);
		}else if(path.equals("/doLogOut")) {
			session.invalidate();
			req.getRequestDispatcher("loginUi/login.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
