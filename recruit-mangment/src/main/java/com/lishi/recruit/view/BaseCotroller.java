package com.lishi.recruit.view;

public class BaseCotroller {
	
	private int limit; // 每页显示多少行
	private int start; // 开始行
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
}
