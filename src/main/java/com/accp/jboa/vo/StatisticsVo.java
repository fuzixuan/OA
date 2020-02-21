package com.accp.jboa.vo;

public class StatisticsVo {
	private String name;
	private Float money;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public StatisticsVo() {
		super();
	}

	public StatisticsVo(String name, Float money) {
		super();
		this.name = name;
		this.money = money;
	}

}
