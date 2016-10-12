package com.lishi.recruit.dal.user.model;

/**
 * Role entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

	// Fields

	private String roleid;
	private String rolename;
	private String bz;

	// Constructors

	/** default constructor */
	public Role() {
	}
	
	public Role(String roleid) {
		this.roleid = roleid;
	}

//	/** minimal constructor */
//	public Role(String rolename) {
//		this.rolename = rolename;
//	}

	/** full constructor */
	public Role(String rolename, String bz) {
		this.rolename = rolename;
		this.bz = bz;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}