package com.johe.dto;

public class UserDto {

	private String uid;
	private String eid;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", eid=" + eid + ", status=" + status + "]";
	}
	
	
	
}
