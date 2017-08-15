package com.johe.service;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.johe.bean.Department;
import com.johe.bean.Employee;
import com.johe.bean.Leave;
import com.johe.bean.User;
import com.johe.dao.*;
import com.johe.dao.impl.DaoImpl;
import com.johe.dto.EmployeeDto;
import com.johe.dto.UserDto;
import com.sun.org.apache.bcel.internal.generic.Select;

public class CrudService {

	private Dao dao;
	private static ThreadLocal threadLocal;
	
	public CrudService() {
		dao = new DaoImpl();
		threadLocal = new ThreadLocal();
		threadLocal.set("nohao");
	}
	
	public void addEmployee(Employee em) {

		try {
			dao.update("insert into \"employee\"(\"e_id\",\"d_id\",\"e_pos\") values(?,?,?)", em.getEid(),em.getDid(),em.getEpos());
			dao.update("insert into \"users\" values(?,?,?,?)", "admin"+em.getEid(),em.getEid(),"000000","否");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
	}

/*	public void addUser(User user) {
		
		System.out.println(user.getEid()+" "+user.getUid()+" "+user.getPasswd());
		try {
			dao.update("insert into \"users\" values(?,?,?)", user.getUid(),user.getEid(),user.getPasswd());
			System.out.println(user.getUid()+user.getPasswd());
		}catch (Exception e) {	
			
		}finally {
			dao.close();
		}
	}*/

	public void addDepartment(Department dep) {

		try {
			System.out.println("insert into \"department\" values(?,?,?)");
			System.out.println( dep.getDname());
			dao.update("insert into \"department\" values(?,?,?)", dep.getDid(),dep.getDname(),dep.getDfunc());
			System.out.println("insert into \"department\" values(?,?,?)");
		}catch (Exception e) {
			
		}finally {
			dao.close();
		}
	}

	public String getEid(String object) {
		String str="";
		try {
			str = dao.queryString("select \"e_id\" from \"users\" where \"u_id\" =?", object);
		}catch (Exception e) {	
			
		}finally {
//			dao.close();
		}
		return str;
	}

	public void addLeave(Leave leave) {

		String sql = "insert into \"leave\"(\"l_id\",\"e_id\",\"reason\",\"l_btime\",\"l_etime\",\"status\") values(?,?,?,?,?,?)";
		try {
			System.out.println(sql+" "+leave.getLid()+" "+leave.getEid()+" "+leave.getReason()+" "+leave.getLbtime()+" "+leave.getLetime()+" "+leave.getStatus());

			dao.update(sql, leave.getLid(),leave.getEid(),leave.getReason(),leave.getLbtime(),leave.getLetime(),leave.getStatus());
			System.out.println("dubug");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
	}

	public List<User> queryAllUser() {

		List<User> users = new ArrayList<User>();
		String sql = "select \"u_id\" \"uid\",\"e_id\" eid,\"passwd\" passwd,\"status\" status from \"users\"";
//		System.out.println("###"+sql);
		try {
			users = dao.queryBeanList(User.class, sql);
//			System.err.println(users.toString());
		}catch (Exception e) {	
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return users;
	}
	
	public List<User> queryAllUser(UserDto dto) {

		System.err.println(dto.toString());
		List<User> users = new ArrayList<User>();
		String sql = "select \"u_id\" \"uid\",\"e_id\" eid,\"status\" status from \"users\" where 1=1";
		StringBuilder where=new StringBuilder();
		List<Object> paramList=new ArrayList<Object>();
		if(dto!=null) {
			if(dto.getEid()!=null&&!dto.getEid().equals("")) {
				where.append(" and \"e_id\" like ?");
				paramList.add(dto.getEid());
			}
			if(dto.getUid()!=null&&!dto.getUid().equals("")) {
				where.append(" and \"u_id\" like ?");
				paramList.add(dto.getUid());
			}
			if(dto.getStatus()!=null&&!dto.getStatus().equals("")) {
				where.append(" and \"status\" like ?");
				paramList.add(dto.getStatus());
			}
			
		}
		try {
			System.err.println(sql+where.toString()+paramList.toArray());
			users = dao.queryBeanList(User.class, sql+where.toString(),paramList.toArray());
//			System.err.println(users.toString());
		}catch (Exception e) {	
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return users;
	}

	public void delUser(String parameter) {
		String sql="delete from \"users\" where \"u_id\"=?";
		try {
			dao.update(sql, parameter);
		}catch (Exception e) {
			
		}finally {
			dao.close();
		}	
	}

	public List<Employee> queryAllEm() {
		List<Employee> ems = new ArrayList<Employee>();
		String sql = "select \"e_id\" \"eid\",\"d_id\" \"did\",\"e_pos\" \"epos\",\"e_name\" ename, \"status\" status from \"employee\" where rownum<=8";
//		System.out.println(sql);
		try {
			ems = dao.queryBeanList(Employee.class, sql);
//			System.out.println(sql);
//			System.out.println(ems.size());
		}catch (Exception e) {	
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return ems;
	}
	
	
	public List<Employee> queryAllEm(EmployeeDto emDto) {
		List<Employee> ems = new ArrayList<Employee>();
		String sql = "select \"e_id\" \"eid\",\"d_id\" \"did\",\"e_pos\" \"epos\",\"e_name\" ename, \"status\" status from \"employee\" where 1=1";
		StringBuilder where = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		if(emDto!=null) {
			if(emDto.getEid()!=null&&!emDto.getEid().equals("")) {
				where.append(" and \"e_id\" like ?");
				paramList.add(emDto.getEid());
			}
			if(emDto.getDid()!=null&&!emDto.getDid().equals("")) {
				where.append(" and \"d_id\" like ?");
				paramList.add(emDto.getDid());
			}
			if(emDto.getEpos()!=null&&!emDto.getEpos().equals("")) {
				where.append(" and \"e_pos\" like ?");
				paramList.add(emDto.getEpos());
			}
			if(emDto.getEname()!=null&&!emDto.getEname().equals("")) {
				where.append(" and \"e_name\" like ?");
				paramList.add(emDto.getEname());
			}
			if(emDto.getStatus()!=null&&!emDto.getStatus().equals("")) {
				where.append(" and \"status\" like ?");
				paramList.add(emDto.getStatus());
			}
			ems = dao.queryBeanList(Employee.class, sql+where.toString(), paramList.toArray());
		}
		return ems;
	}

	public List<Employee> queryEmByPage(String pageNum) {
		List<Employee> ems = new ArrayList<Employee>();
		System.err.println(Integer.valueOf(pageNum));
		String sql = "select \"eid\",\"did\", \"epos\", \"ename\", \"status\" from ( select rownum rn_,\"e_id\" \"eid\",\"d_id\" \"did\",\"e_pos\" \"epos\",\"e_name\" \"ename\", \"status\" \"status\" from \"employee\" where rownum<=?)t where rn_>? order by \"eid\"";
//		String sql2 = "select rownum rn_,\"e_id\" \"eid\",\"d_id\" \"did\",\"e_pos\" \"epos\",\"e_name\" \"ename\", \"status\" \"status\" from \"employee\" where rownum<=?";
		ems = dao.queryBeanList(Employee.class, sql, (Integer.valueOf(pageNum))*8, (Integer.valueOf(pageNum)-1)*8);
		System.err.println(ems.toString());
		return ems;
	}
	
	public void delEm(String parameter) {
		String sql="delete from \"employee\" where \"e_id\"=?";
		try {
			dao.update(sql, parameter);
		}catch (Exception e) {	
			
		}finally {
			dao.close();
		}
	}

	public List<Department> queryAllDep() {
		List<Department> deps= new ArrayList<Department>();
		String sql = "select \"d_id\" did,\"d_name\" dname,\"d_func\" dfunc from \"department\"";
		try {
			deps=dao.queryBeanList(Department.class, sql);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return deps;
	}

	public List<Department> queryAllDep(Department dep) {
		String sql = "select \"d_id\" did,\"d_name\" dname,\"d_func\" dfunc from \"department\" where 1=1";
		StringBuilder where = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();
		if(dep!=null) {
			if(dep.getDid()!=null&&!dep.getDid().equals("")) {
				where.append(" and \"d_id\" like ?");
				paramList.add(dep.getDid());
			}
			if(dep.getDname()!=null&&!dep.getDname().equals("")) {
				where.append(" and \"d_name\" like ?");
				paramList.add(dep.getDname());
			}
		}
		List<Department> deps = new ArrayList<Department>();
		deps = dao.queryBeanList(Department.class, sql+where.toString(),paramList.toArray());
		return deps;
	}

	
	public List<Leave> queryLeave(String uid) {
		List<Leave> leaves = new ArrayList<>();
		try {
			System.out.println(leaves);
			String sql = "select \"l_id\" lid,\"e_id\" eid,\"reason\",\"l_approver\" lapprover,\"l_btime\" lbtime,"
					+ "\"l_etime\" letime,\"l_sugges\" lsugges,\"l_submit\" lsubmit,\"l_ratify\" lratify,\"backup_name\" backname,"
					+ "\"backup_status\" backstatus,\"backup_time\" backtime,\"status\" from \"leave\" where \"e_id\"=?";
			System.out.println(sql);
			leaves = dao.queryBeanList(Leave.class, sql, getEid(uid));
			System.out.println(leaves);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return leaves;
	}

	public Leave getLeave(String parameter) {
		Leave leave = new Leave();
		String sql = "select \"l_id\" lid,\"e_id\" eid,\"reason\",\"l_approver\" lapprover,\"l_btime\" lbtime,"
				+ "\"l_etime\" letime,\"l_sugges\" lsugges,\"l_submit\" lsubmit,\"l_ratify\" lratify,\"backup_name\" backname,"
				+ "\"backup_status\" backstatus,\"backup_time\" backtime,\"status\" from \"leave\" where \"l_id\"=?";
		try {
			
			leave = dao.queryBean(Leave.class, sql, parameter);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return leave;
	}

	public List<Leave> queryLeaveAll() {
		List<Leave> leaves = new ArrayList<>();
		try {
			System.out.println(leaves);
			String sql = "select \"l_id\" lid,\"e_id\" eid,\"reason\",\"l_approver\" lapprover,\"l_btime\" lbtime,"
					+ "\"l_etime\" letime,\"l_sugges\" lsugges,\"l_submit\" lsubmit,\"l_ratify\" lratify,\"backup_name\" backname,"
					+ "\"backup_status\" backstatus,\"backup_time\" backtime,\"status\" from \"leave\"";
			System.out.println(sql);
			leaves = dao.queryBeanList(Leave.class, sql);
			System.out.println(leaves);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return leaves;
	}

	public Employee queryEm(String parameter) {
		Employee em = new Employee();
		try {
			String eid = getEid(parameter);
			String sql = "select \"e_id\" \"eid\",\"d_id\" \"did\",\"e_pos\" \"epos\",\"e_name\" ename,\"e_age\" eage,\"e_sex\" esex from \"employee\" where \"e_id\"=? ";
			em = dao.queryBean(Employee.class, sql, eid);
			System.err.println(em.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
		return em;
	}

	public boolean checkUser(String passwdOld,HttpSession session) {
		
		if(passwdOld.equals(session.getAttribute("passwd")))	return true;
		else	return false;
	}

	public void checkPasswd(HttpSession session) {
		try {
			if(session.getAttribute("passwdNew1").equals(session.getAttribute("passwdNew1Again"))){
				if(!(session.getAttribute("passwdNew1").equals(session.getAttribute("passwd")))) {
					session.removeAttribute("passwd");
					session.setAttribute("passwd", session.getAttribute("passwdNew1"));
					session.removeAttribute("passwdNew1");
					session.removeAttribute("passwdNew1Again");
					String sql = "update \"users\" set \"passwd\"=?where \"u_id\"=?";
					dao.update(sql, session.getAttribute("passwd"),session.getAttribute("user"));
					session.setAttribute("changePasswdSucc", "密码修改成功");
				}else {
					session.setAttribute("twoPasswdAgree", "不能是原密码");
				}
			}else {
				session.setAttribute("twoPasswdDisagree", "两次输入密码不一致");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dao.close();
		}
	}

	public void delDep(String did,PrintWriter out) {
		String sql = "select count(\"e_id\") from \"employee\" where \"d_id\"=?";
		ResultSet rt = dao.query(sql, did);
		int num = 0;
		try {
			if(rt.next())
			num = rt.getInt(1);
//			System.err.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(num==0) {
			String sql2 = "delete from \"department\" where \"d_id\"=?";
			dao.update(sql2, did);
		}else if(num>0) {
			out.println("<script>");
			out.println("alert('此部门包含员工信息，不能删除');");
			out.println("</script>");
		}
	}

	public Employee queryEmByEid(String eid) {
		Employee em = new Employee();
		String sql = "select \"e_id\" \"eid\",\"d_id\" \"did\",\"e_pos\" \"epos\",\"e_name\" ename,\"e_age\" eage,\"e_sex\" esex ,\"status\" status from \"employee\" where \"e_id\"=? ";
		return dao.queryBean(Employee.class, sql, eid);
	}

	public void updEm(Employee em) {
		String sql = "update \"employee\" set \"d_id\" = ?,\"e_pos\" = ?,\"e_age\" = ?,\"e_sex\" = ?,\"status\" = ? where \"e_id\"=?";
		dao.update(sql, em.getDid(),em.getEpos(),em.getEage(),em.getEsex(),em.getStatus(),em.getEid());
		if(em.getStatus()!=null&&em.getStatus().equals("离职")) {
			String sql2 = "update \"users\" set \"status\"=? where \"e_id\"=?";
			dao.update(sql2, "是",em.getEid());
		}
	}


	
}
