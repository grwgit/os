package com.johe.dto;

import java.util.Date;

public class EmployeeDto {

	private String eid;
	private String did;
	private String epos;
	private String ename;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getEpos() {
		return epos;
	}
	public void setEpos(String epos) {
		this.epos = epos;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", did=" + did + ", epos=" + epos + ", ename=" + ename+"]";
	}
	
	
}
