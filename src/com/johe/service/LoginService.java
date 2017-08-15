package com.johe.service;

import javax.servlet.http.HttpSession;

import com.johe.bean.Employee;
import com.johe.bean.User;
import com.johe.dao.Dao;
import com.johe.dao.impl.DaoImpl;

public class LoginService {

	private Dao dao = new DaoImpl();
	public String checkUser(HttpSession session,User userBean) {
		String home = null;
		String user = userBean.getUid();
		String passwd = userBean.getPasswd();
		System.out.println("sdfjalsfjl"+user);
		if(user!="") {
			String uname=dao.queryString("select \"u_id\" from \"users\" where \"u_id\"=? ",user);
			/*
			 * 默认账户登录，转到完善资料页面
			 */
			if(user.startsWith("admin")&&passwd.equals("000000")) {
				home="loginUi/completeInfo.jsp";
			}else if(uname!=null) {
				String upass=dao.queryString("select \"passwd\" from \"users\" where \"u_id\"=?", uname);
				System.err.println("pass"+upass);
				if(upass!=null&&upass.equals(passwd)) {
					/*
					 * 查看账户是否禁用
					 */
					String forbidden = "";
					forbidden = dao.queryString("select \"status\" from \"users\" where \"u_id\"=?", uname);
					/*
					 * 0代表允许登录
					 */
					if(forbidden.equals("否")) {
						session.setAttribute("user", user);
						session.setAttribute("passwd", passwd);
						if(checkPrivilege(uname)==0) {
							session.setAttribute("homeType", "admin");
						}else if(checkPrivilege(uname)==1) {
							session.setAttribute("homeType", "pm");
						}else if(checkPrivilege(uname)==2) {
							session.setAttribute("homeType", "hm");
						}else if(checkPrivilege(uname)==3) {
							session.setAttribute("homeType", "em");
						}else {
							/*
							 * 职位不是上述四种的人
							 */
						}
						home="loginUi/home.jsp";
					}else {
						session.setAttribute("forbidden", "账号已被禁用！");
						home="loginUi/error.jsp";
					}
				}else {
					session.setAttribute("passwdError", "密码不对！");
					home="loginUi/error.jsp";
				}
			}else {
				session.setAttribute("userNone", "无此账号！");
				home="loginUi/error.jsp";
			}
		}else {
			session.setAttribute("userNull", "用户名不能为空！");
			home="loginUi/error.jsp";
		}
		
		dao.close();
		return home;
		
	}
	
	private int checkPrivilege(String user) {
		try {
			int flag=0;
			String eid=dao.queryString("select \"e_id\" from \"users\" where \"u_id\"=?", user);
			String epos=dao.queryString("select \"e_pos\" from \"employee\" where \"e_id\"=?", eid);
			if(epos.equals("管理员"))
				flag=0;
			else if(epos.equals("经理"))
				flag=1;
			else if(epos.equals("人事人员"))
				flag=2;
			else if(epos.equals("员工"))
				flag=3;
			return flag;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return -1;
	}

	public void coptInfo(Employee em, User user) {
		try {
			System.out.println(em.toString()+"  "+user.toString());
			String sql_em = "update \"employee\" set \"e_name\"=?,\"e_sex\"=?,\"e_age\"=? where \"e_id\"=?";
			String sql_user = "update \"users\" set \"u_id\"=?,\"passwd\"=? where \"e_id\"=?";
			dao.update(sql_em, em.getEname(),em.getEsex(),em.getEage(),em.getEid());
			dao.update(sql_user, user.getUid(),user.getPasswd(),user.getEid());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
		
	}
	
}
