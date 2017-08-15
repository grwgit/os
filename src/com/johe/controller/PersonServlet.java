package com.johe.controller;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.johe.service.CrudService;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet({"/toPersonData","/toRetrievePasswd","/doCheckUser","/doCheckPasswd","/doCheckPasswdAgain"})
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CrudService cService = new CrudService();
		String path = request.getServletPath();
		HttpSession session = request.getSession(true);
//		response.setContentType("text/html;utf-8");
		if(path.equals("/toPersonData")) {
			request.setAttribute("em", cService.queryEm(request.getParameter("uid")));
			request.getRequestDispatcher("emUi/personData.jsp").forward(request, response);
		}else if(path.equals("/toRetrievePasswd")) {
			request.getRequestDispatcher("emUi/passwdOld.jsp").forward(request, response);
		}else if(path.equals("/doCheckUser")) {
			if(cService.checkUser(request.getParameter("passwdOld"),session)) {
				request.getRequestDispatcher("emUi/passwdNew1.jsp").forward(request, response);
			}else {
				session.setAttribute("oldPasswd", "√‹¬Î≤ª∂‘");
				request.getRequestDispatcher("emUi/index.jsp").forward(request, response);
				session.removeAttribute("oldPasswd");
			}
		}else if(path.equals("/doCheckPasswd")) {
			session.setAttribute("passwdNew1", request.getParameter("passwd"));
			request.getRequestDispatcher("emUi/passwdNew2.jsp").forward(request, response);
		}else if(path.equals("/doCheckPasswdAgain")) {
			session.setAttribute("passwdNew1Again", request.getParameter("passwdAgain"));
			cService.checkPasswd(session);
			request.getRequestDispatcher("emUi/index.jsp").forward(request, response);
			session.removeAttribute("changePasswdSucc");
			session.removeAttribute("twoPasswdAgree");
			session.removeAttribute("twoPasswdDisagree");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
