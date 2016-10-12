package com.lishi.recruit.service.loginuser.model;

/**
 * 
 * 前端用户VO
 * @author zt
 *
 */
public class LoginUserDTO {
	
	private String id;
	
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String nikeName;
	
	private String phone;
	
	private Integer sex;
	
	private String email;
	
	private Integer loginNum;
	
	//用户类型(0:非企业,1:企业) 
	private Integer type;
	
	//数据来源:0:web注册,1:app注册,2:微信注册,3:其它 
	private Integer source;
	
	//是否实名,0否1是    
	private Integer isReal;
	
	//用户状态（0:正常；1:禁用；3：注销；）
	private Integer status;
	
	//关联真实信息ID（用户是0-关联用户真实信息，1-企业信息）
	private String relationId;
	
	//注册时间
	private String createDate;
	
	//最后登录IP
	private String lastLoginip;
	
	//最后登录时间    
	private String lastLoginDate;
	
	//最后修改登陆密码日期 
	private String lastPwdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getIsReal() {
		return isReal;
	}

	public void setIsReal(Integer isReal) {
		this.isReal = isReal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastLoginip() {
		return lastLoginip;
	}

	public void setLastLoginip(String lastLoginip) {
		this.lastLoginip = lastLoginip;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastPwdDate() {
		return lastPwdDate;
	}

	public void setLastPwdDate(String lastPwdDate) {
		this.lastPwdDate = lastPwdDate;
	}
	

}
