package com.lishi.recruit.exception;

public class RecruitException extends RuntimeException{

	private static final long serialVersionUID = 8045109430878072763L;
	

	// 异常码
	private Integer code;
	
	
	public RecruitException () {
		super();
	}

	public RecruitException(String msg){
		super(msg);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
