package com.lishi.recruit.excel.model;

public class Order {

	private String orderId;
	
	private String yfjOrderId;
	
	public String getYfjOrderId() {
		return yfjOrderId;
	}

	public void setYfjOrderId(String yfjOrderId) {
		this.yfjOrderId = yfjOrderId;
	}

	private Double reMoney;
	
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getReMoney() {
		return reMoney;
	}

	public void setReMoney(Double reMoney) {
		this.reMoney = reMoney;
	}
	
	
}
