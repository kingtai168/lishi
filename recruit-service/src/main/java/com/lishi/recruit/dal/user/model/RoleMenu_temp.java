package com.lishi.recruit.dal.user.model;

/**
 * Rolemenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class RoleMenu_temp implements java.io.Serializable {

	// Fields

	private String roleid;
	
	private String menuid;
	
	private String rolename;
	
	private String bz;
	
	private String menuname;
	
	private String pid;
	
	private String menuurl;
	
	private Integer menutype;
	
	private Integer ordernum;
	
	private String icon;

	// Constructors

	/** default constructor */
	public RoleMenu_temp() {
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Integer getMenutype() {
		return menutype;
	}

	public void setMenutype(Integer menutype) {
		this.menutype = menutype;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bz == null) ? 0 : bz.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((menuid == null) ? 0 : menuid.hashCode());
		result = prime * result
				+ ((menuname == null) ? 0 : menuname.hashCode());
		result = prime * result
				+ ((menutype == null) ? 0 : menutype.hashCode());
		result = prime * result + ((menuurl == null) ? 0 : menuurl.hashCode());
		result = prime * result
				+ ((ordernum == null) ? 0 : ordernum.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
		result = prime * result
				+ ((rolename == null) ? 0 : rolename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleMenu_temp other = (RoleMenu_temp) obj;
		if (bz == null) {
			if (other.bz != null)
				return false;
		} else if (!bz.equals(other.bz))
			return false;
		if (icon == null) {
			if (other.icon != null)
				return false;
		} else if (!icon.equals(other.icon))
			return false;
		if (menuid == null) {
			if (other.menuid != null)
				return false;
		} else if (!menuid.equals(other.menuid))
			return false;
		if (menuname == null) {
			if (other.menuname != null)
				return false;
		} else if (!menuname.equals(other.menuname))
			return false;
		if (menutype == null) {
			if (other.menutype != null)
				return false;
		} else if (!menutype.equals(other.menutype))
			return false;
		if (menuurl == null) {
			if (other.menuurl != null)
				return false;
		} else if (!menuurl.equals(other.menuurl))
			return false;
		if (ordernum == null) {
			if (other.ordernum != null)
				return false;
		} else if (!ordernum.equals(other.ordernum))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		} else if (!roleid.equals(other.roleid))
			return false;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}

	
}