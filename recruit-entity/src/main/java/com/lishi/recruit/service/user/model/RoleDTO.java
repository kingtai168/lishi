package com.lishi.recruit.service.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.lishi.recruit.dal.user.model.Role;

public class RoleDTO {

	private String roleid;
	private String rolename;
	private String bz;
	private String menuids;

	public RoleDTO() {
		super();
	}

	public RoleDTO(String roleid, String rolename, String bz) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
		this.bz = bz;
	}
	
	public static RoleDTO createDto(Role pojo) {
		RoleDTO dto = null;
		if (pojo != null) {
			dto = new RoleDTO(pojo.getRoleid(),pojo.getRolename(),pojo.getBz());
		}
		return dto;
	}

	@SuppressWarnings("unchecked")
	public static List createDtos(Collection pojos) {
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				list.add(createDto((Role) iterator.next()));
			}
		}
		return list;
	}

	public RoleDTO(String roleid) {
		this.roleid = roleid;
	}

//	public RoleDTO(String rolename) {
//		this.rolename = rolename;
//	}

	public RoleDTO(String rolename, String bz) {
		this.rolename = rolename;
		this.bz = bz;
	}

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

	public String getMenuids() {
		return menuids;
	}

	public void setMenuids(String menuids) {
		this.menuids = menuids;
	}

}