package com.johe.bean;

import java.util.Date;

public class Employee {

	private String eid;
	private String did;
	private String epos;
	private String ename;
	private Date eage;
	private Integer esex;
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
	public Date getEage() {
		return eage;
	}
	public void setEage(Date eage) {
		this.eage = eage;
	}
	public Integer getEsex() {
		return esex;
	}
	public void setEsex(Integer esex) {
		this.esex = esex;
	}
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", did=" + did + ", epos=" + epos + ", ename=" + ename + ", eage=" + eage
				+ ", esex=" + esex + "]";
	}
	
	
}
