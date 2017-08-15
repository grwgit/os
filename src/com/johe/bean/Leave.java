package com.johe.bean;

import java.util.Date;

public class Leave {

	String lid;
	String eid;
	String reason;
	Date lsubmit;
	Date lbtime;
	Date letime;
	String lapprover;
	String lsugges;
	Date lratify;
	Date backtime;
	Integer status;
	String backstatus;
	String backname;
	
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getLsubmit() {
		return lsubmit;
	}
	public void setLsubmit(Date lsubmit) {
		this.lsubmit = lsubmit;
	}
	public Date getLbtime() {
		return lbtime;
	}
	public void setLbtime(Date lbtime) {
		this.lbtime = lbtime;
	}
	public Date getLetime() {
		return letime;
	}
	public void setLetime(Date letime) {
		this.letime = letime;
	}
	public String getLapprover() {
		return lapprover;
	}
	public void setLapprover(String lapprover) {
		this.lapprover = lapprover;
	}
	public String getLsugges() {
		return lsugges;
	}
	public void setLsugges(String lsugges) {
		this.lsugges = lsugges;
	}
	public Date getLratify() {
		return lratify;
	}
	public void setLratify(Date lratify) {
		this.lratify = lratify;
	}
	public Date getBacktime() {
		return backtime;
	}
	public void setBacktime(Date backtime) {
		this.backtime = backtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBackstatus() {
		return backstatus;
	}
	public void setBackstatus(String backstatus) {
		this.backstatus = backstatus;
	}
	public String getBackname() {
		return backname;
	}
	public void setBackname(String backname) {
		this.backname = backname;
	}
	@Override
	public String toString() {
		return "Leave [lid=" + lid + ", eid=" + eid + ", reason=" + reason + ", lsubmit=" + lsubmit + ", lbtime="
				+ lbtime + ", letime=" + letime + ", lapprover=" + lapprover + ", lsugges=" + lsugges + ", lratify="
				+ lratify + ", backtime=" + backtime + ", status=" + status + ", backstatus=" + backstatus
				+ ", backname=" + backname + "]";
	}
	
}
