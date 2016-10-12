package com.lishi.recruit.dal.user.model;

/**
 * Rolemenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class RoleMenu implements java.io.Serializable {

	// Fields

	private RoleMenuId id;
	private Role role;
	private Menu menu;

	// Constructors

	/** default constructor */
	public RoleMenu() {
	}

	/** full constructor */
	public RoleMenu(RoleMenuId id, Role role, Menu menu) {
		this.id = id;
		this.role = role;
		this.menu = menu;
	}

	// Property accessors

	public RoleMenuId getId() {
		return this.id;
	}

	public void setId(RoleMenuId id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	

}