package com.johe.bean;

public class Department {

	private String did;
	private String dname;
	private String dfunc;
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDfunc() {
		return dfunc;
	}
	public void setDfunc(String dfunc) {
		this.dfunc = dfunc;
	}
	@Override
	public String toString() {
		return "Department [did=" + did + ", dname=" + dname + ", dfunc=" + dfunc + "]";
	}
	
}
