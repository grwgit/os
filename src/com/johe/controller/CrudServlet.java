package com.johe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.johe.bean.Department;
import com.johe.bean.Employee;
import com.johe.bean.Leave;
import com.johe.bean.User;
import com.johe.dto.EmployeeDto;
import com.johe.dto.UserDto;
import com.johe.service.CrudService;
import com.johe.util.Utils;

/**
 * Servlet implementation class CrudServlet
 */
@WebServlet({"/CrudServlet","/doAddEmployee","/doAddDepartment","/doUserQuery","/doDelUser","/doEmQuery",
	"/doDelEm","/doDepQuery","/doDelDep","/doUpdEm","/toUpdEm","/doMutiQuery","/doMutiQueryEm","/doMutiQueryDep",
	"/doPaging"})
public class CrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static String DATE_FORMAT="yyyy-MM-dd";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		CrudService cService = new CrudService();
		HttpSession session = request.getSession();
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;UTF-8");
		response.setContentType("text/html;charset=GBK");
		DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Utils utils = new Utils();
		if(path.equals("/doAddEmployee")) {
			Employee em = new Employee();
			try {
				utils.fillBean(request, em);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cService.addEmployee(em);
			request.getRequestDispatcher("loginUi/index.jsp").forward(request, response);;
		}else if(path.equals("/doAddDepartment")) {
			Department dep = new Department();
			try {
				utils.fillBean(request, dep);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cService.addDepartment(dep);
			request.getRequestDispatcher("loginUi/index.jsp").forward(request, response);;
		}else if(path.equals("/doUserQuery")) {
			request.setAttribute("userBeans", cService.queryAllUser());
			request.getRequestDispatcher("adminUi/userQuery.jsp").forward(request, response);
			request.removeAttribute("userBeans");
		}else if(path.equals("/doDelUser")) {
			cService.delUser(request.getParameter("uid"));
			request.getRequestDispatcher("doUserQuery").forward(request, response);
		}else if(path.equals("/doEmQuery")) {
			request.setAttribute("emBeans", cService.queryAllEm());
			request.setAttribute("currentPage", 1);
			request.getRequestDispatcher("adminUi/emQuery.jsp").forward(request, response);
		}else if(path.equals("/doDelEm")) {
			cService.delEm(request.getParameter("eid"));
			request.getRequestDispatcher("doEmQuery").forward(request, response);
		}else if(path.equals("/doDepQuery")) {
			request.setAttribute("depBeans", cService.queryAllDep());
			request.getRequestDispatcher("adminUi/depQuery.jsp").forward(request, response);
		}else if(path.equals("/doDelDep")) {
			PrintWriter out = response.getWriter();
			cService.delDep(request.getParameter("did"),out);
			response.sendRedirect("/os04/doDepQuery");
		}else if(path.equals("/toUpdEm")) { 
			request.setAttribute("em", cService.queryEmByEid(request.getParameter("eid")));
			request.getRequestDispatcher("adminUi/emUpd.jsp").forward(request, response);
		}else if(path.equals("/doUpdEm")) {
			Employee em = new Employee();
			try {
				utils.fillBean(request, em);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cService.updEm(em);
			response.sendRedirect("/os04/doEmQuery");
		}else if(path.equals("/doMutiQuery")) {
			UserDto dto = new UserDto();
			try {
				utils.fillBean(request, dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("userBeans", cService.queryAllUser(dto));
			request.getRequestDispatcher("adminUi/userQuery.jsp").forward(request, response);
			request.removeAttribute("userBeans");
		}else if(path.equals("/doMutiQueryEm")) {
			EmployeeDto emDto = new EmployeeDto();
			try {
				utils.fillBean(request, emDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*request.setAttribute("eid", emDto.getEid());
			request.setAttribute("did", emDto.getDid());
			request.setAttribute("epos", emDto.getEpos());
			request.setAttribute("ename", emDto.getEname());
			request.setAttribute("status", emDto.getStatus());*/
			request.setAttribute("emDto", emDto);
			request.setAttribute("emBeans", cService.queryAllEm(emDto));
			request.getRequestDispatcher("adminUi/emQuery.jsp").forward(request, response);
			/**
			 *有必要销毁吗？
			 */
			System.err.println("nihaomadsa");
			request.removeAttribute("emBeans");
			request.removeAttribute("eid");
			request.removeAttribute("did");
			request.removeAttribute("epos");
			request.removeAttribute("ename");
			request.removeAttribute("status");
		}else if(path.equals("/doMutiQueryDep")) {
			Department dep = new Department();
			try {
				utils.fillBean(request, dep);
			}catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("depBeans", cService.queryAllDep(dep));
			request.getRequestDispatcher("adminUi/depQuery.jsp").forward(request, response);
			request.removeAttribute("depBeans");
		}else if(path.equals("/doPaging")) {
			System.out.println("test");
			System.err.println("###"+request.getParameter("pageNum"));
			request.setAttribute("emBeans", cService.queryEmByPage(request.getParameter("pageNum")));
			session.setAttribute("currentPage", request.getParameter("pageNum"));
			request.getRequestDispatcher("adminUi/emQuery.jsp").forward(request, response);
		}
		
		
		
		else if(path.equals("/askLeave")) {
			Leave leave = new Leave();
			String btime=request.getParameter("btime");
			String etime=request.getParameter("etime");
			System.out.println(btime+" "+etime);
			try {
				leave.setLbtime(sdf.parse(request.getParameter("btime")));
				leave.setLetime(sdf.parse(request.getParameter("etime")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			leave.setLid(request.getParameter("lid"));
			leave.setReason(request.getParameter("reason"));
			leave.setLsubmit(new Date());
			leave.setStatus(1);
			
			System.out.println(request.getParameter("uid"));
			/*System.out.println("我是"+cService.getEid(request.getParameter("uid")));*/
			leave.setEid(cService.getEid(request.getParameter("uid")));
			
			cService.addLeave(leave);
			request.getRequestDispatcher("loginUi/index.jsp").forward(request, response);
			
//			System.out.println(leave.getLbtime(),btime,etime);
//			System.out.println(request.getParameter("btime"));
			
//			System.out.println(new Date(dft.format(request.getParameter("btime"))));
			/*try {
				System.out.println(new Date(dft.format(request.getParameter("btime"))));
				leave.setLbtime(new Date(dft.format(request.getParameter("btime"))));
				leave.setLetime(new Date(dft.format(request.getParameter("etime"))));
			}catch (Exception e) {
				throw new DaoException("日期格式不对");
			}*/
//			System.out.println(new Date());
		}else if(path.equals("/leaveStatus")) {
			List<Leave> leaves = new ArrayList<Leave>();
			System.err.println(request.getParameter("uid"));
			leaves = cService.queryLeave(request.getParameter("uid"));
			request.setAttribute("leaves", leaves);
			request.getRequestDispatcher("emUi/leaveStatus.jsp").forward(request, response);
		}else if(path.equals("/leaveStatusMore")) {
			Leave leave = new Leave();
			System.err.println(request.getParameter("lid"));
			leave = cService.getLeave(request.getParameter("lid"));
			request.setAttribute("leave", leave);
			request.getRequestDispatcher("emUi/leaveStatusMore.jsp").forward(request, response);
		}else if(path.equals("/leaveAll")) {
			List<Leave> leaves = cService.queryLeaveAll();
			request.setAttribute("leaves", leaves);
			request.getRequestDispatcher("pmUi/leaveAll.jsp").forward(request, response);
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
