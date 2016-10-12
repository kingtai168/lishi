package com.lishi.recruit.dbaccess.orm.mybatis.constants;

public interface Message {

	public String INSERT_FAILED = "添加对象出错！语句：%s";
	
	public String DELETE_BYID_FAILED = "根据ID删除对象出错！语句：%s";
	
	public String DELETE_ALL_FAILED = "删除所有对象出错！语句：%s";
	
	public String DELETE_FAILED = "删除对象出错！语句：%s";
	
	public String SELECT_BYID_FAILED = "根据ID查询对象出错！语句：%s";
	
	public String SELECT_LIST_FAILED = "查询对象列表出错！语句：%s";
	
	public String SELECT_COUNT_FAILED = "查询对象总数出错！语句：%s";
	
	public String SELECT_ALL_FAILED = "查询所有对象列表出错！语句：%s";
	
	public String UPDATE_BYID_FAILED = "根据ID更新对象出错！语句：%s";
	
	public String UPDATE_SELECTIVE_FAILED = "根据ID更新对象某些属性出错！语句：%s";
	
	
}
